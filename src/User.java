
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
	
	
	public Hand retHand(){
		
		return hand;
	}
	
	/*
	 * User pays ante
	 */
	public void payAnte() {
		//TODO
	}
	
	/*
	 * User folds their hand
	 */
	public void fold() {
		
	}
	
	/*
	 * User calls their hand
	 */
	public void call() {
		
	}
	
	/*
	 * User raises the current bid
	 */
	public void raise() {
		
	}
}
