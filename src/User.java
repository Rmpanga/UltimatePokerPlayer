
public class User {
	
	private String name;
	private Hand hand;
	
	/*
	 *	Creates new user and sets the name variable 
	 */
	public User(String name) {
		this.name = name;
	}
	
	/*
	 * Set hand to user's hand
	 */
	public void addHand(Hand hand) {
		this.hand = hand;
	}
	
	/*
	 * User pays ante
	 */
	public void payAnte() {
		//TODO
	}
	
}
