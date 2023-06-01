package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class Shelf extends AbstractTableModel {
	private final int rows = 6;
	private final int columns = 5;
	private Tile[][] shelf = new Tile[rows][columns];

	private final String[] columnNames = { "0", "1", "2", "3", "4" };

	public Shelf() {
		// player number
		this.shelf = new Tile[rows][columns];
		initialize();
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public Tile[][] getShelf() {
		return shelf;
	}

	public void setShelf(Tile[][] shelf) {
		this.shelf = shelf;
	}

	/**
	 * Set all shelf cell to empty
	 */
	public void initialize() {
		for (int i = 0; i < shelf.length; ++i) {
			for (int j = 0; j < shelf[i].length; ++j) {
				shelf[i][j] = new Tile(ColorTile.EMPTY);
			}
		}
	}

	/**
	 * Print all shelf
	 */
	public void print() {
		System.out.println("Shelf Print");
		for (int i = 0; i < shelf.length; ++i) {
			for (int j = 0; j < shelf[i].length; ++j) {
				System.out.print(" | " + shelf[i][j].getColor() + " | ");
			}
			System.out.println("\n");
		}
	}

	/**
	 * Controlla se in una colonna è possibile inserire un certo numero di carte
	 * 
	 * @param columnSelection colonna da controllare se è libera
	 * @param numberOfCards   numero di carte che devo inserire
	 * @return true Non si può inserire
	 * @return false Si possono inserire
	 */

	/**
	 * Add the card in the column setted
	 * 
	 * @param columnSelection Column selected
	 * @param Tile            Type of Tile to insert
	 */
	public boolean addCard(int columnSelection, Tile card) {
		// System.out.println(card.getClass() + " " + card.getColor() + " " +
		// card.getImg());
		boolean aggiunto = false;
		int i;
		for (i = 5; i >= 0; i--) {

			if (shelf[i][columnSelection].getColor() == ColorTile.EMPTY) {
				shelf[i][columnSelection] = card;
				aggiunto = true;
				break;
			}
		}

		// print();
		fireTableDataChanged();
		return aggiunto;
	}

	@Override
	public int getRowCount() {

		return rows;
	}

	/*
	 * @Override public int getColumnCount() { return columns; }
	 */

	@Override
	public ImageIcon getValueAt(int rowIndex, int columnIndex) {
		if (shelf[rowIndex][columnIndex] != null) {
			return shelf[rowIndex][columnIndex].getImg();
		}
		return null;

	}

	public Tile getValueOfTileAt(int rowIndex, int columnIndex) {
		return shelf[rowIndex][columnIndex];
	}

	@Override
	public Class getColumnClass(int col) {
		return ImageIcon.class;
	}

	/**
	 * Used to check if the shelf is full
	 * 
	 * @return true if the row is full
	 */
	public boolean isShelfFull() {
		boolean full = true;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (this.getValueOfTileAt(i, j).getColor() == ColorTile.EMPTY) {
					full = false;
				}
			}
		}
		return full;
	}

	/**
	 * Used to check if a Row is full
	 * 
	 * @param r indicate the row to control
	 * @return true if the row is full
	 */
	public boolean isRowFull(int r) {
		for (int i = 0; i < columns; i++) {
			if (shelf[r][i].getColor() == ColorTile.EMPTY) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Used to check if a Column is full
	 * 
	 * @param c indicate the column to control
	 * @return true if the column is full
	 */
	public boolean isColumnFull(int c) {
		for (int i = 0; i < rows; i++) {
			if (shelf[i][c].getColor() == ColorTile.EMPTY) {
				return false;
			}
		}
		return true;
	}

	public int countPointsOfAlignedTiles() {
		boolean[][] visited = new boolean[rows][columns];
		int points = 0;

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				if (!visited[row][col] && this.shelf[row][col].getColor() != ColorTile.EMPTY) {
					Tile tile = this.shelf[row][col];
					List<Tile> group = new ArrayList<>();
					findAdjacentTiles(this.shelf, visited, row, col, tile, group);

					switch (group.size()) {
					case 3: {
						points += 2;
					}
						break;
					case 4: {
						points += 3;
					}
						break;
					case 5: {
						points += 5;
					}
						break;
					case 6: {
						points += 8;
					}
						break;

					default: {
						if (group.size() > 6) {
							points += 8;
						}
					}
						break;
					}
				}
			}
		}
		return points;

	}

	public void findAdjacentTiles(Tile[][] playerShelf, boolean[][] visited, int row, int col, Tile tile,
			List<Tile> group) {
		if (row < 0 || row >= rows || col < 0 || col >= columns || visited[row][col]
				|| shelf[row][col].getColor() != tile.getColor()) {
			return;
		}

		visited[row][col] = true;
		group.add(shelf[row][col]);

		// Ricerca ricorsiva nelle quattro direzioni adiacenti
		findAdjacentTiles(playerShelf, visited, row - 1, col, tile, group); // Sopra
		findAdjacentTiles(playerShelf, visited, row + 1, col, tile, group); // Sotto
		findAdjacentTiles(playerShelf, visited, row, col - 1, tile, group); // Sinistra
		findAdjacentTiles(playerShelf, visited, row, col + 1, tile, group); // Destra
	}

}
