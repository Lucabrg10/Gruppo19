package model;

import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * This class represents a tile.
 * every tile has an ID, a color (picked from ColorTile enum) and an image
 */
public class Tile {

	private static int ID;
	private ColorTile color;
	private ImageIcon img;

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
	
	/**
	 * Constructor of Tile: assigns every color its own image
	 * @param color Enum with color
	 */
	public Tile(ColorTile color) {
		this.color = color;

		switch (color) {
		case BLUE: {
			this.img = new ImageIcon("assets/myshelfie/item_tiles/50x50/Cornici1." + RandomizeImages() + ".png");
		}
			break;
		case CYAN: {
			this.img = new ImageIcon( "assets/myshelfie/item_tiles/50x50/Trofei1." + RandomizeImages() + ".png");
		}
			break;
		case GREEN: {
			this.img = new ImageIcon("assets/myshelfie/item_tiles/50x50/Gatti1." + RandomizeImages() + ".png");
		}
			break;
		case PINK: {
			this.img = new ImageIcon("assets/myshelfie/item_tiles/50x50/Piante1." + RandomizeImages() + ".png");
		}
			break;
		case WHITE: {
			this.img = new ImageIcon( "assets/myshelfie/item_tiles/50x50/Libri1." + RandomizeImages() + ".png");
		}
			break;
		case YELLOW: {
			this.img = new ImageIcon("assets/myshelfie/item_tiles/50x50/Giochi1." + RandomizeImages() + ".png");
		}
			break;

		default:
			break;
		}

		
		this.ID++;
	}
	
	public Tile( ColorTile color2, ImageIcon img2) {
		this.color=color2;
		this.img=img2;
	}

	public ColorTile getColor() {
		return color;
	}

	public void setColor(ColorTile color) {
		this.color = color;
	}
	
	/**
	 * Used to get random number to set an Image of a Tile
	 * @return random number from 1 to 3 (int)
	 */
	private int RandomizeImages() {
		Random rnd = new Random();
		int val = rnd.nextInt(3) +1;
		return val;
	}
}
