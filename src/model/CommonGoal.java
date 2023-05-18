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
		boolean bool = true;
		for (int i = 0; i < 6; ++i) {
			for (int j = 0; j < 5; ++j) {

				bool = isColorInColumn(playerShelf.getShelf()[j][i], j, playerShelf.getShelf());
				if (!bool) {
					break;
				}
			}
			if (bool) {
				column++;
			}
		}
		if (column >= 2) {
			return true;
		}
		return false;
	}

	public boolean isColorInColumn(ColorTile color, int r, ColorTile[][] shelf) {
		if (color == ColorTile.EMPTY) {
			return false;
		}
		ColorTile[] array = new ColorTile[6];
		for (int i = 0; i < 6; ++i) {
			array[i] = shelf[r][i];
			System.out.println(array[i]);
		}
		int cont = 0;
		for (int i = 0; i < shelf.length; i++) {
			if (color == array[i]) {
				cont++;
				System.out.println(cont);
			}
		}
		if (cont == 1) {
			return true;
		}
		return false;
	}

	public boolean controlGoal3(Shelf playerShelf) {
		int group = 0;
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}

			for (int i = 0; i < columns; ++i) {
				if (((playerShelf.getShelf()[i][0].equals(cards) && playerShelf.getShelf()[i][1].equals(cards)
						&& playerShelf.getShelf()[i][2].equals(cards) && playerShelf.getShelf()[i][3].equals(cards))
						&& !(playerShelf.getShelf()[i + 1][0].equals(cards)
								&& playerShelf.getShelf()[i + 1][1].equals(cards)
								&& playerShelf.getShelf()[i + 1][2].equals(cards)
								&& playerShelf.getShelf()[i + 1][3].equals(cards)))
						|| ((playerShelf.getShelf()[i][1].equals(cards) && playerShelf.getShelf()[i][2].equals(cards)
								&& playerShelf.getShelf()[i][3].equals(cards)
								&& playerShelf.getShelf()[i][4].equals(cards))
								&& !(playerShelf.getShelf()[i + 1][1].equals(cards)
										&& playerShelf.getShelf()[i + 1][2].equals(cards)
										&& playerShelf.getShelf()[i + 1][3].equals(cards)
										&& playerShelf.getShelf()[i + 1][4].equals(cards)))
						|| ((playerShelf.getShelf()[i][2].equals(cards) && playerShelf.getShelf()[i][3].equals(cards)
								&& playerShelf.getShelf()[i][4].equals(cards)
								&& playerShelf.getShelf()[i][5].equals(cards))
								&& !(playerShelf.getShelf()[i + 1][2].equals(cards)
										&& playerShelf.getShelf()[i + 1][3].equals(cards)
										&& playerShelf.getShelf()[i + 1][4].equals(cards)
										&& playerShelf.getShelf()[i + 1][5].equals(cards)))) {
					group++;
				}
			}

			for (int j = 0; j < rows; ++j) {
				if (((playerShelf.getShelf()[0][j].equals(cards) && playerShelf.getShelf()[1][j].equals(cards)
						&& playerShelf.getShelf()[2][j].equals(cards) && playerShelf.getShelf()[3][j].equals(cards))
						&& !(playerShelf.getShelf()[0][j + 1].equals(cards)
								&& playerShelf.getShelf()[1][j + 1].equals(cards)
								&& playerShelf.getShelf()[2][j + 1].equals(cards)
								&& playerShelf.getShelf()[3][j + 1].equals(cards)))
						|| ((playerShelf.getShelf()[1][j].equals(cards) && playerShelf.getShelf()[2][j].equals(cards)
								&& playerShelf.getShelf()[3][j].equals(cards)
								&& playerShelf.getShelf()[4][j].equals(cards))
								&& !(playerShelf.getShelf()[1][j + 1].equals(cards)
										&& playerShelf.getShelf()[2][j + 1].equals(cards)
										&& playerShelf.getShelf()[3][j + 1].equals(cards)
										&& playerShelf.getShelf()[4][j + 1].equals(cards)))) {
					group++;
				}
			}
		}
		if (group >= 4) {
			return true;
		}
		return false;
	}

	public boolean controlGoal4(Shelf playerShelf) {
		int couple = 0;
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			for (int i = 0; i < columns; ++i) {
				for (int j = 0; j < rows; ++j) {
					if (i < columns - 1 && j < rows - 1) {
						if (playerShelf.getShelf()[i][j] == cards && (playerShelf.getShelf()[i + 1][j] == cards
								^ playerShelf.getShelf()[i][j + 1] == cards)) {
							couple++;
						}
					} else {
						if (playerShelf.getShelf()[columns - 1][rows - 1] == cards
								&& (playerShelf.getShelf()[columns - 2][rows - 1] == cards
										^ playerShelf.getShelf()[columns - 1][rows - 2] == cards)) {
							couple++;
						}
					}

					if (couple >= 6) {
						return true;
					}

				}
			}
		}
		return false;
	}

	public boolean controlGoal5(Shelf playerShelf) {
		
		int counter;
		int column = 0;
		ArrayList<ColorTile> controller = new ArrayList<ColorTile>();
		for (int i = 0; i < 5; ++i) {
			for (ColorTile cards : ColorTile.values()) {
				if (cards == ColorTile.EMPTY) {
					break;
				}
				counter = countColorInColumn(cards, i, playerShelf.getShelf());
				if (counter > 0) {
					controller.add(cards);
				}
			}
			if (controller.size() < 4 && controller.size() > 0) {
				System.out.println(controller.toString());
				column++;
				controller.clear();
			}
		}
		if (column >= 3) {
			return true;
		}
		return false;
	}

	public int countColorInColumn(ColorTile color, int r, ColorTile[][] shelf) {
		if (color == ColorTile.EMPTY) {
			return 0;
		}
		ColorTile[] array = new ColorTile[6];
		for (int i = 0; i < 6; ++i) {
			array[i] = shelf[r][i];
		}
		int cont = 0;
		for (int i = 0; i < shelf.length; i++) {
			if (color == array[i]) {
				cont++;
				System.out.println(cont);
			}
		}
		return cont;
	}

	public boolean controlGoal6(Shelf playerShelf) {
		int row = 0;
		boolean bool = true;
		for (int i = 0; i < 6; ++i) {
			for (int j = 0; j < 5; ++j) {
				bool = isColorInRow(playerShelf.getShelf()[j][i], i, playerShelf.getShelf());
				if (!bool) {
					break;
				}
			}
			if (bool) {
				row++;
			}
		}
		if (row >= 2) {
			return true;
		}
		return false;
	}

	public boolean isColorInRow(ColorTile color, int r, ColorTile[][] shelf) {
		if (color == ColorTile.EMPTY) {
			return false;
		}
		ColorTile[] array = new ColorTile[5];
		for (int i = 0; i < 5; ++i) {
			array[i] = shelf[i][r];
		}
		int cont = 0;
		for (int i = 0; i < shelf.length; i++) {
			if (color == array[i]) {
				cont++;
				System.out.println(cont);
			}
		}
		if (cont == 1) {
			return true;
		}
		return false;
	}
	
	public boolean controlGoal7(Shelf playerShelf) {
		int counter;
		int column = 0;
		ArrayList<ColorTile> controller = new ArrayList<ColorTile>();
		for (int i = 0; i < 6; ++i) {
			for (ColorTile cards : ColorTile.values()) {
				if (cards == ColorTile.EMPTY) {
					break;
				}
				counter = countColorInRow(cards, i, playerShelf.getShelf());
				if (counter > 0) {
					controller.add(cards);
				}
			}
			if (controller.size() < 4 && controller.size() > 0) {
				System.out.println(controller.toString());
				column++;
				controller.clear();
			}
		}
		if (column >= 4) {
			return true;
		}
		return false;
	}

	public int countColorInRow(ColorTile color, int r, ColorTile[][] shelf) {
		if (color == ColorTile.EMPTY) {
			return 0;
		}
		ColorTile[] array = new ColorTile[5];
		for (int i = 0; i < 5; ++i) {
			array[i] = shelf[i][r];
		}
		int cont = 0;
		for (int i = 0; i < shelf.length; i++) {
			if (color == array[i]) {
				cont++;
				System.out.println(cont);
			}
		}
		return cont;
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

	public boolean controlGoal10(Shelf playerShelf) {
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			for (int i = 1; i < 4; ++i) {
				for (int j = 1; j < 5; ++j) {
					if (playerShelf.getShelf()[i][j].equals(cards) && playerShelf.getShelf()[i + 1][j + 1].equals(cards)
							&& playerShelf.getShelf()[i - 1][j - 1].equals(cards)
							&& playerShelf.getShelf()[i - 1][j + 1].equals(cards)
							&& playerShelf.getShelf()[i + 1][j - 1].equals(cards)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean controlGoal11(Shelf playerShelf) {
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			if (playerShelf.getShelf()[0][0].equals(cards) && playerShelf.getShelf()[1][1].equals(cards)
					&& playerShelf.getShelf()[2][2].equals(cards) && playerShelf.getShelf()[3][3].equals(cards)
					&& playerShelf.getShelf()[4][4].equals(cards)) {
				return true;
			}
			if (playerShelf.getShelf()[0][1].equals(cards) && playerShelf.getShelf()[1][2].equals(cards)
					&& playerShelf.getShelf()[2][3].equals(cards) && playerShelf.getShelf()[3][4].equals(cards)
					&& playerShelf.getShelf()[4][5].equals(cards)) {
				return true;
			}
			if (playerShelf.getShelf()[0][4].equals(cards) && playerShelf.getShelf()[1][3].equals(cards)
					&& playerShelf.getShelf()[2][2].equals(cards) && playerShelf.getShelf()[3][1].equals(cards)
					&& playerShelf.getShelf()[4][0].equals(cards)) {
				return true;
			}
			if (playerShelf.getShelf()[0][5].equals(cards) && playerShelf.getShelf()[1][4].equals(cards)
					&& playerShelf.getShelf()[2][3].equals(cards) && playerShelf.getShelf()[3][2].equals(cards)
					&& playerShelf.getShelf()[4][1].equals(cards)) {
				return true;
			}
		}
		return false;
	}

	public boolean controlGoal12(Shelf playerShelf) {
		for (int i = 1; i < 5; ++i) {
			if (playerShelf.getShelf()[0][0].equals(ColorTile.EMPTY)
					&& playerShelf.getShelf()[1][1].equals(ColorTile.EMPTY)
					&& playerShelf.getShelf()[2][2].equals(ColorTile.EMPTY)
					&& playerShelf.getShelf()[3][3].equals(ColorTile.EMPTY)
					&& playerShelf.getShelf()[4][4].equals(ColorTile.EMPTY)
					&& !playerShelf.getShelf()[0][1].equals(ColorTile.EMPTY)
					&& !playerShelf.getShelf()[1][2].equals(ColorTile.EMPTY)
					&& !playerShelf.getShelf()[2][3].equals(ColorTile.EMPTY)
					&& !playerShelf.getShelf()[3][4].equals(ColorTile.EMPTY)
					&& !playerShelf.getShelf()[4][5].equals(ColorTile.EMPTY)) {
				return true;
			}
			if (playerShelf.getShelf()[0][4].equals(ColorTile.EMPTY)
					&& playerShelf.getShelf()[1][3].equals(ColorTile.EMPTY)
					&& playerShelf.getShelf()[2][2].equals(ColorTile.EMPTY)
					&& playerShelf.getShelf()[3][1].equals(ColorTile.EMPTY)
					&& playerShelf.getShelf()[4][0].equals(ColorTile.EMPTY)
					&& !playerShelf.getShelf()[0][4].equals(ColorTile.EMPTY)
					&& !playerShelf.getShelf()[1][3].equals(ColorTile.EMPTY)
					&& !playerShelf.getShelf()[2][2].equals(ColorTile.EMPTY)
					&& !playerShelf.getShelf()[3][1].equals(ColorTile.EMPTY)
					&& !playerShelf.getShelf()[4][0].equals(ColorTile.EMPTY)) {
				return true;
			}
		}
		return false;
	}
}
