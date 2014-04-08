package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Blob {
	private int health;
	private int speed;
	private int x;
	private int y;
	private Grid grid;
	private boolean human;
	private int direction;

	public Blob(int h, int s, Grid grid, boolean computer) {
		health = h;
		speed = s;
		this.grid = grid;
		human = !computer;
		if (human) {
			x = grid.getHBase().getX() * grid.sideLength() + grid.sideLength()
					/ 2;
			y = grid.getHBase().getY() * grid.sideLength() + grid.sideLength()
					/ 2;
		} else {
			direction = 2;
			x = grid.getCBase().getX() * grid.sideLength() + grid.sideLength()
					/ 2;
			y = grid.getCBase().getY() * grid.sideLength() + grid.sideLength()
					/ 2;
		}
	}

	public void tick() {
		if (direction == 0)
			x += speed;
		if (direction == 1)
			y += speed;
		if (direction == 2)
			x -= speed;
		if (direction == 3)
			y -= speed;
		if (x - currentBox().getX() * grid.sideLength() >= grid.sideLength()
				/ 2 - speed / 2)
			if (x - currentBox().getX() * grid.sideLength() <= grid
					.sideLength() / 2 + speed / 2)
				if (y - currentBox().getY() * grid.sideLength() >= grid
						.sideLength() / 2 - speed / 2)
					if (y - currentBox().getY() * grid.sideLength() <= grid
							.sideLength() / 2 + speed / 2)
						while (grid.getMaze()[(int) nextBox().getX()][(int) nextBox()
								.getY()] == false) {
							turn();
							if (grid.getMaze()[(int) nextBox().getX()][(int) nextBox()
									.getY()] == false) {
								turn();
								turn();
							}
						}
	}

	public Point getPoint() {
		return new Point(x, y);
	}

	public void turn() {
		direction++;
		if (direction == 4)
			direction = 0;
	}

	private Point currentBox() {
		return new Point(x / grid.sideLength(), y / grid.sideLength());
	}

	private Point nextBox() {
		if (direction == 0)
			return new Point(x / grid.sideLength() + 1, y / grid.sideLength());
		if (direction == 1)
			return new Point(x / grid.sideLength(), y / grid.sideLength() + 1);
		if (direction == 2)
			return new Point(x / grid.sideLength() - 1, y / grid.sideLength());
		else
			return new Point(x / grid.sideLength(), y / grid.sideLength() - 1);
	}

	public void draw(Graphics g) {
		if (human)
			g.setColor(Color.red);
		else
			g.setColor(Color.blue);
		g.fillOval(x - 10, y - 10, 20, 20);
	}

	public void damage(int n) {
		health -= n;
	}

	public boolean touchesBase() {
		Point p = new Point(x, y);
		Point p2;
		if (human) {
			p2 = new Point(grid.getCBase().getX() * grid.sideLength()
					+ grid.sideLength() / 2, grid.getCBase().getY()
					* grid.sideLength() + grid.sideLength() / 2);
			if (p.distance(p2) < 20)
				return true;
		} else {
			p2 = new Point(grid.getHBase().getX() * grid.sideLength()
					+ grid.sideLength() / 2, grid.getHBase().getY()
					* grid.sideLength() + grid.sideLength() / 2);
			if (p.distance(p2) < 20)
				return true;
		}
		return false;
	}
}
