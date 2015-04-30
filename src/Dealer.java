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
	
	/*
	 * Handles the flop round, bids, etc.
	 */
	public static boolean flopRound(boolean player, Computer george, User user, Scanner user_input, Table table) {
		
		boolean bothPlayersDone = false;
		
		if(player) {
			System.out.println("How much do you want to bid?");
			int user_bid_amt = Integer.parseInt(user_input.nextLine());
			
			// player bids
			// computer can fold, call, or raise
			// if fold - player gets current pot
			// if raise - ask player if he wants to fold, call or raise
				// the 2 line above can repeat**
			// add bids to pot
			// show flop
			
		}
			
		String comp_move = george.decide(0);
			
		if(!comp_move.equals("1")) {
			System.out.println("Pot Amount: " + table.retPot());
			System.out.println("Computer bids");
			// computer bids
			System.out.println("Computers amount before bid: " + george.retChips());
			boolean didCompBid = george.bid(george.wantToBid());							// this will have to change and have the computer do it instead
			System.out.println("Computers amount after bid: " + george.retChips());
			// I think there has to be an if and else statement here because what happens if didCompBid returns false ** problem here
			table.addToPot(george.wantToBid());
			System.out.println("Pot Amount after adding to table: " + table.retPot());
				
			while(!bothPlayersDone && didCompBid) {
				System.out.println("Do you want to fold, call, or raise? (Type '1' = fold, '2' = call, or '3' = raise)");
				// player can fold, call, or raise
				String user_decision = user_input.nextLine();
						
				if(user_decision.toLowerCase().equals("1")) {
					
					// fold - computer gets current pot
					user.fold();
					// give computer current pot amount
					System.out.println("Table amount before user folds: " + table.retPot());
					System.out.println("Computers amount before receiving pot " + george.retChips());
					george.recPot(table.retPot());
					table.resetPot();
					System.out.println("Computers amount after receiving pot " + george.retChips());
					System.out.println("Table amount after user folds: " + table.retPot());
					bothPlayersDone = true;				
					return false;
							
				} else if(user_decision.toLowerCase().equals("2")) {
					System.out.println("Users amt before call "  + user.retChips());
					// call - show flop
					user.call();
					user.bid(george.wantToBid());
					System.out.println("Users amt after call " + user.retChips());
					System.out.println("Table amt before call " + table.retPot());
					table.addToPot(george.wantToBid());
					System.out.println("Table amt after call " + table.retPot());
					bothPlayersDone = true;
							
				} else if(user_decision.toLowerCase().equals("3")) {
					System.out.println("User amt before raise: " + user.retChips());
					System.out.println("Table amt before raise: " + table.retPot());
					// raise - ask computer to fold, raise or call
					System.out.println("How much do you want to raise by? " + " you have: " + user.retChips());
					String user_raise_amt = user_input.nextLine();
					user.bid(george.wantToBid());
					table.addToPot(george.wantToBid());
					user.bid(Integer.parseInt(user_raise_amt));
					table.addToPot(Integer.parseInt(user_raise_amt));
					System.out.println("User amt after raise: " + user.retChips());
					System.out.println("Table amt after raise: " + table.retPot());
					
					
					String comp_decision = george.decide(0);
					System.out.println(comp_decision);
							
					if(comp_decision.toLowerCase().equals("1")) {
							System.out.println("Table amount before comp folds: " + table.retPot());
							// computer folds
							george.fold();
							// give user current pot amount
							System.out.println("Users chips before computer folds: " + user.retChips());
							user.recPot(table.retPot());
							System.out.println("Users chips after computer folds: " + user.retChips());
							table.resetPot();
							System.out.println("Table amount after comp folds: " + table.retPot());
							bothPlayersDone = true;
							return false;
								
					} else if(comp_decision.toLowerCase().equals("2")) {
						System.out.println("Table amt before computer calls: " + table.retPot());
						// computer calls
						System.out.println("Computer chips before calling: " + george.retChips());
						george.call();
						george.bid(Integer.parseInt(user_raise_amt));
						System.out.println("Computer chips after call: " + george.retChips());
						// add raised amount from computer to table pot
						table.addToPot(Integer.parseInt(user_raise_amt));
						System.out.println("Table amt after computer calls: " + table.retPot());
						bothPlayersDone = true;
								
					} else if(comp_decision.toLowerCase().equals("3")) {
				
						// computer raises
						// you have to fix this
						george.bid(Integer.parseInt(user_raise_amt));
						table.addToPot(Integer.parseInt(user_raise_amt));
						george.raise();
						george.bid(george.wantToBid());					// this will have to change and have the computer do it instead
						table.addToPot(george.wantToBid());
					}
							
				} else {
					System.out.println("You typed something incorrectly... you will have to restart the game");
				}
						
				// if fold - computer gets current pot
				// if raise - ask computer if he wants to fold, call or raise
					// the 2 line above can repeat**
				// add bids to pot
			}
			
			// show flop
			table.flop();
			return true;
			
		} 
		
		// user gets current pot amount
		return false;
	}
	
	/*
	 * Handles the turn round, bids, etc.
	 */
	public static boolean turnRound(boolean player, Computer george, User user, Scanner user_input, Table table) {
		if(player) {
			// player bids
			// computer can fold, call, or raise
			// if fold - player gets current pot
			// if raise - ask player if he wants to fold, call or raise
				// the 2 line above can repeat**
			// add bids to pot
		} else {
			// computer bids
			// player can fold, call, or raise
			// if fold - computer gets current pot
			// if raise - ask computer if he wants to fold, call or raise
				// the 2 line above can repeat**
			// add bids to pot
		}
		
		// show turn
		table.turn();
		return true;
	}
	
	/*
	 * Handles the river round, bids, etc.
	 */
	public static boolean riverRound(boolean player, Computer george, User user, Scanner user_input, Table table) {
		if(player) {
			// player bids
			// computer can fold, call, or raise
			// if fold - player gets current pot
			// if raise - ask player if he wants to fold, call or raise
				// the 2 line above can repeat**
			// add bids to pot
		} else {
			// computer bids
			// player can fold, call, or raise
			// if fold - computer gets current pot
			// if raise - ask computer if he wants to fold, call or raise
				// the 2 line above can repeat**
			// add bids to pot
		}
		
		// show river
		table.river();
		return true;
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
		
		if(flopRound(players_turn, george, user, user_input, new_table)) {
			if(turnRound(players_turn, george, user, user_input, new_table)) {
				if(riverRound(players_turn, george, user, user_input, new_table)) {
					
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
