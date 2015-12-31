package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Square extends Rectangle{
	int xCo;
	int yCo;
	
	int ID;
	
	double friction;
	boolean solid;
	double gravity;
	double maxXSpeed;
	
	public Square(int x,int y, int ID){
		xCo = x;
		yCo = y;
		
		this.x = xCo*World.blockSize;
		this.y = yCo*World.blockSize;
		
		width = World.blockSize;
		height = World.blockSize;
		
		this.ID = ID;
		
		if(ID == 0){
			//air
			friction = 0.8;
			solid = true;
			gravity = 0.1;
			maxXSpeed = 2;
		}
		else if (ID == 1){
			friction = 6;
			solid = false;
			gravity = 0;
			maxXSpeed = 2;
		}
		else if (ID == 2){
			friction = 1;
			solid = false;
			gravity = 0;
			maxXSpeed = 3;
		}
		else if (ID == 3){
			friction = 0;
			solid = true;
			gravity = 0.02; 
			maxXSpeed = 2;
		}
	}
	
	public void draw(Graphics g){
		if(ID==0){
			g.setColor(Color.WHITE);
		}
		else if(ID==1){
			g.setColor(Color.BLACK);
		}
		else if(ID==2){
			g.setColor(new Color(100,200,255));
		}
		else if(ID==3){
			g.setColor(Color.GREEN);
		}
		g.fillRect(x, y, width, height);
	}
}
