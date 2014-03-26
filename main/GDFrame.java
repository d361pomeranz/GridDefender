package main;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GDFrame extends Frame implements Runnable {

	private static final long serialVersionUID = 1L;
	private Thread thread;
	private double screenMultiplier = .8;
	private int height = (int) (screenMultiplier * Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	private int width = (int) (screenMultiplier * Toolkit.getDefaultToolkit().getScreenSize().getWidth());
	private GDScreen currentScreen;

	public GDFrame() {
		setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - width) / 2),
					(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - height) / 2));
		setSize(new Dimension(width, height));
		setResizable(false);
		setTitle("Grid Defense");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		thread = new Thread(this);
		setVisible(true);
		createBufferStrategy(3);
		currentScreen = new StartScreen(this);
		thread.start();
		addMouseListener(currentScreen);
	}

	public void run() {
		while (true) {
			tick();
			draw();
			getBufferStrategy().show();
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
