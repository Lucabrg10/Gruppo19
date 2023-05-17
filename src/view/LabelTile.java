package view;

import javax.swing.JLabel;

import model.Tile;

public class LabelTile extends JLabel {
	
	private Tile tile;
	
	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public LabelTile(Tile tile) {
		super();
		this.tile= tile;
	}

}
