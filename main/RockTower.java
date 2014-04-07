package main;

import java.awt.Color;
import java.awt.Graphics;

public class RockTower extends Tower{
	private double shootDirection;
	private class Rock extends Bullet{
		Rock(int speed, double direction, int damage){
			super(speed,direction,damage,false,0);
		}
	}
	RockTower(int x, int y, Player p) {
		super(x, y, p);
	}
	public void shoot() {
		
	}
	public void draw(Graphics g) {
		int xStart=getPlayer().getGrid().sideLength()*getX();
		int yStart=getPlayer().getGrid().sideLength()*getX();
		int sideLength=getPlayer().getGrid().sideLength();
		g.setColor(new Color(110,91,16));
		g.fillRect(xStart, yStart, sideLength,sideLength);
		g.setColor(new Color(130,118,109));
		g.fillOval(xStart+15, yStart+15, sideLength-30, sideLength-30);
	}

}
