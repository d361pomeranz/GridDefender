package main;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class StartScreen extends GDScreen{

	public StartScreen(GDFrame frame) {
		super(frame);
	}

	public void tick() {
		
	}

	public void draw() {
		getG().setColor(Color.LIGHT_GRAY);
		getG().fillRect(0, 0, getWidth(), getHeight());
		getG().setColor(Color.red);
		getG().fillRect(50, 50, 50, 50);
	}

	public void mouseClicked(MouseEvent e) {
		getFrame().switchScreen(new GameScreen(getFrame()));
		System.out.println("Clicked on StartScreen");
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
