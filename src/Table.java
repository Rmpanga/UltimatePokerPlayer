import java.util.ArrayList;


public class Table {
	private ArrayList<String> cards_on_table; 
	private int bid;
	
	/*
	 *  Creating a new table 
	 */
	public Table() {
		cards_on_table = new ArrayList<String>();
		bid = 0;
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
		
	}


}
