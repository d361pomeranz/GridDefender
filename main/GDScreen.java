package main;

import java.awt.Graphics;
import java.awt.event.MouseListener;

public abstract class GDScreen implements MouseListener{
	
	private int width;
	private int height;
	private Graphics g;
	private GDFrame frame;
	
	public GDScreen(GDFrame frame){
		this.frame = frame;
		width = frame.getWidth();
		height = frame.getHeight();
		if(frame.getBufferStrategy() == null)
			System.out.println("poop2");
		g = frame.getBufferStrategy().getDrawGraphics();
	}

	public int getWidth() {
		System.out.println("");
		return width;
	}


	public int getHeight() {
		return height;
	}

	public Graphics getG() {
		return g;
	}
	
	public GDFrame getFrame(){
		return frame;
	}
	
	public abstract void draw();
	public abstract void tick();


}
