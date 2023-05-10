package model;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
<<<<<<< HEAD
import javax.swing.table.DefaultTableCellRenderer;
=======
>>>>>>> master

import java.util.concurrent.ThreadLocalRandom;

public class Board extends AbstractTableModel {
	private int numOfPlayers;
	private final int rowLen = 9;
	private final int columnLen = 9;

	Tile[][] board = new Tile[rowLen][columnLen];
	Bag bag;


	public Board(int numOfPlayers) {
		if (numOfPlayers < 2 || numOfPlayers > 4) {
			throw new IllegalArgumentException("Il numero di giocatori deve essere compreso tra 2 e 4.");
		}

		this.numOfPlayers = numOfPlayers;

		this.bag = new Bag();
		// TO ADD: CHANGE BOARD LAYOUT BASED ON NUMBER OF PLAYERS
		fillBoard();
		printBoard();
		//randomFillBoard();
	}
	@Override
	public Class getColumnClass(int col) {
		return ImageIcon.class;
	}
	
	public int getRowCount() {
		// TODO Auto-generated method stub

		return rowLen;
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub

		return columnLen;
	}

	public ImageIcon getValueAt(int rowIndex, int columnIndex) {
		return board[rowIndex][columnIndex].getImg();

	}

	// print board on screen
	public void printBoard() {
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < columnLen; j++) {
				System.out.print(board[i][j].getImg() + "\t");
			}
			System.out.println("\n");
		}
	}

	// fill the board from the bag that is random //LB
	public void fillBoard() {
		int c = 0;
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < columnLen; j++) {
				this.board[i][j] = bag.tiles.get(c);
				c++;
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
	

	// randomly fills the board
/*	public void randomFillBoard() {
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < columnLen; j++) {

				int randomNumber = ThreadLocalRandom.current().nextInt(0, 6 + 1);
				this.board[i][j] = Tile.values()[randomNumber];

			}
		}
	}
*/
}
