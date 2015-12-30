package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	Thread thread = new Thread(this);
	
	private boolean first = true;
	
	public int myWidth;
	public int myHeight;
	
	public Player player;
	public World world;
	
	public int test;
	
	
	
	public GamePanel(){
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
				// TODO Auto-generated method stub
				
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
		
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		System.out.println("Heyo");
		
		//Set focusable to mouseMotionListener can detect and focus on panel
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		//Debug
		this.setName("GamePanel");
		
		//Start the runnable thread
		thread.start();
	}
	
	@Override
	public void paintComponent(Graphics g){
		if(first){
			define();
			first = false;
		}

		g.setColor(Color.CYAN);
		g.fillRect(20, 20, 100, 100);
		
		world.draw(g);
	}
	
	public void define(){
		myWidth = getWidth();
		myHeight = getHeight();
		
		player = new Player();
		world = new World(10,10);
	}
	
	public void run(){
		while(true){
			try {
				thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
