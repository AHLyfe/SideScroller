package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ShowFPS {
	private static long now;
	private static int framesCount = 0;
	private static int framesCountAvg=0;
	private static long framesTimer = 0;
	
	public static void drawFPS(Graphics g){
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(Integer.toString(framesCountAvg), Frame.frame.getContentPane().getWidth() - 40, 20);
        
        now=System.currentTimeMillis(); 
        framesCount++; 
        if(now-framesTimer>1000){ 
        	framesTimer=now; 
        	framesCountAvg=framesCount; 
        	framesCount=0; 
        }
	}
}
