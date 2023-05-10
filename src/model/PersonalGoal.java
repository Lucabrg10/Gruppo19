package model;

import java.util.ArrayList;

public class PersonalGoal extends Goal{
	private int numOfPlayer;
	private int goalNumber;
	private Shelf pGoal1;
	private static ArrayList<Shelf> personalGoal;
	
	
	
	public PersonalGoal(int numOfPlayer, int goalNumber) {
		this.numOfPlayer = numOfPlayer;
		this.goalNumber=goalNumber;
	}
	
	public void controlPersonalGoal(){
		
	}
	
	
	
}
