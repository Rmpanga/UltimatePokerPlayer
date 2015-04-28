
public class User {
	
	private String name;
	private Hand hand;
	private int chips;
	
	/*
	 *	Creates new user and sets the name variable 
	 */
	public User(String name) {
		this.name = name;
		chips = 5000;
	}
	
	/*
	 * Set hand to user's hand
	 */
	public void addHand(Hand hand) {
		this.hand = hand;
	}
	
	/*
	 * Return user's hand back to dealer
	 */
	public Hand retHand(){
		return hand;
	}
	
	/*
	 * Return user's chip amount
	 */
	public int retChips() {
		return chips;
	}
	
	/*
	 * User pays ante
	 */
	public boolean payAnte(int ante) {
		// Still needs to test
		if((chips-ante) <= 0) {
			return false;
		} else {
			chips-=ante;
			return true;
		}
	}
	
	/*
	 * User folds their hand
	 */
	public void fold() {
		//TODO
	}
	
	/*
	 * User calls their hand
	 */
	public void call() {
		//TODO
	}
	
	/*
	 * User raises the current bid
	 */
	public void raise() {
		//TODO
	}
	
	/*
	 * Displays User's hand
	 */
	public void showHand() {
		//TODO
	}
}
