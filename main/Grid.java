package main;

import java.awt.Graphics;
import java.awt.Point;

public class Grid {

	private int xBoxes = 32;
	private int yBoxes = xBoxes / 16 * 9;
	private int sideLength;
	private GameScreen gameScreen;

	public Grid(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		sideLength = gameScreen.getWidth()/xBoxes;
	}

	public void draw(Graphics g) {
		for (int x = 0; x <= xBoxes; x++)
			g.drawLine(x*sideLength, 0, x*sideLength, sideLength*yBoxes);
		for (int y = 0; y <= yBoxes; y++)
			g.drawLine(0, y*sideLength, sideLength*xBoxes, y*sideLength);
	}
	
	public Point getBox(Point p){
		gameScreen.getHeight();
		
		
		return p;
	}

}
