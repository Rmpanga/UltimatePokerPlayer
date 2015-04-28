
public class Computer {
	
	private String name;
	private Hand hand;
	private int chips;
	/*
	 * Creates new computer and sets the name variable
	 */
	public Computer(String name) {
		this.name = name;
		chips = 5000;
	}
	
	/*
	 * Set hand to computer's hand
	 */
	public void addHand(Hand hand){
		this.hand = hand;
	}
	
	/*
	 * Return computer's hand back to dealer
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
	 * Computer will pay ante
	 */
	public boolean payAnte(int ante){
		// Still needs to test
		if((chips-ante) <= 0) {
			return false;
		} else {
			chips-=ante;
			return true;
		}
	}
	
	/*
	 * Computer folds their hand
	 */
	public void fold() {
		
	}
	
	/*
	 * Computer calls their hand
	 */
	public void call() {
		
	}
	
	/*
	 * Computer raises the current bid
	 */
	public void raise() {
		
	}
	
}
