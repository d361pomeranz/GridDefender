package main;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseListener;

public abstract class GDScreen implements MouseListener{
	
	private int width;
	private int height;
	private GDFrame frame;
	
	public GDScreen(GDFrame frame){
		this.frame = frame;
		width = frame.getWidth();
		height = frame.getHeight();
	}

	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}
	
	public GDFrame getFrame(){
		return frame;
	}
	
	public Point getMouse(){
		Point p = new Point((int) (MouseInfo.getPointerInfo().getLocation().getX()-frame.getX()),
				(int) (MouseInfo.getPointerInfo().getLocation().getY()-frame.getY()));
		return p;
	}
	
	public abstract void draw();
	public abstract void tick();


}
