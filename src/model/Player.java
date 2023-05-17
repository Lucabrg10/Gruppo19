package model;

import java.util.ArrayList;

public class Player {

	private Shelf shelf;
	private String userName;
	
	public Player(String username) {
		this.setUserName(username);
		this.setShelf(new Shelf());
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}
	
	public void addTiles(ArrayList<Tile>tiles) {
		for (Tile tile : tiles) {
			this.shelf.addCard(0, tile);
		}
	}
	
}
