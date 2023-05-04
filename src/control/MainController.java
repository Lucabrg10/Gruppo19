package control;

import model.Board;
import view.MainFrame;
import javax.swing.JOptionPane;



public class MainController {

	private MainFrame main;
	private Board board;
	
	public MainController(MainFrame m, Board b ) {
	//	this.numOfPlayers=numOfPlayers;
		this.main = m;
		this.board = b;
		
		
		
		main.getjOptionPane().showInputDialog(null, "inserisci il numero di giocatori","My Shelfie",1);
		
		main.getTableBoard().setModel(board);
		
	}
}
