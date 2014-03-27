package main;

import java.awt.Graphics;

public abstract class Thing {
	
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

}
