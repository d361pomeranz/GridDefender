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
			x = grid.getCBase().getX() * grid.sideLength() + grid.sideLength()
					/ 2;
			y = grid.getCBase().getY() * grid.sideLength() + grid.sideLength()
					/ 2;
		}
	}

	public void tick() {
		if (human)
			x += speed;
		else
			x -= speed;
	}

	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillOval(x - 10, y - 10, 20, 20);
	}
	
	public boolean touchesBase(){
		Point p = new Point(x,y);
		Point p2;
		if (human){
			p2 = new Point(grid.getCBase().getX() * grid.sideLength() + grid.sideLength() / 2, grid.getCBase().getY() * grid.sideLength() + grid.sideLength() / 2);
			if (p.distance(p2) < 20)
				return true;
		} else {
			p2 = new Point(grid.getHBase().getX() * grid.sideLength() + grid.sideLength() / 2, grid.getHBase().getY() * grid.sideLength() + grid.sideLength() / 2);
			if (p.distance(p2) < 20)
				return true;
		}
		return false;
	}

}
