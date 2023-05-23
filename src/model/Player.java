package model;
import java.util.HashMap;
import java.util.Scanner;

public class Player {
	
	
	private final int idPlayer;
	private String playerName;
	private Shelf playerShelf;
	private final int[][]Direction= {
			{1,0},
			{-1,0},
			{0,1},
			{0,-1}
	};
	public Player(String name, int count){
		this.idPlayer=count;
		
		this.playerName=name;
		
		playerShelf= new Shelf(count);  
		
		
	}
	
	public void addCard(int columnSelection, ColorTile card) {
		
		playerShelf.addCard(columnSelection, card);
		
	}
	
	public int calculateScore() {
		
		
		
		return 0;
	}
	
	public void nearTiles(ColorTile [][]matrix, int i, int j, ColorTile card, HashMap<Integer, Integer> coordinate) {
		
		if(i<0 || j<0 || i>=matrix.length || j>=matrix[0].length) {
			return;
		}
		if(!matrix[i][j].equals(card)) {
			return;
		}
		
	}
	
}
