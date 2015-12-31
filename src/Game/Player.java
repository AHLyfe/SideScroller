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
	double gravity = 150;
	int direction;
	final int maxSpeed = 10;
	final int left = 0, right = 1;
	boolean jumping = false;
	
	
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
		if(jumping == false){
			dy = -100;
			jumping = true;
		}
		System.out.println("jump");
	}
	
	public void left(){
		direction = left;;
		System.out.println("left");
	}
	
	public void right(){
		direction = right;
		System.out.println("right");
	}
	
	public void act(){
		if(dy!=0){
			doubley+=(dy/1000);
			dy+=(gravity/1000);
			y=(int)doubley;
		}
		for(int i = 0;i < World.worldHeight;i++){
			for(int j = 0;j < World.worldWidth;j++){
				if(this.intersects(World.squares[j][i]) && World.squares[j][i].ID != 0){
					dy = 0;
					y--;
					jumping = false;
					break;
				}
			}
		}	
	}
	
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width, height);
	}
}
