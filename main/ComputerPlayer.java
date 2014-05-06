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

	public ComputerPlayer(Grid grid, ArrayList<Blob> e, ArrayList<Blob> p) {
		super(grid, e, p);
		bueno = new int[getGrid().getXboxes()][getGrid().getYBoxes()];
		for (int x = 0; x < getGrid().getXboxes(); x++) {
			for (int y = 0; y < getGrid().getYBoxes(); y++) {
				bueno[x][y] = 0;
				if (x > 0 && getGrid().getMaze()[x - 1][y]) {
					bueno[x][y]++;
				}
				if (x < getGrid().getXboxes()-1 && getGrid().getMaze()[x + 1][y]) {
					bueno[x][y]++;
				}
				if (y > 0 && getGrid().getMaze()[x][y - 1]) {
					bueno[x][y]++;
				}
				if (y < getGrid().getYBoxes()-1 && getGrid().getMaze()[x][y + 1]) {
					bueno[x][y]++;
				}
				if (bueno[x][y] == 3) {
					high.add(new Point(x, y));
				} else if (bueno[x][y] == 2) {
					med.add(new Point(x, y));
				} else if (bueno[x][y] == 1) {
					low.add(new Point(x, y));
				} else {
					zero.add(new Point(x, y));
				}
			}
		}

	}

	public void tick() {
		tick++;
		if (tick % 10 == 0) {
			int rand = (int) (4 * Math.random());
			if (rand == 0) {
				placeTower(new RockTower(this, getTowers().size()));
			}else if(rand==1){
				placeTower(new ArrowTower(this, getTowers().size()));
			}else if(rand==2){
				placeTower(new SpikyTower(this, getTowers().size()));
			}else{
				placeTower(new LightningWizard(this, getTowers().size()));
			}
		}
		super.tick();
	}

	private void placeTower(Tower t) {
		Point p;
		if(high.size()>0){
			p=high.get((int)(high.size()*Math.random()));
		}else if(med.size()>0){
			p=med.get((int)(med.size()*Math.random()));
		}else if(low.size()>0){
			p=low.get((int)(low.size()*Math.random()));
		}else{
			p=zero.get((int)(zero.size()*Math.random()));
		}
		t.place((int)p.getX(), (int)p.getY());
	}

}
