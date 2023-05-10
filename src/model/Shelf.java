package model;

public class Shelf {
	private final int rows=6;
	private final int columns=5;
	private TileType [][] shelf= new TileType[rows][columns];
	private int numOfPlayers;
	
	
	public Shelf(int numOfPlayers) {
		this.numOfPlayers=numOfPlayers; //player number
		this.shelf=new TileType[rows][columns];
	}	

	public TileType[][] getShelf() {
		return shelf;
	}

	public void setShelf(TileType[][] shelf) {
		this.shelf = shelf;
	}

	//Set all shelf cell to null
	public void initialize() {
		for (int i = 0; i < shelf.length; ++i) {
		      for(int j = 0; j < shelf[i].length; ++j) {
		        shelf[i][j]=null;
		      }
		    }
		
		//CREAZIONE QUADRATI COMM GOAL1
		shelf[2][1]=TileType.YELLOW;
		shelf[2][2]=TileType.YELLOW;
		shelf[4][3]=TileType.YELLOW;
		shelf[4][4]=TileType.YELLOW;
		
		shelf[1][1]=TileType.YELLOW;
		shelf[1][2]=TileType.YELLOW;
		shelf[3][3]=TileType.YELLOW;
		shelf[3][4]=TileType.YELLOW;
	}
	
	public void print() {
		System.out.println("Shelf Print");
		for (int i = 0; i < shelf.length; ++i) {
		      for(int j = 0; j < shelf[i].length; ++j) {
		        System.out.print(" | " + shelf[i][j]+" | ");
		      }
		      System.out.println("\n");
		    }
	}
	
	/**
	 * Controlla se in una colonna è possibile inserire un certo numero di carte
	 * @param columnSelection 	colonna da controllare se è libera
	 * @param numberOfCards		numero di carte che devo inserire
	 * @return true 			Non si può inserire
	 * @return false			Si possono inserire
	 */
	public boolean ControlFreeBoxes(int columnSelection, int numberOfCards) {
		int notFree=0;
		boolean control=false;
		for (int i=0; i<shelf[columnSelection].length; i++) {
				if(shelf[columnSelection][i]==null) {
				notFree=i-1;
			} 
			else {
				control=true;
				return control;
			}
			}
		if (numberOfCards>(rows-notFree)) {
			control=true;
		}
		return control;
	}
	
	/**
	 * Aggiunge la carta alla colonna
	 * @param columnSelection 	Colonna selezionata
	 * @param TileType				Tipo di carta da inserire
	 */
	public void addCard(int columnSelection, TileType card) {
		for (int i=0; i<shelf[columnSelection].length; i++) {

				if(shelf[columnSelection][i]==null) {
				shelf[columnSelection][i]=card;
				break;
				}
		}
	}
	
	public int getNumOfPlayers() {
		return numOfPlayers;
	}

		
}
