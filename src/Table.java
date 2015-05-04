import java.util.ArrayList;


public class Table {
	
	private ArrayList<Card> cards_on_table; 
	private int pot;
	
	/*
	 *  Creating a new table 
	 */
	public Table() {
		cards_on_table = new ArrayList<Card>(5);
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
			Card card = Deck.deal();
			cards_on_table.add(card);
	   }
	}
	
	/* 
	 * Shows the current amount of cards on the table
	 */
	public void showCardsOnTable() {
		for(Card entry : cards_on_table) {
			System.out.print("[ " + entry.toString() + " ]  ");
			
			//System.out.print("[ " + entry.getRank() + ", " + entry.getSuit() + " ]  ");
		}
		System.out.println();
	}
	
	/* 
	 * Add to pot
	 */
	public void addToPot(int amount) {
		pot+=amount;
	}
	
	/* 
	 * Return pot amount
	 */
	public int retPot() {
		return pot;
	}
	
	/*
	 * Sets pot back to zero
	 */
	public void resetPot() {
		pot = 0;
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
