
public class Computer {
	
	private String name;
	private Hand hand;
	private int chips;
	
	/*
	 * Creates new computer and sets the name variable
	 */
	public Computer(String name, int start_chips) {
		this.name = name;
		chips = start_chips;
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
	
	/* @Tested
	 * Return user's chip amount
	 */
	public int retChips() {
		return chips;
	}
	
	/* @Tested
	 * Computer will pay ante
	 */
	public boolean payAnte(int ante){
		if((chips-ante) < 0) {
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
