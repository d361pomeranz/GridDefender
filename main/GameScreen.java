package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class GameScreen extends GDScreen {

	private Grid grid;
	private Button exitButton;

	public GameScreen(GDFrame frame) {
		super(frame);
		exitButton = new Button(frame.getWidth() - 30, 0, 30, 30, "X");
		exitButton.setBGColor(Color.red);
		exitButton.setTextColor(Color.white);
		grid = new Grid(this);
	}

	public void tick() {
		grid.tick();
	}

	public void draw() {
		Graphics g = getFrame().getBufferStrategy().getDrawGraphics();
		g.setColor(new Color(240, 240, 240));
		g.fillRect(0, 0, getWidth(), getHeight());
		grid.draw(g);
		exitButton.draw(g);
	}

	public void mouseClicked(MouseEvent e) {
		if (exitButton.onButton(getMouse()))
			System.exit(0);
		if (grid.getUI().onBox(getMouse()))
			grid.getUI().alter();
		System.out.println(grid.getUI().getTowerOn(getMouse()));
		if (grid.getUI().open())
			if (grid.getUI().getTowerOn(getMouse()) != -1)
				grid.getUI().towerClicked(grid.getUI().getTowerOn(getMouse()));
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if (grid.getUI().onBar(getMouse()))
			grid.getUI().setMouseDownOnBar(true);
	}

	public void mouseReleased(MouseEvent e) {
			grid.getUI().setMouseDownOnBar(false);
	}

}
