
public class PlayerCards {
	
	Card card1;
	Card card2;
	
	/*
	 * Creates a hand and sets card1 and card2
	 */
	public PlayerCards(Card c1, Card c2){
		card1 = c1;
		card2 = c2;
	}
	
	/*
	 * Returns card1 from the hand object
	 */
	public Card getCard1(){
		return card1;
	}
	
	/*
	 * Returns card2 from the hand object
	 */
	public Card getCard2(){
		return card2;
	}
	
}
