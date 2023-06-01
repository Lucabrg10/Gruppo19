package main;
import control.MainController;
import model.Board;
import view.MainFrame;

/**
 * It starts the game
 */
public class Main {
	public static void main(String[] args) {
		MainFrame main = new MainFrame();
		MainController mc = new MainController(main);
	}
}
