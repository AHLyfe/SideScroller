package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadLevel {
	static Scanner scanner;
	
	//Load the level
	public static Square[][] loadLevel(String filepath, int width){
		Square[][] squareArray = new Square[width][World.worldHeight];
		
		try {
			scanner = new Scanner(new File(filepath + ".level"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		for(int y = 0;y < World.worldHeight;y++){
			for(int x = 0;x < width;x++){
				squareArray[x][y] = new Square(x,y,scanner.nextInt());
				//System.out.println(squareArray[x][y].ID);
			}
		}
		return squareArray;
	}
	
	//Load level width
	public static int loadLevelInfo(String filepath){
		int width;
		
		try {
			scanner = new Scanner(new File(filepath + ".levelinfo"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		width = scanner.nextInt();
		scanner.close();
		return width;
	}
}
