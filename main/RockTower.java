package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class RockTower extends Tower {
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private int bulletSpeed;
	private int tick=0;

	private class Rock extends Bullet {
		Rock(double speed, double direction, int damage, Point start) {
			super(speed, direction, damage, false, 0, start);
		}

		public void draw(Graphics g) {
			g.setColor(new Color(117, 46, 7));
			g.fillOval((int) this.getPoint().getX() - 2, (int) this.getPoint()
					.getY() - 2, 4, 4);
		}

		public void tick() {
			Point newPoint=new Point();
			newPoint.setLocation(this.getPoint().getX()+getSpeed()*Math.cos(getDirection()),this.getPoint().getY()+getSpeed()*Math.sin(getDirection()));
			setPoint(newPoint);
		}

	}

	RockTower(int x, int y, Player p) {
		super(x, y, p);
	}
	public void tick(){
		tick++;
		if(tick%5==0){
			shoot();
		}
		for (Bullet b : bullets) {
			b.tick();
		}
	}
	public void shoot() {
		Blob closest = getTarget(getPlayer().getBlobs());
		if (closest != null) {
			bullets.add(new Rock(getSpeed(), getDirection(getPoint(),
					closest.getPoint()), getDamage(), getPoint()));
		}
	}

	private Blob getTarget(ArrayList<Blob> blobs) {
		Point p = getPoint();
		double distance = getRange();
		for (Blob b : blobs) {
			double test = p.distance(b.getPoint());
			if (test < distance) {
				return b;
			}
		}
		return null;
	}

	private double getDirection(Point p1, Point p2) {
		return Math.atan((p1.getY() - p2.getY()) / (p1.getX() - p2.getX()));
	}

	private Point getPoint() {
		return new Point(getPlayer().getGrid().sideLength() * getX()
				+ getPlayer().getGrid().sideLength() / 2, getPlayer().getGrid()
				.sideLength() * getY() + getPlayer().getGrid().sideLength() / 2);
	}

	public void draw(Graphics g) {
		int xStart = getPlayer().getGrid().sideLength() * getX();
		int yStart = getPlayer().getGrid().sideLength() * getY();
		int sideLength = getPlayer().getGrid().sideLength();
		g.setColor(new Color(110, 91, 16));
		g.fillRect(xStart, yStart, sideLength, sideLength);
		g.setColor(new Color(130, 118, 109));
		g.fillOval(xStart + 5, yStart + 5, sideLength - 10, sideLength - 10);
		g.setColor(Color.cyan);
		g.drawOval((int)(getPoint().getX()-getRange()/2), (int)(getPoint().getY()-getRange()/2), getRange(), getRange());
		for (Bullet b : bullets) {
			b.draw(g);
		}
	}

	public int getRange() {
		return 150;
	}

	public int getDamage() {
		return 25;
	}
	
	public double getSpeed() {
		return 5;
	}

}
