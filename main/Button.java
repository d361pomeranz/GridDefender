package main;

import java.awt.*;

public class Button {
	private int width;
	private int height;
	private int x;
	private int y;
	private String text;
	private Color bgColor = Color.black;
	private Color textColor = Color.green;

	Button(int nx, int ny, int w, int h, String t) {
		x = nx;
		y = ny;
		width = w;
		height = h;
		text = t;
	}

	public void tick() {

	}
	
	public void setBGColor(Color c){
		bgColor = c;
	}
	
	public void setTextColor(Color c){
		textColor = c;
	}

	public void draw(Graphics g) {
		g.setColor(bgColor);
		g.fillRect(x, y, width, height);
		g.setColor(textColor);
		g.setFont(new Font("Times New Roman", Font.PLAIN, height - 10));
		g.drawString(text, x + 2, y + height - 5);
	}

	public boolean onButton(Point p) {
		if (p.getX() >= x && p.getX() < x + width)
			if (p.getY() >= y && p.getY() < y + height)
				return true;
		return false;
	}

}
