package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;


public class EndScreen extends GDScreen{
	private String name;
	private Button exitButton;
	public EndScreen(GDFrame frame, String s) {
		super(frame);
		name=s;
		exitButton = new Button(frame.getWidth() - 30, 0, 30, 30, "X");
		exitButton.setBGColor(Color.red);
		exitButton.setTextColor(Color.white);
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
		g.setColor(Color.black);
		g.setFont(new Font("Impact", 64, 32));
		g.drawString("This nigga "+name+" just won yo", 100, 100);
		exitButton.draw(g);
		
		
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
