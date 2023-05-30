package model;

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

	// Set all shelf cell to empty

	public void initialize() {
	/*	for (int i = 0; i < shelf.length; ++i) {
			for (int j = 0; j < shelf[i].length; ++j) {
				shelf[i][j] = new Tile(ColorTile.PINK);
			}
		}
		shelf[0][0]=new Tile(ColorTile.EMPTY);
		shelf[1][0]=new Tile(ColorTile.EMPTY);*/
		
		ColorTile[] control = new ColorTile[6];
		for (int i = 0; i < shelf.length; ++i) {
			for (int j = 0; j < shelf[i].length; ++j) {
				shelf[i][j] = new Tile(ColorTile.EMPTY);
			}
		}
	}

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

	/*public boolean ControlFreeCells(int columnSelection, int numberOfCards) {

		int notFree = 0;
		boolean control = false;
		for (int i = 0; i < shelf[columnSelection].length; i++) {
			if (shelf[columnSelection][i] == null) {
				notFree = i - 1;
			} else {
				control = true;
				return control;
			}
		}

		if (numberOfCards > (columns - notFree)) {
			control = true;
		}
		return control;
	}*/

	/**
	 * Aggiunge la carta alla colonna
	 * 
	 * @param columnSelection Colonna selezionata

	 * @param Tile            Tipo di carta da inserire
	 */
	public boolean addCard(int columnSelection, Tile card) {
		// System.out.println(card.getClass() + " " + card.getColor() + " " +
		// card.getImg());ù
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
		// TODO Auto-generated method stub
		return rows;
	}

	/*
	 * @Override public int getColumnCount() { // TODO Auto-generated method stub
	 * return columns; }
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
	
	
	public boolean isShelfFull() {
		boolean full = true;
		for(int i = 0; i< rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(this.getValueOfTileAt(i,j).getColor() == ColorTile.EMPTY) {
					full = false;
				}
			}
		}
		return full;
	}

}
