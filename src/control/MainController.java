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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class MainController {

	private MainFrame frame;
	private MainframeController frameController;
	private BoardController board;
	private ArrayList<Player> listOfPlayers = new ArrayList<>();
	boolean gameIsOver = false;
	private PlayerController playerController;

	public MainController(MainFrame f) {
		// this.numOfPlayers=numOfPlayers;
		this.frame = f;

		int numberOfPlayers = Integer
				.parseInt(JOptionPane.showInputDialog(null, "inserisci il numero di giocatori", "My Shelfie", 1));

		String username;
		for (int i = 0; i < numberOfPlayers; i++) {

			username = JOptionPane.showInputDialog(null, "Inserisci l'username del primo giocatore", "Username", 1);
			listOfPlayers.add(new Player(username));
		}
		this.board = new BoardController(frame, new Board(numberOfPlayers));
		this.playerController = new PlayerController(f, board);
		this.frameController = new MainframeController(f, board, playerController, board.getBoard(), listOfPlayers);

		frame.pack();
		frame.setLocationRelativeTo(null);
		// frame.setVisible(true);
	/*	TableColumnModel columnModel1 = frame.getShelfTable().getColumnModel();
		TableColumnModel columnModel2 = frame.getTableBoard().getColumnModel();
		for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
			TableColumn column1 = columnModel1.getColumn(columnIndex);

			column1.setPreferredWidth(50);
		}
		for (int columnIndex = 0; columnIndex < 9; columnIndex++) {

			TableColumn column2 = columnModel2.getColumn(columnIndex);

			column2.setPreferredWidth(50);
		}*/

		frame.getShelfTable().getTableHeader().setReorderingAllowed(false);
		frame.getTableBoard().getTableHeader().setReorderingAllowed(false);
		frame.getShelfTable().setRowHeight(70);
		frame.getTableBoard().setRowHeight(70);
		frame.getTableBoard().setCellSelectionEnabled(true);
		frame.setSize(1020, 890);
		frame.setResizable(false);

	}

}
