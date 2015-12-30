package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadLevel {
	static Scanner scanner;
	
	//Load the level
	public static Square[][] loadLevel(String filepath, int width, int height){
		Square[][] squareArray = new Square[width][height];
		
		try {
			scanner = new Scanner(new File(filepath + ".level"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				squareArray[x][y] = new Square(x,y,scanner.nextInt());
				System.out.println(squareArray[x][y].ID);
			}
		}
		
		return squareArray;
	}
	
	//Load level info such as width, height etc.
	public static int[] loadLevelInfo(String filepath){
		int[] dimensions = new int[2];
		
		try {
			scanner = new Scanner(new File(filepath + ".levelinfo"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line = scanner.nextLine();
		String[] dimensionsStr = line.split(",");
		
		dimensions[0] = Integer.parseInt(dimensionsStr[0]);
		dimensions[1] = Integer.parseInt(dimensionsStr[1]);
		
		return dimensions;
	}
}
