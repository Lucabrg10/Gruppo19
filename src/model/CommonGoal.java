package model;

public class CommonGoal extends Goal{
	private final int columns=5;
	private final int rows=6;
	private int numOfPlayer;
	private int goalNumber;
	private Tile [] cellControl= new Tile[6];
	private Tile [][] playerShelf= new Tile[columns][rows];
	
	public CommonGoal(int numOfPlayer) {
		this.numOfPlayer = numOfPlayer;
	}
	
	/*public boolean controlGoal1(Shelf playerShelf) {
		int square = 0;
		TileType prevcard=null;
		for (TileType cards : ColorTile.values()) {
			if(cards==null) {
				break;
			}
			for (int i = 1; i < playerShelf.getShelf().length; ++i) {
		      for(int j = 1; j < playerShelf.getShelf()[i].length; ++j) {
		        if(playerShelf.getShelf()[i][j].equals(cards) && playerShelf.getShelf()[i][j-1].equals(cards) 
		        		&& playerShelf.getShelf()[i-1][j].equals(cards) && playerShelf.getShelf()[i-1][j-1].equals(cards)) {
		        		if(i<3 && j<4) {
		        			if(!playerShelf.getShelf()[i+1][j].equals(cards) && !playerShelf.getShelf()[i][j+1].equals(cards)) {
		        				square++;
		        			}
		        			}
		        		else {
		        			if(!playerShelf.getShelf()[i-2][j].equals(cards) && !playerShelf.getShelf()[i][j-2].equals(cards)) {
		        				square++;
		        			}
		        		}
		        		if (square==2 && cards==prevcard) {
		        			return true;
		        		}
		        		prevcard=cards;
		        }
		      }
			}	
		}
		return false;
	}
	*/
	
}
