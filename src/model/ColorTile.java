package model;

import java.util.Random;

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