package control;

import model.Board;
import view.MainFrame;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JTable;



public class MainController {

	private MainFrame frame;
	private Board board;
	
	public MainController(MainFrame f) {
	//	this.numOfPlayers=numOfPlayers;
		this.frame = f;
		
		
		int numberOfPlayers=Integer.parseInt(JOptionPane.showInputDialog(null, "inserisci il numero di giocatori","My Shelfie",1));	
		this.board = new Board(numberOfPlayers);
		frame.getTableBoard().setModel(board);
		frame.pack();
		frame.setVisible(true);
		frame.getTableBoard().setRowHeight(50);
		frame.setSize(450,450);
		board.fireTableDataChanged();
		
		
	}
	
}
