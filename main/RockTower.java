package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class RockTower extends Tower {
	private double shootDirection;

	private class Rock extends Bullet {
		Rock(int speed, double direction, int damage) {
			super(speed, direction, damage, false, 0);
		}

	}

	RockTower(int x, int y, Player p) {
		super(x, y, p);
	}

	public void shoot() {
		Blob closest;
		Point p = new Point(getPlayer().getGrid().sideLength() * getX()
				+ getPlayer().getGrid().sideLength() / 2, getPlayer().getGrid()
				.sideLength() * getY() + getPlayer().getGrid().sideLength() / 2);
		double distance=1000000;
		for (Blob b : getPlayer().getBlobs()) {
			double test=p.distance(b.getPoint());
			if(test<distance){
				closest=b;
				distance=test;
			}
			
		}
	}

	public void draw(Graphics g) {
		int xStart = getPlayer().getGrid().sideLength() * getX();
		int yStart = getPlayer().getGrid().sideLength() * getX();
		int sideLength = getPlayer().getGrid().sideLength();
		g.setColor(new Color(110, 91, 16));
		g.fillRect(xStart, yStart, sideLength, sideLength);
		g.setColor(new Color(130, 118, 109));
		g.fillOval(xStart + 5, yStart + 5, sideLength - 10, sideLength - 10);
	}

}
