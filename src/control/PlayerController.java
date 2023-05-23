package control;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Player;
import model.Tile;
import view.LabelTile;
import view.MainFrame;

public class PlayerController {

	private MainFrame frame;
	private BoardController board;
	private Player player;
	private ArrayList<Tile> tilesChoosen;
	private ArrayList<Tile> tilesSorted;

	public PlayerController(MainFrame frame, BoardController board) {
		this.frame = frame;
		this.board = board;

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
	public JFrame selectOrderOfTiles(ArrayList<Tile> tilesChoosen, Player playerNext) {

		int col = chooseCol();

		this.tilesChoosen = tilesChoosen;
		ArrayList<Integer> positions = new ArrayList<>();

		JFrame Jframe = new JFrame();
		JPanel panelLabel = new JPanel(new FlowLayout());

		for (int i = 0; i < tilesChoosen.size(); i++) {
			LabelTile label = new LabelTile(tilesChoosen.get(i));
			System.out.println(tilesChoosen.get(i));
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
						System.out.println(player.getPersonalGoal().counterPersonalGoalPoint(player.getShelf()));
						player.setPoints(player.getPersonalGoal().counterPersonalGoalPoint(player.getShelf()));
						System.out.println("Punteggio "+player.getPlayerName()+" : "+player.getPoints());
						frame.getLbPoints().setText("Punteggio: "+player.getPoints());
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
				setPlayer(playerNext);
				Jframe.setVisible(false);
				frame.setVisible(true);
				frame.getShelfTable().setModel(player.getShelf());
				if(board.getBoard().checkForRefill()) {
					board.getBoard().refillBoard();
					
				}
				frame.getLbPoints().setText("Punteggio: "+player.getPoints());
				//frame.getLbPlayerName().setText(player.getPlayerName());

				// player.getShelf().fireTableDataChanged();
			}
		});
		panelLabel.add(button);
		Jframe.add(panelLabel);
		Jframe.pack();
		Jframe.setLocationRelativeTo(null);

		return Jframe;
	}

	public int chooseCol() {

		int col = Integer.parseInt(JOptionPane.showInputDialog(null, "inserisci la colonna", "My Shelfie", 1));
		return col;
	}

}
