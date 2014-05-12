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

	Tower(Player p, int ti, int x, int y) {
		player = p;
		towerIndex = ti;
		xPos = x * player.getGrid().sideLength();
		yPos = y * player.getGrid().sideLength();
		inPlay = true;
	}

	public boolean inPlay() {
		return inPlay;
	}

	public int index() {
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

	public boolean clicked() {
		return clicked;
	}

	public void click() {
		clicked = !clicked;
	}

	public boolean onTower(Point p) {
		if (p.getX() > xPos)
			if (p.getX() < (xPos + player.getGrid().sideLength()))
				if (p.getY() > yPos)
					if (p.getY() < (yPos + player.getGrid().sideLength()))
						return true;
		return false;
	}

	public Point getPoint() {
		return new Point(getX() + player.getGrid().sideLength() / 2, getY()
				+ player.getGrid().sideLength() / 2);
	}

	public Blob getTarget(ArrayList<Blob> blobs) {
		Point p = getPoint();
		double distance = getRange();
		for (int i = 0; i < blobs.size(); i++) {
			double test = p.distance(blobs.get(i).getPoint());
			if (test < distance) {
				return blobs.get(i);
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
		yPos = g.getUI().y + g.sideLength() / 2 + 5 + (towerIndex / 4)
				* (g.sideLength() + 5);
		xPos = g.getUI().x + g.sideLength() / 10 + (towerIndex % 4)
				* (g.sideLength() + g.sideLength() / 10);
	}

	public void drawClicked(Graphics g) {
		draw(g);
		g.setColor(Color.green);
		g.drawRect(xPos + 4, yPos + 4, player.getGrid().sideLength() - 8,
				player.getGrid().sideLength() - 8);
		g.drawRect(xPos + 5, yPos + 3, player.getGrid().sideLength() - 10,
				player.getGrid().sideLength() - 6);
		g.drawRect(xPos + 3, yPos + 5, player.getGrid().sideLength() - 6,
				player.getGrid().sideLength() - 10);
	}

	public void setClick(boolean b) {
		clicked = b;
	}

	public void place(int x, int y) {
		if (this instanceof RockTower &&player.getGold()>=600) {
			player.addTower(new RockTower(player, player.getTowers().size(), x, y));
			player.pay(600);
		} else if (this instanceof SpikyTower&&player.getGold()>=1000) {
			player.addTower(new SpikyTower(player, player.getTowers().size(), x, y));
			player.pay(1000);
		} else if (this instanceof ArrowTower&&player.getGold()>=800) {
			player.addTower(new ArrowTower(player, player.getTowers().size(), x, y));
			player.pay(800);
		} else if(player.getGold()>=1200){
			player.addTower(new LightningWizard(player, player.getTowers().size(), x, y));
			player.pay(1200);
		}
		clicked = false;
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
