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
import java.awt.Font;

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
	private JPanel panel_2;
	private JLabel lbMyShelfieTitle;

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
		getContentPane().setBackground(Color.DARK_GRAY);
		setBackground(Color.WHITE);
		
		
		setTitle("My Shelfie");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		Dimension dim = new Dimension(200,400);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
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
		splitPane_1.setForeground(Color.WHITE);
		splitPane_1.setBackground(Color.DARK_GRAY);
		panel_1.add(splitPane_1);
		
		lbBoard = new JLabel("BOARD");
		lbBoard.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbBoard.setForeground(Color.WHITE);
		lbBoard.setBackground(Color.DARK_GRAY);
		lbBoard.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane_1.setRightComponent(lbBoard);
		
		lbPlayer = new JLabel("");
		lbPlayer.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbPlayer.setForeground(Color.WHITE);
		lbPlayer.setBackground(Color.DARK_GRAY);
		splitPane_1.setLeftComponent(lbPlayer);
		lbPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		splitPane_1.setDividerLocation(350);
		 
		splitPane_1.setEnabled(false);
		shelfPanel = new JPanel();
		shelfPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		shelfPanel.setForeground(Color.BLACK);
		shelfPanel.setBackground(Color.WHITE);
		//shelfPanel.setPreferredSize(new Dimension(200, 300));
		contentPane.add(shelfPanel, BorderLayout.CENTER);
		shelfPanel.setLayout(new BoxLayout(shelfPanel, BoxLayout.X_AXIS));
		
		splitPane = new JSplitPane();
		
		splitPane.setDividerLocation(350);
		shelfPanel.add(splitPane);
		
		
		
		shelfTable = new JTable();
		shelfTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				
			}
		));
		shelfTable.setEnabled(false);
		splitPane.setLeftComponent(shelfTable);
		splitPane.setEnabled(false);
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
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		getContentPane().add(panel_2, BorderLayout.NORTH);
		
		lbMyShelfieTitle = new JLabel("");
		//System.out.println(new ImageIcon("/assets/myshelfie/pub/title1"));
		lbMyShelfieTitle.setIcon(new ImageIcon("C:\\Users\\Black\\eclipse-workspace\\myshelfie\\Gruppo19\\assets\\myshelfie\\pub\\title1.png"));
		panel_2.add(lbMyShelfieTitle);
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
