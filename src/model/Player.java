package model;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class Player {
	private int playerId;
	private String playerName;
	private int points = 0;
	private Shelf shelf;
	private PersonalGoal personalGoal;
	


	private int idcount = 1;
	private static Set<String> existingNames = new HashSet<String>();

	public Player(String playerName, PersonalGoal goal) {
		if (playerName == "") {
			throw new IllegalArgumentException("Il nome del giocatore non può essere vuoto.");
		}
		if (existingNames.contains(playerName)) {
			throw new IllegalArgumentException("Il nome '" + playerName + "' \u00E8 già in uso da un altro giocatore.");
		}

		existingNames.add(playerName);
		this.playerName = playerName;
		this.setShelf(new Shelf());
		this.playerId = idcount;
		this.personalGoal = goal;
		idcount++;

		this.points = 0;
	}
	public PersonalGoal getPersonalGoal() {
		return personalGoal;
	}



	public void setPersonalGoal(PersonalGoal personalGoal) {
		this.personalGoal = personalGoal;
	}

	
	
	public void setPoints(int points) {
		this.points = points;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getPoints() {
		return points;
	}

	public boolean addTile(Tile tile, int col) {
		boolean aggiunto = false;

		aggiunto = this.shelf.addCard(col, tile);
		return aggiunto;

	}

}
