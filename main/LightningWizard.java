package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class LightningWizard extends Tower {
	private double direction = Math.PI / 2;
	private int tick = 0;

	private class LightningBolt extends Bullet {
		private Point targ;
		LightningBolt(double speed, double direction, int damage, Point start, Tower t, Point target) {
			super(speed, direction, damage, false, 0, start, t);
			targ=target;
		}

		public void draw(Graphics g) {
			g.setColor(Color.cyan);
			g.drawLine((int)getPoint().getX(),(int)getPoint().getY(),(int)targ.getX(),(int)targ.getY());
		}

	}

	LightningWizard(Player p, int ti) {
		super(p, ti);
	}

	public void draw(Graphics g) {
		int xStart = getX();
		int yStart = getY();
		int sideLength = getPlayer().getGrid().sideLength();
		g.setColor(Color.BLUE);
		g.fillRect(xStart, yStart, sideLength, sideLength);
		g.setColor(Color.cyan);
		g.fillOval(xStart, yStart, sideLength, sideLength);
		g.setColor(Color.yellow);
		for (int i = 0; i < 10; i+=2) {
			g.drawLine(
					(int) (xStart + sideLength / 2 + sideLength/2
							* Math.cos(Math.PI * 3 / 2 + (Math.PI * 2 / 5 * i)))+1,
					(int) (yStart + sideLength / 2 - sideLength/2
							* Math.sin(Math.PI * 3 / 2 + (Math.PI * 2 / 5 * i))),
					(int) (xStart + sideLength / 2 + sideLength/2
							* Math.cos(Math.PI * 3 / 2
									+ (Math.PI * 2 / 5 * (i + 2))))+1,
					(int) (yStart + sideLength / 2 - sideLength/2
							* Math.sin(Math.PI * 3 / 2
									+ (Math.PI * 2 / 5 * (i + 2)))));
		}
	}

	public void shoot() {
		Blob closest = getTarget(getPlayer().getBlobs());
		if (closest != null) {
			direction = getDirection(getPoint(), closest.getPoint());
			getBullets().add(
					new LightningBolt(getSpeed(), direction, getDamage(),
							getPoint(), this,closest.getPoint()));
		}
	}

	public int getRange() {
		return 150;
	}

	public int getDamage() {
		return 25;
	}

	public void tick() {
		tick++;
		if (tick % 15 == 0) {
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
