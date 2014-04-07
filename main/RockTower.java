package main;

import java.awt.Color;
import java.awt.Graphics;

public class RockTower extends Tower{

	RockTower(int x, int y, Grid g) {
		super(x, y, g);
	}
	public void shoot() {
		
	}
	public void draw(Graphics g) {
		int xStart=getGrid().sideLength()*getxPos();
		int yStart=getGrid().sideLength()*getxPos();
		int sideLength=getGrid().sideLength();
		g.setColor(new Color(110,91,16));
		g.fillRect(xStart, yStart, sideLength,sideLength);
		g.setColor(new Color(130,118,109));
		g.fillOval(xStart+15, yStart+15, sideLength-30, sideLength-30);
	}

}
