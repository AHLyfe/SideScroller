package Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;

import Main.Frame;

public class World {
	public static int worldWidth;
	public static final int worldHeight = 18;
	public static final int blockSize = 32;
	public static int screenWidth = 32*blockSize;
	
	public static Square[][] squares;
	
	public static double offset = 0;
	
	public World(String filepath){
		
		worldWidth = LoadLevel.loadLevelInfo(filepath);
		
		squares = LoadLevel.loadLevel(filepath, worldWidth);
		
		Frame.panel.setPreferredSize(new Dimension(32*blockSize, worldHeight*blockSize));
		Frame.frame.pack();
	}
	
	public void draw(Graphics g){
		for(int y = 0;y < worldHeight;y++){
			for(int x = 0;x < worldWidth;x++){
				squares[x][y].draw(g, (int)offset);
			}
		}
	}
}
