package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Grid {

	private int xBoxes = 32;
	private int yBoxes = xBoxes / 16 * 9;
	private int sideLength;
	private GameScreen gameScreen;
	private boolean[][] maze;
	private Base hBase;
	private Base cBase;
	private ArrayList<Blob> cBlobs = new ArrayList<Blob>();
	private ArrayList<Blob> hBlobs = new ArrayList<Blob>();
	private boolean worked=false;

	public Grid(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		sideLength = gameScreen.getWidth() / xBoxes;
//		while(!worked){
//			generateRandomMaze();
//			System.out.println("test");
//		}
		dansGenerateRandomMaze();
		placeBases();
		hBlobs.add(new Blob(100, 3, this, false));
		cBlobs.add(new Blob(100, 3, this, true));
	}

	private void generateSimpleMaze() {
		maze = new boolean[xBoxes][yBoxes];
		for (int x = 0; x < xBoxes; x++)
			for (int y = 0; y < yBoxes; y++)
				maze[x][y] = false;
		for (int i = 0; i < xBoxes; i++) {
			maze[i][8] = true;
		}
	}
	
	private void dansGenerateRandomMaze(){
		int cy = (int) (Math.random() * (yBoxes - 1) + 1);
		int hy = (int) (Math.random() * (yBoxes - 1) + 1);
	
		maze = new boolean[xBoxes][yBoxes];
		boolean noBueno[][] = new boolean[xBoxes][yBoxes];
		for (int x = 0; x < xBoxes; x++) {
			for (int y = 0; y < yBoxes; y++){ 
				maze[x][y] = false;
				noBueno[x][y] = false;
			}
		}
		for (int i = 0; i < 2; i++){
			maze[i][hy] = true;
			maze[xBoxes - 1 - i][cy] = true;
		}
		int turnNum = 3;
		Point[] turns = new Point[turnNum];
		int turnCount = 0;
		while (turnCount < turnNum){
			int rx =  (int) (Math.random() * (xBoxes - 4) + 2);
			int ry =  (int) (Math.random() * (yBoxes - 3) + 1);
			if (noBueno[rx][ry] == false){
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
				for(int i = 0; i < yBoxes; i++)
					noBueno[rx][i] = true;
				turnCount++;
			}	
		}
		
		Point[] turnsF = new Point[turnNum];
		
		for (int i = 0; i < turnNum; i++){
			Point lowX = new Point(100,100);
			for (int z = 0; z < turnNum; z++){
				if (turns[z].getX() < lowX.getX()){
					lowX = new Point(turns[z]);
				}
			}
			turns[i] = new Point(100,100);
			turnsF[i] = lowX;
			System.out.println(turnsF[i].getX());
		}
		

		
		
	}

	private void generateRandomMaze() {
		maze = new boolean[xBoxes][yBoxes];
		boolean[][] offLimits = new boolean[xBoxes][yBoxes];
		for (int x = 0; x < xBoxes; x++) {
			for (int y = 0; y < yBoxes; y++) {
				maze[x][y] = false;
				offLimits[x][y] = false;
			}
		}
		offLimits[0][0] = true;
		offLimits[0][yBoxes - 1] = true;
		offLimits[xBoxes - 1][0] = true;
		offLimits[xBoxes - 1][yBoxes - 1] = true;
		ArrayList<Point> path = new ArrayList<Point>();
		int rand = (int) (4 * Math.random());
		if (rand == 0) {
			rand = (int) ((xBoxes - 2) * Math.random()) + 1;
			maze[rand][0] = true;
			path.add(new Point(rand, 0));
			offLimits[rand][0] = true;
			offLimits[rand - 1][0] = true;
			offLimits[rand + 1][0] = true;
		} else if (rand == 1) {
			rand = (int) ((yBoxes - 2) * Math.random()) + 1;
			maze[xBoxes - 1][rand] = true;
			path.add(new Point(xBoxes - 1, rand));
			offLimits[xBoxes - 1][rand] = true;
			offLimits[xBoxes - 1][rand - 1] = true;
			offLimits[xBoxes - 1][rand + 1] = true;
		} else if (rand == 2) {
			rand = (int) ((xBoxes - 2) * Math.random()) + 1;
			maze[rand][yBoxes - 1] = true;
			path.add(new Point(rand, yBoxes - 1));
			offLimits[rand][yBoxes - 1] = true;
			offLimits[rand - 1][yBoxes - 1] = true;
			offLimits[rand + 1][yBoxes - 1] = true;
		} else {
			rand = (int) ((yBoxes - 2) * Math.random()) + 1;
			maze[0][rand] = true;
			path.add(new Point(0, rand));
			offLimits[0][rand] = true;
			offLimits[0][rand - 1] = true;
			offLimits[0][rand + 1] = true;
		}
		boolean finished = false;
		int length = 1;
		int test=length;
		int otherTest=0;
		int minLength = 30;
		while (!finished) {
			rand = (int) (4 * Math.random());
			if (rand == 0) {
				int x = (int) path.get(path.size() - 1).getX();
				int y = (int) path.get(path.size() - 1).getY() - 1;
				if (((length >= minLength && y >= 0) || (y > 0))
						&& !offLimits[x][y]) {
					maze[x][y] = true;
					path.add(new Point(x, y));
					offLimits[x][y + 1] = true;
					offLimits[x - 1][y + 1] = true;
					offLimits[x + 1][y + 1] = true;
					length++;
				}
			} else if (rand == 1) {
				int x = (int) path.get(path.size() - 1).getX() + 1;
				int y = (int) path.get(path.size() - 1).getY();
				if (((length >= minLength && x <= xBoxes - 1) || (x < xBoxes - 1))
						&& !offLimits[x][y]) {
					maze[x][y] = true;
					path.add(new Point(x, y));
					offLimits[x - 1][y] = true;
					offLimits[x - 1][y - 1] = true;
					offLimits[x - 1][y + 1] = true;
					length++;
				}
			} else if (rand == 2) {
				int x = (int) path.get(path.size() - 1).getX();
				int y = (int) path.get(path.size() - 1).getY() + 1;
				if (((length >= minLength && y <= yBoxes - 1) || (y < yBoxes - 1))
						&& !offLimits[x][y]) {
					maze[x][y] = true;
					path.add(new Point(x, y));
					offLimits[x][y - 1] = true;
					offLimits[x - 1][y - 1] = true;
					offLimits[x + 1][y - 1] = true;
					length++;
				}
			} else {
				int x = (int) path.get(path.size() - 1).getX() - 1;
				int y = (int) path.get(path.size() - 1).getY();
				if (((length >= minLength && x >= 0) || (x > 0))
						&& !offLimits[x][y]) {
					maze[x][y] = true;
					path.add(new Point(x, y));
					offLimits[x + 1][y] = true;
					offLimits[x + 1][y - 1] = true;
					offLimits[x + 1][y + 1] = true;
					length++;
				}
			}
			if(test!=length){
				test=length;
			}else{
				otherTest++;
				if(otherTest>10){
					return;
				}
			}
			
			int x = (int) path.get(path.size() - 1).getX();
			int y = (int) path.get(path.size() - 1).getY();
			if ((x == 0 || x == xBoxes - 1 || y == 0 || y == yBoxes - 1)
					&& path.size() > 1) {
				finished = true;
				
			}
		}
		worked=true;
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
//		for (Blob b : cBlobs) {
//			if (b.touchesBase()) {
//				cBlobs.remove(b);
//				hBase.damage(10);
//				return;
//			}
//			b.tick();
//		}
//		for (Blob b : hBlobs) {
//			if (b.touchesBase()) {
//				hBlobs.remove(b);
//				cBase.damage(10);
//				return;
//			}
//			b.tick();
//		}
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
		for (Blob b : cBlobs)
			b.draw(g);
		for (Blob b : hBlobs)
			b.draw(g);
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

}
