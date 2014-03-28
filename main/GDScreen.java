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
		System.out.println("Mother Fucker");
		return width;
	}


	public int getHeight() {
		System.out.println("Mother Fucker");
		return height;
	}
	
	public GDFrame getFrame(){
		System.out.println("Mother Fucker");
		return frame;
	}
	
	public abstract void draw();
	public abstract void tick();


}
