package model;

import javax.swing.table.AbstractTableModel;

import java.util.concurrent.ThreadLocalRandom;

public class Board extends AbstractTableModel {
	private int numOfPlayers;
	private final int rowLen = 9;
	private final int columnLen = 9;
	TileType[][] board = new TileType[rowLen][columnLen];
	int[][] emptyTiles = {
			{1,1},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7},{1,8},{1,9},
			{2,1},{2,2},{2,3},{2,6},{2,7},{2,8},{2,9},
			{3,1},{3,2},{3,3},{3,7},{3,8},{3,9},
			{4,1},{4,2},{4,9},
			{5,1},{5,9},
			{6,1},{6,8},{6,9},
			{7,1},{7,2},{7,3},{7,7},{7,8},{7,9},
			{8,1},{8,2},{8,3},{8,4},{8,7},{8,8},{8,9},
			{9,1},{9,2},{9,3},{9,4},{9,5},{9,6},{9,7},{9,8},{9,9},
	};
	
	
	
	
	public Board(int numOfPlayers) {
		if(numOfPlayers < 2 || numOfPlayers > 4) {
			throw new IllegalArgumentException("Il numero di giocatori deve essere compreso tra 2 e 4.");
		}
		
		this.numOfPlayers = numOfPlayers;
		
		randomFillBoard(this.numOfPlayers);
	}
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 9;
	}
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 9;
	}
	
	public TileType getValueAt(int rowIndex, int columnIndex) {
		return board[rowIndex][columnIndex];
	}
	
	
	//print board on screen
	public void printBoard() {
		System.out.println(board[1][3].toString());
		System.out.println("\n\nBOARD:\n\n");
		for(int i = 0; i < rowLen; i++) {
			for(int j = 0; j < columnLen; j++) {
				if(board[i][j] == TileType.EMPTY) {
					System.out.print("\t");
				}
				else {
					System.out.print(board[i][j].toString() + "\t");
				}
			} 
			System.out.println("\n");
		}
	}
	
	//randomly fills the board, based on the number of players
	public void randomFillBoard(int numOfPlayers) {
		
		//sets the always empty tiles to "EMPTY"
		for(int i = 0; i < 52; i++) {
			board[emptyTiles[i][0]-1][emptyTiles[i][1]-1] = TileType.EMPTY;
		}
		
		
		//adds specific tiles based on the number of players
		int randomNumber;
		switch(numOfPlayers) {
		case 4:
			this.board[0][4] = getRandomTile();
			this.board[1][5] = getRandomTile();
			this.board[3][1] = getRandomTile();
			this.board[4][0] = getRandomTile();
			this.board[4][8] = getRandomTile();
			this.board[5][7] = getRandomTile();
			this.board[7][3] = getRandomTile();
			this.board[8][4] = getRandomTile();
			//no break but it's fine.
			
		case 3:
			this.board[0][3] = getRandomTile();
			this.board[2][2] = getRandomTile();
			this.board[2][6] = getRandomTile();
			this.board[3][8] = getRandomTile();
			this.board[5][0] = getRandomTile();
			this.board[6][2] = getRandomTile();
			this.board[6][6] = getRandomTile();
			this.board[8][5] = getRandomTile();
			break;
		}
		
		
		for(int i = 0; i < rowLen; i++) {
			for(int j = 0; j < columnLen; j++) {
				if(board[i][j] != TileType.EMPTY) {
					board[i][j] = getRandomTile();
				}
			}
		}
	}
	
	
	//returns random TileType (not empty)
	public TileType getRandomTile() {
		int randomNumber;
		randomNumber = ThreadLocalRandom.current().nextInt(0, 5 + 1);
		return TileType.values()[randomNumber];
	}
	

}
