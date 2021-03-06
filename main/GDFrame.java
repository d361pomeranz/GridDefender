package main;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

public class GDFrame extends Frame implements Runnable {

	private static final long serialVersionUID = 1L;

	private double screenMultiplier = .8;
	private int width = 1440;
	private int height = width * 9 / 16;
	private GDScreen currentScreen;
	private Thread thread;
	private int fps = 60;
	private long lastTime = System.currentTimeMillis();

	public GDFrame() {
		setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - width) / 2),
					(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - height) / 2));
		setSize(new Dimension(width + 1, height + 1));
		setResizable(false);
		setUndecorated(true);
		thread = new Thread(this);
		setVisible(true);
		createBufferStrategy(3);
		currentScreen = new StartScreen(this);
		thread.start();
		addMouseListener(currentScreen);
	}

	public void run() {
		while (true) {
			long time = System.currentTimeMillis();
			if ((time - lastTime) * fps > 1000) {
				lastTime = time;
				tick();
				draw();
				getBufferStrategy().show();
			}
		}
	}

	private void tick() {
		currentScreen.tick();
	}

	private void draw() {
		currentScreen.draw();
	}
	
	public void switchScreen(GDScreen screen){
		removeMouseListener(currentScreen);
		currentScreen = screen;
		addMouseListener(currentScreen);
	}

	public static void main(String[] args) {
		new GDFrame();
	}

}
