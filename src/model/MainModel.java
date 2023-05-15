package model;

public class MainModel {

	public static void main(String[] args) {
		int numOfPlayers = 4;
		CommonGoal commonGoalPlayer1=new CommonGoal(1);
		Shelf shelfPlayer1=new Shelf(numOfPlayers);
		//test per obiettivi comuni
		shelfPlayer1.initialize();
		shelfPlayer1.print();
	//	System.out.println(commonGoalPlayer1.controlGoal1(shelfPlayer1));
		//
		Board board = new Board(numOfPlayers);
		board.printBoard();
		
	}

}
