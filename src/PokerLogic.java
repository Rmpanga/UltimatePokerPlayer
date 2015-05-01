import java.util.Scanner;


public class PokerLogic {
	/*
	 * Handles the final round, bids, etc.
	 */
	public static boolean initRound(boolean player, Computer george, User user, Scanner user_input, Table table) {
		// You have to handle when there are no chips from one person, or both people
		System.out.println();
		System.out.println(" --------------------- Initial Round --------------------- ");
		return round(player, george, user, user_input, table, "final");
	}
	
	/*
	 * Handles the flop round, bids, etc.
	 */
	public static boolean flopRound(boolean player, Computer george, User user, Scanner user_input, Table table) {
		// You have to handle when there are no chips from one person, or both people
		System.out.println();
		System.out.println(" --------------------- Flop Round --------------------- ");
		return round(player, george, user, user_input, table, "flop");
	}
	
	/*
	 * Handles the turn round, bids, etc.
	 */
	public static boolean turnRound(boolean player, Computer george, User user, Scanner user_input, Table table) {
		// You have to handle when there are no chips from one person, or both people
		System.out.println(" --------------------- Turn Round --------------------- ");
		return round(player, george, user, user_input, table, "turn");
	}
	
	/*
	 * Handles the river round, bids, etc.
	 */
	public static boolean riverRound(boolean player, Computer george, User user, Scanner user_input, Table table) {
		// You have to handle when there are no chips from one person, or both people
		System.out.println(" --------------------- River Round --------------------- ");
		return round(player, george, user, user_input, table, "river");
	}
	
	
	public static boolean round(boolean player, Computer george, User user, Scanner user_input, Table table, String specific_round) {
		
		boolean bothPlayersDone = false;
		
		if(player) {
			// show flop, turn or river
			if(specific_round.equals("flop")) {
				table.flop();
			} else if(specific_round.equals("turn")) {
				table.turn();
			} else if(specific_round.equals("river")) {
				table.river();
			} 	
			
			System.out.println();
			System.out.print("Cards on Table: ");
			table.showCardsOnTable();
			System.out.println();
			
			System.out.println("Pot Amount: " + table.retPot());
			user.showHand();
			System.out.println();
			
			//If one player is ALL IN keep returning true
//			if (user.retChips() > 0 && george.retChips() > 0){ 							// this will need to be removed here
			
				System.out.println("Do you want to bid or fold? (Type '1' = fold '2' = bid)");
				// player can fold, call, or raise
				String user_decision = user_input.nextLine();
			
				if(user_decision.toLowerCase().equals("1")) {
					// computer receives pot amount
					// reset the pot
					return false;
					
				} else if(user_decision.toLowerCase().equals("2")) {
				
					System.out.println("How much do you want to bid?");
					int user_bid_amt = Integer.parseInt(user_input.nextLine());
				
					user.updateBid(user_bid_amt);
					user.bid(user.wantToBid(), george.retChips());
				
					System.out.println("You bid " + user.wantToBid());																	
					// I think there has to be an if and else statement here because what happens if didCompBid returns false ** problem here
					table.addToPot(user.wantToBid());
					while(!bothPlayersDone) {
						
						String comp_decision = george.decide(1);						// put -1 for fold, 0 for call, and 1 for raise
						
						if(comp_decision.toLowerCase().equals("1")) {
							
							// fold - user gets current pot
							george.fold();
							// give user current pot amount
							user.recPot(table.retPot());
							table.resetPot();
							bothPlayersDone = true;				
							return false;
							
						} else if(comp_decision.toLowerCase().equals("2")) {
							
							// call - show flop
							george.call();
							george.bid(user_bid_amt, user.retChips());
							table.addToPot(user_bid_amt);
							bothPlayersDone = true;
							
						} else if(comp_decision.toLowerCase().equals("3")) {
							
							// raise - ask computer to fold, raise or call
							
							int comp_raise = george.howMuchToRaise();
							
							george.bid(user_bid_amt, user.retChips());
							table.addToPot(user_bid_amt);
							george.bid(comp_raise, user.retChips());
							table.addToPot(comp_raise);
							
							System.out.println("Do you want to fold, call, or raise? (Type '1' = fold, '2' = call, or '3' = raise) ");
							// player can fold, call, or raise
							String user_move = user_input.nextLine();
							
							if(user_move.toLowerCase().equals("1")) {
								
								// user folds
								user.fold();
								// give computer current pot amount
								george.recPot(table.retPot());
								table.resetPot();
								bothPlayersDone = true;
								return false;
								
							} else if(user_move.toLowerCase().equals("2")) {
								// user calls
								user.call();
								user.bid(comp_raise, george.retChips());
								// add raised amount from user to table pot
								table.addToPot(comp_raise);
								bothPlayersDone = true;
								
							} else if(user_move.toLowerCase().equals("3")) {
								// user raises
								System.out.println("How much do you want to raise by? " + " You have: " + user.retChips() + " Pot amount: " + table.retPot());
								String user_raise_amt = user_input.nextLine();
								
								user.bid(comp_raise, george.retChips());
								table.addToPot(comp_raise);
								user.raise();
								user.bid(Integer.parseInt(user_raise_amt), george.retChips());		// this will have to change and have the computer do it instead
								table.addToPot(Integer.parseInt(user_raise_amt));
							}
							
							
						} else {
							System.out.println("Restart program you typed something invalid");
						}
						
					}
					
					return true;
					
					// you need to work on
				} else {
					System.out.println("Restart game you typed in something invalid");
				}
			
				// player bids
				// computer can fold, call, or raise
				// if fold - player gets current pot
				// if raise - ask player if he wants to fold, call or raise
					// the 2 line above can repeat**
				// add bids to pot
				// show flop
//			}
				return false;
		}
			
			String comp_move = george.decide(0);
				
			if(!comp_move.equals("1")) {
	 
		  /** BUG:  flop gets called twice in one round */
				// show flop, turn or river
				 
				if(specific_round.equals("flop")) {
					table.flop();
				} else if(specific_round.equals("turn")) {
					table.turn();
				} else if(specific_round.equals("river")) {
					table.river();
				} 	
				
				// computer bids
				
				george.bid(george.wantToBid(), user.retChips());							// this will have to change and have the computer do it instead
				System.out.println();
				System.out.print("Cards on Table: ");
				table.showCardsOnTable();
				System.out.println();
				System.out.println("Computer bids " + george.wantToBid());																	
				// I think there has to be an if and else statement here because what happens if didCompBid returns false ** problem here
				table.addToPot(george.wantToBid());
				while(!bothPlayersDone) {
					System.out.println("Pot Amount: " + table.retPot());
					user.showHand();
					System.out.println();
					System.out.println("Do you want to fold, call, or raise? (Type '1' = fold, '2' = call, or '3' = raise) ");
					// player can fold, call, or raise
					String user_decision2 = user_input.nextLine();
							
					if(user_decision2.toLowerCase().equals("1")) {
						
						// fold - computer gets current pot
						user.fold();
						// give computer current pot amount
						george.recPot(table.retPot());
						table.resetPot();
						bothPlayersDone = true;				
						return false;
								
					} else if(user_decision2.toLowerCase().equals("2")) {
						// call - show flop
						user.call();
						user.bid(george.wantToBid(), george.retChips());
						table.addToPot(george.wantToBid());
						bothPlayersDone = true;
								
					} else if(user_decision2.toLowerCase().equals("3")) {
						// raise - ask computer to fold, raise or call
						System.out.println("How much do you want to raise by? " + " You have: " + user.retChips() + " Pot amount: " + table.retPot());
						String user_raise_amt = user_input.nextLine();
						user.bid(george.wantToBid(), george.retChips());
						table.addToPot(george.wantToBid());
						user.bid(Integer.parseInt(user_raise_amt), george.retChips());
						table.addToPot(Integer.parseInt(user_raise_amt));
						
						
						String comp_decision = george.decide(1);		// put -1 for fold, 0 for call, and 1 for raise
								
						if(comp_decision.toLowerCase().equals("1")) {
							// computer folds
							george.fold();
							// give user current pot amount
							user.recPot(table.retPot());
							table.resetPot();
							bothPlayersDone = true;
							return false;
									
						} else if(comp_decision.toLowerCase().equals("2")) {
							// computer calls
							george.call();
							george.bid(Integer.parseInt(user_raise_amt), user.retChips());
							// add raised amount from computer to table pot
							table.addToPot(Integer.parseInt(user_raise_amt));
							bothPlayersDone = true;
									
						} else if(comp_decision.toLowerCase().equals("3")) {
							// computer raises
							// you have to fix this
							george.bid(Integer.parseInt(user_raise_amt), user.retChips());
							table.addToPot(Integer.parseInt(user_raise_amt));
							george.raise();
							george.bid(george.wantToBid(), user.retChips());					// this will have to change and have the computer do it instead
							table.addToPot(george.wantToBid());
						}
								
					} else {
						System.out.println("You typed something incorrectly... you will have to restart the game");
					}
				}
				return true;
			}	// if fold - computer gets current pot
					// if raise - ask computer if he wants to fold, call or raise
						// the 2 line above can repeat**
					// add bids to pot
		// user gets current pot amount
		return false;
	}
}
