package model;

public class MainModel {

	public static void main(String[] args) {
		int numOfPlayers = 4;
		Shelf shelfPlayer1=new Shelf(numOfPlayers);
		shelfPlayer1.initialize();
		/*Board board = new Board(numOfPlayers);
		board.printBoard();*/
	}

}
