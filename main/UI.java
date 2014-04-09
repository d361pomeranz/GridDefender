package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

public class UI {

	private Grid grid;
	private Player player;
	private ArrayList<Tower> towers;
	private boolean open = true;
	private boolean mouseDownOnBar = false;
	int x = 10;
	int y = 10;
	private Point lastPosition = new Point(x, y);
	private Point currentPosition = new Point(x, y);

	public UI(Player p) {
		player = p;
		grid = p.getGrid();
		towers = p.getTowers();
	}

	public void alter() {
		open = !open;
	}

	public boolean onBox(Point p) {
		if (p.getX() > x + grid.sideLength() * 4
				&& p.getX() < x + grid.sideLength() * 4.5)
			if (p.getY() > y && p.getY() < y + grid.sideLength() / 2)
				return true;
		return false;
	}

	public void setMouseDownOnBar(boolean b) {
		mouseDownOnBar = b;
	}

	public void tick() {
		if (mouseDownOnBar) {
			x = (int) (grid.getScreen().getMouse().getX());
			y = (int) (grid.getScreen().getMouse().getY());
		}
	}

	public boolean onBar(Point p) {
		if (p.getX() > x && p.getX() < x + 4 * grid.sideLength())
			if (p.getY() > y && p.getY() < y + grid.sideLength() / 2){
				lastPosition = p;
				return true;
			}
		return false;
	}

	public void draw(Graphics g) {
		if (open) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x, y, (int) (grid.sideLength() * 4.5),
					(int) (grid.sideLength() * 4.5));
		}
		g.setColor(Color.GRAY);
		g.fillRect(x, y, (int) (grid.sideLength() * 4.5), grid.sideLength() / 2);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x + grid.sideLength() * 4, y, grid.sideLength() / 2,
				grid.sideLength() / 2);

	}

}
