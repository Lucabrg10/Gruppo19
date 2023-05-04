package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

	
	private JPanel contentPane;
	private JTable tableBoard;
	JOptionPane jOptionPane = new JOptionPane();
	
	public JOptionPane getjOptionPane() {
		return jOptionPane;
	}

	public void setjOptionPane(JOptionPane jOptionPane) {
		this.jOptionPane = jOptionPane;
	}

	public JTable getTableBoard() {
		return tableBoard;
	}

	public void setTableBoard(JTable tableBoard) {
		this.tableBoard = tableBoard;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		
		setTitle("My Shelfie");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		//setSize(size);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableBoard = new JTable();
		
		tableBoard.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableBoard.setBounds(75, 55, 278, 144);
		contentPane.add(tableBoard);
	}
}
