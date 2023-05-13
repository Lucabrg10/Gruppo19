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

public class BoardController {

	private MainFrame frame;
	private JTable table;
	private Board board;
	private ArrayList<Tile> tilesChoosen = new ArrayList<>();

	public BoardController(MainFrame frame, Board board) {

		this.frame = frame;
		this.board = board;
		this.table = frame.getTableBoard();
		table.setModel(board);
		assignTableController();
		assignBtnChooseController();

	}

	private void assignBtnChooseController() {
		// TODO Auto-generated method stub
		frame.getBtnChooseTiles().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (Tile tile : tilesChoosen) {
					board.removeTile(tile);
					System.out.println("Funziona");
				}
				tilesChoosen.clear();
			}
		});
	}

	public void assignTableController() {
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					// do some action if appropriate column
					if (!board.isTileEmpty(row, column)) {
						if (board.isTileAvailable(row, column)) {
							if(tilesChoosen.size()<3) {
								tilesChoosen.add(board.getValueOfTileAt(row, column));
							}
						}

					} else {
						System.out.println("error");
					}

					System.out.println("Cliccato" + row + " - " + column);
				}
			}
		});
	}

}
