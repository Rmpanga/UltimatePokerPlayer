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
	
	public static final int ante = 20; //<-- shouldnt be final
	
	/*
	 * Distribute hands after shuffling the deck
	 */
	public static void distributeHands(boolean player, Computer george , User user){
		
		if (player){
			
			Hand playerHand = new Hand(Deck.deck.remove(0) , Deck.deck.remove(1));
			Hand compHand = new Hand(Deck.deck.remove(0), Deck.deck.remove(0));
			
			george.addHand(compHand);
			user.addHand(playerHand);
			
			System.out.println("Player hand : " +playerHand.getCard1().retValue() + " " + playerHand.getCard1().retSuit() + " "+ playerHand.getCard2().retValue() + " " + playerHand.getCard2().retSuit());
			System.out.println("Comp hand : " +compHand.getCard1().retValue() + " " + compHand.getCard1().retSuit() +" "+ compHand.getCard2().retValue() + " " + compHand.getCard2().retSuit());
			return;
		}
		else if (!player){
			Hand compHand = new Hand(Deck.deck.remove(0) , Deck.deck.remove(1));
			Hand playerHand = new Hand(Deck.deck.remove(0), Deck.deck.remove(0));

			george.addHand(compHand);
			user.addHand(playerHand);
			
			System.out.println("Player hand : " +playerHand.getCard1().retValue() + " " + playerHand.getCard1().retSuit() + " "+ playerHand.getCard2().retValue() + " " + playerHand.getCard2().retSuit());
			System.out.println("Comp hand : " +compHand.getCard1().retValue() + " " + compHand.getCard1().retSuit() +" "+ compHand.getCard2().retValue() + " " + compHand.getCard2().retSuit());
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
		Computer george = new Computer("George");
		User user = new User(username);
		
		/*
		 * Worry about this first
		 */
		
		System.out.println("Creating deck...");
		// create deck
		
		System.out.println("Shuffling deck...");
		// shuffle deck
		
		System.out.println("Creating table...");
		// create table
		
		System.out.println("Ante amount is " + ante);
		
		System.out.println("Hey " + username + ", Do you want to play the ante (Type 'yes' or 'no') ?");
		String yes_or_no = user_input.nextLine();
		// ask user if he wants to pay ante, assuming he is going first (lets just have the user play first)
		// pay the ante, if you don't have enough for the current ante, you put as much as you can to pay and the other player will put the same amount
		if(yes_or_no.toLowerCase().equals("yes")) {
			// check if the user and the computer has enough to pay ante
			// if not, pay as much as you can pay and the other play will put the same amount
			System.out.println("Player pays ante");
			System.out.println("Computer pays ante");
			
			System.out.println("Update players amount left after paying ante");
			// update each players current amount left after paying ante
			
			System.out.println("Distributes cards to player and computer");
			// distribute cards
		} else {
			System.out.println("Restart game");
		}
	
		
		
		/*
		 * Worry about the stuff below second
		 */
		
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
		
		
		

	}

}
