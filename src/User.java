
public class User {
	
	private String name;
	private Hand hand;
	private int chips;
	
	/*
	 *	Creates new user and sets the name variable 
	 */
	public User(String name, int start_chips) {
		this.name = name;
		chips = start_chips;
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
	
	/* @Tested
	 * Return user's chip amount
	 */
	public int retChips() {
		return chips;
	}
	
	/* @Tested
	 * User pays ante
	 */
	public boolean payAnte(int ante) {
		if((chips-ante) < 0) {
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
