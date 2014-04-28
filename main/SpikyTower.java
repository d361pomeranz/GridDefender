package main;

import java.awt.Color;
import java.awt.Graphics;

public class SpikyTower extends Tower{
	SpikyTower(Player p, int ti) {
		super(p, ti);
	}
	public void draw(Graphics g) {
		int xStart = getX();
		int yStart = getY();
		int sideLength = getPlayer().getGrid().sideLength();
		g.setColor(Color.black);
		g.fillOval(xStart+10, yStart+10, sideLength-20, sideLength-20);
		g.setColor(Color.pink);
		g.fillOval(xStart+15, yStart+15, sideLength-30, sideLength-30);
		g.setColor(Color.yellow);
		for (int i = 0; i < 10; i+=2) {
			
		}
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

}
