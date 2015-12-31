package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Square extends Rectangle{
	int xCo;
	int yCo;
	
	int ID;
	
	double friction;
	boolean free;
	double gravity;
	
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
			free = true;
			gravity = 1000;
		}
		else if (ID == 1){
			friction = 6;
			free = false;
			gravity = 0;
		}
		else if (ID == 2){
			friction = 1;
			free = false;
			gravity = 0;
		}
		else if (ID == 3){
			friction = 0;
			free = true;
			gravity = 400;
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
