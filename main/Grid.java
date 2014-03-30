package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Grid {

	private int xBoxes = 32;
	private int yBoxes = xBoxes / 16 * 9;
	private int sideLength;
	private GameScreen gameScreen;
	private boolean[][] maze;

	public Grid(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		sideLength = gameScreen.getWidth() / xBoxes;
		generateSimpleMaze();
	}

	private void generateSimpleMaze() {
		maze = new boolean[xBoxes][yBoxes];
		for (int x = 0; x < xBoxes; x++)
			for (int y = 0; y < yBoxes; y++)
				maze[x][y] = false;
		for (int i = 0; i < xBoxes; i++){
			maze[i][9] = true;
			maze[i][8] = true;
			maze[i][7] = true;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.green);
		for (int x = 0; x < xBoxes; x++)
			for (int y = 0; y < yBoxes; y++)
				if (maze[x][y] == true)
					g.fillRect(sideLength * x, sideLength * y, sideLength,
							sideLength);
		g.setColor(Color.BLUE);
		for (int x = 0; x <= xBoxes; x++)
			g.drawLine(x * sideLength, 0, x * sideLength, sideLength * yBoxes);
		for (int y = 0; y <= yBoxes; y++)
			g.drawLine(0, y * sideLength, sideLength * xBoxes, y * sideLength);
	}

	public Point getBox(Point p) {
		gameScreen.getHeight();
		return p;
	}

}
