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
		cards_on_table = new ArrayList<Card>(5);
		pot = 0;
	}
	
	/* @Tested
	 * Dealer plays flop
	 */
	public void flop(){
		addCardsToTable(3);
	}
	
	/* @Tested
	 * Dealer plays turn
	 */
	public void turn(){
		addCardsToTable(1);
	}
	
	/* @Tested
	 * Dealer plays river
	 */
	public void river(){
		addCardsToTable(1);
		
	}
	
	/* @Tested
	 * Add specific amount of cards into the table for both players to see
	 */
	private void addCardsToTable(int num){
	   
		for(int j =0; j < num; j++){
			Card card = Deck.deck.remove(0);
			cards_on_table.add(card);
	   }
	}
	
	/* @Tested
	 * Shows the current amount of cards on the table
	 */
	public void showCardsOnTable() {
		for(Card entry : cards_on_table) {
			System.out.print("[ " + entry.retValue() + ", " + entry.retSuit() + " ]  ");
		}
		System.out.println();
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
