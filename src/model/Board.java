package model;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

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
			}
		}
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
