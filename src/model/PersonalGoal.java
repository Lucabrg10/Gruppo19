package model;

import javax.swing.ImageIcon;

/**
 * This class is used to control the personal goal.
 * it's selected with random number.
 */
public class PersonalGoal {
	private int numOfGoal;
	private ImageIcon imgGoal;

	public ImageIcon getImgGoal() {
		return imgGoal;
	}

	public void setImgGoal(ImageIcon imgGoal) {
		this.imgGoal = imgGoal;
	}

	// YE CY BL GR PI WH
	int[][] pGoalArray = { 
			{ 31, 52, 02, 14, 00, 23 }, 
			{ 22, 43, 54, 20, 11, 34 }, 
			{ 13, 34, 10, 31, 22, 50 },
			{ 04, 20, 22, 42, 33, 41 }, 
			{ 50, 11, 31, 53, 44, 32 }, 
			{ 41, 02, 43, 04, 50, 23 },			
			{ 44, 30, 13, 00, 21, 52 }, 
			{ 53, 22, 04, 11, 30, 43 }, 
			{ 02, 41, 50, 22, 44, 34 },
			{ 11, 04, 41, 33, 53, 20 }, 
			{ 20, 53, 32, 44, 02, 11 }, 
			{ 44, 33, 22, 50, 11, 02 } 
			};
	
	/**
	 * Assign at a personal goal its image
	 * @param numOfGoal
	 */
	public PersonalGoal(int numOfGoal) {
		this.numOfGoal = numOfGoal;
		this.imgGoal = new ImageIcon("./assets/myshelfie/pers_goal/Personal_Goals" + (numOfGoal + 1) + ".png");
	}

	/**
	 * Counter for tile in the correct position
	 * @param playerShelf     personal player shelf
	 * @return number of tile in the correct position
	 */
	public int counterPersonalGoalPoint(Shelf playerShelf) {
		int cardsOk = 0;
		if (playerShelf.getValueOfTileAt((pGoalArray[numOfGoal][0]) / 10, (pGoalArray[numOfGoal][0]) % 10)
				.getColor() == ColorTile.YELLOW) {
			cardsOk++;
		}
		if (playerShelf.getValueOfTileAt((pGoalArray[numOfGoal][1]) / 10, (pGoalArray[numOfGoal][1]) % 10)
				.getColor() == ColorTile.CYAN) {
			cardsOk++;
		}
		if (playerShelf.getValueOfTileAt((pGoalArray[numOfGoal][2]) / 10, (pGoalArray[numOfGoal][2]) % 10)
				.getColor() == ColorTile.BLUE) {
			cardsOk++;
		}
		if (playerShelf.getValueOfTileAt((pGoalArray[numOfGoal][3]) / 10, (pGoalArray[numOfGoal][3]) % 10)
				.getColor() == ColorTile.GREEN) {
			cardsOk++;
		}
		if (playerShelf.getValueOfTileAt((pGoalArray[numOfGoal][4]) / 10, (pGoalArray[numOfGoal][4]) % 10)
				.getColor() == ColorTile.PINK) {
			cardsOk++;
		}
		if (playerShelf.getValueOfTileAt((pGoalArray[numOfGoal][5]) / 10, (pGoalArray[numOfGoal][5]) % 10)
				.getColor() == ColorTile.WHITE) {
			cardsOk++;
		}
		return cardsOk;
	}


}
