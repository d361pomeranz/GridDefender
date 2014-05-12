package main;

import java.awt.Point;
import java.util.ArrayList;

public class ComputerPlayer extends Player {
	private int[][] bueno;
	private ArrayList<Point> high = new ArrayList<Point>();
	private ArrayList<Point> med = new ArrayList<Point>();
	private ArrayList<Point> low = new ArrayList<Point>();
	private ArrayList<Point> zero = new ArrayList<Point>();
	private int tick = 0;
	private int targ;

	public ComputerPlayer(Grid grid, ArrayList<Blob> e, ArrayList<Blob> p) {
		super(grid, e, p);
		bueno = new int[getGrid().getXboxes()][getGrid().getYBoxes()];
		for (int x = 0; x < getGrid().getXboxes(); x++) {
			for (int y = 0; y < getGrid().getYBoxes(); y++) {
				bueno[x][y] = 0;
				if (x > 0 && getGrid().getMaze()[x - 1][y]) {
					bueno[x][y]++;
				}
				if (x < getGrid().getXboxes() - 1
						&& getGrid().getMaze()[x + 1][y]) {
					bueno[x][y]++;
				}
				if (y > 0 && getGrid().getMaze()[x][y - 1]) {
					bueno[x][y]++;
				}
				if (y < getGrid().getYBoxes() - 1
						&& getGrid().getMaze()[x][y + 1]) {
					bueno[x][y]++;
				}
				if (getGrid().getMaze()[x][y]) {
					bueno[x][y] -= 100;
				}
				for (int i = 0; i < grid.getXboxes(); i++) {
					zero.add(new Point(i, 0));
					zero.add(new Point(i, grid.getXboxes() - 1));
				}
				for (int i = 0; i < grid.getXboxes(); i++) {
					bueno[i][0] = -1;
					bueno[i][grid.getYBoxes() - 1] = -1;
				}
				for (int i = 0; i < grid.getYBoxes(); i++) {
					bueno[0][i] = -1;
					bueno[grid.getXboxes() - 1][i] = -1;
				}
				if (bueno[x][y] == 3) {
					high.add(new Point(x, y));
				} else if (bueno[x][y] == 2) {
					med.add(new Point(x, y));
				} else if (bueno[x][y] == 1) {
					low.add(new Point(x, y));
				} else if (bueno[x][y] == 0) {
					zero.add(new Point(x, y));
				}
			}
		}

		targ = (int) (4 * Math.random());

	}

	public void tick() {
		tick++;
		if (targ == 0 && getGold() >= 600) {
			placeTower(new RockTower(this, getTowers().size()));
			targ = (int) (4 * Math.random());
		} else if (targ == 1 && getGold() >= 800) {
			placeTower(new ArrowTower(this, getTowers().size()));
			targ = (int) (4 * Math.random());
		} else if (targ == 2 && getGold() >= 1000) {
			placeTower(new SpikyTower(this, getTowers().size()));
			targ = (int) (4 * Math.random());
		} else if (targ == 3 && getGold() >= 1200) {
			placeTower(new LightningWizard(this, getTowers().size()));
			targ = (int) (4 * Math.random());
		}

		super.tick();
	}

	private void placeTower(Tower t) {
		Point p;
		int rand;
		if (high.size() > 0) {
			p = high.remove((int) (high.size() * Math.random()));
		} else if (med.size() > 0) {
			p = med.remove((int) (med.size() * Math.random()));
		} else if (low.size() > 0) {
			p = low.remove((int) (low.size() * Math.random()));
		} else {
			p = zero.remove((int) (zero.size() * Math.random()));
		}
		if (getGrid().canBePlaced((int) (p.getX()),
				(int) (p.getY()))) {
			t.place((int) p.getX(), (int) p.getY());
		}

	}

}
