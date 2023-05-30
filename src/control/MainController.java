package control;

import model.Board;
import model.CommonGoal;
import model.PersonalGoal;
import model.Player;
import view.MainFrame;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private PlayerController playerController;
	private static ArrayList<Integer> selectedNumbers = new ArrayList<>();
	private int numberOfCommonGoals=2;
	private List<CommonGoal> listOfCommonGoals= new ArrayList<CommonGoal>();
	
	public MainController(MainFrame f) {
		// this.numOfPlayers=numOfPlayers;
		this.frame = f;

		int numberOfPlayers = 0;
		
		boolean invalid = false;
		do {
			try {
				numberOfPlayers= Integer.parseInt(JOptionPane.showInputDialog(null, "inserisci il numero di giocatori", "My Shelfie", 1));
				invalid = false;
				if(numberOfPlayers < 2 || numberOfPlayers > 4) {
					invalid = true;
					showMessageError("Il numero di giocatori deve essere compreso tra 2 e 4.");
				}
			}
			catch(NumberFormatException e){
				invalid = true;
				showMessageError("Il numero di giocatori deve essere un intero.");
			}
			
		}while(invalid);

		
		
		String username;
		int numOfPersonalGoal=0;
		
		for (int i = 0; i < numberOfPlayers; i++) {
			
			numOfPersonalGoal = noDuplicatesRandomNumber();
		//	System.out.println(numOfPersonalGoal);
				
			
			invalid = false;
			do{
	
				try {
					username = JOptionPane.showInputDialog(null, "Inserisci l'username del giocatore "+(i+1), "Username", 1);
					listOfPlayers.add(new Player(username, new PersonalGoal(numOfPersonalGoal)));
					invalid = false;
				}
				catch(IllegalArgumentException e){
					invalid = true;
					
					//this should be modified
					//System.out.println("ERRORE: nome non valido.");
					showMessageError(e.getMessage());
				}
			}while(invalid);
			
		}
		
		this.selectedNumbers.clear();
		int numOfCommonGoal;
		for(int i=0; i<numberOfCommonGoals;i++) {
			//numOfCommonGoal= noDuplicatesRandomNumber()+1;
			numOfCommonGoal=5+i;
			System.out.println("Commongoal -- "+numOfCommonGoal);
			listOfCommonGoals.add(new CommonGoal(numOfCommonGoal));
		}
		
		
		this.board = new BoardController(frame, new Board(numberOfPlayers));
		this.playerController = new PlayerController(f, board);
		this.frameController = new MainframeController(f, board, playerController, board.getBoard(), listOfPlayers, listOfCommonGoals);

		frame.pack();
		frame.setLocationRelativeTo(null);

		frame.getShelfTable().getTableHeader().setReorderingAllowed(false);
		frame.getTableBoard().getTableHeader().setReorderingAllowed(false);
		frame.getShelfTable().setRowHeight(70);
		frame.getTableBoard().setRowHeight(70);
		frame.getTableBoard().setCellSelectionEnabled(true);
		frame.setSize(1020, 890);
		frame.setResizable(false);

	}
	
	public void showMessageError(String message) {
		JOptionPane pane = new JOptionPane();
		pane.showMessageDialog(frame, message);
	}

	public int noDuplicatesRandomNumber() {
		Random rnd = new Random();
		int number;
		boolean invalid = false;
		do {
			invalid = false;
			number = rnd.nextInt(12);
			for(int i = 0; i < selectedNumbers.size(); i++) {
				if(selectedNumbers.get(i) == number) {
					invalid = true;
				}
			}
		}while(invalid);
		
		
		selectedNumbers.add(number);
		return number;
	}
}
