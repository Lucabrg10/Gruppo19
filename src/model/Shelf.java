package model;

public class Shelf {
	private final int columns = 5;
	private final int rows = 6;
	private ColorTile[][] shelf = new ColorTile[columns][rows];
	private int numOfPlayers;

	public Shelf(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers; // player number
		this.shelf = new ColorTile[columns][rows];
	}

	public ColorTile[][] getShelf() {
		return shelf;
	}

	public void setShelf(ColorTile[][] shelf) {
		this.shelf = shelf;
	}

	// Set all shelf cell to null
	public void initialize() {
		ColorTile[] control = new ColorTile[6];
		for (int i = 0; i < shelf.length; ++i) {
			for (int j = 0; j < shelf[i].length; ++j) {
				shelf[i][j] = ColorTile.EMPTY;
				for (int c = 0; c < 5; c++) {
					control = ColorTile.values();
					shelf[i][j] = control[c];
				}
			}

		}

	}

	public void print() {
		System.out.println("Shelf Print");
		for (int i = 0; i < shelf.length; ++i) {
			for (int j = 0; j < shelf[i].length; ++j) {
				System.out.print(" | " + shelf[i][j] + " | ");
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
	public boolean ControlFreeCells(int columnSelection, int numberOfCards) {
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
	}

	/**
	 * Aggiunge la carta alla colonna
	 * 
	 * @param columnSelection Colonna selezionata
	 * @param TileType        Tipo di carta da inserire
	 */
	public void addCard(int columnSelection, ColorTile card) {
		for (int i = 0; i < shelf[columnSelection].length; i++) {

			if (shelf[columnSelection][i] == null) {
				shelf[columnSelection][i] = card;
				break;
			}
		}
	}

	public int getNumOfPlayers() {
		return numOfPlayers;
	}

}
