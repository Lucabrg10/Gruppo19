package model;

import java.util.Random;

/**
 * this enum contains the possible values of a tile.
 * it can be a colored tile or an empty tile.
 */

public enum ColorTile {
	YELLOW,
	CYAN,
	BLUE,
	GREEN,
	PINK,
	WHITE,
	EMPTY;

    public static ColorTile random ()
    {
        Random rnd = new Random ();

        return values()[rnd.nextInt (6)];
    }
}