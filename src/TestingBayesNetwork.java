import BayesianNetworks.*;

// 39767 - best of 2 cards option - has to be used in the first round
// 2970357 - best of 5 cards option - has to be used in flop, turn, and river round

// P(winflop) = P(hand_strength) * (1 - P(opponent_strength))
// P(opponent_strength) = lambda(opponents_chips / total_chips)
// lambda = 1/3

public class TestingBayesNetwork extends BayesNet {
	
	
	public static double[] probOfWinningRound(double hand_strength, double opponents_strength) {
		double[] result = new double[2];
		result[0] = hand_strength * (1 - opponents_strength);
		System.out.println(result[0]);
		result[1] = (1 - result[0]);
		System.out.println(result[1]);
		return result;
	} 
	
	public static double[] calcOpponentStrength(double opponents_chips, double total_chips) {
		double[] result = new double[2];
		
		result[0] = opponents_chips/total_chips;
		System.out.println(result[0]);
		result[1] = 1-result[0];
		System.out.println(result[1]);
		return result;
	}
	
	public static double[] calcHandStrength() {
		double[] result = new double[2];
		// need to do
		result[0] = 0.4;
	    result[1] = 0.6;
		
		return result;
	}
	
	public static void main(String[] args) {
		DiscreteVariable bowel_problem = new DiscreteVariable("bowel-problem", DiscreteVariable.CHANCE, new String[] {"true", "false"});
		
		DiscreteVariable dog_out = new DiscreteVariable("dog-out", DiscreteVariable.CHANCE, new String[] {"true","false"});

		DiscreteVariable family_out = new DiscreteVariable("family-out", DiscreteVariable.CHANCE, new String[] {"true","false"});
		
		DiscreteFunction p3 = new DiscreteFunction( new DiscreteVariable[] {dog_out}, new DiscreteVariable[] {bowel_problem, family_out}, new double[] {0.99, 0.97, 0.9, 0.3, 0.01, 0.03, 0.1, 0.7});
		
		
		
		
		
		
		/*
		 * Bayesian node for opponent_move
		 */
		DiscreteVariable opponent_strength = new DiscreteVariable("opponent_move", DiscreteVariable.CHANCE, new String[] {"strong" , "weak"});
		double[] opponent_strength_calc = calcOpponentStrength(3000.0, 9000.0);
		DiscreteFunction opponent_strength_prob = new DiscreteFunction( new DiscreteVariable[] {opponent_strength}, new double[] {opponent_strength_calc[0], opponent_strength_calc[1]});
		
		System.out.println(opponent_strength_prob.get_value(0));
		System.out.println(opponent_strength_prob.get_value(1));

		
		/*
		 * Bayesian node for hand_strength
		 */
		DiscreteVariable hand_strength = new DiscreteVariable("hand_strength", DiscreteVariable.CHANCE, new String[] {"strong", "weak"});
		double[] hand_strength_calc = calcHandStrength();
		DiscreteFunction hand_strength_prob = new DiscreteFunction( new DiscreteVariable[] {hand_strength}, new double[] {hand_strength_calc[0], hand_strength_calc[1]});
		
		System.out.println(hand_strength_prob.get_value(0));
		System.out.println(hand_strength_prob.get_value(1));

		
		/*
		 * Bayesian node for winning any round
		 */
		
		DiscreteVariable win_round = new DiscreteVariable("round", DiscreteVariable.CHANCE, new String[] {"win", "lose"});
		double[] win_round_calc = probOfWinningRound(hand_strength_calc[0], opponent_strength_calc[0]);
		DiscreteFunction win_round_prob = new DiscreteFunction(new DiscreteVariable[] {win_round}, new double[] {win_round_calc[0], win_round_calc[1]});
		
		System.out.println(win_round_prob.get_value(0));
		System.out.println(win_round_prob.get_value(1));
	}

}
