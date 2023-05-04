package model;

public class Shelf {
	private final int columns=5;
	private final int rows=6;
	private int player;
	private int [][] shelf= new int[rows][columns];
	
	
	public Shelf(int player) {
		//this.player=Player.id; //player number
		
	}
	
	//Set all shelf cell to null
	public void reset() {
		for (int i = 0; i < shelf.length; ++i) {
		      for(int j = 0; j < shelf[i].length; ++j) {
		        shelf[rows][columns]=0;
		      }
		    }
	}
	
	public void print() {
		for (int i = 0; i < shelf.length; ++i) {
		      for(int j = 0; j < shelf[i].length; ++j) {
		        System.out.println(shelf[rows][columns]);
		      }
		    }
	}
}
