package main;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class GameScreen extends GDScreen{

	public GameScreen(GDFrame frame) {
		super(frame);
	}

	public void tick() {
				
	}
	
	public void draw() {
		getG().setColor(Color.LIGHT_GRAY);
		getG().fillRect(0, 0, getWidth(), getHeight());
		getG().setColor(Color.blue);
		getG().fillRect(500, 500, 500, 500);
	}
	
	public void mouseClicked(MouseEvent e) {
		getFrame().switchScreen(new StartScreen(getFrame()));
		System.out.println("Clicked on GameScreen");
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

}
