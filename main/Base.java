package main;

import java.awt.Color;
import java.awt.Graphics;

public class Base {
	
	private int health;
	private int x;
	private int y;
	
	public Base(int x, int y){
		this.x = x;
		this.y = y;
		health = 100;
	}
	
	public void draw(Graphics g, int sideLength){
		g.setColor(Color.red);
		g.fillRect(sideLength * x + 8, sideLength * y + 8, sideLength - 14, sideLength - 14);
		g.setColor(Color.black);
		g.drawString(Integer.toString(health), sideLength * x + sideLength/2, sideLength * y + sideLength/2);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

}
