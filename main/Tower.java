package main;

import java.awt.Graphics;

public abstract class Tower{
	
	private int level = 1;
	private int range;
	private int xPos;
	private int yPos;
	
	public int getxPos() {
		return xPos;
	}
	
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
	public int getyPos() {
		return yPos;
	}
	
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public void draw(Graphics g){
		
	}
	public abstract void shoot();
	

}
