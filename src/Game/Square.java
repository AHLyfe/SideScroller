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
	double acceleration;
	
	public Square(int x,int y, int ID){
		xCo = x;
		yCo = y;
		
		this.x = xCo*World.blockSize;
		this.y = yCo*World.blockSize;
		
		width = World.blockSize;
		height = World.blockSize;
		
		this.ID = ID;
		
		friction = Value.friction[ID];
		solid = Value.solid[ID];
		gravity = Value.gravity[ID];
		maxXSpeed = Value.maxXSpeed[ID];
		acceleration = Value.acceleration[ID];
		
		
	}
	
	public void draw(Graphics g, int offset){
		g.setColor(Value.squareColor[ID]);
		g.fillRect(x - offset, y, width, height);
	}
}
