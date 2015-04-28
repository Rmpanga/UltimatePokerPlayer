import java.util.ArrayList;
import java.util.Random;


public class Deck {
	
	public static final String[] possible_cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	public static final String[] possible_suits = {"clubs", "hearts", "spades", "diamonds"};
	

	
	static ArrayList<Card> deck = new ArrayList<Card>(52);
	
	/*@Tested
	 *  populates the deck accurately
	 */
	public static void populateDeck(){
		
		for (String card : possible_cards){
			for (String suit : possible_suits){
				deck.add(new Card(card, suit));
			}
		}
	}
	
	/*@Tested
	 * Shuffles Deck randomly
	 */
	
	public static void shuffleDeck(){
		ArrayList<Integer> indexes = new ArrayList<Integer>(52);
		ArrayList<Card> shuffledDeck = new ArrayList<Card>(52);
		Random r = new Random ();
		
		for (int x =0; x<deck.size(); x++){
			indexes.add(x);
		}
		
		
		for (int j =0; j<52; j++){
			//ystem.out.println("J: " + j);
			boolean redo = true;
			int rValue = r.nextInt(indexes.size());
			shuffledDeck.add(deck.get(rValue));
			indexes.remove(rValue);
			deck.remove(rValue);
		
		}
		deck = shuffledDeck;
	}
	
	/**                                                                          DEBUG SECTION                                                                   **/
	
	

	/*@Debug
	 * 
	 */
	public static void printDeck(){
		int count = 0;
		for (Card card : deck){
			
			System.out.println(card.retValue() +"," + card.retSuit() +" " + count);
		
			
			count++;
		}
		
	}
	

	
	/*@Debug
	 * 
	 */
	
	public static void main ( String [] args){
		
		populateDeck();
		
		System.out.println();
		System.out.println("Shuffling the deck & Printing");
		shuffleDeck();
		printDeck();
		
	}

//	public Deck() {
//		for(possible_cards) {
//			for(possible_suits) {
//				new Card();
//			}
//		}
//	}
	
}
