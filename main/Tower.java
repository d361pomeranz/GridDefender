package main;

import java.awt.Graphics;

public abstract class Tower{
	
	private int level = 1;
	private int range;
	private int xPos;
	private int yPos;
	private Grid grid;
	Tower(int x, int y, Grid g){
		xPos=x;
		yPos=y;
		grid=g;
	}
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
	
	public Grid getGrid(){
		return grid;
	}
	
	public abstract void draw(Graphics g);
	public abstract void shoot();
	

}
