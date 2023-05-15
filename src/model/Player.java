package model;
import java.util.Set;
import java.util.HashSet;

public class Player {
	private int playerId;
	private String playerName;
	private int points;
	
	private int idcount = 1;
	private static Set<String> existingNames = new HashSet<String>();
	
	
	public Player(String playerName) {
		if(playerName == "") {
			throw new IllegalArgumentException("Il nome del giocatore non può essere vuoto.");
		}
		if(existingNames.contains(playerName)) {
			throw new IllegalArgumentException("Il nome '"+playerName+"' \u00E8 già in uso da un altro giocatore.");
		}
		
		existingNames.add(playerName);
		this.playerName = playerName;
			
		this.playerId = idcount;
		idcount++;
		
		this.points = 0;
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


}
