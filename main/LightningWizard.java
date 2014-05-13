package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class LightningWizard extends Tower {
	private double direction = Math.PI / 2;
	private int tick = 0;
	private boolean zap = false;

	private class LightningBolt extends Bullet {
		private Point targ;
		private int chainCount = 0;
		private int tx;
		private int ty;

		LightningBolt(double speed, double direction, int damage, Point start,
				Tower t, Point target) {
			super(speed, direction, damage, false, 0, start, t);
			targ = target;
			tx = (int) start.getX();
			ty = (int) start.getY();
		}

		public void draw(Graphics g) {
			g.setColor(Color.cyan);
			g.drawLine((int) getPoint().getX() - 1,
					(int) getPoint().getY() - 1, (int) targ.getX() - 1,
					(int) targ.getY() - 1);
			g.drawLine((int) getPoint().getX(), (int) getPoint().getY(),
					(int) targ.getX(), (int) targ.getY());
			g.drawLine((int) getPoint().getX() + 1,
					(int) getPoint().getY() + 1, (int) targ.getX() + 1,
					(int) targ.getY() + 1);
			g.drawLine(tx, ty, (int) targ.getX(), (int) targ.getY());
		}

		public void tick() {
			Point newPoint = new Point();
			newPoint.setLocation(targ);
			setPoint(newPoint);
			checkCollision(getTower().getPlayer().getBlobs());
			if (getPoint().distance(getTower().getPoint()) > getTower()
					.getRange()) {
				remove();
			}
		}

		public void remove() {
			if (chainCount < 5
					&& getTower().getTarget(getTower().getPlayer().getBlobs()) != null) {
				targ = getTower().getTarget(getTower().getPlayer().getBlobs())
						.getPoint();

				setDirection(getTower().getDirection(getPoint(), targ));
				chainCount++;
			} else {
				getTower().getBullets().remove(this);
			}
		}

	}

	LightningWizard(Player p, int ti) {
		super(p, ti);
	}

	LightningWizard(Player p, int ti, int x, int y) {
		super(p, ti, x, y);
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
		for (int i = 0; i < 10; i += 2) {
			g.drawLine(
					(int) (xStart + sideLength / 2 + sideLength / 2
							* Math.cos(Math.PI * 3 / 2 + (Math.PI * 2 / 5 * i))) + 1,
					(int) (yStart + sideLength / 2 - sideLength / 2
							* Math.sin(Math.PI * 3 / 2 + (Math.PI * 2 / 5 * i))),
					(int) (xStart + sideLength / 2 + sideLength
							/ 2
							* Math.cos(Math.PI * 3 / 2
									+ (Math.PI * 2 / 5 * (i + 2)))) + 1,
					(int) (yStart + sideLength / 2 - sideLength
							/ 2
							* Math.sin(Math.PI * 3 / 2
									+ (Math.PI * 2 / 5 * (i + 2)))));
		}
		if (zap) {
			g.setColor(Color.BLACK);
			g.drawString("ZAP!", (int) (sideLength * Math.random()) + xStart,
					(int) (sideLength * Math.random()) + yStart);
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
					new LightningBolt(getSpeed(), direction, getDamage(),
							getPoint(), this, closest.getPoint()));
			zap = true;
		}
	}

	public int getRange() {
		return 150;
	}

	public int getDamage() {
		return 35;
	}

	public void tick() {
		tick++;
		if (tick % 15 == 0) {
			shoot();
		} else if (tick % 15 == 5) {
			zap = false;
		}
		for (int i = 0; i < getBullets().size(); i++) {
			getBullets().get(i).tick();
		}
	}

	public double getSpeed() {
		return 5;
	}
}
