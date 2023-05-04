package model;

import javax.swing.table.AbstractTableModel;

public class Board extends AbstractTableModel {
	private int numOfPlayers;
	private final int rowLen = 9;
	private final int columnLen = 9;
	Tile [][] board= {};
	public Board(int numOfPlayers) {
		if(numOfPlayers < 2 || numOfPlayers > 4) {
			throw new IllegalArgumentException("Il numero di giocatori deve essere compreso tra 2 e 4.");
		}
		
		this.numOfPlayers = numOfPlayers;
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
		// TODO Auto-generated method stub
		return board[rowIndex][columnIndex];
	}
}
