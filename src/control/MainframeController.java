package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.Board;
import model.Player;
import model.Tile;
import view.MainFrame;

public class MainframeController {

	private MainFrame frame;
	private JTable table;
	private JTable shelfTable;
	private BoardController boardC;
	private PlayerController playerC;
	private ArrayList<Tile> tilesChoosen = new ArrayList<>();
	private ArrayList<Player> players;
	private Board board;
	int prevRow;
	int prevCol;
	int cont = 0;

	public MainframeController(MainFrame frame, BoardController boardC, PlayerController player, Board board,
			ArrayList<Player> players) {
		this.frame = frame;
		this.playerC = player;
		this.board = board;
		this.table = frame.getTableBoard();
		this.players = players;
		this.playerC.setPlayer(players.get(0));
		this.shelfTable = frame.getShelfTable();
		shelfTable.setModel(players.get(0).getShelf());

		// assegno un listener alla tabella e al pulsante "prova"
		assignTableController();
		assignBtnChooseController();
		assignShelfTableController();
		// assignBtnShelfController();
	}
	/*
	 * private void assignBtnShelfController() { // TODO Auto-generated method stub
	 * frame.getBtnShelf().addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
	 * method stub playerC.getPlayer().getShelf().print(); } }); }
	 */

	private void assignBtnChooseController() {
		// TODO Auto-generated method stub
		frame.getBtnChooseTiles().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				cont++;
				cont = (cont) % board.getNumOfPlayers();
				JFrame jframe = playerC.selectOrderOfTiles(tilesChoosen, players.get(cont));
				/*	
				*/
				tilesChoosen.clear();
				// playerC.getPlayer().getShelf().fireTableDataChanged();
				/*
				 * if(board.checkForRefill()) { board.refillBoard(); }
				 */
				jframe.setVisible(true);

			}

		});

	}

	public void assignShelfTableController() {
		shelfTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					System.out.println(shelfTable.getValueAt(row, column));
				}
			}
		});
	}

	public void showMessageError() {
		JOptionPane pane = new JOptionPane();
		pane.showMessageDialog(frame, "Le tiles devono essere allineate");
	}

	public boolean checkIfIsNext(boolean isRow, int row, int column) {

		if (isRow) {
			for (Tile tile2 : tilesChoosen) {
				if (board.getValueOfTileAt(row, column + 1) == tile2
						|| board.getValueOfTileAt(row, column - 1) == tile2) {
					return true;
				}
			}

		} else {
			for (Tile tile2 : tilesChoosen) {
				if (board.getValueOfTileAt(row + 1, column) == tile2
						|| board.getValueOfTileAt(row - 1, column) == tile2) {
					return true;
				}
			}
		}
		return false;

	}

	public boolean tileIsInTiles(Tile tile1) {
		for (Tile tile : tilesChoosen) {
			if (tile == tile1) {
				return true;
			}
		}
		return false;
	}

	public void assignTableController() {
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					boolean isRow = true;
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
									if (!tileIsInTiles(board.getValueOfTileAt(row, column))) {
										if (row == prevRow && isRow) {
											if (checkIfIsNext(isRow, row, column)) {
												// prevRow = row;
												// prevCol = column;
												isRow = true;
												tilesChoosen.add(board.getValueOfTileAt(row, column));
											} else {
												System.out.println("Le tiles devono essere allineate");
												showMessageError();
											}

										} else if (column == prevCol) {
											isRow = false;
											if (checkIfIsNext(isRow, row, column)) {
												// prevCol = column;
												// prevRow = row;
												
												tilesChoosen.add(board.getValueOfTileAt(row, column));
											} else {
												System.out.println("Le tiles devono essere allineate");
												showMessageError();
											}

										} else {
											System.out.println("Le tiles devono essere allineate");
											showMessageError();
										}
									} else {
										JOptionPane pane = new JOptionPane();
										pane.showMessageDialog(frame, "Non puoi pescare la stessa carta");
									}

								}
							}

						} else {
							System.out.println("La cella non \u00E8 disponibile");
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
