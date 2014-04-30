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
		for (int i = 0; i < grid.getUI().getTowers().size(); i++) {
			if (grid.getUI().getTowers().get(i).clicked()) {
				if (getMouse().getY() > 0 && getMouse().getY() < getHeight()) {
					if (getMouse().getX() > 0 && getMouse().getX() < getWidth()) {
						int px = (int) (getMouse().getX() / grid.sideLength());
						int py = (int) (getMouse().getY() / grid.sideLength());
						g.setColor(new Color(119, 230, 127));
						if (grid.canBePlaced(px, py))
							if (!grid.getUI().on(getMouse()))
								g.fillRect(px * grid.sideLength() + 3, py
										* grid.sideLength() + 3,
										grid.sideLength() - 5,
										grid.sideLength() - 5);
					}
				}
			}
		}
		grid.draw(g);
		exitButton.draw(g);
	}

	public void mouseClicked(MouseEvent e) {
		if (exitButton.onButton(getMouse()))
			System.exit(0);
		if (grid.getUI().onBox(getMouse()))
			grid.getUI().alter();
		if (grid.getUI().open()){
			if (grid.getUI().on(getMouse())){
				if (grid.getUI().getTowerOn(getMouse()) != -1){
					grid.getUI().towerClicked(grid.getUI().getTowerOn(getMouse()));
				}
			} else {
				for (int i = 0; i < grid.getUI().getTowers().size(); i++)
				if (grid.getUI().getTowers().get(i).clicked())
					if (grid.canBePlaced((int) getMouse().getX() / grid.sideLength(), (int) getMouse().getY() / grid.sideLength()))
						grid.getUI().getTowers().get(i).place((int) getMouse().getX() / grid.sideLength(), (int) getMouse().getY() / grid.sideLength());
			}
			
		}

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
