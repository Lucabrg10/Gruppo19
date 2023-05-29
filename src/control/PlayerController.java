package control;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Player;
import model.Tile;
import model.CommonGoal;
import view.FinalFrame;
import view.LabelTile;
import view.MainFrame;

public class PlayerController {

	private MainFrame frame;
	private BoardController board;
	private Player player;
	private ArrayList<Tile> tilesChoosen;
	private ArrayList<Tile> tilesSorted;
	private ArrayList<Integer> commonGoalPoints1 = new ArrayList<>();
	private int cont1 = 0;
	private ArrayList<Integer> commonGoalPoints2 = new ArrayList<>();
	private int cont2 = 0;
	boolean endGame=false;
	boolean showFinalFrame=false;
	boolean finalPoint=true;
	
	public PlayerController(MainFrame frame, BoardController board) {
		this.frame = frame;
		this.board = board;
		commonGoalPoints1.add(8);
		commonGoalPoints1.add(6);
		commonGoalPoints1.add(4);
		commonGoalPoints1.add(2);

		commonGoalPoints2.add(8);
		commonGoalPoints2.add(6);
		commonGoalPoints2.add(4);
		commonGoalPoints2.add(2);

	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		this.showBoard();
	}

	public void showBoard() {
		this.frame.getLbPlayer().setText("Shelf di " + this.player.getPlayerName());
		this.frame.setVisible(true);
	}

	/**
	 * 
	 * @param tilesChoosen Lista delle tessere scelte
	 * 
	 *                     Il metodo crea un jframe che permette di vedere le carte
	 *                     scelte e cliccando su esse si sceglie l'ordine. Le carte
	 *                     in ordine vengono inserite in tilesSorted e inviate al
	 *                     player che le aggiungerà nella colonna desiderata
	 * 
	 */
	public JDialog selectOrderOfTiles(ArrayList<Tile> tilesChoosen, Player playerNext, List<CommonGoal>commonGoalsList, int cont, List<Player>players) {

		int col = chooseCol();

		this.tilesChoosen = tilesChoosen;
		ArrayList<Integer> positions = new ArrayList<>();

		JDialog Jframe = new JDialog(new JFrame(),"Scegli l'ordine delle carte");
		Jframe.setMinimumSize(new Dimension(300,80));
		JPanel panelLabel = new JPanel(new FlowLayout());

		for (int i = 0; i < tilesChoosen.size(); i++) {
			LabelTile label = new LabelTile(tilesChoosen.get(i));
			//System.out.println(tilesChoosen.get(i));
			label.setIcon(tilesChoosen.get(i).getImg());
			label.addMouseListener(new MouseAdapter() {
				/**
				 * al click del mouse su una label si aggiunge una tile alla shelf e la si
				 * rimuove dalla board
				 */
				public void mousePressed(MouseEvent e) {
					boolean aggiunto;
					Tile tile1 = new Tile(label.getTile().getColor(), label.getTile().getImg());
					// System.out.println(tile1.getColor());
					aggiunto = player.addTile(tile1, col);
					if (aggiunto) {
						Container container = label.getParent();
						container.remove(label);
						container.validate();
						container.repaint();
						// player.getShelf().print();
						board.getBoard().removeTile(label.getTile());
						// System.out.println(player.getPersonalGoal().counterPersonalGoalPoint(player.getShelf()));
						player.addPointsPersonalGoal(player.getPersonalGoal().counterPersonalGoalPoint(player.getShelf()));
						// System.out.println("Punteggio "+player.getPlayerName()+" :
						// "+player.getPoints());
						if (commonGoalsList.get(0).controlGoal(player.getShelf())) {
						//	System.out.println("prova0 "+commonGoalPoints1.get(0));
							if (player.addPoints(commonGoalPoints1.get(0), 0))
								commonGoalPoints1.remove(0);
						//	System.out.println("Punti del player " + player.getPoints());
						//	System.out.println(commonGoalPoints1.get(0));
						}
						if (commonGoalsList.get(1).controlGoal(player.getShelf())) {
						//	System.out.println("prova1 "+commonGoalPoints2.get(0));
							if (player.addPoints(commonGoalPoints2.get(0), 1))
								commonGoalPoints2.remove(0);
						}
						frame.getLbPoints().setText("Punteggio di "+player.getPlayerName()+": " + player.getPoints());	
						
					}

					else {
						JOptionPane errore = new JOptionPane();
						errore.showMessageDialog(label, "La colonna è piena");
					}

				}

			});
			panelLabel.add(label);
		}

		/**
		 * Al click sul button si passa al giocatore successivo
		 */
		JButton button = new JButton("Passa!");
		// System.out.println(button.isVisible());
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(player.getShelf().isShelfFull()) {
					endGame=true;
					if(finalPoint) {
						player.addPoints(1);
						finalPoint=false;
					}
				//	System.out.println("finito1");
				}
				if(endGame) {
					if(cont==0) {
					//	System.out.println("finito2");
						showFinalFrame=true;
						Jframe.setVisible(false);
						frame.setVisible(false);
						System.out.println("funziona");
						rankPlayers(players);
						FinalFrame finalFrame= new FinalFrame(players);
						finalFrame.setVisible(true);
						finalFrame.pack();
						finalFrame.setLocationRelativeTo(null);
						finalFrame.setSize(600,600);
						finalFrame.setResizable(false);

					}
					
				}
				
				if(!showFinalFrame) {
					setPlayer(playerNext);
					Jframe.setVisible(false);
					frame.setVisible(true);
					frame.getShelfTable().setModel(player.getShelf());
					if (board.getBoard().checkForRefill()) {
						board.getBoard().refillBoard();

					}
					frame.getLbPoints().setText("Punteggio di "+player.getPlayerName()+": " + player.getPoints());	
				}
				
			}
		});
		panelLabel.add(button);
		Jframe.setModal(true);
		Jframe.add(panelLabel);
		Jframe.pack();
		Jframe.setLocationRelativeTo(null);
		Jframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Jframe.addWindowListener(new WindowAdapter() {
			  @Override
	            public void windowClosing(WindowEvent e) {
	                // Display a message to indicate that the window can only be closed using the button
	                JOptionPane.showMessageDialog(frame, "Premi passa per passare il turno.");
	            }
		});
		
		if(showFinalFrame) {
			return null;
		}
		return Jframe;
	}

	public int chooseCol() {
		int col = 0;
		boolean invalid = false;
		do {
			try {
				col = Integer.parseInt(JOptionPane.showInputDialog(null, "inserisci la colonna", "My Shelfie", 1));
				invalid = false;
				if(col<0 || col >4) {
					invalid = true;
					showMessageError("La colonna deve essere compresa tra 0 e 4.");
				}
			}
			catch(NumberFormatException e) {
				invalid = true;
				showMessageError("La colonna deve essere un intero.");
			}
		}while(invalid);
		return col;
	}
	public void rankPlayers(List<Player>players) {
		Collections.sort(players, Comparator.comparingInt(Player::getPoints).reversed());
	}
	
	public void showMessageError(String message) {
		JOptionPane pane = new JOptionPane();
		pane.showMessageDialog(frame, message);
	}

}









