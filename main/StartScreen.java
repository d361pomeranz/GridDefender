package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class StartScreen extends GDScreen{
	private Button newGameButton;
	private Button loadGameButton;
	public StartScreen(GDFrame frame) {
		super(frame);
		newGameButton=new Button(frame.getWidth()/2-50,frame.getHeight()/2,100,50,"New Game");
		loadGameButton=new Button(frame.getWidth()/2-50,frame.getHeight()/2+75,100,50,"Load Game");
	}

	public void tick() {
		
	}

	public void draw() {
		Graphics g = getFrame().getBufferStrategy().getDrawGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		//g.setColor(Color.red);
		//g.drawString("You are on the Start Screen", 500, 400);
		newGameButton.draw(g);
		loadGameButton.draw(g);
		
	}

	public void mouseClicked(MouseEvent e) {
		getFrame().switchScreen(new GameScreen(getFrame(), new Player()));
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
