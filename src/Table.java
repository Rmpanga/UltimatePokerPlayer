import java.util.ArrayList;


public class Table {
	
	/* TODO
	 * Test clear table
	 * Test addCardsToTable
	 */
	
	private ArrayList<Card> cards_on_table; 
	private int pot;
	
	/*
	 *  Creating a new table 
	 */
	public Table() {
		cards_on_table = new ArrayList<Card>();
		pot = 0;
	}
	
	/*
	 * Dealer plays flop
	 */
	public void flop(){
		addCardsToTable(3);
	}
	
	/*
	 * Dealer plays turn
	 */
	public void turn(){
		addCardsToTable(1);
	}
	
	/*
	 * Dealer plays river
	 */
	public void river(){
		addCardsToTable(1);
		
	}
	
	/*
	 * Add specific amount of cards into the table for both players to see
	 */
	private void addCardsToTable(int num){
	   
		for(int j =0; j < num; j++){
			Card card = Deck.deck.remove(0);
			cards_on_table.add(card);
	   }
	}
	
	/*
	 * Return cards on table
	 */
	
	public ArrayList<Card> getCardsOnTable(){
		
	 return cards_on_table;
	}
	
	/*
	 * Clears cards_on_table
	 */
	
	public void clearTable(){
		cards_on_table.clear();

	}


}
