package model;
import java.util.Scanner;

public class Player {
	
	
	private final int idPlayer;
	private String playerName;
	private Shelf playerShelf;
	
	
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
	
}
