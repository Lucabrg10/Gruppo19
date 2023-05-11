package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Board;

public class BoardController {
	
	private JTable table;
	private Board board;
	
	public BoardController(JTable table,Board board) {
		
		this.table=table;
		this.board=board;
		table.setModel(board);
		board.fireTableDataChanged();
		table.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				    if (e.getClickCount() == 1) {
				      JTable target = (JTable)e.getSource();
				      int row = target.getSelectedRow();
				      int column = target.getSelectedColumn();
				      // do some action if appropriate column
				      System.out.println("Cliccato"+row+" - "+column);
				    }
				  }
				});
	}
	

}
