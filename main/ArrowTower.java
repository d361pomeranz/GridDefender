package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ArrowTower extends Tower {
	private double direction = Math.PI / 2;
	private int tick = 0;

	private class Arrow extends Bullet {
		Arrow(double speed, double direction, int damage, Point start, Tower t) {
			super(speed, direction, damage, false, 0, start, t);
		}

		public void draw(Graphics g) {
			g.setColor(new Color(87, 55, 10));
			g.drawLine(
					(int) (getPoint().getX() - 2 * Math.cos(getDirection())),
					(int) (getPoint().getY() - 2 * Math.sin(getDirection())),
					(int) (getPoint().getX() + 2 * Math.cos(getDirection())),
					(int) (getPoint().getY() + 2 * Math.sin(getDirection())));
		}

	}

	ArrowTower(Player p, int ti) {
		super(p, ti);
	}

	public void draw(Graphics g) {
		int xStart = getX();
		int yStart = getY();
		int sideLength = getPlayer().getGrid().sideLength();
		g.setColor(new Color(87, 55, 10));
		g.fillRect(xStart, yStart, sideLength, sideLength);
		g.setColor(new Color(32, 110, 22));
		g.fillOval(xStart, yStart, sideLength, sideLength);
		g.setColor(Color.black);
		g.drawLine((int) (xStart + sideLength / 2 - 10 * Math.cos(direction)),
				(int) (yStart + sideLength / 2 - 10 * Math.sin(direction)),
				(int) (xStart + sideLength / 2 + 10 * Math.cos(direction)),
				(int) (yStart + sideLength / 2 + 10 * Math.sin(direction)));
		if (inPlay()) {
			g.setColor(Color.cyan);
			g.drawOval((int) (getPoint().getX() - getRange()),
					(int) (getPoint().getY() - getRange()), getRange() * 2,
					getRange() * 2);
		}
		for (Bullet b : getBullets()) {
			b.draw(g);
		}
	}

	public void shoot() {
		Blob closest = getTarget(getPlayer().getBlobs());
		if (closest != null) {
			direction = getDirection(getPoint(), closest.getPoint());
			getBullets().add(
					new Arrow(getSpeed(), direction, getDamage(), getPoint(),
							this));
		}
	}

	public int getRange() {
		return 250;
	}

	public int getDamage() {
		return 7;
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

	public double getSpeed() {
		return 50;
	}

}
