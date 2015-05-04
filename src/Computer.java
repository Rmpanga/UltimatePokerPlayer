
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
	
	/*
	 * Add card to hands
	 */
	public void addCardToHand(Card card){
		hand.addCard(card);
	}
	
	/*
	 * Add computers card to hand
	 */
	public void addPlayerCards(PlayerCards pc){
		this.playerCards = pc;
		addCardToHand(pc.card1);
		addCardToHand(pc.card2);
	}
	
	/*
	 * Clears computers hand
	 */
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
		return comp_bid;
	}
	
	/*
	 * Computer bidding
	 */
	public void bid(int amount, int user_chips) {
		chips-=amount;
	}
	
	/*
	 * Computer decides to fold, call, or raise
	 */
	public String decide(Network bayes_network) {				
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
		System.out.println("George Folds");
	}
	
	/*
	 * Computer calls their hand
	 */
	public void call() {
		System.out.println("George Calls");
	}
	
	/*
	 * Computer raises the current bid
	 */
	public void raise() {
		System.out.println("George raises");
	}
	
	/*
	 *  Computer checks
	 */
	public void check() {
		System.out.println("George Checks");
	}
	
	/*
	 * Returns the users hand
	 */
	public Hand getHand() {
		return hand;
	}
}
