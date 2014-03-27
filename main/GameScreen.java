package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class GameScreen extends GDScreen {
	
	private Grid grid;
	private Player player;

	public GameScreen(GDFrame frame, Player player) {
		super(frame);
		grid = new Grid(this);
	}

	public void tick() {

	}

	public void draw() {
		Graphics g = getFrame().getBufferStrategy().getDrawGraphics();
		g.setColor(new Color(240, 240, 240));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.blue);
		grid.draw(g);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void mouseClicked(MouseEvent e) {
		getFrame().switchScreen(new StartScreen(getFrame()));
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
