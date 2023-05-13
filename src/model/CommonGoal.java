package model;

import java.awt.PageAttributes.ColorType;
import java.util.ArrayList;

public class CommonGoal extends Goal {
	private final int columns = 5;
	private final int rows = 6;
	private int numOfPlayer;
	private int goalNumber;
	private ColorTile[][] playerShelf = new ColorTile[columns][rows];

	public CommonGoal(int numOfPlayer) {
		this.numOfPlayer = numOfPlayer;
	}

	public boolean controlGoal1(Shelf playerShelf) {
		int square = 0;
		ColorTile prevcard = null;
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			for (int i = 1; i < playerShelf.getShelf().length; ++i) {
				for (int j = 1; j < playerShelf.getShelf()[i].length; ++j) {
					if (playerShelf.getShelf()[i][j].equals(cards) && playerShelf.getShelf()[i][j - 1].equals(cards)
							&& playerShelf.getShelf()[i - 1][j].equals(cards)
							&& playerShelf.getShelf()[i - 1][j - 1].equals(cards)) {
						if (i < 3 && j < 4) {
							if (!playerShelf.getShelf()[i + 1][j].equals(cards)
									&& !playerShelf.getShelf()[i][j + 1].equals(cards)) {
								square++;
							}
						} else {
							if (!playerShelf.getShelf()[i - 2][j].equals(cards)
									&& !playerShelf.getShelf()[i][j - 2].equals(cards)) {
								square++;
							}
						}
						if (square == 2 && cards == prevcard) {
							return true;
						}
						prevcard = cards;
					}
				}
			}
		}
		return false;
	}

	public boolean controlGoal2(Shelf playerShelf) {
		int column = 0;
		ArrayList<ColorTile> controlColumn = new ArrayList<ColorTile>();
		for (int r = 0; r < 6; r++) {
			controlColumn.add(r, ColorTile.EMPTY);
		}
		for (int i = 0; i < playerShelf.getShelf()[i].length; ++i) {
			for (int j = 0; j < playerShelf.getShelf().length; ++j) {
				for (int x = 0; x < 5; x++) {
					if (playerShelf.getShelf()[j][i] != controlColumn.get(x)) {
						controlColumn.set(j, playerShelf.getShelf()[j][i]);
						if (controlColumn.get(controlColumn.size() - 1) != ColorTile.EMPTY) {
							column++;
							if (column == 2) {
								return true;
							}
							for (int r = 0; r < 6; r++) {
								controlColumn.set(r, ColorTile.EMPTY);
							}
							break;
						}

					}
				}
			}
		}
		return false;
	}

	public boolean controlGoal4(Shelf playerShelf) {
		int couple=0;
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			for (int i = 0; i < columns; ++i) {
				for (int j = 0; j < rows; ++j) {
					if (i<columns-1 && j<rows-1) {
						if (playerShelf.getShelf()[i][j] == cards && (playerShelf.getShelf()[i+1][j] == cards ^ playerShelf.getShelf()[i][j+1] == cards)) {
							couple++;
						}
					}
					else {
						if (playerShelf.getShelf()[columns-1][rows-1] == cards && (playerShelf.getShelf()[columns-2][rows-1] == cards ^ playerShelf.getShelf()[columns-1][rows-2] == cards)) {
							couple++;
						}
					}
					
					if (couple == 6) {
						return true;
					}
					
			}
			}
		}
		return false;
	}

	public boolean controlGoal6(Shelf playerShelf) {
		int row = 0;
		ArrayList<ColorTile> controlRow = new ArrayList<ColorTile>();
		for (int r = 0; r < 5; r++) {
			controlRow.add(r, ColorTile.EMPTY);
		}
		for (int j = 0; j < rows; ++j) {

			for (int i = 0; i < columns; ++i) {

				for (int x = 0; x < 5; x++) {
					if ((controlRow.get(x) != playerShelf.getShelf()[i][j])) {
						controlRow.set(j, playerShelf.getShelf()[i][j]);
						if (controlRow.get(controlRow.size() - 1) != ColorTile.EMPTY) {
							row++;
							if (row == 2) {
								return true;
							}
							for (int r = 0; r < 5; r++) {
								controlRow.set(r, ColorTile.EMPTY);
							}
							break;
						}

					}
				}
			}
		}
		return false;
	}

	public boolean controlGoal8(Shelf playerShelf) {
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			if (playerShelf.getShelf()[0][0] == cards && playerShelf.getShelf()[0][5] == cards
					&& playerShelf.getShelf()[4][0] == cards && playerShelf.getShelf()[4][5] == cards) {
				return true;
			}
		}
		return false;
	}

	public boolean controlGoal9(Shelf playerShelf) {
		int cells = 0;
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			for (int i = 0; i < playerShelf.getShelf().length; ++i) {
				for (int j = 0; j < playerShelf.getShelf()[i].length; ++j) {
					if (playerShelf.getShelf()[i][j] == cards) {
						cells++;
						if (cells == 8) {
							return true;
						}
					}
				}
			}
			cells = 0;
		}
		return false;
	}

}
