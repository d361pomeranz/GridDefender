package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class UI {
	
	private Grid grid;
	private ArrayList<Tower> towers;
	private boolean open = false;
	
	public UI(Player player){
		grid = player.getGrid();
		towers = player.getTowers();
	}
	
	public void draw(Graphics g){
		if (open){
			
		} else {
			
		}
		
	}

}
