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
	
	public World(String filepath){
		int[] dimensions = LoadLevel.loadLevelInfo(filepath);
		
		worldWidth = dimensions[0];
		worldHeight = dimensions[1];
		
		squares = LoadLevel.loadLevel(filepath, worldWidth, worldHeight);
		
		Frame.panel.setPreferredSize(new Dimension(worldWidth*blockSize, worldHeight*blockSize));
		Frame.frame.pack();
	}
	
	public void draw(Graphics g){
		for(int y = 0;y < worldHeight;y++){
			for(int x = 0;x < worldWidth;x++){
				squares[x][y].draw(g);
			}
		}
	}
}
