package control;

import model.Board;
import model.Player;
import view.MainFrame;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class MainController {

	private MainFrame frame;
	private MainframeController frameController;
	private BoardController board;
	private ArrayList<Player> listOfPlayers = new ArrayList<>();
	boolean gameIsOver = false;
	private PlayerController playerController ;

	public MainController(MainFrame f) {
		// this.numOfPlayers=numOfPlayers;
		this.frame = f;

		int numberOfPlayers = Integer.parseInt(JOptionPane.showInputDialog(null, "inserisci il numero di giocatori", "My Shelfie", 1));

		String username;
		for (int i = 0; i < numberOfPlayers; i++) {

			username = JOptionPane.showInputDialog(null, "Inserisci l'username del primo giocatore", "Username", 1);
			listOfPlayers.add(new Player(username));
		}
		this.board = new BoardController(frame, new Board(numberOfPlayers));
		this.playerController= new PlayerController(f, board);
		
		this.frameController = new MainframeController(f, board, playerController, board.getBoard(),listOfPlayers);
		
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		// frame.setVisible(true);

		frame.getTableBoard().setRowHeight(50);
		frame.getTableBoard().setCellSelectionEnabled(true);
		frame.setSize(550, 600);

		

	}

}
