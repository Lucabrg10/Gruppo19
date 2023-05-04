package model;

public class Shelf {
	private final int columns=5;
	private final int rows=6;
	private int [][] shelf= new int[columns][rows];
	private int player;
	
	
	public Shelf(int player) {
		this.player=player; //player number
	}
	
	//Set all shelf cell to null
	public void reset() {
		for (int i = 0; i < shelf.length; ++i) {
		      for(int j = 0; j < shelf[i].length; ++j) {
		        shelf[i][j]=-1;
		      }
		    }
	}
	
	public void print() {
		for (int i = 0; i < shelf.length; ++i) {
		      for(int j = 0; j < shelf[i].length; ++j) {
		        System.out.println(shelf[i][j]);
		      }
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
			if(shelf[i]==null) {
				notFree=i-1;
			} 
			else {
				control=true;
				return control;
			}
		}
		if (numberOfCards>(columns-notFree)) {
			control=true;
		}
		return control;
	}
	
	/**
	 * Aggiunge la carta alla colonna
	 * @param columnSelection 	Colonna selezionata
	 * @param card				Tipo di carta da inserire
	 */
	/*public void addCard(int columnSelection, Tile card) {
		for (int i=0; i<shelf[columnSelection].length; i++) {
			if(shelf[i]==null) {
				shelf[i]=card;
			}
		}
	}
	*/
		
}
