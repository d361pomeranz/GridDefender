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
	public static int x = 10;
	public static int y = 10;
	private int dx = 0;
	private int dy = 0;

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
				&& p.getX() < x + grid.sideLength() * 4.5) {
			if (p.getY() > y && p.getY() < y + grid.sideLength() / 2) {
				return true;
			}
		}

		return false;
	}

	public void setMouseDownOnBar(boolean b) {
		mouseDownOnBar = b;
	}

	public boolean open() {
		return open;
	}

	public void tick() {
		if (mouseDownOnBar) {
			x = (int) (grid.getScreen().getMouse().getX() - dx);
			y = (int) (grid.getScreen().getMouse().getY() - dy);
		}
		for (int i = 0; i < player.getTowers().size(); i++)
			if (player.getTowers().get(i).inPlay() == false)
				player.getTowers().get(i).uiAdjust();
	}

	public boolean onBar(Point p) {
		if (p.getX() > x && p.getX() < x + 4 * grid.sideLength())
			if (p.getY() > y && p.getY() < y + grid.sideLength() / 2) {
				dx = (int) (p.getX() - x);
				dy = (int) (p.getY() - y);
				return true;
			}
		return false;
	}

	public void draw(Graphics g) {
		if (open) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x, y, (int) (grid.sideLength() * 4.5),
					(int) (grid.sideLength() * 4.5));
			for (int i = 0; i < towers.size(); i++)
				if (towers.get(i).inPlay() == false) {
					if (towers.get(i).clicked())
						towers.get(i).drawClicked(g);
					else
						towers.get(i).draw(g);
				}
		}
		g.setColor(Color.GRAY);
		g.fillRect(x, y, (int) (grid.sideLength() * 4.5), grid.sideLength() / 2);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x + grid.sideLength() * 4, y, grid.sideLength() / 2,
				grid.sideLength() / 2);

	}

	public void towerClicked(int z) {
		for (int i = 0; i < towers.size(); i++) {
			if (z != i)
				towers.get(i).setClick(false);
		}
		towers.get(z).click();
	}

	public int getTowerOn(Point p) {

		for (int i = 0; i < towers.size(); i++) {
			if (towers.get(i).inPlay() == false) {
				if (towers.get(i).onTower(p)) {
					return towers.get(i).index();
				}
			}
		}
		return -1;
	}

}
