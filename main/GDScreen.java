package main;

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
	
	public abstract void draw();
	public abstract void tick();


}
