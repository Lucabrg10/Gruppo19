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
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	
	private JPanel contentPane;
	private JTable tableBoard;
	private JButton btnChooseTiles;
	private JLabel lbPlayer;
	

	private JButton btnPersonalGoal;
	private JButton btnCommonGoal;
	private JPanel shelfPanel;
	private JTable shelfTable;
	private JLabel lbBoard;
	private JSplitPane splitPane;
	private JSplitPane splitPane_1;

	public JTable getShelfTable() {
		return shelfTable;
	}

	public void setShelfTable(JTable shelfTable) {
		this.shelfTable = shelfTable;
	}

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
		getContentPane().add(contentPane, BorderLayout.CENTER);
		Dimension dim = new Dimension(200,400);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnCommonGoal = new JButton("Vedi obiettivi comuni");
		
		btnPersonalGoal = new JButton("Vedi obiettivo personale");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(btnCommonGoal);
		panel.add(btnPersonalGoal);
		
		btnChooseTiles = new JButton("Pesca");
		panel.add(btnChooseTiles);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		splitPane_1 = new JSplitPane();
		panel_1.add(splitPane_1);
		
		lbBoard = new JLabel("BOARD");
		lbBoard.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane_1.setRightComponent(lbBoard);
		
		lbPlayer = new JLabel("");
		splitPane_1.setLeftComponent(lbPlayer);
		lbPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane_1.setDividerLocation(250);
		
		shelfPanel = new JPanel();
		shelfPanel.setBorder(new EmptyBorder(0, 0, 0, 20));
		shelfPanel.setForeground(Color.BLACK);
		shelfPanel.setBackground(Color.WHITE);
		//shelfPanel.setPreferredSize(new Dimension(200, 300));
		contentPane.add(shelfPanel, BorderLayout.CENTER);
		shelfPanel.setLayout(new BoxLayout(shelfPanel, BoxLayout.X_AXIS));
		
		splitPane = new JSplitPane();
		
		splitPane.setDividerLocation(250);
		shelfPanel.add(splitPane);
		
		
		
		shelfTable = new JTable();
		splitPane.setLeftComponent(shelfTable);
		shelfTable.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		shelfTable.setBackground(Color.WHITE);
		shelfTable.setForeground(Color.BLACK);
		shelfTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.tableBoard = new JTable();
		splitPane.setRightComponent(tableBoard);
		tableBoard.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		tableBoard.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableBoard.setPreferredScrollableViewportSize(tableBoard.getPreferredSize());
		tableBoard.setFillsViewportHeight(true);
		//Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		//setSize(size);
	}

	public JLabel getLbPlayerName() {
		return lbBoard;
	}

	public void setLbPlayerName(JLabel lbPlayerName) {
		this.lbBoard = lbPlayerName;
	}

	public JPanel getShelfPanel() {
		return shelfPanel;
	}

	public void setShelfPanel(JPanel shelfPanel) {
		this.shelfPanel = shelfPanel;
	}
}
