package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Square extends Rectangle{
	int xCo;
	int yCo;
	
	int ID;
	
	public Square(int x,int y, int ID){
		xCo = x;
		yCo = y;
		
		this.x = xCo*World.blockSize;
		this.y = yCo*World.blockSize;
		
		width = World.blockSize;
		height = World.blockSize;
		
		this.ID = ID;
	}
	
	public void draw(Graphics g){
		if(ID==0){
			g.setColor(Color.WHITE);
		}
		else if(ID==1){
			g.setColor(Color.BLACK);
		}
		g.fillRect(x, y, width, height);
	}
}
