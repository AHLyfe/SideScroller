package Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Game.GamePanel;
import Main.Frame;
import Main.ShowFPS;

public class MenuPanel extends JPanel implements Runnable{
	Thread thread = new Thread(this);
	
	private boolean first = true;
	
	public int myWidth, myHeight;
	
	
	public MenuPanel(){
		//Add a mouse Listener to the JPanel
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				Frame.setComponent(new GamePanel());
				System.out.println("Hiu");
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//Add a mouseMotionListner to the JPanel
		this.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//Set focusable to mouseMotionListener can detect and focus on panel
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		//Debug
		this.setName("MenuPanel");
		
		//Start the runnable thread
		thread.start();
	}
	
	//PaintComponent for the MenuPanel
	@Override
	public void paintComponent(Graphics g){
		if(first){
			define();
			first = false;
		}
		
		g.setColor(new Color(100,0,255));
		g.fillRect(0, 0, myWidth, myHeight);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.ITALIC, 40));
		g.drawString("Click anywhere to start", 40, 60);
		
		ShowFPS.drawFPS(g);
	}
	
	public void define(){
		myWidth = getWidth();
		myHeight = getHeight();
	}
	
	public void run(){
		
	}
}
