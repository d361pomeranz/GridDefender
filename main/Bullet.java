package main;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Bullet {
	private int speed;
	private double direction;
	private int damage;
	private boolean splash;
	private int splashRandge;
	private Point point;
	Bullet(int speed, double direction,int damage, boolean splash,int splashRange,Point start){
		this.speed=speed;
		this.direction=direction;
		this.damage=damage;
		this.splash=splash;
		this.splashRandge=splashRange;
		this.point=start;
	}
	public abstract void draw(Graphics g);
	public Point getPoint(){
		return point;
	}
	public void setPoint(Point p){
		point=p;
	}
	public double getDirection(){
		return direction;
	}
	public int getSpeed(){
		return speed;
	}
}
