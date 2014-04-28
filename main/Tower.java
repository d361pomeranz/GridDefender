package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Tower {

	private int level = 1;
	private int range;
	private int xPos;
	private int yPos;
	private Player player;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private boolean inPlay = false;
	private int towerIndex;
	private boolean clicked;

	Tower(Player p, int ti) {
		player = p;
		towerIndex = ti;
		uiAdjust();
	}

	public boolean inPlay() {
		return inPlay;
	}
	
	public int index(){
		return towerIndex;
	}

	public void setInPlay() {
		inPlay = !inPlay;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	public boolean clicked(){
		return clicked;
	}
	
	public void click(){
		System.out.println("click");
		clicked = !clicked;
	}

	public boolean onTower(Point p) {
		System.out.println(xPos);
		System.out.println(p.getX());
		System.out.println(xPos + player.getGrid().sideLength());
		System.out.println(yPos);
		System.out.println(p.getY());
		System.out.println(yPos + player.getGrid().sideLength());
		if (p.getX() > xPos )
			if (p.getX() < (xPos + player.getGrid().sideLength()))
				if (p.getY() > yPos)
					if (p.getY() < (yPos + player.getGrid().sideLength()))
						return true;
		return false;
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
		if (p1.getX() <= p2.getX()) {
			return Math.atan((p2.getY() - p1.getY()) / (p2.getX() - p1.getX()));
		} else {
			return (Math
					.atan((p2.getY() - p1.getY()) / (p2.getX() - p1.getX())) + Math.PI);
		}
	}

	public void uiAdjust() {
		Grid g = player.getGrid();
		yPos = g.getUI().y + g.sideLength() / 2 + 5 + (towerIndex / 4) * (g.sideLength() + 5);
		xPos = g.getUI().x + g.sideLength() / 10 + (towerIndex % 4) * (g.sideLength() + g.sideLength() / 10);
	}
	
	public void drawClicked(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(getX(), getY(), player.getGrid().sideLength(), player.getGrid().sideLength());
	}
	
	public void setClick(boolean b){
		clicked = b;
	}

	public abstract void draw(Graphics g);

	public abstract void shoot();

	public abstract int getRange();

	public abstract int getDamage();

	public abstract void tick();

	public abstract double getSpeed();

	public int getLevel() {
		return level;
	}

}
