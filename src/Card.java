
public class Card {
	private String value;
	private String suit;
	
	public Card(String value, String suit) {
		this.value = value;
		this.suit = suit;
	}
	
	public String retValue() {
		return value;
	}
	
	public String retSuit() {
		return suit;
	}
}
