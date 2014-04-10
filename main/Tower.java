package main;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Tower {

	private int level = 1;
	private int range;
	private int xPos;
	private int yPos;
	private Player player;
	private ArrayList<Bullet> bullets=new ArrayList<Bullet>();
	private boolean inPlay = false;
	private int towerIndex;

	Tower(int x, int y, Player p) {
		xPos = x;
		yPos = y;
		player = p;
		towerIndex = p.getTowers().indexOf(this);
	}
	
	public boolean inPlay(){
		return inPlay;
	}
	
	public void setInPlay(){
		inPlay = !inPlay;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public Player getPlayer(){
		return player;
	}
	public ArrayList<Bullet> getBullets(){
		return bullets;
	}
	public Point getPoint() {
		return new Point(player.getGrid().sideLength() * getX()
				+ player.getGrid().sideLength() / 2, player.getGrid()
				.sideLength() * getY() + player.getGrid().sideLength() / 2);
	}
	public Blob getTarget(ArrayList<Blob> blobs) {
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
	public double getDirection(Point p1, Point p2) {
		if(p1.getX()<=p2.getX()){
			return Math.atan((p2.getY()-p1.getY())/(p2.getX()-p1.getX()));
		}else{
			return (Math.atan((p2.getY()-p1.getY())/(p2.getX()-p1.getX()))+Math.PI);
		}
	}
	
	public void uiAdjust(){
		Grid g = player.getGrid();
		yPos = g.getUI().y + g.sideLength()/2 + 5 + (towerIndex / 4) * (g.sideLength() + 5);
		xPos = g.getUI().x + 5 + (towerIndex % 4) * (g.sideLength() + 5);
		System.out.println("adjusting");
	}

	public abstract void draw(Graphics g);
	public abstract void shoot();
	public abstract int getRange();
	public abstract int getDamage();
	public abstract void tick();
	public abstract double getSpeed();
	public int getLevel(){
		return level;
	}

}
