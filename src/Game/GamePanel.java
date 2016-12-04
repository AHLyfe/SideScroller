package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Game.Sound.SoundManager;
import Main.ShowFPS;

public class GamePanel extends JPanel implements Runnable{
	Thread thread = new Thread(this);
	
	private boolean first = true;
	
	public int myWidth;
	public int myHeight;
	
	public Player player = null;
	public World world;
	private String level;
	public SoundManager soundManager;
	
	public int test; //Delete this variable
	
	
	
	public GamePanel(String level){
		this.level = level;
		
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
				if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
					player.left();
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
					player.right();
				}
				else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_W){
					player.isUp = true;
					player.jump();
				}	
				else if(e.getKeyCode() == KeyEvent.VK_R){
					reset();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
					player.isLeft = false;
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
					player.isRight = false;
				}
				else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_W){
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
	
	public void reset(){
		define();
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
		
		world = new World("res/levels/" + level);
		player = new Player();
		soundManager = new SoundManager();
		

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
