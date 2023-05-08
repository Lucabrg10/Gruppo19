package model;

public abstract class Goal {
	private int points;
	
	public void addPoint(int player, int points) {
		this.points=this.points+points;
	}
	
	public void printPoint(int player, int points) {
		System.out.println("Player: " + player + "Point: " + points);
	}
}
