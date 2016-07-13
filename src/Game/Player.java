package Game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{
	double dy;
	double doublex;
	double doubley;
	boolean jumping = false;
	boolean grounded = true;
	boolean isLeft;
	boolean isRight;
	boolean isUp;
	double xVelocity = 0;
	
	public Player(){
		width = 160;
		height = 40;
		
		//Place the Player on top of highest Block
		x = 0;
		doublex = 0;
		
		for(int i = 0;i < World.worldHeight;i++){
			for(int j = 0;j < World.worldWidth;j++){
				if(World.squares[j][i].ID == Value.squareSpawn){
					this.x = j*World.blockSize + width/2;
					this.y = i*World.blockSize - height + World.blockSize;
					System.out.println(x + " " + y);

					doublex = x;
					doubley = y;
					break;
				}
			}
		}
	}
	
	public void jump(){
		isUp = true;
		if(grounded){
			dy = -3;
			grounded = false;
		}
		System.out.println("jump");
	}
	
	public void left(){
		isLeft = true;
		isRight = false;
		System.out.println("left");
	}
	
	public void right(){
		isRight = true;
		isLeft = false;
		System.out.println("right");
	}
	
	public void doGravity(double gravity){
				
		dy += gravity;
		doubley+=dy;
		y=(int)doubley;
		
		boolean collision = false;
		for(int i = 0;i < World.worldHeight;i++){
			for(int j = 0;j < World.worldWidth;j++){
				while(this.intersects(World.squares[j][i]) && World.squares[j][i].solid){
					
					if(dy > 0){
						y--;
					}
					if(dy < 0){
						y++;
					}
					
					collision = true;
				}
				if(collision){
					doubley = y;
					dy = 0;
					break;
				}
			}
		}
	}
	
	public void doXFriction(double friction){

		
		if (xVelocity > 0){
			xVelocity -= friction/100;
			if (xVelocity < 0){
				xVelocity = 0;
			}
		}
		else {
			xVelocity += friction/100;
			if (xVelocity > 0){
				xVelocity = 0;
			}
		}
	}
	
	public void calculateOffset(){
		if(x - (World.screenWidth/2) > 0 && x + (World.screenWidth/2) < World.worldWidth*World.blockSize){
			World.offset = x - (World.screenWidth/2);
		}
		else if(x - (World.screenWidth/2) < 0){
			World.offset = 0;
		}
		else if(x + (World.screenWidth/2) > World.worldWidth*World.blockSize){
			World.offset = World.worldWidth*World.blockSize - World.screenWidth;
		}
	}
	
	public void act(){
		//calculate 2 squares below
		Square squareLowerLeft = null;
		Square squareLowerRight = null;
		Square squareCentre = null;
		Square squareBelow = null;
		
		for(int i = 0;i < World.worldHeight;i++){
			for(int j = 0;j < World.worldWidth;j++){
				if(World.squares[j][i].contains(x, y + height + 1)){
					squareLowerLeft = World.squares[j][i];
					break;
				}
			}
		}
		for(int i = 0;i < World.worldHeight;i++){
			for(int j = 0;j < World.worldWidth;j++){
				if(World.squares[j][i].contains(x + width - 1, y + height + 1)){
					squareLowerRight = World.squares[j][i];
					break;
				}
			}
		}
		
		for(int i = 0;i < World.worldHeight;i++){
			for(int j = 0;j < World.worldWidth;j++){
				if(World.squares[j][i].contains(x + (width-1)/2, y + (height + 1)/2)){
					squareCentre = World.squares[j][i];
					break;
				}
			}
		}
		for(int i = 0;i < World.worldHeight;i++){
			for(int j = 0;j < World.worldWidth;j++){
				if(World.squares[j][i].contains(x + (width-1)/2, y + height + 1)){
					squareBelow = World.squares[j][i];
					break;
				}
			}
		}
		
		
		double maxXSpeed;
		double acceleration;
		double friction;
		if(grounded){
			maxXSpeed = squareBelow.maxXSpeed;
			acceleration = squareBelow.acceleration;
			friction = squareBelow.friction;
		}
		else{
			maxXSpeed = squareCentre.maxXSpeed;
			acceleration = squareCentre.acceleration;
			friction = squareCentre.friction;
		}

		
		
		if(isRight){
			if(xVelocity < 0){
				xVelocity += acceleration*2;
			}
			else if(xVelocity < maxXSpeed){
				xVelocity += acceleration; 
			}
			
		}
		else if(isLeft){
			if(xVelocity > 0){
				xVelocity -= acceleration*2;
			}
			if(xVelocity > -maxXSpeed){
				xVelocity -= acceleration;
			}

		}
		
		else if(xVelocity != 0){
			doXFriction(friction);
		}
		
		if(Math.abs(xVelocity) > maxXSpeed){ // for high speeds
			doXFriction(friction);// * 1.5);
		}
		
		doublex += xVelocity;
		x = (int)doublex;
		
		//Check collisions from x changes
		boolean collision = false;
		for(int i = 0;i < World.worldHeight;i++){
			for(int j = 0;j < World.worldWidth;j++){
				while(this.intersects(World.squares[j][i]) && World.squares[j][i].solid){
					
					if(xVelocity > 0){
						x--;
					}
					if(xVelocity < 0){
						x++;
					}
					collision = true;
				}
				if(collision){
					xVelocity = 0;
					doublex = x;
					break;
				}
			}
		}
		
		doGravity(squareCentre.gravity);
		
		grounded = squareLowerLeft.solid | squareLowerRight.solid;

		calculateOffset();
		
		//Check edges of world
		if(x<0){
			x = 0;
			doublex = 0;
			xVelocity = 0;
		}
		if(doublex > World.worldWidth*World.blockSize - width){
			doublex = World.worldWidth*World.blockSize - width;
			x = (int)doublex;
			xVelocity = 0;
		}
		if(y<0){
			//player hits top of world and stops
			y = 0;
			doubley = 0;
			dy = 0;
			
			
		}
		if(y > World.worldHeight*World.blockSize - height - 2){
			doubley = World.worldHeight*World.blockSize - height - 2; //most likely dead
			y = (int)doubley;
			dy = 0;
			
		}
	}
	
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(x - (int)World.offset, y, width, height);
	}
}
