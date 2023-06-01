package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Board;
import model.Tile;
import view.MainFrame;

/**
 * The class is used to connect the board to the table
 *
 */
public class BoardController {

	private MainFrame frame;
	private JTable table;
	private Board board;

	public BoardController(MainFrame frame, Board board) {

		this.frame = frame;
		this.setBoard(board);
		this.table = frame.getTableBoard();
		table.setModel(board);
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}
