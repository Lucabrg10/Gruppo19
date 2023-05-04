package model;

import javax.swing.table.AbstractTableModel;
import java.util.concurrent.ThreadLocalRandom;

public class Board extends AbstractTableModel {
	private int numOfPlayers;
	private final int rowLen = 9;
	private final int columnLen = 9;
	Tile[][] board = new Tile[rowLen][columnLen];
	
	public Board(int numOfPlayers) {
		if(numOfPlayers < 2 || numOfPlayers > 4) {
			throw new IllegalArgumentException("Il numero di giocatori deve essere compreso tra 2 e 4.");
		}
		
		this.numOfPlayers = numOfPlayers;
		
		//TO ADD: CHANGE BOARD LAYOUT BASED ON NUMBER OF PLAYERS 
		
		randomFillBoard();
	}
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Tile getValueAt(int rowIndex, int columnIndex) {
		return board[rowIndex][columnIndex];
	}
	
	
	//print board on screen
	public void printBoard() {
		for(int i = 0; i < rowLen; i++) {
			for(int j = 0; j < columnLen; j++) {
				System.out.print(board[i][j].toString() + "\t");
			}
			System.out.println("\n");
		}
	}
	
	//randomly fills the board
	public void randomFillBoard() {
		for(int i = 0; i < rowLen; i++) {
			for(int j = 0; j < columnLen; j++) {
				
				int randomNumber = ThreadLocalRandom.current().nextInt(0, 6 + 1);
				this.board[i][j] = Tile.values()[randomNumber];
				
			}
		}
	}

}
