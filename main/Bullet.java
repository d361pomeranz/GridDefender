package main;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Bullet {
	private double speed;
	private double direction;
	private int damage;
	private boolean splash;
	private int splashRandge;
	private Point point;
	private Tower tower;

	Bullet(double speed, double direction, int damage, boolean splash,
			int splashRange, Point start, Tower t) {
		this.speed = speed;
		this.direction = direction;
		this.damage = damage;
		this.splash = splash;
		this.splashRandge = splashRange;
		this.point = start;
		this.tower=t;
	}

	public abstract void draw(Graphics g);

	public void tick(){
		Point newPoint=new Point();
		newPoint.setLocation(this.getPoint().getX()+getSpeed()*Math.cos(getDirection()),this.getPoint().getY()+getSpeed()*Math.sin(getDirection()));
		setPoint(newPoint);
		checkCollision(getTower().getPlayer().getBlobs());
		if(getPoint().distance(getTower().getPoint())>getTower().getRange()){
			remove();
		}
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point p) {
		point = p;
	}

	public double getDirection() {
		return direction;
	}
	public void setDirection(double d){
		direction=d;
	}

	public double getSpeed() {
		return speed;
	}
	public Tower getTower(){
		return tower;
	}

	public void checkCollision(ArrayList<Blob> blobs) {
		for (Blob b : blobs) {
			if(b.getPoint().distance(getPoint())<=20){
				b.damage(damage);
				remove();
			}
		}
	}
	public void remove(){
		getTower().getBullets().remove(this);
	}
}
