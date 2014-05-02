package main;

import java.awt.Point;
import java.util.ArrayList;

public class ComputerPlayer extends Player{
	private int[][] bueno;
	public ComputerPlayer(Grid grid, ArrayList<Blob> e, ArrayList<Blob> p) {
		super(grid, e, p);
		bueno=new int[getGrid().getXboxes()][getGrid().getYBoxes()];
		for(int x=0;x<getGrid().getXboxes();x++){
			for(int y=0;y<getGrid().getYBoxes();y++){
				bueno[x][y]=0;
				if(x>0&&getGrid().getMaze()[x-1][y]){
					bueno[x][y]++;
				}
				if(x<getGrid().getXboxes()&&getGrid().getMaze()[x+1][y]){
					bueno[x][y]++;
				}
				if(y>0&&getGrid().getMaze()[x][y-1]){
					bueno[x][y]++;
				}
				if(y<getGrid().getYBoxes()&&getGrid().getMaze()[x][y+1]){
					bueno[x][y]++;
				}
			}
		}
	}
	public void tick(){
		
		super.tick();
	}
	
	
	
}
