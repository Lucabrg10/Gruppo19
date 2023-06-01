package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.Board;
import model.Player;
import model.Tile;
import view.FinalFrame;
import view.MainFrame;
import model.CommonGoal;

/**
 * 
 * This class manage all the controller of the main frame and it allows the user
 * to click on the buttons and click on the board and trigger event
 *
 */
public class MainframeController {

	private MainFrame frame;
	private JTable table;
	private JTable shelfTable;
	private List<CommonGoal> commonGoalsList;
	private PlayerController playerC;
	private ArrayList<Tile> tilesChoosen = new ArrayList<>();
	private ArrayList<Player> players;

	private Board board;
	int prevRow;
	int prevCol;
	int cont[] = { 0 };

	public MainframeController(MainFrame frame, BoardController boardC, PlayerController player, Board board,
			ArrayList<Player> players, List<CommonGoal> commonGoalsList) {
		this.frame = frame;
		this.playerC = player;
		this.board = board;
		this.table = frame.getTableBoard();
		this.players = players;
		this.playerC.setPlayer(players.get(0));
		this.shelfTable = frame.getShelfTable();
		this.commonGoalsList = commonGoalsList;

		shelfTable.setModel(players.get(0).getShelf());
		frame.getLbPoints()
				.setText("Punteggio di " + player.getPlayer().getPlayerName() + ": " + playerC.getPlayer().getPoints());
		frame.getLbTileClicked().setText("");
		assignTableController();
		assignBtnChooseController();
		assignBtnPersonalGoalController();
		assignBtnCommonGoalController();
		assignBtnDeselctTilesController();
	}

	/**
	 * assign to the button BtnDeselctTiles method that allows an user to deselect
	 * the tiles that him choose
	 */
	private void assignBtnDeselctTilesController() {
		frame.getBtnDeselectTiles().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tilesChoosen.clear();
				showMessageError("Le tiles sono state deselezionate! Puoi sceglierne altre");

			}
		});

	}

	/**
	 * assign to the button BtnCommonGoal method that show to an user the common
	 * goals
	 */
	private void assignBtnCommonGoalController() {
		frame.getBtnCommonGoal().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = new JFrame("Common Goals");
				JPanel jPanel = new JPanel();

				JLabel label1 = new JLabel();
				JLabel label2 = new JLabel();
				JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, label1, label2);
				jPanel.add(jSplitPane);
				label1.setIcon(commonGoalsList.get(0).getImageGoal());
				label2.setIcon(commonGoalsList.get(1).getImageGoal());
				frame.add(jPanel);
				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});

	}

	/**
	 * assign to the button BtnDeselctTiles method that show to an user the personal
	 * goals
	 */
	private void assignBtnPersonalGoalController() {

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
				frame.setLocationRelativeTo(null);
			}
		});
	}

	/**
	 * assign to the button BtnDeselctTiles method that allows an user to deselect
	 * the tiles that him choose
	 */
	private void assignBtnChooseController() {
		// TODO Auto-generated method stub
		frame.getBtnChooseTiles().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (tilesChoosen.size() > 0) {
					cont[0]++;
					cont[0] = (cont[0]) % board.getNumOfPlayers();
					JDialog jframe = playerC.selectOrderOfTiles(tilesChoosen, players.get(cont[0]), commonGoalsList,
							cont, players);
					if (jframe != null) {
						jframe.setVisible(true);
					}
					tilesChoosen.clear();

				} else {
					showMessageError("Devi pescare almeno una tile!");
				}

			}

		});

	}

	public void showMessageError(String message) {
		JOptionPane pane = new JOptionPane();
		pane.showMessageDialog(frame, message);
	}

	/**
	 * method that allows to check if the tiles are aligned before take them from
	 * the board
	 * 
	 * @param isRow  boolean that show if the user choose a row or column
	 * @param row
	 * @param column
	 * @return
	 */
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

	/**
	 * assign a controller to the Table method that allows an user to select the
	 * tiles that him choose from the board
	 */
	public void assignTableController() {
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					boolean isRow = true;
					if (!board.isTileEmpty(row, column)) {
						if (board.isTileAvailable(row, column)) {
							if (tilesChoosen.size() < 3) {
								if (tilesChoosen.size() == 0) {
									prevRow = row;
									prevCol = column;
									tilesChoosen.add(board.getValueOfTileAt(row, column));
									frame.getLbTileClicked().setText("Tile selezionata: " + row + " - " + column);
								} else {
									if (!tileIsInTiles(board.getValueOfTileAt(row, column))) {
										if (row == prevRow && isRow) {
											if (checkIfIsNext(isRow, row, column)) {
												isRow = true;
												tilesChoosen.add(board.getValueOfTileAt(row, column));
												frame.getLbTileClicked()
														.setText("Tile selezionata: " + row + " - " + column);
											} else {
												System.out.println("Le tiles devono essere allineate");
												showMessageError("Le tiles devono essere allineate");
											}

										} else if (column == prevCol) {
											isRow = false;
											if (checkIfIsNext(isRow, row, column)) {
												tilesChoosen.add(board.getValueOfTileAt(row, column));
												frame.getLbTileClicked()
														.setText("Tile selezionata: " + row + " - " + column);
											} else {
												System.out.println("Le tiles devono essere allineate");
												showMessageError("Le tiles devono essere allineate");
											}

										} else {
											System.out.println("Le tiles devono essere allineate");
											showMessageError("Le tiles devono essere allineate");
										}
									} else {
										showMessageError("Non puoi pescare la stessa tile");
									}

								}
							} else {
								showMessageError("Non puoi pescare più di tre tile");
							}

						} else {
							System.out.println("La cella non \u00E8 disponibile");
							showMessageError("La cella non \u00E8 disponibile");
						}

					} else {
						System.out.println("error");
						showMessageError("La cella \u00E8 vuota");
					}
				}
			}
		});
	}

}
