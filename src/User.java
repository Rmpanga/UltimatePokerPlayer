
public class User {
	
	private String name;
	private PlayerCards playerCards;
	private Hand hand;
	private int chips;
	private int user_bid;
	
	/*
	 *	Creates new user and sets the name variable 
	 */
	public User(String name, int start_chips) {
		this.name = name;
		chips = start_chips;
		hand = new Hand();
	}
	
	/*
	 * Set hand to user's hand
	 */
	public void addPlayerCards(PlayerCards pc) {
		this.playerCards = pc;
		
		addCardToHand(pc.card1);
		addCardToHand(pc.card2);
	}
	
	
	/*
	 * Return user's hand back to dealer
	 */
	public PlayerCards retPlayerCards(){
		return playerCards;
	}
	
	public void clearHand(){
		hand.makeEmpty();
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
	 * Updates user's bid amount
	 */
	public void updateBid(int amount) {
		user_bid = amount;
	}
	
	/*
	 * User checks
	 */
	public void check() {
		
	}
	
	/*
	 * Amount user wants to bid
	 */
	public int wantToBid() {
		return user_bid;
	}
	
	/*
	 * User bidding
	 */
	public void bid(int amount, int comp_chips) {
		chips-=amount;
	}
	
	/*
	 * User receives pot from table
	 */
	public void recPot(int amount) {
		chips+=amount;
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
	public void showCards() {
		System.out.println("[ " + playerCards.getCard1().toString() + " ] " + "[ " + playerCards.getCard2().toString() + " ]  ");
	}

	public void addCardToHand(Card card){
		hand.addCard(card);
	}
	
	public Hand getHand(){
		return hand; 
	}
}
