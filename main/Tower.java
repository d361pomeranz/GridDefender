package main;

import java.awt.Graphics;

public abstract class Tower {

	private int level = 1;
	private int range;
	private int xPos;
	private int yPos;
	private Player player;

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

	public abstract void draw(Graphics g);
	public abstract void shoot();
	public abstract int getRange();
	public abstract int getDamage();
	public int getLevel(){
		return level;
	}

}
