package Main;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.GamePanel;
import Menu.MenuPanel;

/**
 * @author Alastair
 * Main class for the game
 *
 */
public class Frame{
	public static JFrame frame;
	public static JPanel panel;
	public static final Dimension size = new Dimension(800,600);
	public static final String title = "SideScroller Game";
	
	public static void setComponent(Component component){
		panel.removeAll();
		panel.add(component);
		panel.validate();
		Component[] test = panel.getComponents();
		for(Component i:test){
			System.out.println(i.getName());
		}
	}
	
	public static void createFrame(){
		frame = new JFrame();
		
		frame.setSize(size);
		frame.setTitle(title);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(1,1,0,0));
		
		
		//Repaint thread - should probably have a static thread that can stop and start but whatever
		new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(frame.getComponentCount() != 0){
						panel.repaint();
					}
				}
			}
		}.start();
		
		panel = new JPanel(new CardLayout());
		frame.add(panel);
		
		//Add the menu to start with
		setComponent(new MenuPanel());	
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		createFrame();
	}
}
