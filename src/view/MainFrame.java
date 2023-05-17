package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextPane;

public class MainFrame extends JFrame {

	
	private JPanel contentPane;
	private JTable tableBoard;
	private JButton btnChooseTiles;
	private JLabel lbPlayer;
	private JButton btnShelf;
	public JButton getBtnShelf() {
		return btnShelf;
	}

	public void setBtnShelf(JButton btnShelf) {
		this.btnShelf = btnShelf;
	}

	private JButton btnPersonalGoal;
	private JButton btnCommonGoal;

	public JLabel getLbPlayer() {
		return lbPlayer;
	}

	public void setLbPlayer(JLabel lbPlayer) {
		this.lbPlayer = lbPlayer;
	}

	public JButton getBtnChooseTiles() {
		return btnChooseTiles;
	}

	public void setBtnChooseTiles(JButton btnChooseTiles) {
		this.btnChooseTiles = btnChooseTiles;
	}

	public JTable getTableBoard() {
		return tableBoard;
	}

	public void setTableBoard(JTable tableBoard) {
		this.tableBoard = tableBoard;
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		
		setTitle("My Shelfie");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.tableBoard = new JTable();
		getContentPane().add(contentPane, BorderLayout.CENTER);
		Dimension dim = new Dimension(500,500);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(tableBoard);
		tableBoard.setPreferredScrollableViewportSize(tableBoard.getPreferredSize());
		tableBoard.setFillsViewportHeight(true);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnShelf = new JButton("Vedi Shelf");
		panel.add(btnShelf);
		
		btnPersonalGoal = new JButton("Vedi obiettivo personale");
		panel.add(btnPersonalGoal);
		
		btnCommonGoal = new JButton("Vedi obiettivi comuni");
		panel.add(btnCommonGoal);
		
		btnChooseTiles = new JButton("Pesca");
		panel.add(btnChooseTiles);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		lbPlayer = new JLabel("");
		panel_1.add(lbPlayer);
		//Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		//setSize(size);
	}
}
