package Game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{
	double dy;
	double dx;
	double dxx;
	double doublex;
	double doubley;
	double gravity = 1000;
	int direction;
	final int left = 0, right = 1;
	boolean jumping = false;
	boolean grounded = true;
	boolean isLeft;
	boolean isRight;
	boolean isUp;
	double xVelocity = 0;
	
	
	
	public Player(){
		width = 16;
		height = 40;
		
		//Place the Player on top of highest Block
		x = 0;
		doublex = 0;
		
		for(int i = 0;i<World.worldHeight;i++){
			if(World.squares[0][i].solid){
				System.out.println(i);
				this.y = i*World.blockSize - height;
				doubley = y;
				break;
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
		
		for(int i = 0;i < World.worldHeight;i++){
			for(int j = 0;j < World.worldWidth;j++){
				while(this.intersects(World.squares[j][i]) && World.squares[j][i].solid){
					dy = 0;
					y--;
					doubley = y;
					
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

		doGravity(squareCentre.gravity);
		
		grounded = squareLowerLeft.solid | squareLowerRight.solid;
		
		if(isRight){
			if(xVelocity < maxXSpeed){
				xVelocity += acceleration; 
			}
			if(xVelocity > maxXSpeed){
				doXFriction(friction);
			}
		}
		else if(isLeft){
			if(xVelocity > -maxXSpeed){
				xVelocity -= acceleration;
			}
			if(xVelocity < -maxXSpeed){
				doXFriction(friction);
			}

		}
		else if(xVelocity != 0){
			doXFriction(friction);
		}
		doublex += xVelocity;
		x = (int)doublex;
		
		
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
					doublex = x;
					
					collision = true;
				}if(collision){xVelocity = 0;break;}
			}
		}


		
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
	}
	
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width, height);
	}
}
