package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	// private BoardController boardC;
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
		frame.getLbPoints().setText("Punteggio: " + playerC.getPlayer().getPoints());
		// assegno un listener alla tabella e al pulsante "prova"
		assignTableController();
		assignBtnChooseController();
		assignShelfTableController();
		assignBtnPersonalGoal();
		// assignBtnShelfController();
	}

	private void assignBtnPersonalGoal() {

		frame.getBtnPersonalGoal().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = new JFrame("Player " + playerC.getPlayer().getPlayerName());
				JPanel jPanel = new JPanel();
				JLabel label = new JLabel();
				jPanel.add(label);
				label.setIcon(playerC.getPlayer().getPersonalGoal().getImgGoal());
				frame.add(jPanel);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

	private void assignBtnChooseController() {
		// TODO Auto-generated method stub
		frame.getBtnChooseTiles().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				cont++;
				cont = (cont) % board.getNumOfPlayers();
				JDialog jframe = playerC.selectOrderOfTiles(tilesChoosen, players.get(cont));
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

	public void showMessageError(String message) {
		JOptionPane pane = new JOptionPane();
		pane.showMessageDialog(frame, message);
	}

	public boolean checkIfIsNext(boolean isRow, int row, int column) {

		if (isRow) {
			for (Tile tile2 : tilesChoosen) {
				if (column + 1 <= 8 && board.getValueOfTileAt(row, column + 1) == tile2) {
					return true;
				} else if (column - 1 >= 0 && board.getValueOfTileAt(row, column - 1) == tile2) {
					return true;
				}

			}

		} else {
			for (Tile tile2 : tilesChoosen) {
				if (row + 1 <= 8 && board.getValueOfTileAt(row + 1, column) == tile2) {
					return true;
				}
				if (row - 1 >= 0 && board.getValueOfTileAt(row - 1, column) == tile2) {
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
												showMessageError("Le tiles devono essere allineate");
											}

										} else if (column == prevCol) {
											isRow = false;
											if (checkIfIsNext(isRow, row, column)) {
												// prevCol = column;
												// prevRow = row;

												tilesChoosen.add(board.getValueOfTileAt(row, column));
											} else {
												System.out.println("Le tiles devono essere allineate");

											}

										} else {
											System.out.println("Le tiles devono essere allineate");
											showMessageError("Le tiles devono essere allineate");
										}
									} else {
										showMessageError("Non puoi pescare la stessa tile");
									}

								}
							}

						} else {
							System.out.println("La cella non \u00E8 disponibile");
							showMessageError("La cella non \u00E8 disponibile");
						}

					} else {
						System.out.println("error");
						showMessageError("La cella \u00E8 vuota");
					}

					System.out.println("Cliccato" + row + " - " + column);
				}
			}
		});
	}

}
