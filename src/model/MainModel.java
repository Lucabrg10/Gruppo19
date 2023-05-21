package model;

import java.util.ArrayList;

public class MainModel {

	
	
	public static void main(String[] args) {
		int numOfPlayers = 4;
		CommonGoal commonGoalPlayer1 = new CommonGoal(1);
		PersonalGoal personalGoalPlayer1 = new PersonalGoal(1);
		Shelf shelfPlayer1 = new Shelf(numOfPlayers);
		// test per obiettivi comuni
		shelfPlayer1.initialize();
		shelfPlayer1.print();
		System.out.println(personalGoalPlayer1.counterPersonalGoal(0,shelfPlayer1));
		// Board board = new Board(numOfPlayers);
		// board.printBoard();
	}

}
