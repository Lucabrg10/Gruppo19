package control;

import model.Board;
import view.MainFrame;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;



public class MainController {

	private MainFrame frame;
	private BoardController board;
	
	public MainController(MainFrame f) {
	//	this.numOfPlayers=numOfPlayers;
		this.frame = f;
		
		
		int numberOfPlayers=Integer.parseInt(JOptionPane.showInputDialog(null, "inserisci il numero di giocatori","My Shelfie",1));	
		this.board= new BoardController (frame,new Board(numberOfPlayers));
		frame.pack();
		frame.setVisible(true);
		
		frame.getTableBoard().setRowHeight(50);
		frame.getTableBoard().setCellSelectionEnabled(true);
		frame.setSize(550,600);
	
		
		
	}
	
}
