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
	final int maxSpeed = 2;
	final int left = 0, right = 1;
	boolean jumping = false;
	boolean isLeft;
	boolean isRight;
	boolean isUp;
	double xVelocity = 0;
	double xAcceleration =4;
	double xGroundedFriction = 3;
	double xAirFriction = 0.8;
	
	
	
	public Player(){
		width = 16;
		height = 40;
		
		//Place the Player on top of highest Block
		x = 0;
		doublex = 0;
		
		for(int i = 0;i<World.worldHeight;i++){
			if(World.squares[0][i].ID != 0){
				System.out.println(i);
				this.y = i*World.blockSize - height;
				doubley = y;
				break;
			}
		}
	}
	
	public void jump(){
		isUp = true;
		if(jumping == false){
			dy = -300;
			jumping = true;
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
	
	public void act(){
		if(jumping){
			doubley+=(dy/100);
			dy+=(gravity/100);
			y=(int)doubley;
			
			boolean collision = false;
			for(int i = 0;i < World.worldHeight;i++){
				for(int j = 0;j < World.worldWidth;j++){
					while(this.intersects(World.squares[j][i]) && World.squares[j][i].ID != 0){
						dy = 0;
						y--;
						doubley = y;
						jumping = false;
						collision = true;
					}if(collision){break;}
				}
			}
		}
		
		if(isRight){
			if(xVelocity < maxSpeed){
				xVelocity += xAcceleration/100; 
			}
		}
		else if(isLeft){
			if(xVelocity > -maxSpeed){
				xVelocity -= xAcceleration/100;
			}
		}
		else if(xVelocity != 0){
			if(jumping & xVelocity > 0){
				xVelocity -= xAirFriction/100;
			}
			else if(jumping & xVelocity > 0){
				xVelocity += xAirFriction/100;
			}
			else if(xVelocity > 0.8){
				xVelocity -= xGroundedFriction/100*Math.pow(xVelocity*1.5, 2);
			}
			else if(xVelocity < -0.8){
				xVelocity += xGroundedFriction/100*Math.pow(xVelocity*1.5, 2);
			}
			else if(xVelocity > 0){
				xVelocity -= xGroundedFriction/100;
			}
			else if(xVelocity < 0){
				xVelocity += xGroundedFriction/100;
			}
		}
		doublex += xVelocity;
		x = (int)doublex;
		
		
		boolean collision = false;
		for(int i = 0;i < World.worldHeight;i++){
			for(int j = 0;j < World.worldWidth;j++){
				while(this.intersects(World.squares[j][i]) && World.squares[j][i].ID != 0){
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
	
		if(!jumping){
			Square squareLeft = null;
			Square squareRight = null;
			for(int i = 0;i < World.worldHeight;i++){
				for(int j = 0;j < World.worldWidth;j++){
					if(World.squares[j][i].contains(x, y + height + 1)){
						squareLeft = World.squares[j][i];
						break;
					}
				}
			}
			for(int i = 0;i < World.worldHeight;i++){
				for(int j = 0;j < World.worldWidth;j++){
					if(World.squares[j][i].contains(x + width, y+ height + 1)){
						squareRight = World.squares[j][i];
						break;
					}
				}
			}
			if(squareLeft != null && squareRight != null){
				if(squareLeft.ID == 0 && squareRight.ID == 0){
					System.out.println(squareLeft.xCo);
					System.out.println(squareLeft.yCo);
					System.out.println(squareRight.xCo);
					System.out.println(squareRight.yCo);
					System.out.println("?");
					jumping = true;
				}
			}
		}
		
		
		if(x < 0){
			doublex++;
			xVelocity = 0;
			System.out.println("?");
		}
		else if(x > World.worldWidth*World.blockSize - width){
			doublex--;
			xVelocity = 0;
		}
	}
	
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width, height);
	}
}
