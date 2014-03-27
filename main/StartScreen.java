package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class StartScreen extends GDScreen{

	public StartScreen(GDFrame frame) {
		super(frame);
	}

	public void tick() {
		
	}

	public void draw() {
		Graphics g = getFrame().getBufferStrategy().getDrawGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.red);
		g.drawString("You are on the Start Screen", 500, 400);
	}

	public void mouseClicked(MouseEvent e) {
		getFrame().switchScreen(new GameScreen(getFrame()));
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
