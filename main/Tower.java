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

	Tower(int x, int y, Player p) {
		xPos = x;
		yPos = y;
		player = p;
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
		return new Point(getPlayer().getGrid().sideLength() * getX()
				+ getPlayer().getGrid().sideLength() / 2, getPlayer().getGrid()
				.sideLength() * getY() + getPlayer().getGrid().sideLength() / 2);
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
