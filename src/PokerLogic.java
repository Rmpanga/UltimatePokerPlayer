import java.util.Scanner;


public class PokerLogic {
	/*
	 * Handles the final round, bids, etc.
	 */
	public static boolean initRound(boolean player, Computer george, User user, Scanner user_input, Table table) {
		// You have to handle when there are no chips from one person, or both people
		System.out.println();
		System.out.println(" --------------------- Initial Round --------------------- ");
		return round(player, george, user, user_input, table, "init");
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
				System.out.println("Do you want to fold, check, or raise (Type '1' = fold '2' = check '3' = raise)");
//				System.out.println("Do you want to bid or fold? (Type '1' = fold '2' = bid)");
				// player can fold, call, or raise
				String user_decision = user_input.nextLine();
			
				if(user_decision.toLowerCase().equals("1")) {
					// computer receives pot amount
					// reset the pot
					george.recPot(table.retPot());
					table.resetPot();
					return false;
					
				} else if(user_decision.toLowerCase().equals("2")) {
					int count = 0;
					int user_raised_bid = 0;
					// user checks
					user.check();
					
					while(!bothPlayersDone){
						String comp_decision = george.decide(1);						// put -1 for fold, 0 for check, 2 for check,  1 for raise
						
						if(comp_decision.toLowerCase().equals("1")) {
							
							// computer - folds
							// user gets pot
							// table pot reset
							// return false;
							
							george.fold();
							user.recPot(table.retPot());
							table.resetPot();
							return false;
							
						} else if(comp_decision.toLowerCase().equals("2")) { 
							
							george.call();
							george.bid(user_raised_bid, user.retChips());
							table.addToPot(user_raised_bid);
							bothPlayersDone = true;
							return true;
							
						} else if(comp_decision.toLowerCase().equals("4")) {
							// computer also checks
							// no one bids or receives pot
							// move to next round
							
							george.check();
							return true;
							
						} else if(comp_decision.toLowerCase().equals("3")) {
							// this is for raise
							
							int comp_raise = george.howMuchToRaise();
							
							george.bid(comp_raise, user.retChips());
							table.addToPot(comp_raise);
							
							if(count != 0) {
								george.bid(user_raised_bid, user.retChips());
								table.addToPot(user_raised_bid);
							} 
							
							// ask user what he wants to do here
							System.out.println("Do you want to fold, call, or raise? (Type '1' = fold, '2' = call, or '3' = raise) ");
							String user_move = user_input.nextLine();
							
							if(user_move.toLowerCase().equals("1")) {
								// user folds
								// computer gets pot
								// reset table pot
								// bothPlayersDone = true;
								// return false;
								
								user.fold();
								george.recPot(table.retPot());
								table.resetPot();
								bothPlayersDone = true;
								return false;
								
							} else if(user_move.toLowerCase().equals("2")) {
								// user calls
								// user bids computer raised amt (comp_raise)
								// add comp_raise amt to table pot
								// bothPlayersDone = true;
								// return true;
								
								user.call();
								user.bid(comp_raise, george.retChips());
								table.addToPot(comp_raise);
								bothPlayersDone = true;
								return true;
								
							} else if(user_move.toLowerCase().equals("3")) {
								// user raises
								// Ask user how much he wants to raise by
								// user bids computer raised amt (comp_raise)
								// add comp_raise amt to table pot
								// user bids his raised amt
								// add user_bid amount to table pot
								
								System.out.println("How much do you want to raise by?");
								int user_raise_amt = Integer.parseInt(user_input.nextLine());
								
								user.raise();
								user.bid(comp_raise, george.retChips());
								table.addToPot(comp_raise);
								user.bid(user_raise_amt, george.retChips());
								table.addToPot(user_raise_amt);
								user_raised_bid = user_raise_amt;
							}
							count++;
						}
					}
					
					return true;
					
				} else if(user_decision.toLowerCase().equals("3")) {
					
					user.raise();
					System.out.println("How much you want to raise by?");
					int user_raise_amt = Integer.parseInt(user_input.nextLine());
					
					user.bid(user_raise_amt, george.retChips());
					table.addToPot(user_raise_amt);
					
					while(!bothPlayersDone) {
						
						String comp_decision = george.decide(1);			// -1 for fold, 0 for call, 1 for raise
						
						if(comp_decision.toLowerCase().equals("1")) {
							// computer - folds
							george.fold();
							// give user current pot amount
							user.recPot(table.retPot());
							table.resetPot();
							bothPlayersDone = true;				
							return false;
							
						} else if(comp_decision.toLowerCase().equals("2")) {
							// computer calls - show flop
							george.call();
							george.bid(user_raise_amt, user.retChips());
							table.addToPot(user_raise_amt);
							bothPlayersDone = true;
							return true;
							
						} else if(comp_decision.toLowerCase().equals("3")) {
							// computer raises
							
							int comp_raise = george.howMuchToRaise();
							
							george.bid(user_raise_amt, user.retChips());
							table.addToPot(user_raise_amt);
							george.bid(comp_raise, user.retChips());
							table.addToPot(comp_raise);
							
							System.out.println("Computer raised by " + comp_raise);
							System.out.println("Do you want to fold, call, or raise? (Type '1' = fold, '2' = call, or '3' = raise) ");
							// player can fold, call, or raise
							String user_move = user_input.nextLine();
							
							if(user_move.toLowerCase().equals("1")) {
								// user fold
								user.fold();
								// give computer current pot amount
								george.recPot(table.retPot());
								table.resetPot();
								bothPlayersDone = true;
								return false;
								
							} else if(user_move.toLowerCase().equals("2")) {
								// user call
								user.call();
								user.bid(comp_raise, george.retChips());
								// add raised amount from user to table pot
								table.addToPot(comp_raise);
								bothPlayersDone = true;
								return true;
								
							} else if(user_move.toLowerCase().equals("3")) {
								// user raise
								System.out.println("How much do you want to raise by? " + " You have: " + user.retChips() + " Pot amount: " + table.retPot());
								int user_raise_amt2 = Integer.parseInt(user_input.nextLine());
								
								user.bid(comp_raise, george.retChips());
								table.addToPot(comp_raise);
								user.raise();
								user.bid(user_raise_amt2, george.retChips());
								table.addToPot(user_raise_amt2);
								
							}
						}
						
					}
					
					return true;
					
				}
				
				else {
					System.out.println("Restart game you typed in something invalid");
				}

			return false;
		}
			
