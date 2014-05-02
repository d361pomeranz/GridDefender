package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Grid {
	public int getXboxes(){return xBoxes;}
	public int getYBoxes(){return yBoxes;}
	ArrayList<Point> ps;
	private int xBoxes = 32;
	private int yBoxes = xBoxes / 16 * 9;
	private int sideLength;
	private GameScreen gameScreen;
	private boolean[][] maze;
	private Base hBase;
	private Base cBase;
	private ArrayList<Blob> cBlobs = new ArrayList<Blob>();
	private ArrayList<Blob> hBlobs = new ArrayList<Blob>();
	private long ticks = 0;
	private Player player;
	private Player cPlayer;
	private UI ui;
	private int level=0;

	public Grid(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		sideLength = gameScreen.getWidth() / xBoxes;
		dansGenerateRandomMaze();
		placeBases();
		cPlayer = new Player(this, hBlobs, cBlobs);
		player = new Player(this, cBlobs, hBlobs);
		ui = new UI(player);
	}

	public GameScreen getScreen() {
		return gameScreen;
	}

	private void dansGenerateRandomMaze() {
		int cy = (int) (Math.random() * (yBoxes - 2) + 1);
		int hy = (int) (Math.random() * (yBoxes - 2) + 1);

		maze = new boolean[xBoxes][yBoxes];
		boolean noBueno[][] = new boolean[xBoxes][yBoxes];
		for (int x = 0; x < xBoxes; x++) {
			for (int y = 0; y < yBoxes; y++) {
				maze[x][y] = false;
				noBueno[x][y] = false;
			}
		}
		for (int i = 0; i < 2; i++) {
			maze[i][hy] = true;
			maze[xBoxes - 1 - i][cy] = true;
		}
		for (int i = 0; i < yBoxes; i++) {
			noBueno[2][i] = true;
			noBueno[xBoxes - 3][i] = true;
		}
		int turnNum = (int) (Math.random() * 11) + 1;
		Point[] turns = new Point[turnNum];
		int turnCount = 0;
		while (turnCount < turnNum) {
			int rx = (int) (Math.random() * (xBoxes - 4) + 2);
			int ry = (int) (Math.random() * (yBoxes - 3) + 1);
			if (noBueno[rx][ry] == false) {
				turns[turnCount] = new Point(rx, ry);
				maze[rx][ry] = true;
				noBueno[rx + 1][ry] = true;
				noBueno[rx - 1][ry] = true;
				noBueno[rx + 2][ry] = true;
				noBueno[rx - 2][ry] = true;
				noBueno[rx + 1][ry + 1] = true;
				noBueno[rx - 1][ry + 1] = true;
				noBueno[rx + 1][ry - 1] = true;
				noBueno[rx - 1][ry - 1] = true;
				for (int i = 0; i < yBoxes; i++) {
					noBueno[rx][i] = true;
					noBueno[rx + 1][i] = true;
					noBueno[rx - 1][i] = true;
				}
				turnCount++;
			}
		}

		ps = new ArrayList<Point>();

		for (int i = 0; i < turnCount; i++)
			ps.add(turns[i]);

		for (int i = 0; i < turnNum; i++) {
			Point lowX = new Point(100, 100);
			for (int z = 0; z < ps.size(); z++)
				if (ps.get(z).getX() < lowX.getX())
					lowX = ps.get(z);
			ps.remove(lowX);
			turns[i] = lowX;
		}

		ps = new ArrayList<Point>();
		ps.add(new Point(1, hy));
		for (int i = 0; i < turnCount; i++)
			ps.add(turns[i]);
		ps.add(new Point(xBoxes - 2, cy));

		for (int i = 0; i < turnCount + 1; i++) {
			boolean horizontal = Math.random() < 0.5;
			Point start = ps.get(i);
			Point end = ps.get(i + 1);
			if (maze[(int) start.getX() + 1][(int) start.getY()]) {
				horizontal = false;
			} else {
				if (end.getY() > start.getY()) {
					if (maze[(int) start.getX()][(int) start.getY() + 1])
						horizontal = true;
				} else {
					if (maze[(int) start.getX()][(int) start.getY() - 1])
						horizontal = true;
				}
			}
			if (horizontal) {
				for (int x = (int) start.getX(); x <= end.getX(); x++) {
					maze[x][(int) start.getY()] = true;
				}
				if (end.getY() > start.getY()) {
					for (int y = (int) start.getY(); y <= end.getY(); y++)
						maze[(int) end.getX()][y] = true;
				} else {
					for (int y = (int) end.getY(); y <= start.getY(); y++)
						maze[(int) end.getX()][y] = true;
				}
			} else {
				if (end.getY() > start.getY()) {
					for (int y = (int) start.getY(); y <= end.getY(); y++) {
						maze[(int) start.getX()][y] = true;
					}
				} else {
					for (int y = (int) start.getY(); y >= end.getY(); y--) {
						maze[(int) start.getX()][y] = true;
					}
				}
				for (int x = (int) start.getX(); x <= end.getX(); x++) {
					maze[x][(int) end.getY()] = true;
				}
			}

		}

	}

	private void placeBases() {
		for (int i = 1; i < xBoxes - 1; i++)
			if (maze[i][0] == true)
				hBase = new Base(i, 0);
		if (hBase == null)
			for (int i = 1; i < yBoxes - 1; i++)
				if (maze[0][i] == true)
					hBase = new Base(0, i);
		if (hBase == null)
			for (int i = 1; i < xBoxes - 1; i++)
				if (maze[i][yBoxes - 1] == true)
					hBase = new Base(i, yBoxes - 1);
		if (hBase == null)
			for (int i = 1; i < yBoxes - 1; i++)
				if (maze[xBoxes - 1][i] == true)
					hBase = new Base(xBoxes - 1, i);
		for (int i = 1; i < xBoxes - 1; i++)
			if (maze[i][0] == true)
				if (i != hBase.getX() || hBase.getY() != 0)
					cBase = new Base(i, 0);
		if (cBase == null)
			for (int i = 1; i < yBoxes - 1; i++)
				if (maze[0][i] == true)
					if (i != hBase.getY() || hBase.getX() != 0)
						cBase = new Base(0, i);
		if (cBase == null)
			for (int i = 1; i < xBoxes - 1; i++)
				if (maze[i][yBoxes - 1] == true)
					if (i != hBase.getX() || hBase.getY() != yBoxes - 1)
						cBase = new Base(i, yBoxes - 1);
		if (cBase == null)
			for (int i = 1; i < yBoxes - 1; i++)
				if (maze[xBoxes - 1][i] == true)
					if (i != hBase.getY() || hBase.getX() != xBoxes - 1)
						cBase = new Base(xBoxes - 1, i);
	}

	public void tick() {
		for (Blob b : cBlobs) {
			if (b.touchesBase()) {
				cBlobs.remove(b);
				hBase.damage(10);
				return;
			}
			b.tick();
		}
		for (Blob b : hBlobs) {
			if (b.touchesBase()) {
				hBlobs.remove(b);
				cBase.damage(10);
				return;
			}
			b.tick();
		}
		if (ticks % 3 == 0) {
			cBlobs.add(new Blob(200, (int) (Math.random() * 2) + 6, this, true));
		}
		
		
		for (int i = 0; i < ui.getTowers().size(); i++)
			if (ui.getTowers().get(i).inPlay())
				ui.getTowers().get(i).tick();
		for (int i = 0; i < player.getTowers().size(); i++)
			if (player.getTowers().get(i).inPlay())
				player.getTowers().get(i).tick();
		for (int i = 0; i < cPlayer.getTowers().size(); i++)
			if (cPlayer.getTowers().get(i).inPlay())
				cPlayer.getTowers().get(i).tick();
		player.tick();
		
		ticks++;

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
		g.setColor(Color.red);
		hBase.draw(g, sideLength);
		g.setColor(Color.blue);
		cBase.draw(g, sideLength);
		for (Blob b : cBlobs)
			b.draw(g);
		for (Blob b : hBlobs)
			b.draw(g);
		ui.draw(g);
		for (int i = 0; i < ui.getTowers().size(); i++)
			if (ui.getTowers().get(i).inPlay())
				ui.getTowers().get(i).draw(g);
		for (int i = 0; i < player.getTowers().size(); i++)
			if (player.getTowers().get(i).inPlay())
				player.getTowers().get(i).draw(g);
	}

	public Base getHBase() {
		return hBase;
	}

	public Base getCBase() {
		return cBase;
	}

	public boolean[][] getMaze() {
		return maze;
	}

	public int sideLength() {
		return sideLength;
	}

	public Point getBox(Point p) {
		gameScreen.getHeight();
		return p;
	}

	public UI getUI() {
		return ui;
	}

	public boolean canBePlaced(int px, int py) {
		if (px >= xBoxes || px < 0)
			return false;
		if (py >= yBoxes || py < 0)
			return false;
		if (maze[px][py])
			return false;
		for (Tower t : player.getTowers())
			if (px * sideLength == t.getX())
				if (py * sideLength == t.getY())
					return false;
		return true;
	}

}
