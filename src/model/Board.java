package model;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.util.concurrent.ThreadLocalRandom;

public class Board extends AbstractTableModel {
	
	private int numOfPlayers;
	private final int rowLen = 9;
	private final int columnLen = 9;
	private final String[] columnNames = { "0", "1", "2", "3", "4", "5", "6", "7", "8" };
	private int c = 0;
	

	Tile[][] board = new Tile[rowLen][columnLen];
	Bag bag;
	
	
	int[][] emptyTiles = { { 1, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 1, 6 }, { 1, 7 }, { 1, 8 }, { 1, 9 },
			{ 2, 1 }, { 2, 2 }, { 2, 3 }, { 2, 6 }, { 2, 7 }, { 2, 8 }, { 2, 9 }, { 3, 1 }, { 3, 2 }, { 3, 3 },
			{ 3, 7 }, { 3, 8 }, { 3, 9 }, { 4, 1 }, { 4, 2 }, { 4, 9 }, { 5, 1 }, { 5, 9 }, { 6, 1 }, { 6, 8 },
			{ 6, 9 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 7 }, { 7, 8 }, { 7, 9 }, { 8, 1 }, { 8, 2 }, { 8, 3 },
			{ 8, 4 }, { 8, 7 }, { 8, 8 }, { 8, 9 }, { 9, 1 }, { 9, 2 }, { 9, 3 }, { 9, 4 }, { 9, 5 }, { 9, 6 },
			{ 9, 7 }, { 9, 8 }, { 9, 9 }, };

	
	
	public Board(int numOfPlayers) {
		if (numOfPlayers < 2 || numOfPlayers > 4) {
			throw new IllegalArgumentException("Il numero di giocatori deve essere compreso tra 2 e 4.");
		}

		this.numOfPlayers = numOfPlayers;

		this.bag = new Bag();
		randomFillBoard(numOfPlayers);
		
		// printBoard();
		// randomFillBoard();
	}

	public int getNumOfPlayers() {
		return numOfPlayers;
	}

	public void setNumOfPlayers(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}

	
	
	

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}
	
	@Override
	public Class getColumnClass(int col) {
		return ImageIcon.class;
	}

	public int getRowCount() {
		return rowLen;
	}

	public int getColumnCount() {
		return columnLen;
	}

	public ImageIcon getValueAt(int rowIndex, int columnIndex) {
		if(board[rowIndex][columnIndex]!=null) {
			return board[rowIndex][columnIndex].getImg();
		}
		return null;

	}

	public Tile getValueOfTileAt(int rowIndex, int columnIndex) {
		return board[rowIndex][columnIndex];
	}



	// print board on screen
	public void printBoard() {
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < columnLen; j++) {
				if(board[i][j]!= null && board[i][j].getColor()!=ColorTile.EMPTY) {
					System.out.print(board[i][j].getColor() + "\t");	
				}
				else {
					System.out.print("\t");
				}
			}
			System.out.println("\n");
		}
	}

	// randomly fills the board, based on the number of players
	public void randomFillBoard(int numOfPlayers) {
		
		//sets every tile to EMPTY
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < columnLen; j++) {
					board[i][j] = new Tile(ColorTile.EMPTY);
					//tile.setImg(null);
			}
		}

		// sets the always empty tiles to null
		for (int i = 0; i < 52; i++) {
			board[emptyTiles[i][0] - 1][emptyTiles[i][1] - 1] = null;
		}

		// adds specific tiles based on the number of players
		switch (numOfPlayers) {
		case 4:
			this.board[0][4] = getRandomTile();
			this.board[1][5] = getRandomTile();
			this.board[3][1] = getRandomTile();
			this.board[4][0] = getRandomTile();
			this.board[4][8] = getRandomTile();
			this.board[5][7] = getRandomTile();
			this.board[7][3] = getRandomTile();
			this.board[8][4] = getRandomTile();
			// no break but it's fine.

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
		
		//fills EMPTY tiles
		refillBoard();
		
	}
	
	public void refillBoard() {
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < columnLen; j++) {
				if (board[i][j]!=null && board[i][j].getColor() == ColorTile.EMPTY) {
					this.board[i][j] = getRandomTile();
				}
			}
		}
		fireTableDataChanged();
	}

	// returns random TileType (not empty)
	public Tile getRandomTile() {
		Tile tile = bag.tiles.get(c);
		c++;
		return tile;
	}

	
	//remove tile with the specific tile
	public void removeTile(Tile tile1) {
		
		for (Tile[]tiles : board) {
			for (Tile tile : tiles) {
				if(tile==tile1) {
					tile.setColor(ColorTile.EMPTY);
					tile.setImg(null);
				}
			}
			
			
		}
	//	this.printBoard();
		
		fireTableDataChanged();
	}
	
	// sets the given tile to EMPTY
	public void removeTile(int rowIndex, int columnIndex) {
		this.board[rowIndex][columnIndex] = new Tile(ColorTile.EMPTY);
	}

	// returns true if the tile is EMPTY
	public boolean isTileEmpty(int rowIndex, int columnIndex) {
		if (this.board[rowIndex][columnIndex] == null) {
			return true;
		}
		else if((this.board[rowIndex][columnIndex]).getColor() == ColorTile.EMPTY) {
			return true;
		}
		return false;
	}
	
	//checks if the tile is available (has at least one free side and a max of 3 free sides)
	public boolean isTileAvailable (int rowIndex, int columnIndex) {
		///aggiungere controllo su 4 lati: se tutti empty, la carta non è prendibilide: return false (all'inizio)
		
		//freeSides conta il numero di lati liberi (deve essere >0 e <4)
		int freeSides = 0;
		
		if(this.board[rowIndex][columnIndex]!=null && this.board[rowIndex][columnIndex].getColor()!=ColorTile.EMPTY) {
			//System.out.println(this.board[rowIndex][columnIndex].getColor());
			//check upper tile
			if(rowIndex > 0 && isTileEmpty(rowIndex-1, columnIndex)) {
				freeSides++;
			}
			
			//check lower tile
			if(rowIndex < rowLen-1 && isTileEmpty(rowIndex+1, columnIndex)) {
				freeSides++;
			}
			
			//check left tile
			if(columnIndex > 0 && isTileEmpty(rowIndex, columnIndex-1)) {
				freeSides++;
			}
			
			//check right tile
			if(columnIndex < columnLen-1 && isTileEmpty(rowIndex, columnIndex+1)) {
				freeSides++;
			}
		}
		//System.out.println("Lati liberi: "+freeSides+" (at "+rowIndex+" - "+columnIndex+")");
		
		if(freeSides > 0 && freeSides < 4) {
			return true;
		}
		return false;
	}
	
	public boolean checkForRefill() {
		for(int i = 0; i < rowLen; i++) {
			for(int j = 0; j < columnLen; j++) {
				if(isTileAvailable(i, j)) {
					
					//if it gets here it means that at least one tile is available
					//there is no need to refill.
					return false;
				}
			}
		}
		
		//if no tile is available, the refill is needed (return true)
		return true;
	}
	
}
