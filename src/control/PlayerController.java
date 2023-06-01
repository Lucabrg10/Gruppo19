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

/**
 * 
 * This class manage the player class and some logic of the application
 *
 */

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
	private JButton button;
	boolean endGame = false;
	boolean showFinalFrame = false;
	boolean finalPoint = true;

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
 * This method manage the logic of the application.
 * It controls and insert the tiles into the shelf.
 * It removes the tiles from the board.
 * It switches the turn after an user click on button Passa.
 * 
 * @param tilesChoosen this is the list of the tiles choosen
 * @param playerNext	this is the next player that have to play the turn
 * @param commonGoalsList
 * @param cont this is the counter of the player
 * @param players this is the list of the players
 * @return
 */
	public JDialog selectOrderOfTiles(ArrayList<Tile> tilesChoosen, Player playerNext, List<CommonGoal> commonGoalsList,
			int[] cont, List<Player> players) {
		 this.button= new JButton("Passa!");
		int col = chooseCol();
		button.setEnabled(false);
		if (player.getShelf().ControlFreeCells(col, tilesChoosen.size())) {

			this.tilesChoosen = tilesChoosen;
			//ArrayList<Integer> positions = new ArrayList<>();

			JDialog Jframe = new JDialog(new JFrame(), "Scegli l'ordine delle carte");
			Jframe.setMinimumSize(new Dimension(300, 80));
			JPanel panelLabel = new JPanel(new FlowLayout());

			for (int i = 0; i < tilesChoosen.size(); i++) {
				LabelTile label = new LabelTile(tilesChoosen.get(i));
				// System.out.println(tilesChoosen.get(i));
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
							tilesChoosen.remove(label.getTile());
							if (tilesChoosen.size() == 0) {
								button.setEnabled(true);
							}
							Container container = label.getParent();
							container.remove(label);
							container.validate();
							container.repaint();
							// player.getShelf().print();
							board.getBoard().removeTile(label.getTile());
							// System.out.println(player.getPersonalGoal().counterPersonalGoalPoint(player.getShelf()));
							player.addPointsPersonalGoal(
									player.getPersonalGoal().counterPersonalGoalPoint(player.getShelf()));
							// System.out.println("Punteggio "+player.getPlayerName()+" :
							// "+player.getPoints());
							if (commonGoalsList.get(0).controlGoal(player.getShelf())) {

								// System.out.println("prova0 "+commonGoalPoints1.get(0));
								try {
									if (player.addPoints(commonGoalPoints1.get(0), 0)) {
										commonGoalPoints1.remove(0);
										showMessageError("Complimenti! Hai completato un obiettivo comune!");

									}

								} catch (Exception e2) {
								}

								// System.out.println("Punti del player " + player.getPoints());
								// System.out.println(commonGoalPoints1.get(0));
							}
							if (commonGoalsList.get(1).controlGoal(player.getShelf())) {
								// System.out.println("prova1 "+commonGoalPoints2.get(0));
								try {
									if (player.addPoints(commonGoalPoints2.get(0), 1)) {
										commonGoalPoints2.remove(0);
										showMessageError("Complimenti! Hai completato un obiettivo comune!");

									}

								} catch (Exception e2) {
								}
							}
							frame.getLbPoints()
									.setText("Punteggio di " + player.getPlayerName() + ": " + player.getPoints());

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
			
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
				
					if (player.getShelf().isShelfFull()) {
						endGame = true;
						if (finalPoint) {
							player.addPoints(1);
							finalPoint = false;
						}
						// System.out.println("finito1");
					}
					if (endGame) {
						if (cont[0] == 0) {
							// System.out.println("finito2");
							showFinalFrame = true;
							Jframe.setVisible(false);
							frame.setVisible(false);
							System.out.println("funziona");

							FinalFrame finalFrame = new FinalFrame(players);
							// finalFrame.setVisible(true);
							finalFrame.pack();

							finalFrame.setSize(600, 531 + (players.size() * 80));
						}

					}

					if (!showFinalFrame) {
						setPlayer(playerNext);
						Jframe.setVisible(false);
						frame.setVisible(true);
						frame.getShelfTable().setModel(player.getShelf());
						if (board.getBoard().checkForRefill()) {
							board.getBoard().refillBoard();

						}
						frame.getLbPoints()
								.setText("Punteggio di " + player.getPlayerName() + ": " + player.getPoints());
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
					// Display a message to indicate that the window can only be closed using the
					// button
					JOptionPane.showMessageDialog(frame, "Premi passa per passare il turno.");
				}
			});

			if (showFinalFrame) {
				return null;
			}
			return Jframe;
		} else {
			cont[0]--;
			showMessageError("Hai selezionato troppe tiles per la colonna! Ripesca");
		}
		return null;
	}

	public int chooseCol() {
		int col = 0;
		boolean invalid = false;
		do {
			try {
				col = Integer.parseInt(JOptionPane.showInputDialog(null, "inserisci la colonna", "My Shelfie", 1));
				invalid = false;
				if (col < 0 || col > 4) {
					invalid = true;
					showMessageError("La colonna deve essere compresa tra 0 e 4.");
				}
			} catch (NumberFormatException e) {
				invalid = true;
				showMessageError("La colonna deve essere un intero.");
			}
		} while (invalid);
		return col;
	}

	public void showMessageError(String message) {
		JOptionPane pane = new JOptionPane();
		pane.showMessageDialog(frame, message);
	}

}
