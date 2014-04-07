package main;
import java.util.ArrayList;

public class Player {
	private ArrayList<Tower> towers;
	private Grid g;
	private ArrayList<Blob> enemyBlobs;
	private ArrayList<Blob> playerBlobs;
	
	public Player(Grid grid, ArrayList<Blob> e, ArrayList<Blob> p){
		g = grid;
		enemyBlobs = e;
		playerBlobs = p;
	}
	
	public void addTower(Tower t){
		towers.add(t);
	}

}
