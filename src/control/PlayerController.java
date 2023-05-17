package control;

import java.awt.FlowLayout;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import model.Player;
import model.Tile;
import view.MainFrame;

public class PlayerController {

	private MainFrame frame;
	private BoardController board;
	private Player player;
	private ArrayList<Tile> tilesChoosen;

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
		this.frame.getLbPlayer().setText(this.player.getUserName());
		this.frame.setVisible(true);
	}
	public void selectOrderOfTiles(ArrayList<Tile> tilesChoosen) {
		this.tilesChoosen= tilesChoosen;
		JFrame Jframe = new JFrame();
		JPanel panel = new JPanel(new FlowLayout());
		for(int i=0;i<tilesChoosen.size();i++) {
			JLabel label = new JLabel("");
			label.setIcon(tilesChoosen.get(i).getImg());
			panel.add(label);
		}
		Jframe.add(panel);
		Jframe.pack();
		Jframe.setVisible(true);
		player.addTiles(tilesChoosen);
	}
	

}
