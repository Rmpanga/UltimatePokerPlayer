import java.util.ArrayList;


public class Deck {
	
	public static final String[] possible_cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	public static final String[] possible_suits = {"clubs", "hearts", "spades", "diamonds"};
	

	
	static ArrayList<Card> deck = new ArrayList<Card>(52);
	
	/*@Tested
	 *  - populates the deck accurately
	 */
	public static void populateDeck(){
		
		for (String card : possible_cards){
			for (String suit : possible_suits){
				deck.add(new Card(card, suit));
			}
		}
	}

	//For debugging 
	public static void printDeck(){
		
		for (Card card : deck){
			
			System.out.println(card.retValue() +"," + card.retSuit());
			
		}
		
	}
	
	
	public static void shuffleDeck(){
		
		
	}
	
	
	
	
	public static void main ( String [] args){
		
		populateDeck();
		printDeck();
		
		System.out.println();
		
		distributeHands(false);
			
	}

//	public Deck() {
//		for(possible_cards) {
//			for(possible_suits) {
//				new Card();
//			}
//		}
//	}
	
}
