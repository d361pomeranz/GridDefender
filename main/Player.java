package main;

import java.util.ArrayList;

public class Player {
	private ArrayList<Tower> towers = new ArrayList<Tower>();
	private Grid g;
	private ArrayList<Blob> enemyBlobs;
	private ArrayList<Blob> playerBlobs;
	private int gold = 1800;

	public Player(Grid grid, ArrayList<Blob> e, ArrayList<Blob> p) {
		g = grid;
		enemyBlobs = e;
		playerBlobs = p;
	}

	public void pay(int x) {
		gold -= x;
	}

	public int getGold() {
		return gold;
	}

	public void tick() {
		for (int i = 0; i < getBlobs().size(); i++) {
			if (getBlobs().get(i).getHealth() <= 0) {
				gold += 10;
				if (this instanceof ComputerPlayer)
					gold +=3;
				getBlobs().remove(i);
			}
		}
	}

	public void addTower(Tower t) {
		towers.add(t);
	}

	public ArrayList<Blob> getBlobs() {
		return enemyBlobs;
	}

	public ArrayList<Tower> getTowers() {
		return towers;
	}

	public Grid getGrid() {
		return g;
	}

}