		// show flop, turn or river
		 
		if(specific_round.equals("flop")) {
			table.flop();
		} else if(specific_round.equals("turn")) {
			table.turn();
		} else if(specific_round.equals("river")) { 
			table.river();
		} 	
		
		String comp_move = george.decide(2);					// -1 for fold, 2 for check, and 1 for raise
				
		if(comp_move.toLowerCase().equals("1")) {
			// computer folds
			// user receives pot amount
			// reset the pot
			user.recPot(table.retPot());
			table.resetPot();
			return false;
			
		} else if (comp_move.toLowerCase().equals("4")) {
			int count = 0;
			int george_raised_bid = 0;
			// computer checks
			george.check();
			
			while(!bothPlayersDone) {
				if(count == 0) {
					System.out.println("Do you want to fold, check, or raise? (Type '1' = fold, '2' = check, or '3' = raise) ");
				} else {
					System.out.println("Do you want to fold, call, or raise? (Type '1' = fold, '2' = call, or '3' = raise) ");
				}
				String user_move = user_input.nextLine();
				
				if(user_move.toLowerCase().equals("1")) {
					// user folds
					// computer gets pot
					// table pot reset
					// return false;
					
					user.fold();
					george.recPot(table.retPot());
					table.resetPot();
					return false;
					
				} else if (user_move.toLowerCase().equals("2")) {
					// user also checks
					// no one bids or receives pot
					// move to next round
					if(count == 0) {
						user.check();
						return true;
					} else {
						user.call();
						user.bid(george_raised_bid, george.retChips());
						table.addToPot(george_raised_bid);
						bothPlayersDone = true;
						return true;
					}
					
				} else if(user_move.toLowerCase().equals("3")) {
					// user raises
					
					System.out.println("How much do you want to raise by?");
					int user_raise_amt = Integer.parseInt(user_input.nextLine());
					
					user.bid(user_raise_amt, george.retChips());
					table.addToPot(user_raise_amt);
					
					if(count != 0) {
						user.bid(george_raised_bid, george.retChips());
						table.addToPot(george_raised_bid);
					}
					
					String comp_decision = george.decide(0);							// -1 for fold, 0 for call, 1 for raise
					
					if(comp_decision.toLowerCase().equals("1")) {
						// computer folds
						// user getrs pot
						// reset table pot
						// bothPlayersDone = true
						// return false;
						
						george.fold();
						user.recPot(table.retPot());
						table.resetPot();
						bothPlayersDone = true;
						return false;
						
					} else if(comp_decision.toLowerCase().equals("2")) {
						// computer calls
						// computer bids user raised amt (user_raise_amt)
						// add user_raise_amt to table pot
						// bothPlayersDone = true;
						// return true;
						
						george.call();
						george.bid(user_raise_amt, user.retChips());
						table.addToPot(user_raise_amt);
						bothPlayersDone = true;
						return true;
						
					} else if(comp_decision.toLowerCase().equals("3")) {
						// computer raises
						// ask how much computer wants to raise by
						// computer bids user raised amt (user_raise_amt)
						// add user_raise_amt to table pot
						// computer bids his raised amt
						// add computer bids amount to table pot
						
						george.raise();
						int george_raise_amt = george.howMuchToRaise();
						
						george.bid(user_raise_amt, user.retChips());
						table.addToPot(user_raise_amt);
						george.bid(george_raise_amt, user.retChips());
						table.addToPot(george_raise_amt);
						george_raised_bid = george_raise_amt;
					}
					count++;
				}
				
			}
			
		} else if(comp_move.toLowerCase().equals("3")) {
			// computer raises
			george.raise();
			int george_raise_amt = george.howMuchToRaise();
			
			george.bid(george_raise_amt, user.retChips());
			table.addToPot(george_raise_amt);
			
			while(!bothPlayersDone) {
				
				System.out.println("Do you want to fold, call, or raise? (Type '1' = fold, '2' = call, or '3' = raise) ");
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
					// user calls - show flop
					user.call();
					user.bid(george_raise_amt, george.retChips());
					table.addToPot(george_raise_amt);
					bothPlayersDone = true;
					return true;
					
				} else if(user_move.toLowerCase().equals("3")) {
					// user raises
					
					System.out.println("How much do you want to raise by? ");
					int user_raise_amt = Integer.parseInt(user_input.nextLine());
					
					user.bid(george_raise_amt, george.retChips());
					table.addToPot(george_raise_amt);
					user.bid(user_raise_amt, george.retChips());
					table.addToPot(user_raise_amt);
					
					String comp_move2 = george.decide(0);					// -1 for fold, 0 for call, 1 for raise
					
					if(comp_move2.equals("1")) {
						// computer folds
						george.fold();
						// give user current pot amount
						user.recPot(table.retPot());
						table.resetPot();
						bothPlayersDone = true;
						return false;
						
					} else if(comp_move2.equals("2")) {
						// computer calls
						george.call();
						george.bid(user_raise_amt, user.retChips());
						table.addToPot(user_raise_amt);
						bothPlayersDone = true;
						return true;
						
					} else if(comp_move2.equals("3")) {
						// computer raise
						
						int comp_raise_amt = george.howMuchToRaise();
						
						george.bid(user_raise_amt, user.retChips());
						table.addToPot(user_raise_amt);
						george.raise();
						george.bid(comp_raise_amt, user.retChips());
						table.addToPot(comp_raise_amt);

					}					
				}
			}
			
		}

		return false;
	}
}
