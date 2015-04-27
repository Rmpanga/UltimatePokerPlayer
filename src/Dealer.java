import java.util.Scanner;


public class Dealer {
	

	// $5000 buy in 
	// Computer and User starts with $5000
	// ante will be $20, you pay every round, and increases by 5 every 10 rounds
	
	/*Distribute hands after shuffling the deck!!
	 * 
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
	
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Poker!!!!");
		System.out.println("What is your name?");
		Scanner user_input = new Scanner(System.in);
		String user_name = user_input.nextLine();
		System.out.println("Welcome " + user_name + "! You automatically start with $5000, Have Fun!");
		System.out.println("You will be playing against George");
		Computer George = new Computer("George");
		
		// create deck
		// shuffle deck
		// create table
		
		// pay the ante, if you don't have enough for the current ante, you put as much as you can to pay and the other player will put the same amount
		// distribute card to 1st player
		// distribute card to 2nd player
		// distribute card to 1st player again
		// distribute card to 2nd player again
		
		// first person who pays the ante first bids first
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
