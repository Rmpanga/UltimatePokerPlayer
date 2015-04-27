
public class Card {
	
	private String value;
	private String suit;
	
	/*
	 * Creates a card and sets the value and suit
	 */
	public Card(String value, String suit) {
		this.value = value;
		this.suit = suit;
	}
	
	/*
	 * Returns the value of a card
	 */
	public String retValue() {
		return value;
	}
	
	/*
	 * Returns the suit of a card
	 */
	public String retSuit() {
		return suit;
	}
}
