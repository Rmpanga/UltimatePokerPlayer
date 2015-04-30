
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
	 * Computer's bid amount
	 */
	public int wantToBid() {
		int comp_bid = 10;
		return comp_bid;
	}
	
	/*
	 * Computer bidding
	 */
	public boolean bid(int amount) {
		if(!((chips - amount) < 0)) {
			chips-=amount;
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Computer decides to fold, call, or raise
	 */
	public String decide(int test) {				// you will eventually remove test and have the computer decide on its own
		// you will have to have the computer do calculations here
		int test1 = test;
		
		if(test1 < 0) {
			return "1";					// this means fold
		} else if(test1 == 0) {
			return "2";					// this means call
		} else {
			return "3";					// this means raise
		}
		
	}
	
	/*
	 * Computer receives pot from table
	 */
	public void recPot(int amount) {
		chips+=amount;
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
