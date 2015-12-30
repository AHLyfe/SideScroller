package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadLevel {
	static Scanner scanner;
	
	public static Square[][] loadLevel(File filepath, int width, int height){
		Square[][] squareArray = new Square[width][height];
		
		try {
			scanner = new Scanner(filepath);
			for(int y = 0;y < width;y++){
				for(int x = 0;x < height;x++){
					squareArray[x][y] = new Square(x,y,scanner.nextInt());
					System.out.println(squareArray[x][y].ID);
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		
		
		
		
		return squareArray;
	}
}
