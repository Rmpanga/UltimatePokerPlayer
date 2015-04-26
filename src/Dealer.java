import java.util.Scanner;


public class Dealer {
	
	// $5000 buy in 
	// Computer and User starts with $5000
	// ante will be $20, you pay every round, and increases by 5 every 10 rounds
	
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
		
		// pay the ante, if you don't have enough for the current ante, you put as much as you can to pay and the other player will put the same amount
		// distribute card to 1st player
		// distribute card to 2nd player
		// distribute card to 1st player again
		// distribute card to 2nd player again
		
		
		
		

	}

}
