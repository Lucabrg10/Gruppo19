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

public class MainFrame extends JFrame {

	
	private JPanel contentPane;
	private JTable tableBoard;

	public JTable getTableBoard() {
		return tableBoard;
	}

	public void setTableBoard(JTable tableBoard) {
		this.tableBoard = tableBoard;
	}

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
*/
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
		contentPane.setLayout(new BorderLayout(0, 0));
		

	
		contentPane.add(tableBoard);
		//Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		//setSize(size);
	}
}
