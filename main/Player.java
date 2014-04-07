package main;
import java.util.ArrayList;

public class Player {
	private ArrayList<Tower> towers=new ArrayList<Tower>();
	private Grid g;
	private ArrayList<Blob> enemyBlobs;
	private ArrayList<Blob> playerBlobs;
	private UI ui;
	
	public Player(Grid grid, ArrayList<Blob> e, ArrayList<Blob> p){
		g = grid;
		enemyBlobs = e;
		playerBlobs = p;
		ui = new UI(this);
	}
	
	public void addTower(Tower t){
		towers.add(t);
	}
	
	public ArrayList<Blob> getBlobs(){
		return enemyBlobs;
	}
	public ArrayList<Tower> getTowers(){
		return towers;
	}
	
	public Grid getGrid(){
		return g;
	}
	
	public UI getUI(){
		return ui;
	}
	
	

}
