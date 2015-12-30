package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{
	
	
	
	
	public Player(){
		width = 16;
		height = 40;
		
		//Place the Player on top of highest Block
		x = 0;
		
		for(int i = 0;i<World.worldHeight;i++){
			if(World.squares[0][i].ID != 0){
				System.out.println(i);
				this.y = i*World.blockSize - height;
				break;
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
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width, height);
	}
}
