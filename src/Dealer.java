import java.util.Scanner;

public class Dealer {
	
	public static int ante = 20; 
	public static final int start_chip_amt = 5000;
	public static boolean players_turn = true;
	static final HandEvaluator evaluator = new HandEvaluator();

	/* 
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
	
	
	/*
	 * Main start to poker game
	 */
	public static void main(String[] args) {
		
		System.out.println("Welcome to Poker!!!!");
		System.out.println("What is your name?");
		Scanner user_input = new Scanner(System.in);
		String username = user_input.nextLine();
		System.out.println("Welcome " + username + "! You automatically start with $5000, Have Fun!");
		System.out.println("You will be playing against a computer named George");
		Computer george = new Computer("George", start_chip_amt);
		User user = new User(username, start_chip_amt);
		Deck deck = new Deck();
		
		while((user.retChips() != 0) || (george.retChips() != 0)) {
			deck.shuffleDeck();
			Table new_table = new Table();
		
			boolean userPaid = user.payAnte(ante);
			boolean compPaid = george.payAnte(ante);
			
			if(userPaid && compPaid) {
				new_table.addToPot(ante*2);
			} else {				
				if (user.retChips() < ante && george.retChips() < ante){ 
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
			
			distributeHands(players_turn, george, user);
		
			if(PokerLogic.initRound(players_turn, george, user, user_input, new_table, evaluator)) {
				if(PokerLogic.flopRound(players_turn, george, user, user_input, new_table, evaluator)) {
					if(PokerLogic.turnRound(players_turn, george, user, user_input, new_table, evaluator)) {
						if(PokerLogic.riverRound(players_turn, george, user, user_input, new_table, evaluator)) {
							System.out.println("George's hand " +george.getHand().toString());
							System.out.println("User hand " + user.getHand().toString());
							
							int winner = evaluator.compareHands(george.getHand(), user.getHand());
							
							if (winner == 1){
								String nameOfHand = evaluator.nameHand(george.getHand());
								System.out.println("George wins, with a " + nameOfHand);
							}
							else if (winner == 2){
								String nameOfHand = evaluator.nameHand(user.getHand());
								System.out.println(username + " wins, with a " + nameOfHand);
							
							}
							else if (winner == 0){
								System.out.println("Its a tie");
							}

						} 
					}
				}
			}
			players_turn = !players_turn;
			System.out.println();
			System.out.println("----------------------------------- New Round -----------------------------------");
			System.out.println();
		}
	}
}
