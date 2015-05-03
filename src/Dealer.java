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
	static final HandEvaluator evaluator = new HandEvaluator();

	/* @Tested
	 * Distribute hands after shuffling the deck
	 */
	public static void distributeHands(boolean player, Computer george , User user){
		
		if (player){
			System.out.println("Player goes first");
			Card c1 = Deck.deal();
			Card c2 = Deck.deal();
			Card c3 = Deck.deal();
			Card c4 = Deck.deal();
			
			
			PlayerCards userCards = new PlayerCards(c1 , c3);
			PlayerCards compCards = new PlayerCards(c2, c4);
			
			george.addPlayerCards(compCards);
			user.addPlayerCards(userCards);
			
			System.out.println("Player hand : " +userCards.getCard1().toString() + " " +  userCards.getCard2().toString());
			System.out.println("Comp hand : " +compCards.getCard1().toString() + " " + compCards.getCard2().toString());
		}
		else if (!player){
			System.out.println("Computer goes first");
			Card c1 = Deck.deal();
			Card c2 = Deck.deal();
			Card c3 = Deck.deal();
			Card c4 = Deck.deal();
			
			
			PlayerCards compHand = new PlayerCards(c1 , c3);
			PlayerCards playerHand = new PlayerCards(c2, c4);

			george.addPlayerCards(compHand);
			user.addPlayerCards(playerHand);
			
			System.out.println("Comp hand : " +compHand.getCard1().toString() + compHand.getCard2().toString());
			System.out.println("Player hand : " +playerHand.getCard1().toString() + " "+ playerHand.getCard2().toString());
			return;
		}	
	}
	
	/*
	 * Get cards from players and returns it to the deck
	 */
	
	public static void returnPlayerHandsToDeck(Computer george , User user){
		PlayerCards h1 = george.retPlayerCards();
		PlayerCards h2 = user.retPlayerCards();
		
		
		george.addPlayerCards(null);
		user.addPlayerCards(null);
		george.clearHand();
		user.clearHand();
		
	}
	
	/*
	 * Return cards  on table to deck
	 * Clears cards_on_table
	 */
	
	public static void returnTableCardsToDeck(Table table){
		
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
		Deck deck = new Deck();
		
		System.out.println("Shuffling deck...");
		// shuffle deck
		deck.shuffleDeck();
		
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
			
			if (user.retChips() < ante && george.retChips() < ante){ //Should never be called
				System.out.println("No player can afford the ante, we are going all in folks");
				user.payAnte(user.retChips());
				george.payAnte(george.retChips());
				
				
				
			}
			else if ( user.retChips() < ante){
				System.out.println("User cannot afford the ANTE, going all in ");
				user.payAnte(user.retChips());
			}
			else if (george.retChips() < ante){
				george.payAnte(user.retChips());
				System.out.println("George cannot afford the ANTE, going all in ");

			}
		}
		
		System.out.println("Distributes cards to player and computer");
		// distribute cards
		distributeHands(players_turn, george, user);
		
		/*
		 * Worry about the stuff below second
		 */
		if(PokerLogic.initRound(players_turn, george, user, user_input, new_table)) {
			if(PokerLogic.flopRound(players_turn, george, user, user_input, new_table)) {
				if(PokerLogic.turnRound(players_turn, george, user, user_input, new_table)) {
					if(PokerLogic.riverRound(players_turn, george, user, user_input, new_table)) {
						//DETERMINE WINNER 
						System.out.println("George's hand " +george.getHand().toString());
						System.out.println("User hand " + user.getHand().toString());
						
						int winner = evaluator.compareHands(george.getHand(), user.getHand());
					
						if (winner == 1){
							String nameOfHand = evaluator.nameHand(george.getHand());
							System.out.println("George wins, with a " + nameOfHand);
							///george.recPot(ta);
							//int num = evaluator.rankHand(georgehand);
							
						}
						else if (winner == 2){
							String nameOfHand = evaluator.nameHand(user.getHand());
							System.out.println(username + " wins, with a " + nameOfHand);
						
						}
						else if (winner == 0){
							System.out.println("Its a tie");
						}
						
						
					} else {
						
					}
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
