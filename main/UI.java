package main;

import java.util.ArrayList;

public class UI {
	
	private Grid grid;
	private ArrayList<Tower> towers;
	
	public UI(Player player){
		grid = player.getGrid();
		towers = player.getTowers();
	}

}
