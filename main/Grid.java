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
	private Base hBase;
	private Base cBase;

	public Grid(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		sideLength = gameScreen.getWidth() / xBoxes;
		generateSimpleMaze();
		placeBases();
	}

	private void generateSimpleMaze() {
		maze = new boolean[xBoxes][yBoxes];
		for (int x = 0; x < xBoxes; x++)
			for (int y = 0; y < yBoxes; y++)
				maze[x][y] = false;
		for (int i = 0; i < xBoxes; i++) {
			maze[i][9] = true;
			maze[i][8] = true;
			maze[i][7] = true;
		}
	}

	private void placeBases() {

		for (int i = 1; i < xBoxes - 1; i++)
			if (maze[i][0] == true && maze[i - 1][0] == true
			&& maze[i + 1][0] == true)
				hBase = new Base(i, 0);
		if (hBase == null)
			for (int i = 1; i < yBoxes - 1; i++)
				if (maze[0][i] == true && maze[0][i - 1] == true
				&& maze[0][i + 1] == true)
					hBase = new Base(0, i);
		if (hBase == null)
			for (int i = 1; i < xBoxes - 1; i++)
				if (maze[i][yBoxes - 1] == true && maze[i - 1][yBoxes - 1] == true
				&& maze[i + 1][yBoxes - 1] == true)
					hBase = new Base(i, yBoxes - 1);
		if (hBase == null)
			for (int i = 1; i < yBoxes - 1; i++)
				if (maze[xBoxes - 1][i] == true && maze[xBoxes - 1][i - 1] == true
				&& maze[xBoxes - 1][i + 1] == true)
					hBase = new Base(xBoxes - 1, i);

		for (int i = 1; i < xBoxes - 1; i++)
			if (maze[i][0] == true && maze[i - 1][0] == true
			&& maze[i + 1][0] == true)
				if(i != hBase.getX() || hBase.getY() != 0)
					cBase = new Base(i, 0);
		if (cBase == null)
			for (int i = 1; i < yBoxes - 1; i++)
				if (maze[0][i] == true && maze[0][i - 1] == true
				&& maze[0][i + 1] == true)
					if(i != hBase.getY() || hBase.getX() != 0)
						cBase = new Base(0, i);
		if (cBase == null)
			for (int i = 1; i < xBoxes - 1; i++)
				if (maze[i][yBoxes - 1] == true && maze[i - 1][yBoxes - 1] == true
				&& maze[i + 1][yBoxes - 1] == true)
					if(i != hBase.getX() || hBase.getY() != yBoxes-1)
						cBase = new Base(i, yBoxes - 1);
		if (cBase == null)
			for (int i = 1; i < yBoxes - 1; i++)
				if (maze[xBoxes - 1][i] == true && maze[xBoxes - 1][i - 1] == true
				&& maze[xBoxes - 1][i + 1] == true)
					if(i != hBase.getY() || hBase.getX() != xBoxes - 1)
						cBase = new Base(xBoxes - 1, i);
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
		hBase.draw(g, sideLength);
		cBase.draw(g, sideLength);
	}

	public Point getBox(Point p) {
		gameScreen.getHeight();
		return p;
	}

}
