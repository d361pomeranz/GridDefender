package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class UI {

	private Grid grid;
	private Player player;
	private ArrayList<Tower> towers;
	private boolean open = false;
	int x = 10;
	int y = 10;

	public UI(Player p) {
		player = p;
		grid = p.getGrid();
		towers = p.getTowers();
	}

	public void draw(Graphics g) {
		if (open) {
			g.fillRect(x, y, (int) (grid.sideLength() * 4.5),
					(int) (grid.sideLength() * 4.5));
		} else {
			g.setColor(Color.GRAY);
			g.fillRect(x, y, (int) (grid.sideLength() * 4.5),
					grid.sideLength() / 2);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(x + grid.sideLength() * 4, y, grid.sideLength() / 2,
					grid.sideLength() / 2);
			g.setColor(Color.white);
			g.fillRect( (int) (x + grid.sideLength() * 4.1),
					(int) (y + grid.sideLength() / 10),
					(int) (grid.sideLength() * .3),
					(int) (grid.sideLength() / 5));
		}

	}
}
