import java.util.Scanner;


public class Dealer {
	/**TODO
	 * Test return cards to deck
	 * Test return player hands to deck
	 * 
	 */

	// $5000 buy in 
	// Computer and User starts with $5000
	// ante will be $20, you pay every round, and increases by 5 every 10 rounds
	
	public static int ante = 20; 
	public static final int start_chip_amt = 5000;
	public static boolean players_turn = false;
	
	/* @Tested
	 * Distribute hands after shuffling the deck
	 */
	public static void distributeHands(boolean player, Computer george , User user){
		
		if (player){
			System.out.println("Player goes first");
			Hand playerHand = new Hand(Deck.deck.remove(0) , Deck.deck.remove(1));
			Hand compHand = new Hand(Deck.deck.remove(0), Deck.deck.remove(0));
			
			george.addHand(compHand);
			user.addHand(playerHand);
			
			System.out.println("Player hand : " +playerHand.getCard1().retValue() + " " + playerHand.getCard1().retSuit() + " "+ playerHand.getCard2().retValue() + " " + playerHand.getCard2().retSuit());
			System.out.println("Comp hand : " +compHand.getCard1().retValue() + " " + compHand.getCard1().retSuit() +" "+ compHand.getCard2().retValue() + " " + compHand.getCard2().retSuit());
			return;
		}
		else if (!player){
			System.out.println("Computer goes first");
			Hand compHand = new Hand(Deck.deck.remove(0) , Deck.deck.remove(1));
			Hand playerHand = new Hand(Deck.deck.remove(0), Deck.deck.remove(0));

			george.addHand(compHand);
			user.addHand(playerHand);
			
			System.out.println("Comp hand : " +compHand.getCard1().retValue() + " " + compHand.getCard1().retSuit() +" "+ compHand.getCard2().retValue() + " " + compHand.getCard2().retSuit());
			System.out.println("Player hand : " +playerHand.getCard1().retValue() + " " + playerHand.getCard1().retSuit() + " "+ playerHand.getCard2().retValue() + " " + playerHand.getCard2().retSuit());
			return;
		}	
	}
	
	/*
	 * Get cards from players and returns it to the deck
	 */
	
	public static void returnPlayerHandsToDeck(Computer george , User user){
		Hand h1 = george.retHand();
		Hand h2 = user.retHand();
		
		Deck.deck.add(h1.card1);
		Deck.deck.add(h1.card2);
		
		Deck.deck.add(h2.card1);
		Deck.deck.add(h2.card2);
		
	}
	
	/*
	 * Return cards  on table to deck
	 * Clears cards_on_table
	 */
	
	public static void returnTableCardsToDeck(Table table){
		
		Deck.deck.addAll(table.getCardsOnTable());
		table.clearTable();
	}
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Poker!!!!");
		System.out.println("What is your name?");
		Scanner user_input = new Scanner(System.in);
		String username = user_input.nextLine();
		System.out.println("Welcome " + username + "! You automatically start with $5000, Have Fun!");
		System.out.println("You will be playing against a computer named George");
		Computer george = new Computer("George", start_chip_amt);
		User user = new User(username, start_chip_amt);
		
		/*
		 * Worry about this first
		 */
		
		System.out.println("Creating deck...");
		// create deck
		Deck.populateDeck();
		
		System.out.println("Shuffling deck...");
		// shuffle deck
		Deck.shuffleDeck();
		
		System.out.println("Creating table...");
		// create table
		Table new_table = new Table();
		
		// check if computer can pay ante amount
		// check if player can pay ante amount
		// there is a condition here, if one of the players doesn't have enough to pay ante, that player needs to put as much as he can to pay the ante **
		
		// player pays ante and updates its current amount left after ante
		boolean userPaid = user.payAnte(ante);
		// computer pays ante and updates its current amount left after ante
		boolean compPaid = george.payAnte(ante);
		
		if(userPaid && compPaid) {
			// add ante from user and computer to pot on table
			new_table.addToPot(ante*2);
		
		} else {
			// this will handle when the computer or play doesn't have enough to pay full ante - will have to figure out logic to pay only specific amount
		}
		
		System.out.println("Distributes cards to player and computer");
		// distribute cards
		distributeHands(players_turn, george, user);
		
		/*
		 * Worry about the stuff below second
		 */
		
		if(PokerLogic.flopRound(players_turn, george, user, user_input, new_table)) {
			if(PokerLogic.turnRound(players_turn, george, user, user_input, new_table)) {
				if(PokerLogic.riverRound(players_turn, george, user, user_input, new_table)) {
					
				} else {
					
				}
			} else {
				
			}
		} else {
			
		}
		// first person who pays the ante first bids first
			// if user -> ask him how much he wants to bid
			// if computer -> needs to figure out how much to bid (first just keep it the same for now)
		// second person can fold, call (match the first persons bid), or raise
		// if second person folds, first person wins pot
		// if second person calls, the dealer does flop, and first player gets a bid again and this process repeats
		// if second person raises, the first person has the option to fold, call, or raise (this process can keep repeating over and over between players)
		
		// After second round of bids, dealer drops a turn card
		// the first player gets to bid again and this process of fold, call, or raise repeats again
		
		// After third round of bids, dealer drops a river card
		// the first play gets to bid again and this process of fold, call, or raise repeats again
		
		// After last bid, show cards 
		// Determine who wins pot
		// repeat this process
		
		// next round the player doesn't go first - this will be put at the end
		players_turn = !players_turn;
		
	}
}
