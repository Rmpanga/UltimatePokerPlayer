import java.util.Scanner;


public class PokerLogic {
	/*
	 * Handles the flop round, bids, etc.
	 */
	public static boolean flopRound(boolean player, Computer george, User user, Scanner user_input, Table table) {
		// You have to handle when there are no chips from one person, or both people
		return round(player, george, user, user_input, table, "flop");
	}
	
	/*
	 * Handles the turn round, bids, etc.
	 */
	public static boolean turnRound(boolean player, Computer george, User user, Scanner user_input, Table table) {
		// You have to handle when there are no chips from one person, or both people
		return round(player, george, user, user_input, table, "turn");
	}
	
	/*
	 * Handles the river round, bids, etc.
	 */
	public static boolean riverRound(boolean player, Computer george, User user, Scanner user_input, Table table) {
		// You have to handle when there are no chips from one person, or both people
		return round(player, george, user, user_input, table, "river");
	}
	
	
	public static boolean round(boolean player, Computer george, User user, Scanner user_input, Table table, String specific_round) {
		
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
			System.out.println("Computers amount before bid: " + george.retChips() + " User amount before bid: " + user.retChips());

			george.bid(george.wantToBid(), user.retChips());							// this will have to change and have the computer do it instead
			System.out.println("Computers amount after bid: " + george.retChips() + " User amount after bid: " + user.retChips());
																						// I think there has to be an if and else statement here because what happens if didCompBid returns false ** problem here
			table.addToPot(george.wantToBid());
			System.out.println("Pot Amount after adding to table: " + table.retPot());
			while(!bothPlayersDone) {
				System.out.println("Do you want to fold, call, or raise? (Type '1' = fold, '2' = call, or '3' = raise)");
				// player can fold, call, or raise
				String user_decision = user_input.nextLine();
						
				if(user_decision.toLowerCase().equals("1")) {
					
					// fold - computer gets current pot
					user.fold();
					// give computer current pot amount
					System.out.println("Table amount before user folds: " + table.retPot());
					System.out.println("Computers amount before receiving pot " + george.retChips() + " User amount before computer recieves pot " + user.retChips());
					george.recPot(table.retPot());
					table.resetPot();
					System.out.println("Computers amount after receiving pot " + george.retChips() +" User amount after computer recieves pot " + user.retChips());
					System.out.println("Table amount after user folds: " + table.retPot());
					bothPlayersDone = true;				
					return false;
							
				} else if(user_decision.toLowerCase().equals("2")) {
//					System.out.println("Users amt before call "  + user.retChips());
					// call - show flop
					user.call();
					user.bid(george.wantToBid(), george.retChips());
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
					user.bid(george.wantToBid(), george.retChips());
					table.addToPot(george.wantToBid());
					user.bid(Integer.parseInt(user_raise_amt), george.retChips());
					table.addToPot(Integer.parseInt(user_raise_amt));
					System.out.println("User amt after raise: " + user.retChips());
					System.out.println("Table amt after raise: " + table.retPot());
					
					
					String comp_decision = george.decide(1);		// put -1 for fold, 0 for call, and 1 for raise
					System.out.println(comp_decision);
							
					if(comp_decision.toLowerCase().equals("1")) {
//							System.out.println("Table amount before comp folds: " + table.retPot());
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
						george.bid(Integer.parseInt(user_raise_amt), user.retChips());
						System.out.println("Computer chips after call: " + george.retChips());
						// add raised amount from computer to table pot
						table.addToPot(Integer.parseInt(user_raise_amt));
						System.out.println("Table amt after computer calls: " + table.retPot());
						bothPlayersDone = true;
								
					} else if(comp_decision.toLowerCase().equals("3")) {
						System.out.println("Computer amt before raise: " + george.retChips());
						System.out.println("Table amt before raise: " + table.retPot());
						// computer raises
						// you have to fix this
						george.bid(Integer.parseInt(user_raise_amt), user.retChips());
						table.addToPot(Integer.parseInt(user_raise_amt));
						george.raise();
						george.bid(george.wantToBid(), user.retChips());					// this will have to change and have the computer do it instead
						table.addToPot(george.wantToBid());
						System.out.println("Computer amt after raise: " + george.retChips());
						System.out.println("Table amt after raise: " + table.retPot());
					}
							
				} else {
					System.out.println("You typed something incorrectly... you will have to restart the game");
				}
						
				// if fold - computer gets current pot
				// if raise - ask computer if he wants to fold, call or raise
					// the 2 line above can repeat**
				// add bids to pot
			}
			
			// show flop, turn or river
			if(specific_round.equals("flop")) {
				table.flop();
			} else if(specific_round.equals("turn")) {
				table.turn();
			} else {
				table.river();
			}
			return true;
		} 
		
		// user gets current pot amount
		return false;
	}

}
