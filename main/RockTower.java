package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class RockTower extends Tower {
	private int bulletSpeed;
	private int tick=0;

	private class Rock extends Bullet {
		Rock(double speed, double direction, int damage, Point start, Tower t) {
			super(speed, direction, damage, false, 0, start,t);
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
			checkCollision(getPlayer().getBlobs());
			if(getPoint().distance(getTower().getPoint())>getTower().getRange()){
				remove();
			}
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
		for(int i=0;i<getBullets().size();i++){
			getBullets().get(i).tick();
		}
	}
	public void shoot() {
		Blob closest = getTarget(getPlayer().getBlobs());
		if (closest != null) {
			getBullets().add(new Rock(getSpeed(), getDirection(getPoint(),
					closest.getPoint()), getDamage(), getPoint(),this));
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
		if(p1.getX()<=p2.getX()){
			return Math.atan((p2.getY()-p1.getY())/(p2.getX()-p1.getX()));
		}else{
			return (Math.atan((p2.getY()-p1.getY())/(p2.getX()-p1.getX()))+Math.PI);
		}
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
		g.drawOval((int)(getPoint().getX()-getRange()), (int)(getPoint().getY()-getRange()), getRange()*2, getRange()*2);
		for (Bullet b : getBullets()) {
			b.draw(g);
		}
	}

	public int getRange() {
		return 100;
	}

	public int getDamage() {
		return 25;
	}
	
	public double getSpeed() {
		return 30;
	}

}
