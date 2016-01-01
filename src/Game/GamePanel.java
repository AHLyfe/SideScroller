package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Main.ShowFPS;

public class GamePanel extends JPanel implements Runnable{
	Thread thread = new Thread(this);
	
	private boolean first = true;
	
	public int myWidth;
	public int myHeight;
	
	public Player player = null;
	public World world;
	
	public int test; //Delete this variable
	
	
	
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
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					player.left();
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					player.right();
				}
				else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE){
					player.jump();
				}	
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					player.isLeft = false;
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					player.isRight = false;
				}
				else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE){
					player.isUp = false;
				}	
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
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
		player.draw(g);
		
		ShowFPS.drawFPS(g);

	}
	
	public void define(){
		myWidth = getWidth();
		myHeight = getHeight();
		
		world = new World("res/levels/test");
		player = new Player();
		

		//Set focusable so mouseMotionListener and keyListener can detect and focus on panel
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void run(){
		while(true){
			try {
				thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(player != null){
				player.act();
			}
		}
	}
}
