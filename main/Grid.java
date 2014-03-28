package main;

import java.awt.Graphics;

public class Grid {

	private int xBoxes = 32;
	private int yBoxes = xBoxes / 16 * 9;
	private int sideLength;
	private GameScreen gameScreen;
	private Maze maze;

	public Grid(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		sideLength = gameScreen.getWidth()/xBoxes;
		maze=new Maze(xBoxes,yBoxes);
	}

	public void draw(Graphics g) {
		for (int x = 0; x <= xBoxes; x++)
			g.drawLine(x*sideLength, 0, x*sideLength, sideLength*yBoxes);
		for (int y = 0; y <= yBoxes; y++)
			g.drawLine(0, y*sideLength, sideLength*xBoxes, y*sideLength);
	}

}
