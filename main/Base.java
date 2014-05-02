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
		health = 1000;
	}
	
	public void damage(int n){
		health -= n;
	}
	
	public void draw(Graphics g, int sideLength){
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
