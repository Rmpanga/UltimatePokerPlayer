
public class Computer {
	
	private String name;
	public Hand hand;
	
	/*
	 * Creates new computer and sets the name variable
	 */
	public Computer(String name) {
		this.name = name;
	}
	
	/*
	 * Set hand to computer's hand
	 */
	public void addHand(Hand hand){
		this.hand = hand;
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
