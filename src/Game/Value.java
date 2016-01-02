package Game;

import java.awt.Color;

//Hold final variable values
public class Value {
	
	//location in array corresponds to the block ID's
	public static final int squareAir = 0;
	public static final int squareGround = 1;
	public static final int squareIce = 2;
	public static final int squareDenseAir = 3;
	public static final int squareSpawn = 4;
	
	public static final double[] maxXSpeed = {2, 2, 6, 2, 2};
	public static final double[] gravity = {0.08, 0, 0, 0.02, 0.08};
	public static final double[] friction = {0.8, 8, 1, 0.8, 0.8};
	public static final double[] acceleration = {0.04, 0.04, 0.08, 0.04, 0.04};
	
	
	public static final boolean[] solid = {false, true, true, false, false};
	
	public static final Color[] squareColor = {Color.WHITE, Color.BLACK, new Color(100,200,255), Color.GREEN, Color.CYAN};
	
}
