package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{
	
	
	
	
	public Player(){
		width = 16;
		height = 40;
		
		//Place the Player on top of highest Block
		x = 0;
		
		for(int y = 0;y<World.worldHeight;y++){
			if(World.squares[0][y].ID != 0){
				y = y*World.worldHeight - height;
			}
		}
	}
	
	public void jump(){
		
	}
	
	public void left(){
		
	}
	
	public void right(){
		
	}
	
	
	
	public void draw(Graphics g){
		g.drawRect(x, y, width, height);
	}
}
