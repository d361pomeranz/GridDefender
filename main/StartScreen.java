package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class StartScreen extends GDScreen {
	private Button newGameButton;
	private Button exitButton;

	public StartScreen(GDFrame frame) {
		super(frame);
		newGameButton = new Button(frame.getWidth() / 2 - 200,
				frame.getHeight() / 2, 400, 80, "New Game");
		exitButton = new Button(frame.getWidth() - 30, 0, 30, 30, "X");
		exitButton.setBGColor(Color.red);
		exitButton.setTextColor(Color.white);
	}

	public void tick() {

	}

	public void draw() {
		Graphics g = getFrame().getBufferStrategy().getDrawGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		newGameButton.draw(g);
		exitButton.draw(g);
	}

	public void mouseClicked(MouseEvent e) {
		if (newGameButton.onButton(getMouse()))
			getFrame().switchScreen(new GameScreen(getFrame()));
		if (exitButton.onButton(getMouse()))
			System.exit(0);
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
