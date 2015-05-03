
public class Computer {
	
	private String name;
	private PlayerCards playerCards;
	private Hand hand;
	private int chips;
	private int comp_bid = 10;
	
	/*
	 * Creates new computer and sets the name variable
	 */
	public Computer(String name, int start_chips) {
		this.name = name;
		chips = start_chips;
		hand = new Hand();
	}
	
	/*
	 * Set hand to computer's hand
	 */
	public void addHand(Hand hand){
		this.hand = hand;
	}
	
	public void addCardToHand(Card card){
		hand.addCard(card);
	}
	
	public void addPlayerCards(PlayerCards pc){
		this.playerCards = pc;
		System.out.println(pc.card1);
		System.out.println(pc.card2);
		addCardToHand(pc.card1);
		addCardToHand(pc.card2);
	}
	
	
	public void clearHand(){
		hand.makeEmpty();
	}
	
	/*
	 * Return computer's hand back to dealer
	 */
	public PlayerCards retPlayerCards(){
		return playerCards;
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
//		comp_bid = amount;
		
		// user doesn't have same bid amount
		// computer needs to lower it or force the other player to go all in
	}
	
	/*
	 * Computer decides to fold, call, or raise
	 */
	public String decide(Network bayes_network) {				// you will eventually remove test and have the computer decide on its own
		String result = bayes_network.evaluate();
		
		if(result.toLowerCase().equals("fold")) {
			// fold - 1
			return "1";
		} else if(result.toLowerCase().equals("call")) {
			// call - 2
			return "2";
		} else if(result.toLowerCase().equals("raise")) {
			// raise - 3
			return "3";
		}
		// check - 4
		return "4";
		// you will have to have the computer do calculations here
//		int test1 = test;
//		
//		if(test1 == -1) {
//			return "1";					// this means fold
//		} else if(test1 == 0) {
//			return "2";					// this means call
//		} else if(test1 == 1) {
//			return "3";					// this means raise
//		} else {
//			return "4";					// this means check
//		}
//		
		
	}
	
	/*
	 * Computer raise amount
	 */
	public int howMuchToRaise() {
		return 20;
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
	
	/*
	 *  Computer checks
	 */
	public void check() {
		
	}
	
	public Hand getHand() {
		// TODO Auto-generated method stub
		return hand;
	}
}
