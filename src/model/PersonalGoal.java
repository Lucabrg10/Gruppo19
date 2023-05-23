package model;

public class PersonalGoal extends Goal {
	private int numOfGoal;

//	YE	   CY	  BL    GR	  PI	WH
	int[][] pGoalArray = { { 12, 20, 25, 44, 05, 33 }, { 23, 31, 40, 04, 14, 42 }, { 34, 42, 04, 12, 23, 00 },
			{ 45, 03, 23, 21, 32, 11 }, { 00, 14, 12, 30, 41, 22 }, { 11, 25, 31, 45, 00, 33 },
			{ 41, 02, 34, 05, 13, 20 }, { 30, 23, 45, 14, 02, 31 }, { 25, 11, 00, 23, 41, 42 },
			{ 14, 45, 11, 32, 30, 03 }, { 03, 30, 22, 41, 25, 14 }, { 41, 32, 23, 00, 14, 25 } };

	public PersonalGoal(int numOfGoal) {
		this.numOfGoal = numOfGoal;
	}

	/**
	 * Counter for tile in the correct position
	 * 
	 * @param personalGoalNum identifier of personal player goal
	 * @param playerShelf     personal player shelf
	 * @return number of tile in the correct position
	 */

	public int counterPersonalGoalPoint( Shelf playerShelf) {
		int cardsOk = 0;
		if (playerShelf.getShelf()[(pGoalArray[numOfGoal][0]) / 10][(pGoalArray[numOfGoal][0]) % 10]
				.getColor() == ColorTile.YELLOW) {
			cardsOk++;
		}
		if (playerShelf.getShelf()[(pGoalArray[numOfGoal][1]) / 10][(pGoalArray[numOfGoal][1]) % 10]
				.getColor() == ColorTile.CYAN) {
			cardsOk++;
		}
		if (playerShelf.getShelf()[(pGoalArray[numOfGoal][2]) / 10][(pGoalArray[numOfGoal][2]) % 10]
				.getColor() == ColorTile.BLUE) {
			cardsOk++;
		}
		if (playerShelf.getShelf()[(pGoalArray[numOfGoal][3]) / 10][(pGoalArray[numOfGoal][3]) % 10]
				.getColor() == ColorTile.GREEN) {
			cardsOk++;
		}
		if (playerShelf.getShelf()[(pGoalArray[numOfGoal][4]) / 10][(pGoalArray[numOfGoal][4]) % 10]
				.getColor() == ColorTile.PINK) {
			cardsOk++;
		}
		if (playerShelf.getShelf()[(pGoalArray[numOfGoal][5]) / 10][(pGoalArray[numOfGoal][5]) % 10]
				.getColor() == ColorTile.WHITE) {
			cardsOk++;
		}

		return countPoint(cardsOk);
	}

	private int countPoint(int cards) {
		int array[] = { 0, 1, 2, 4, 6, 9, 12 };
		return array[cards];

	}

}
