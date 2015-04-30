
public class Computer {
	
	private String name;
	private Hand hand;
	private int chips;
	private int comp_bid = 10;
	
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
	
//	/*
//	 * Update Computer's bid amount
//	 */
//	public void updateBid(int amount) {
//		comp_bid = amount;
//	}
	
	/*
	 * Computer's bid amount
	 */
	public int wantToBid() {
		return comp_bid;
	}

//	public boolean canMakeBid(int amount) {
//		if(!((chips - amount) < 0)) {
//			chips-=amount;
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	/*
	 * Computer bidding
	 */
	public void bid(int amount, int user_chips) {
		// the computer will have to handle how much it should bid, without going over its amount
		// the computer will have to consider the users_chip amount here too as well
		// you have to update comp_bid amount after the computer makes it choice 
		
		chips-=amount;
		
		
		// user doesn't have same bid amount
		// computer needs to lower it or force the other player to go all in
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
