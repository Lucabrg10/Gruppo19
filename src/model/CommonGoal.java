package model;

import java.awt.PageAttributes.ColorType;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class CommonGoal {
	private final int columns = 5;
	private final int rows = 6;
	private int goalNumber;
	private ImageIcon imageGoal;
	ArrayList<ColorTile> controller = new ArrayList<ColorTile>();
	ColorTile[] arrayC = new ColorTile[6];
	ColorTile[] arrayR = new ColorTile[5];

	/**
	 * 
	 * @param goalNumber
	 */
	public CommonGoal(int goalNumber) {
		this.goalNumber = goalNumber;
		this.imageGoal = new ImageIcon("./assets/myshelfie/common_goal/" + goalNumber + ".jpg");
	}

	/**
	 * Used to get the number of the common goal
	 * @return number of goal (int)
	 */
	public int getGoalNumber() {
		return goalNumber;
	}
	
	/**
	 * Used to set the number of the goal
	 * @param goalNumber
	 */
	public void setGoalNumber(int goalNumber) {
		this.goalNumber = goalNumber;
	}
	
	/**
	 * Used to get the image of the common goal
	 * @return
	 */
	public ImageIcon getImageGoal() {
		return imageGoal;
	}
	
	/**
	 * Used to set the image of the common goal
	 * @param imageGoal
	 */
	public void setImageGoal(ImageIcon imageGoal) {
		this.imageGoal = imageGoal;
	}
	
	/**
	 * Used to control the goals
	 * @param playerShelf shelf that has to be controlled
	 * @return true if one of the goal is successfully controlled
	 */
	public boolean controlGoal(Shelf playerShelf) {

		boolean val;
		switch (goalNumber) {
		case 1: {
			val = controlGoal1(playerShelf);
		}
			break;
		case 2: {
			val = controlGoal2(playerShelf);
		}
			break;
		case 3: {
			val = controlGoal3(playerShelf);
		}
			break;
		case 4: {
			val = controlGoal4(playerShelf);
		}
			break;
		case 5: {
			val = controlGoal5(playerShelf);
		}
			break;
		case 6: {
			val = controlGoal6(playerShelf);
		}
			break;
		case 7: {
			val = controlGoal7(playerShelf);
		}
			break;
		case 8: {
			val = controlGoal8(playerShelf);
		}
			break;
		case 9: {
			val = controlGoal9(playerShelf);
		}
			break;
		case 10: {
			val = controlGoal10(playerShelf);
		}
			break;
		case 11: {
			val = controlGoal11(playerShelf);
		}
			break;
		case 12: {
			val = controlGoal12(playerShelf);
		}
			break;
		default:
			val = false;
			break;
		}
		return val;
	}
	
	/**
	 * Controller for Common Goal 1
	 * @param playerShelf player shelf that has to be controlled
	 * @return true if the goal is correct in the shelf
	 */
	public boolean controlGoal1(Shelf playerShelf) {
		int square = 0;
		ColorTile prevcard = null;
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			for (int i = 0; i < playerShelf.getShelf().length-1; ++i) {
				for (int j = 0; j < playerShelf.getShelf()[i].length-1; ++j) {
						if (playerShelf.getShelf()[i][j].getColor().equals(cards)
							&& playerShelf.getShelf()[i][j + 1].getColor().equals(cards)
							&& playerShelf.getShelf()[i + 1][j].getColor().equals(cards)
							&& playerShelf.getShelf()[i + 1][j + 1].getColor().equals(cards)) {
								square++;
							}
						} 
						if (square == 2 && cards == prevcard) {
							return true;
						}
						prevcard = cards;
					}
				}
		return false;
	}
	
	/**
	 * Controller for Common Goal 2
	 * @param playerShelf player shelf that has to be controlled
	 * @return true if the goal is correct in the shelf
	 */
	public boolean controlGoal2(Shelf playerShelf) {
		int column = 0;
		boolean bool = true;
		for (int i = 0; i < columns; ++i) {
			for (int j = 0; j < rows; ++j) {
				
				bool = isColorInColumn(playerShelf.getShelf()[j][i], i, playerShelf.getShelf());
				
				if (!bool) {
					break;
				}
			}
			if (bool) {
				System.out.println("colonne" + column);
				column++;
				for (int x=0; x < arrayC.length; x++) {
					arrayC[x]=null;
				}
			}
		}
		if (column >= 2) {
			return true;
		}
		return false;
	}
	
	/**
	 * Used to control if a color is present in a column
	 * @param tile indicate the reference tile
	 * @param c indicate the number of the column that has to be controlled
	 * @param tiles import all the shelf like a matrix of Tile
	 * @return true if the color is present only one time in the column
	 */
	public boolean isColorInColumn(Tile tile, int c, Tile[][] tiles) {
		if (tile.getColor() == ColorTile.EMPTY) {
			return false;
		}
		for (int i = 0; i < 6; ++i) {
			arrayC[i] = tiles[i][c].getColor();
			 System.out.println("array" + arrayC[i]);
		}
		int cont = 0;
		for (int i = 0; i < tiles.length; i++) {
			if (tile.getColor() == arrayC[i]) {
				cont++;
				// System.out.println("cont"+cont);
			}
		}
		if (cont < 2) {
			//System.out.println("ok");
			return true;
		}
		return false;
	}

	/**
	 * Controller for Common Goal 3
	 * @param playerShelf player shelf that has to be controlled
	 * @return true if the goal is correct in the shelf
	 */
	public boolean controlGoal3(Shelf playerShelf) {
		int group = 0;
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}

			for (int i = 0; i < columns; i++) {
				if (i<columns-2){
				if (((playerShelf.getShelf()[0][i].getColor().equals(cards)
						&& playerShelf.getShelf()[1][i].getColor().equals(cards)
						&& playerShelf.getShelf()[2][i].getColor().equals(cards)
						&& playerShelf.getShelf()[3][i].getColor().equals(cards))
						&& !(playerShelf.getShelf()[0][i + 1].getColor().equals(cards)
								&& playerShelf.getShelf()[1][i + 1].getColor().equals(cards)
								&& playerShelf.getShelf()[2][i + 1].getColor().equals(cards)
								&& playerShelf.getShelf()[3][i + 1].getColor().equals(cards)))
						|| ((playerShelf.getShelf()[1][i].getColor().equals(cards)
								&& playerShelf.getShelf()[2][i].getColor().equals(cards)
								&& playerShelf.getShelf()[3][i].getColor().equals(cards)
								&& playerShelf.getShelf()[4][i].getColor().equals(cards))
								&& !(playerShelf.getShelf()[1][i + 1].getColor().equals(cards)
										&& playerShelf.getShelf()[2][i + 1].getColor().equals(cards)
										&& playerShelf.getShelf()[3][i + 1].getColor().equals(cards)
										&& playerShelf.getShelf()[4][i + 1].getColor().equals(cards)))
						|| ((playerShelf.getShelf()[2][i].getColor().equals(cards)
								&& playerShelf.getShelf()[3][i].getColor().equals(cards)
								&& playerShelf.getShelf()[4][i].getColor().equals(cards)
								&& playerShelf.getShelf()[5][i].getColor().equals(cards))
								&& !(playerShelf.getShelf()[2][i + 1].getColor().equals(cards)
										&& playerShelf.getShelf()[3][i + 1].getColor().equals(cards)
										&& playerShelf.getShelf()[4][i + 1].getColor().equals(cards)
										&& playerShelf.getShelf()[5][i + 1].getColor().equals(cards)))) {
					group++;
				}
				}
				else if ((playerShelf.getShelf()[0][columns-1].getColor().equals(cards)
						&& playerShelf.getShelf()[1][columns-1].getColor().equals(cards)
						&& playerShelf.getShelf()[2][columns-1].getColor().equals(cards)
						&& playerShelf.getShelf()[3][columns-1].getColor().equals(cards))
						|| (playerShelf.getShelf()[1][columns-1].getColor().equals(cards)
								&& playerShelf.getShelf()[2][columns-1].getColor().equals(cards)
								&& playerShelf.getShelf()[3][columns-1].getColor().equals(cards)
								&& playerShelf.getShelf()[4][columns-1].getColor().equals(cards))
						|| (playerShelf.getShelf()[2][columns-1].getColor().equals(cards)
								&& playerShelf.getShelf()[3][columns-1].getColor().equals(cards)
								&& playerShelf.getShelf()[4][columns-1].getColor().equals(cards)
								&& playerShelf.getShelf()[5][columns-1].getColor().equals(cards))){
					group++;
				}
			}

			for (int j = 0; j < rows; ++j) {
				if (j<rows-2){
				if (((playerShelf.getShelf()[j][0].getColor().equals(cards)
						&& playerShelf.getShelf()[j][1].getColor().equals(cards)
						&& playerShelf.getShelf()[j][2].getColor().equals(cards)
						&& playerShelf.getShelf()[j][3].getColor().equals(cards))
						&& !(playerShelf.getShelf()[j + 1][0].getColor().equals(cards)
								&& playerShelf.getShelf()[j + 1][1].getColor().equals(cards)
								&& playerShelf.getShelf()[j + 1][2].getColor().equals(cards)
								&& playerShelf.getShelf()[j + 1][3].getColor().equals(cards)))
						|| ((playerShelf.getShelf()[j][1].getColor().equals(cards)
								&& playerShelf.getShelf()[j][2].getColor().equals(cards)
								&& playerShelf.getShelf()[j][3].getColor().equals(cards)
								&& playerShelf.getShelf()[j][4].getColor().equals(cards))
								&& !(playerShelf.getShelf()[j + 1][1].getColor().equals(cards)
										&& playerShelf.getShelf()[j + 1][2].getColor().equals(cards)
										&& playerShelf.getShelf()[j + 1][3].getColor().equals(cards)
										&& playerShelf.getShelf()[j + 1][4].getColor().equals(cards)))) {
					group++;
				}
				}
				else if((playerShelf.getShelf()[rows-1][0].getColor().equals(cards)
						&& playerShelf.getShelf()[rows-1][1].getColor().equals(cards)
						&& playerShelf.getShelf()[rows-1][2].getColor().equals(cards)
						&& playerShelf.getShelf()[rows-1][3].getColor().equals(cards))
						|| (playerShelf.getShelf()[rows-1][1].getColor().equals(cards)
								&& playerShelf.getShelf()[rows-1][2].getColor().equals(cards)
								&& playerShelf.getShelf()[rows-1][3].getColor().equals(cards)
								&& playerShelf.getShelf()[rows-1][4].getColor().equals(cards))){
					group++;
				}
			}
		}
		if (group >= 4) {
			return true;
		}
		return false;
	}
	
	/**
	 * Controller for Common Goal 4
	 * @param playerShelf player shelf that has to be controlled
	 * @return true if the goal is correct in the shelf
	 */
	public boolean controlGoal4(Shelf playerShelf) {
		int couple = 0;
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			for (int i = 0; i < columns; ++i) {
				for (int j = 0; j < rows; ++j) {
					if (i < columns - 1 && j < rows - 1) {
						if (playerShelf.getShelf()[j][i].getColor() == cards
								&& (playerShelf.getShelf()[j][i + 1].getColor() == cards
										^ playerShelf.getShelf()[j + 1][i].getColor() == cards)) {
							couple++;
						}
					} else {
						if (playerShelf.getShelf()[rows - 1][columns - 1].getColor() == cards
								&& (playerShelf.getShelf()[rows - 1][columns - 2].getColor() == cards
										^ playerShelf.getShelf()[rows - 2][columns - 1].getColor() == cards)) {
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
	
	/**
	 * Controller for Common Goal 5
	 * @param playerShelf player shelf that has to be controlled
	 * @return true if the goal is correct in the shelf
	 */
	public boolean controlGoal5(Shelf playerShelf) {

		int counter;
		int column = 0;
		for (int i = 0; i < 5; ++i) {
			for (ColorTile cards : ColorTile.values()) {
				if (cards == ColorTile.EMPTY) {
					break;
				}
				else {
				counter = countColorInColumn(cards, i, playerShelf.getShelf());
					if (counter > 0) {
						controller.add(cards);
					}
				}
			}
			//System.out.println(controller.toString());
			if (controller.size() < 4 && controller.size() > 0 && playerShelf.isColumnFull(i)) {	
				column++;
			}
			controller.clear();
		}
		if (column >= 3) {
			return true;
		}
		return false;
	}

	/**
	 * Used to count how many times a color is present in a column
	 * @param color indicate the reference color
	 * @param c indicate the number of the column that has to be controlled
	 * @param tiles import all the shelf like a matrix of Tile
	 * @return the number of how many times the color is present in the column (int)
	 */
	public int countColorInColumn(ColorTile color, int c, Tile[][] tiles) {
		if (color == ColorTile.EMPTY) {
			return 0;
		}
		ColorTile[] array = new ColorTile[6];
		for (int i = 0; i < 6; ++i) {
			array[i] = tiles[i][c].getColor();
		}
		int cont = 0;
		for (int i = 0; i < tiles.length; i++) {
			if (color == array[i]) {
				cont++;
				//System.out.println("cont" + cont);
			}
		}
		return cont;
	}
	
	/**
	 * Controller for Common Goal 6
	 * @param playerShelf player shelf that has to be controlled
	 * @return true if the goal is correct in the shelf
	 */
	public boolean controlGoal6(Shelf playerShelf) {
		int row = 0;
		boolean bool = true;
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				bool = isColorInRow(playerShelf.getShelf()[i][j], i, playerShelf.getShelf());
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
	
	/**
	 * Used to control if a color is present in a row
	 * @param tile indicate the reference tile
	 * @param r indicate the number of the rows that has to be controlled
	 * @param tiles import all the shelf like a matrix of Tile
	 * @return true if the color is present only one time in the row
	 */
	public boolean isColorInRow(Tile tile, int r, Tile[][] tiles) {
		if (tile.getColor() == ColorTile.EMPTY) {
			return false;
		}
		
		for (int i = 0; i < 5; ++i) {
			arrayR[i] = tiles[r][i].getColor();
		}
		int cont = 0;
		// System.out.println(tiles[r].length);
		for (int i = 0; i < tiles[r].length; i++) {
			if (tile.getColor() == arrayR[i]) {
				cont++;
				for (int x=0; x < arrayR.length; x++) {
					arrayR[x]=null;
				}
			}
		}
		if (cont < 2) {
			return true;
		}
		return false;
	}
	
	/**
	 * Controller for Common Goal 7
	 * @param playerShelf player shelf that has to be controlled
	 * @return true if the goal is correct in the shelf
	 */
	public boolean controlGoal7(Shelf playerShelf) {
		int counter;
		int row = 0;
		for (int i = 0; i < 6; i++) {
			for (ColorTile card : ColorTile.values()) {
				if (card == ColorTile.EMPTY) {
					break;
				}
				else {
				counter = countColorInRow(card, i, playerShelf.getShelf());
				if (counter > 0) {
					controller.add(card);
				}
				}
			}
			
			//System.out.println(controller.toString());
			if (controller.size() < 4 && controller.size() > 0 && playerShelf.isRowFull(i)) {
				//System.out.println("OK");
				row++;
			}
			controller.clear();
		}
		
		if (row >= 4) {
			return true;
		}
		return false;
	}
	
	/**
	 * Used to count how many times a color is present in a row
	 * @param color indicate the reference color
	 * @param r indicate the number of the row that has to be controlled
	 * @param tiles import all the shelf like a matrix of Tile
	 * @return the number of how many times the color is present in the row (int)
	 */
	public int countColorInRow(ColorTile color, int r, Tile[][] tiles) {
		if (color == ColorTile.EMPTY) {
			return 0;
		}
		ColorTile[] array = new ColorTile[5];
		for (int i = 0; i < 5; ++i) {
			array[i] = tiles[r][i].getColor();
		}
		int cont = 0;
		// System.out.println(tiles[0].length);
		for (int i = 0; i < tiles[0].length; i++) {
			if (color == array[i]) {
				cont++;
			}
		}
		return cont;
	}
	
	/**
	 * Controller for Common Goal 8
	 * @param playerShelf player shelf that has to be controlled
	 * @return true if the goal is correct in the shelf
	 */	
	public boolean controlGoal8(Shelf playerShelf) {
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			if (playerShelf.getShelf()[0][0].getColor() == cards && playerShelf.getShelf()[5][0].getColor() == cards
					&& playerShelf.getShelf()[0][4].getColor() == cards
					&& playerShelf.getShelf()[5][4].getColor() == cards) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Controller for Common Goal 9
	 * @param playerShelf player shelf that has to be controlled
	 * @return true if the goal is correct in the shelf
	 */
	public boolean controlGoal9(Shelf playerShelf) {
		int cells = 0;
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			for (int i = 0; i < playerShelf.getShelf().length; ++i) {
				for (int j = 0; j < playerShelf.getShelf()[i].length; ++j) {
					if (playerShelf.getShelf()[i][j].getColor() == cards) {
						cells++;
						if (cells == 8) {
							return true;
						}
					}
				}
			}
			cells = 0;
		}
		// System.out.println(cells);
		return false;
	}
	
	/**
	 * Controller for Common Goal 10
	 * @param playerShelf player shelf that has to be controlled
	 * @return true if the goal is correct in the shelf
	 */
	public boolean controlGoal10(Shelf playerShelf) {
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			for (int i = 1; i < 4; ++i) {
				for (int j = 1; j < 5; ++j) {
					if (playerShelf.getShelf()[j][i].getColor().equals(cards)
							&& playerShelf.getShelf()[j + 1][i + 1].getColor().equals(cards)
							&& playerShelf.getShelf()[j - 1][i - 1].getColor().equals(cards)
							&& playerShelf.getShelf()[j + 1][i - 1].getColor().equals(cards)
							&& playerShelf.getShelf()[j - 1][i + 1].getColor().equals(cards)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Controller for Common Goal 11
	 * @param playerShelf player shelf that has to be controlled
	 * @return true if the goal is correct in the shelf
	 */
	public boolean controlGoal11(Shelf playerShelf) {
		for (ColorTile cards : ColorTile.values()) {
			if (cards == ColorTile.EMPTY) {
				break;
			}
			if (playerShelf.getShelf()[0][0].getColor().equals(cards)
					&& playerShelf.getShelf()[1][1].getColor().equals(cards)
					&& playerShelf.getShelf()[2][2].getColor().equals(cards)
					&& playerShelf.getShelf()[3][3].getColor().equals(cards)
					&& playerShelf.getShelf()[4][4].getColor().equals(cards)) {
				return true;
			}
			if (playerShelf.getShelf()[1][0].getColor().equals(cards)
					&& playerShelf.getShelf()[2][1].getColor().equals(cards)
					&& playerShelf.getShelf()[3][2].getColor().equals(cards)
					&& playerShelf.getShelf()[4][3].getColor().equals(cards)
					&& playerShelf.getShelf()[5][4].getColor().equals(cards)) {
				return true;
			}
			if (playerShelf.getShelf()[4][0].getColor().equals(cards)
					&& playerShelf.getShelf()[3][1].getColor().equals(cards)
					&& playerShelf.getShelf()[2][2].getColor().equals(cards)
					&& playerShelf.getShelf()[1][3].getColor().equals(cards)
					&& playerShelf.getShelf()[0][4].getColor().equals(cards)) {
				return true;
			}
			if (playerShelf.getShelf()[5][0].getColor().equals(cards)
					&& playerShelf.getShelf()[4][1].getColor().equals(cards)
					&& playerShelf.getShelf()[3][2].getColor().equals(cards)
					&& playerShelf.getShelf()[2][3].getColor().equals(cards)
					&& playerShelf.getShelf()[1][4].getColor().equals(cards)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Controller for Common Goal 12
	 * @param playerShelf player shelf that has to be controlled
	 * @return true if the goal is correct in the shelf
	 */
	public boolean controlGoal12(Shelf playerShelf) {
		if (playerShelf.getShelf()[0][0].getColor().equals(ColorTile.EMPTY)
				&& playerShelf.getShelf()[1][1].getColor().equals(ColorTile.EMPTY)
				&& playerShelf.getShelf()[2][2].getColor().equals(ColorTile.EMPTY)
				&& playerShelf.getShelf()[3][3].getColor().equals(ColorTile.EMPTY)
				&& playerShelf.getShelf()[4][4].getColor().equals(ColorTile.EMPTY)
				&& !playerShelf.getShelf()[1][0].getColor().equals(ColorTile.EMPTY)
				&& !playerShelf.getShelf()[2][1].getColor().equals(ColorTile.EMPTY)
				&& !playerShelf.getShelf()[3][2].getColor().equals(ColorTile.EMPTY)
				&& !playerShelf.getShelf()[4][3].getColor().equals(ColorTile.EMPTY)
				&& !playerShelf.getShelf()[5][4].getColor().equals(ColorTile.EMPTY)) {
			return true;
		}
		if (playerShelf.getShelf()[0][4].getColor().equals(ColorTile.EMPTY)
				&& playerShelf.getShelf()[1][3].getColor().equals(ColorTile.EMPTY)
				&& playerShelf.getShelf()[2][2].getColor().equals(ColorTile.EMPTY)
				&& playerShelf.getShelf()[3][1].getColor().equals(ColorTile.EMPTY)
				&& playerShelf.getShelf()[4][0].getColor().equals(ColorTile.EMPTY)
				&& !playerShelf.getShelf()[1][4].getColor().equals(ColorTile.EMPTY)
				&& !playerShelf.getShelf()[2][3].getColor().equals(ColorTile.EMPTY)
				&& !playerShelf.getShelf()[3][2].getColor().equals(ColorTile.EMPTY)
				&& !playerShelf.getShelf()[4][1].getColor().equals(ColorTile.EMPTY)
				&& !playerShelf.getShelf()[5][0].getColor().equals(ColorTile.EMPTY)) {
			return true;
		}
		return false;
	}
}
