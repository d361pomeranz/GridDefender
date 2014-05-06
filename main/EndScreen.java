package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;


public class EndScreen extends GDScreen{
	private String name;
	public EndScreen(GDFrame frame, String s) {
		super(frame);
		name=s;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		Graphics g = getFrame().getBufferStrategy().getDrawGraphics();
		g.setColor(Color.gray);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawString("This nigga "+name+" just won yo", 100, 100);
		
		
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
