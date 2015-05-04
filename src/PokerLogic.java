import java.util.Scanner;


public class PokerLogic {
	/*
	 * Handles the final round, bids, etc.
	 */
	public static boolean initRound(boolean player, Computer george, User user, Scanner user_input, Table table, HandEvaluator evaluator) {
		// You have to handle when there are no chips from one person, or both people
		System.out.println();
		System.out.println(" --------------------- Initial Round --------------------- ");
		Network bayes_network = new Network();
		
		return round(player, george, user, user_input, table, bayes_network, evaluator, "init");
	}
	
	/*
	 * Handles the flop round, bids, etc.
	 */
	public static boolean flopRound(boolean player, Computer george, User user, Scanner user_input, Table table, HandEvaluator evaluator) {
		// You have to handle when there are no chips from one person, or both people
		System.out.println();
		System.out.println(" --------------------- Flop Round --------------------- ");
		Network bayes_network = new Network();
		return round(player, george, user, user_input, table, bayes_network, evaluator, "flop");
	}
	
	/*
	 * Handles the turn round, bids, etc.
	 */
	public static boolean turnRound(boolean player, Computer george, User user, Scanner user_input, Table table, HandEvaluator evaluator) {
		// You have to handle when there are no chips from one person, or both people
		System.out.println(" --------------------- Turn Round --------------------- ");
		Network bayes_network = new Network();
		return round(player, george, user, user_input, table, bayes_network, evaluator, "turn");
	}
	
