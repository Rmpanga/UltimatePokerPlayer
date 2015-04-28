
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
	 * Return hand back to Dealer
	 */
	
	public Hand retHand(){
		
		return hand;
	}
	
	/*
	 * Computer will pay ante
	 */
	public void payAnte(){
		//TODO
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
