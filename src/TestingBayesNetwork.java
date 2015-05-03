import BayesianNetworks.*;

// 39767 - best of 2 cards option - has to be used in the first round
// 2970357 - best of 5 cards option - has to be used in flop, turn, and river round

// P(winflop) = P(hand_strength) * (1 - P(opponent_strength))
// P(opponent_strength) = lambda(opponents_chips / total_chips)
// lambda = 1/3

public class TestingBayesNetwork extends BayesNet {
	
	
	public static double probOfWinningRound() {
		return 0.0;
	} 
	
	public static void main(String[] args) {
		DiscreteVariable bowel_problem = new DiscreteVariable("bowel-problem", DiscreteVariable.CHANCE, new String[] {"true", "false"});
		
//		System.out.println(bowel_problem.get_explanation_index());		// -1
//		System.out.println(bowel_problem.get_index());					// 0
//		System.out.println(bowel_problem.get_name());					// bowel-problem
//		System.out.println(bowel_problem.get_observed_index());			// -1
//		System.out.println(bowel_problem.get_type());					// 0
//		System.out.println(bowel_problem.is_observed());				// false
//		System.out.println(bowel_problem.is_explanation());				// false
//		System.out.println(bowel_problem.get_values());
		
		DiscreteVariable dog_out = new DiscreteVariable("dog-out", DiscreteVariable.CHANCE, new String[] {"true","false"});

		DiscreteVariable family_out = new DiscreteVariable("family-out", DiscreteVariable.CHANCE, new String[] {"true","false"});
		
		DiscreteFunction p3 = new DiscreteFunction( new DiscreteVariable[] {dog_out}, new DiscreteVariable[] {bowel_problem, family_out}, new double[] {0.99, 0.97, 0.9, 0.3, 0.01, 0.03, 0.1, 0.7});
		
		
		
		
		
		
		/*
		 * Bayesian node for opponent_move
		 */
		DiscreteVariable opponent_move = new DiscreteVariable("opponent_move", DiscreteVariable.CHANCE, new String[] {"true" , "false"});
		DiscreteFunction opponent_move_prob = new DiscreteFunction( new DiscreteVariable[] {opponent_move}, new double[] {0.3, 1.0});
		
	
		
		/*
		 * Bayesian node for hand_strength
		 */
		DiscreteVariable hand_strength = new DiscreteVariable("hand_strength", DiscreteVariable.CHANCE, new String[] {"true", "false"});
		DiscreteFunction hand_strength_prob = new DiscreteFunction( new DiscreteVariable[] {hand_strength}, new DiscreteVariable[] {opponent_move},  new double[] {0.1, 0.2, 0.3, 0.4});
		
		
		
		
		
		
		
		
		/*
		 *  Bayesian node for win_flop_round
		 */
		DiscreteVariable win_flop_round = new DiscreteVariable("win_flop_round", DiscreteVariable.CHANCE, new String[] {"true", "false"});
		DiscreteFunction win_flop_round_prob = new DiscreteFunction( new DiscreteVariable[] {win_flop_round}, new DiscreteVariable[] {hand_strength, opponent_move}, new double[] {} );
		
		
		/*
		 * Bayesian node for win_turn_round
		 */
		DiscreteVariable win_turn_round = new DiscreteVariable("win_turn_round", DiscreteVariable.CHANCE, new String[] {"true", "false"});
		DiscreteFunction win_turn_round_prob = new DiscreteFunction( new DiscreteVariable[] {win_turn_round}, new DiscreteVariable[] {win_flop_round, hand_strength, opponent_move}, new double[] {});
		
		
		/*
		 * Bayesian node for win_river_round
		 */
		DiscreteVariable win_river_round = new DiscreteVariable("win_river_round", DiscreteVariable.CHANCE, new String[] {"true", "false"});
		DiscreteFunction win_river_round_prob = new DiscreteFunction( new DiscreteVariable[] {win_river_round}, new DiscreteVariable[] {win_turn_round, win_flop_round, hand_strength, opponent_move}, new double[] {});
		
		
		// testing
		
		
		
	}

}
