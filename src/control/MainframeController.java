package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JTable;

import model.Board;
import model.Player;
import model.Tile;
import view.MainFrame;

public class MainframeController {
	
	private MainFrame frame;
	private JTable table;
	private BoardController boardC;
	private PlayerController playerC;
	private ArrayList<Tile> tilesChoosen = new ArrayList<>();
	private ArrayList<Player> players;
	private Board board;
	int prevRow;
	int prevCol;
	int cont=0;
	public MainframeController(MainFrame frame, BoardController boardC,PlayerController player, Board board, ArrayList<Player> players) {
		this.frame = frame;
		this.boardC = boardC;
		this.playerC = player;
		this.board = board;
		this.table = frame.getTableBoard();
		this.players = players;
		this.playerC.setPlayer(players.get(cont));
		//assegno un listener alla tabella e al pulsante "prova"
		assignTableController();
		assignBtnChooseController();
		assignBtnShelfController();
	}
	private void assignBtnShelfController() {
		// TODO Auto-generated method stub
		frame.getBtnShelf().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playerC.getPlayer().getShelf().print();
			}
		});
	}
	private void assignBtnChooseController() {
		// TODO Auto-generated method stub
		frame.getBtnChooseTiles().addActionListener(new ActionListener() {

			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playerC.selectOrderOfTiles(tilesChoosen);
				for (Tile tile : tilesChoosen) {
					board.removeTile(tile);
				//	System.out.println("Funziona");
				}
				
				tilesChoosen.clear();
				cont=(cont+1)%board.getNumOfPlayers();
				frame.setVisible(false);
				playerC.setPlayer(players.get(cont));
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
	}

}
