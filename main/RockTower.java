package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class RockTower extends Tower {
	private int tick = 0;

	private class Rock extends Bullet {
		Rock(double speed, double direction, int damage, Point start, Tower t) {
			super(speed, direction, damage, false, 0, start, t);
		}

		public void draw(Graphics g) {
			g.setColor(new Color(117, 46, 7));
			g.fillOval((int) this.getPoint().getX() - 2, (int) this.getPoint()
					.getY() - 2, 4, 4);
		}

	}

	RockTower(Player p, int ti) {
		super(p, ti);
	}
	
	RockTower(Player p, int ti, int x, int y) {
		super(p, ti, x, y);
	}

	public void tick() {
		tick++;
		if (tick % 4 == 0) {
			shoot();
		}
		for (int i = 0; i < getBullets().size(); i++) {
			getBullets().get(i).tick();
		}
	}

	public void shoot() {
		Blob closest = getTarget(getPlayer().getBlobs());
		if (closest != null) {
			getBullets()
					.add(new Rock(getSpeed(), getDirection(getPoint(),
							closest.getPoint()), getDamage(), getPoint(), this));
		}
	}

	public void draw(Graphics g) {
		int xStart = getX();
		int yStart = getY();
		int sideLength = getPlayer().getGrid().sideLength();
		g.setColor(new Color(110, 91, 16));
		g.fillRect(xStart, yStart, sideLength, sideLength);
		g.setColor(new Color(130, 118, 109));
		g.fillOval(xStart + 5, yStart + 5, sideLength - 10, sideLength - 10);
		for (int i = 0; i < getBullets().size(); i++) {
			getBullets().get(i).draw(g);
		}
	}

	public int getRange() {
		return 125;
	}

	public int getDamage() {
		return 20;
	}

	public double getSpeed() {
		return 30;
	}

}
