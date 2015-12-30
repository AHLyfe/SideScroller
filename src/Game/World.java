package Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;

import Main.Frame;

public class World {
	public int worldWidth;
	public int worldHeight;
	
	public static final int blockSize = 32;
	
	public Square[][] squares;
	
	public World(int width, int height){
		worldWidth = width;
		worldHeight = height;
		
		Frame.panel.setPreferredSize(new Dimension(worldWidth*blockSize, worldHeight*blockSize));
		Frame.frame.pack();
		
		squares = LoadLevel.loadLevel(new File("res/levels/test.level"), width, height);
	}
	
	public void draw(Graphics g){
		for(int y = 0;y < worldHeight;y++){
			for(int x = 0;x < worldWidth;x++){
				squares[x][y].draw(g);
			}
		}
	}
}
