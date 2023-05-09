package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bag {

	List<Tile> tiles = new ArrayList<Tile>();

	public Bag() {

		int numberOfTiles = 132;
		HashMap<ColorTile, Integer> tilesCardColor = new HashMap<ColorTile, Integer>();
		tilesCardColor.put(ColorTile.BLUE, 22);
		tilesCardColor.put(ColorTile.CYAN, 22);
		tilesCardColor.put(ColorTile.GREEN, 22);
		tilesCardColor.put(ColorTile.PINK, 22);
		tilesCardColor.put(ColorTile.WHITE, 22);
		tilesCardColor.put(ColorTile.YELLOW, 22);

		for (int i = 0; i < numberOfTiles; i++) {
			Tile tile = new Tile(ColorTile.random());
			if (tilesCardColor.get(tile.getColor()) > 0) {
				tiles.add(tile);
				tilesCardColor.put(tile.getColor(), tilesCardColor.get(tile.getColor()) + 1);
			}
		}
		printBag();

	}

	public void printBag() {
		for (Tile tile : tiles) {
			System.out.println(" " + tile.getColor());
		}
	}

}
