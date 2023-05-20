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

	public BoardController(MainFrame frame, Board board) {

		this.frame = frame;
		this.setBoard(board);
		this.table = frame.getTableBoard();
		table.setModel(board);
		/*assignTableController();
		assignBtnChooseController();*/

	}


	public Board getBoard() {
		return board;
	}


	public void setBoard(Board board) {
		this.board = board;
	}

	/*private void assignBtnChooseController() {
		// TODO Auto-generated method stub
		frame.getBtnChooseTiles().addActionListener(new ActionListener() {

			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				for (Tile tile : tilesChoosen) {
					board.removeTile(tile);
				//	System.out.println("Funziona");
				}
				playerController.addCardsToShelf(tilesChoosen);
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
					boolean isRow=true;
					// do some action if appropriate column
					if (!board.isTileEmpty(row, column)) {
						if (board.isTileAvailable(row, column)) {
							if (tilesChoosen.size() < 3) {
								// check if the tiles are aligned on the same column or row
								if (tilesChoosen.size() == 0) {
									prevRow = row;
									prevCol = column;
									tilesChoosen.add(board.getValueOfTileAt(row, column));
								} else {
									if (row == prevRow && isRow) {
										if(column == prevCol+1 || column == prevCol-1) {
											prevRow = row;
											prevCol = column;
											isRow=true;
											tilesChoosen.add(board.getValueOfTileAt(row, column));
										}else {
											System.out.println("Le tiles devono essere allineate");
										}
										
									} else if (column == prevCol) {
										if(row == prevRow+1 || column == prevRow-1) {
											prevCol = column;
											prevRow = row;
											isRow=false;
											tilesChoosen.add(board.getValueOfTileAt(row, column));
										}else {
											System.out.println("Le tiles devono essere allineate");
										}
										
									}else {
										System.out.println("Le tiles devono essere allineate");
									}
								}
							}

						} else {
							System.out.println("La cella non è disponibile");
						}

					} else {
						System.out.println("error");
					}

					System.out.println("Cliccato" + row + " - " + column);
				}
			}
		});
	}*/

}
