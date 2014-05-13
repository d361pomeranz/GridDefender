package main;

import java.awt.Color;
import java.awt.Graphics;

public class Base {

	private int health;
	private int x;
	private int y;

	public Base(int x, int y) {
		this.x = x;
		this.y = y;
		health = 1000;
	}

	public void damage(int n) {
		health -= n;
	}

	public int getHealth() {
		return health;
	}

	public void draw(Graphics g, int sideLength) {
		g.fillRect(sideLength * x + 8, sideLength * y + 8, sideLength - 14,
				sideLength - 14);
		if (g.getColor().equals(Color.blue))
			g.setColor(Color.white);
		else
			g.setColor(Color.black);
		g.drawString(Integer.toString(health), sideLength * x + sideLength / 4 - 2,
				sideLength * y + sideLength / 2 + 5);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
