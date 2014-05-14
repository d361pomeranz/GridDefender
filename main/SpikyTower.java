package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class SpikyTower extends Tower {
	private class Spike extends Bullet {
		Spike(double speed, double direction, int damage, Point start, Tower t) {
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

	int tick = 0;

	SpikyTower(Player p, int ti) {
		super(p, ti);
	}

	SpikyTower(Player p, int ti, int x, int y) {
		super(p, ti, x, y);
	}

	public void draw(Graphics g) {
		int xStart = getX();
		int yStart = getY();
		int sideLength = getPlayer().getGrid().sideLength();
		g.setColor(new Color(200, 200, 200));
		g.fillRect(xStart, yStart, sideLength, sideLength);
		g.setColor(Color.black);
		g.fillOval(xStart + 5, yStart + 5, sideLength - 10, sideLength - 10);
		g.setColor(Color.pink);
		g.fillOval(xStart + 10, yStart + 10, sideLength - 20, sideLength - 20);
		for (Bullet b : getBullets()) {
			b.draw(g);
		}
	}

	public void shoot() {
		if (getTarget(getPlayer().getBlobs()) != null) {
			for (int i = 0; i < 10; i++) {
				getBullets().add(
						new Spike(getSpeed(), (i * Math.PI / 5), getDamage(),
								getPoint(), this));
			}
		}
	}

	public int getRange() {
		return 75;
	}

	public int getDamage() {
		return 14;
	}

	public void tick() {
		tick++;
		if (tick % 6 == 0) {
			shoot();
		}
		for (int i = 0; i < getBullets().size(); i++) {
			getBullets().get(i).tick();
		}

	}

	public double getSpeed() {
		return 5;
	}

}
