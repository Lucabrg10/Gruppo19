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
		this.frame.getLbPlayer().setText(this.player.getPlayerName());
		this.frame.setVisible(true);
	}

	/**
	 * 
	 * @param tilesChoosen Lista delle tessere scelte
	 * 
	 * Il metodo crea un jframe che permette di vedere le carte scelte
	 * e cliccando su esse si sceglie l'ordine. Le carte in ordine vengono inserite
	 * in tilesSorted e inviate al player che le aggiungerà nella colonna desiderata
	 * 
	 */
	public void selectOrderOfTiles(ArrayList<Tile> tilesChoosen) {
		
		int col=chooseCol();

		this.tilesChoosen= tilesChoosen;
		ArrayList<Integer>positions = new ArrayList<>();
		
		
		JFrame Jframe = new JFrame();
		JPanel panelLabel = new JPanel(new FlowLayout());
		
		for(int i=0;i<tilesChoosen.size();i++) {
			LabelTile label = new LabelTile(tilesChoosen.get(i));
			
		
			label.setIcon(tilesChoosen.get(i).getImg());
			label.addMouseListener(new MouseAdapter()   {   

		        public void mouseClicked(MouseEvent e)   
		        {   
		        	
		        	player.addTile(label.getTile(),col);
		        	Container container = label.getParent();
		        	container.remove(label);
		        	container.validate();
		        	container.repaint();
		        }   
		        
		});
			panelLabel.add(label);
		}
		
		JButton button = new JButton("Inserisci!");
		System.out.println(button.isVisible()); 
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Jframe.setVisible(false);
	        	frame.setVisible(true);
	        	player.getShelf().print();
			}
		});
		panelLabel.add(button);
	/*	LabelTile label = new LabelTile(tilesChoosen.get(tilesChoosen.size()-1));
		label.setIcon(tilesChoosen.get(tilesChoosen.size()-1).getImg());
		label.addMouseListener(new MouseAdapter()   {   

	        public void mouseClicked(MouseEvent e)   
	        {   
	        	
	        	player.addTile(label.getTile(),col);
	        	Container container = label.getParent();
	        	container.remove(label);
	        	container.validate();
	        	container.repaint();
	        	Jframe.setVisible(false);
	        	frame.setVisible(true);
	        	player.getShelf().print();
	        }   
		});
		
		panelLabel.add(label);*/
		Jframe.add(panelLabel);
		Jframe.pack();
		Jframe.setLocationRelativeTo(null);
		Jframe.setVisible(true);
		
		
		
		//
	}
	
	public int chooseCol() {
	
		int col = Integer.parseInt(JOptionPane.showInputDialog(null, "inserisci la colonna", "My Shelfie", 1));
		return col;
	}
	
	

}