	/*
	 * Handles the river round, bids, etc.
	 */
	public static boolean riverRound(boolean player, Computer george, User user, Scanner user_input, Table table, HandEvaluator evaluator) {
		// You have to handle when there are no chips from one person, or both people
		System.out.println(" --------------------- River Round --------------------- ");
		Network bayes_network = new Network();
		return round(player, george, user, user_input, table, bayes_network, evaluator, "river");
	}
	
	
	public static boolean round(boolean player, Computer george, User user, Scanner user_input, Table table, Network bayes_network, HandEvaluator evaluator, String specific_round) {
		
		boolean bothPlayersDone = false;
		double bestRank;
		
		if(player) {
			if(specific_round.equals("flop")) {
				table.flop();
				for (int j = 0; j< table.getCardsOnTable().size(); j++){
					Card c = table.getCardsOnTable().get(j);
					george.addCardToHand(c);
					user.addCardToHand(c);
				}
			} else if(specific_round.equals("turn")) {
				table.turn();
				george.addCardToHand(table.getCardsOnTable().get(3));
				user.addCardToHand(table.getCardsOnTable().get(3));
			} else if(specific_round.equals("river")) {
				table.river();
				george.addCardToHand(table.getCardsOnTable().get(4));
				user.addCardToHand(table.getCardsOnTable().get(4));
			} 	
			
			System.out.println();
			System.out.print("Cards on Table: ");
			table.showCardsOnTable();
			System.out.println();
			
			System.out.println("Pot Amount: " + table.retPot());
			user.showCards();
			System.out.println();
			
			System.out.println("Do you want to fold, check, or raise (Type '1' = fold '2' = check '3' = raise)");
				
			String user_decision = user_input.nextLine();
			
			if(user_decision.toLowerCase().equals("1")) {
				george.recPot(table.retPot());
				table.resetPot();
				return false;		
			} else if(user_decision.toLowerCase().equals("2")) {
				int count = 0;
				int user_raised_bid = 0;
				user.check();
				while(!bothPlayersDone){
					double rank = evaluator.rankHand(george.getHand());
						
					if((george.retHand().size() == 2) && rank > 300000) {
						bestRank = 39767.0;
					} else if((george.retHand().size() == 2) && rank < 300000) {
						bestRank = 167; 
					} else {
						bestRank = 2970357.0;
					}
					
					double rank_of_hand;
					if(george.retHand().size() == 2) {															
						rank_of_hand = ((double) (evaluator.rankHand(george.getHand())) / bestRank);
					} else {
						rank_of_hand = ((double) evaluator.rankHand(george.getHand()) / bestRank);
					}
						
					bayes_network.update(rank_of_hand, (double) user.retChips(), (double) Dealer.start_chip_amt*2);
					String comp_decision = george.decide(bayes_network);
						
					if(comp_decision.toLowerCase().equals("1")) {
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
						george.check();
						return true;	
					} else if(comp_decision.toLowerCase().equals("3")) {
						george.raise();
						int comp_raise = george.howMuchToRaise();
							
						System.out.println("George raises by: " + comp_raise);
							
						george.bid(comp_raise, user.retChips());
						table.addToPot(comp_raise);
							
						if(count != 0) {
							george.bid(user_raised_bid, user.retChips());
							table.addToPot(user_raised_bid);
						} 	
							
						System.out.println("Do you want to fold, call, or raise? (Type '1' = fold, '2' = call, or '3' = raise) ");
						String user_move = user_input.nextLine();
							
						if(user_move.toLowerCase().equals("1")) {
							user.fold();
							george.recPot(table.retPot());
							table.resetPot();
							bothPlayersDone = true;
							return false;	
						} else if(user_move.toLowerCase().equals("2")) {
							user.call();
							user.bid(comp_raise, george.retChips());
							table.addToPot(comp_raise);
							bothPlayersDone = true;
							return true;	
						} else if(user_move.toLowerCase().equals("3")) {
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
					double rank = evaluator.rankHand(george.getHand());
						
					if((george.retHand().size() == 2) && rank > 300000) {
						bestRank = 39767.0;
					} else if((george.retHand().size() == 2) && rank < 300000) {
						bestRank = 167; 
					} else {
						bestRank = 2970357.0;
					}
					
					double rank_of_hand;
					if(george.retHand().size() == 2) {														
						rank_of_hand = ((double) (evaluator.rankHand(george.getHand())) / bestRank);
					} else {
						rank_of_hand = ((double) evaluator.rankHand(george.getHand()) / bestRank);
					}
					
					bayes_network.update(rank_of_hand, (double) user.retChips(), (double) Dealer.start_chip_amt*2);						
					String comp_decision = george.decide(bayes_network);	
						
					if(comp_decision.toLowerCase().equals("1")) {
						george.fold();
						user.recPot(table.retPot());
						table.resetPot();
						bothPlayersDone = true;				
						return false;		
					} else if(comp_decision.toLowerCase().equals("2")) {
						george.call();
						george.bid(user_raise_amt, user.retChips());
						table.addToPot(user_raise_amt);
						bothPlayersDone = true;
						return true;	
					} else if(comp_decision.toLowerCase().equals("3")) {
						george.raise();
						int comp_raise = george.howMuchToRaise();
							
						george.bid(user_raise_amt, user.retChips());
						table.addToPot(user_raise_amt);
						george.bid(comp_raise, user.retChips());
						table.addToPot(comp_raise);
							
						System.out.println("Computer raised by " + comp_raise);
						System.out.println("Do you want to fold, call, or raise? (Type '1' = fold, '2' = call, or '3' = raise) ");
						String user_move = user_input.nextLine();
							
						if(user_move.toLowerCase().equals("1")) {
							user.fold();
							george.recPot(table.retPot());
							table.resetPot();
							bothPlayersDone = true;
							return false;	
						} else if(user_move.toLowerCase().equals("2")) {
							user.call();
							user.bid(comp_raise, george.retChips());
							table.addToPot(comp_raise);
							bothPlayersDone = true;
							return true;	
						} else if(user_move.toLowerCase().equals("3")) {
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
			} else {
				System.out.println("Restart game you typed in something invalid");
			}
			return false;
		}
		
		
		
		 
		if(specific_round.equals("flop")) {
			table.flop();
			for (int j = 0; j< table.getCardsOnTable().size(); j++){
				Card c = table.getCardsOnTable().get(j);
				george.addCardToHand(c);
				user.addCardToHand(c);
			}
		} else if(specific_round.equals("turn")) {
			table.turn();
			table.turn();
			george.addCardToHand(table.getCardsOnTable().get(3));
			user.addCardToHand(table.getCardsOnTable().get(3));
		} else if(specific_round.equals("river")) { 
			table.river();
			george.addCardToHand(table.getCardsOnTable().get(4));
			user.addCardToHand(table.getCardsOnTable().get(4));
		} 	
		
		System.out.println();
		System.out.print("Cards on Table: ");
		table.showCardsOnTable();
		System.out.println();
		
		System.out.println("Pot Amount: " + table.retPot());
		user.showCards();
		System.out.println();
		
		double rank = evaluator.rankHand(george.getHand());
		
		if((george.retHand().size() == 2) && rank > 300000) {
			bestRank = 39767.0;
		} else if((george.retHand().size() == 2) && rank < 300000) {
			bestRank = 167; 
		} else {
			bestRank = 2970357.0;
		}

		double rank_of_hand;
		if(george.retHand().size() == 2) {														
			rank_of_hand = ((double) (evaluator.rankHand(george.getHand())) / bestRank);
		} else {
			rank_of_hand = ((double) evaluator.rankHand(george.getHand()) / bestRank);
		}
		
		bayes_network.update(rank_of_hand, (double) user.retChips(), (double) Dealer.start_chip_amt*2);		
		String comp_move = george.decide(bayes_network);	
				
		if(comp_move.toLowerCase().equals("1")) {
			george.fold();
			user.recPot(table.retPot());
			table.resetPot();
			return false;
		} else if (comp_move.toLowerCase().equals("4")) {
			george.check();
			int count = 0;
			int george_raised_bid = 0;
			
			while(!bothPlayersDone) {
				
				if(count == 0) {
					System.out.println("Do you want to fold, check, or raise? (Type '1' = fold, '2' = check, or '3' = raise) ");
				} else {
					System.out.println("Do you want to fold, call, or raise? (Type '1' = fold, '2' = call, or '3' = raise) ");
				}
				
				String user_move = user_input.nextLine();
				
				if(user_move.toLowerCase().equals("1")) {
					user.fold();
					george.recPot(table.retPot());
					table.resetPot();
					return false;
				} else if (user_move.toLowerCase().equals("2")) {
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
					user.raise();		
					
					System.out.println("How much do you want to raise by?");
					int user_raise_amt = Integer.parseInt(user_input.nextLine());
					
					user.bid(user_raise_amt, george.retChips());
					table.addToPot(user_raise_amt);
					
					if(count != 0) {
						user.bid(george_raised_bid, george.retChips());
						table.addToPot(george_raised_bid);
					}
					
					double rank2 = evaluator.rankHand(george.getHand());
					
					if((george.retHand().size() == 2) && rank2 > 300000) {
						bestRank = 39767.0;
					} else if((george.retHand().size() == 2) && rank2 < 300000) {
						bestRank = 167; 
					} else {
						bestRank = 2970357.0;
					}
					
					double rank_hand;
					if(george.retHand().size() == 2) {													
						rank_hand = ((double) (evaluator.rankHand(george.getHand())) / bestRank);
					} else {
						rank_hand = ((double) evaluator.rankHand(george.getHand()) / bestRank);
					}
					
					bayes_network.update(rank_hand, (double) user.retChips(), (double) Dealer.start_chip_amt*2);					
					String comp_decision = george.decide(bayes_network);
					
					if(comp_decision.toLowerCase().equals("1")) {
						george.fold();
						user.recPot(table.retPot());
						table.resetPot();
						bothPlayersDone = true;
						return false;
					} else if(comp_decision.toLowerCase().equals("2")) {
						george.call();
						george.bid(user_raise_amt, user.retChips());
						table.addToPot(user_raise_amt);
						bothPlayersDone = true;
						return true;
					} else if(comp_decision.toLowerCase().equals("3")) {
						george.raise();
						
						int george_raise_amt = george.howMuchToRaise();
						System.out.println("Computer raises by " + george_raise_amt);
						
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
			
			george.raise();
			int george_raise_amt = george.howMuchToRaise();
			
			george.bid(george_raise_amt, user.retChips());
			table.addToPot(george_raise_amt);
			
			while(!bothPlayersDone) {
				
				System.out.println("Do you want to fold, call, or raise? (Type '1' = fold, '2' = call, or '3' = raise) ");
				String user_move = user_input.nextLine();
				
				if(user_move.toLowerCase().equals("1")) {
					user.fold();
					george.recPot(table.retPot());
					table.resetPot();
					bothPlayersDone = true;
					return false;
				} else if(user_move.toLowerCase().equals("2")) {
					user.call();
					user.bid(george_raise_amt, george.retChips());
					table.addToPot(george_raise_amt);
					bothPlayersDone = true;
					return true;
				} else if(user_move.toLowerCase().equals("3")) {
					user.raise();	
					System.out.println("How much do you want to raise by? ");
					int user_raise_amt = Integer.parseInt(user_input.nextLine());
					
					user.bid(george_raise_amt, george.retChips());
					table.addToPot(george_raise_amt);
					user.bid(user_raise_amt, george.retChips());
					table.addToPot(user_raise_amt);
					
					double rank2 = evaluator.rankHand(george.getHand());
					
					if((george.retHand().size() == 2) && rank2 > 300000) {
						bestRank = 39767.0;
					} else if((george.retHand().size() == 2) && rank2 < 300000) {
						bestRank = 167; 
					} else {
						bestRank = 2970357.0;
					}

					double rank_hand;
					if(george.retHand().size() == 2) {															
						rank_hand = ((double) (evaluator.rankHand(george.getHand())) / bestRank);
					} else {
						rank_hand = ((double) evaluator.rankHand(george.getHand()) / bestRank);
					}
					
					bayes_network.update(rank_hand, (double) user.retChips(), (double) Dealer.start_chip_amt*2);					
					String comp_move2 = george.decide(bayes_network);	
					
					if(comp_move2.equals("1")) {
						george.fold();
						user.recPot(table.retPot());
						table.resetPot();
						bothPlayersDone = true;
						return false;
					} else if(comp_move2.equals("2")) {
						george.call();
						george.bid(user_raise_amt, user.retChips());
						table.addToPot(user_raise_amt);
						bothPlayersDone = true;
						return true;
					} else if(comp_move2.equals("3")) {
						george.raise();
						int comp_raise_amt = george.howMuchToRaise();
				
						System.out.println("Computer raises by " + comp_raise_amt);
						george.bid(user_raise_amt, user.retChips());
						table.addToPot(user_raise_amt);
						george.bid(comp_raise_amt, user.retChips());
						table.addToPot(comp_raise_amt);
					}					
				}
			}	
		}
		return false;
	}
}
