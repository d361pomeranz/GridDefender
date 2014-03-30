package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class GameScreen extends GDScreen {
	
	private Grid grid;
	private Player player;

	public GameScreen(GDFrame frame, Player player) {
		super(frame);
		this.player = player;
		grid = new Grid(this);
	}

	public void tick() {

	}
	
	public Player getPlayer() {
		return player;
	}

	public void draw() {
		Graphics g = getFrame().getBufferStrategy().getDrawGraphics();
		g.setColor(new Color(240, 240, 240));
		g.fillRect(0, 0, getWidth(), getHeight());
		grid.draw(g);
		
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
