import BayesianNetworks.BayesNet;
import BayesianNetworks.DiscreteFunction;
import BayesianNetworks.DiscreteVariable;


public class Network extends BayesNet {
	
	public static double lambda = 0.33;
	
	/*
	 * Calculate opponents strength
	 */
	public static double[] calcOpponentStrength(double opponents_chips, double total_chips) {
		double[] result = new double[2];
		
		result[0] = (lambda)*(opponents_chips/total_chips);
		result[1] = (1-result[0]);

		return result;
	}
	
	/*
	 * Calculate hand strength
	 */
	public static double[] calcHandStrength(double hand_strength) {
		double[] result = new double[2];
		result[0] = hand_strength;
	    result[1] = (1 - result[0]);	
	    
		return result;
	}
	
	/*
	 * calculate probability of winning a round 
	 */
	public static double[] probOfWinningRound(double hand_strength, double opponents_strength) {
		double[] result = new double[2];
		
		result[0] = hand_strength * (1 - opponents_strength);
		result[1] = (1 - result[0]);
		
		return result;
	} 
	
	DiscreteVariable opponent_strength = new DiscreteVariable("opponent_move", DiscreteVariable.CHANCE, new String[] {"strong" , "weak"});
	DiscreteVariable hand_strength = new DiscreteVariable("hand_strength", DiscreteVariable.CHANCE, new String[] {"strong", "weak"});
	DiscreteVariable win_round = new DiscreteVariable("round", DiscreteVariable.CHANCE, new String[] {"win", "lose"});
	double[] opponent_strength_calc;
	double[] hand_strength_calc;
	double[] win_round_calc;
	
	/*
	 * Determine what move the computer should do
	 */
	public String evaluate() {
		DiscreteFunction opponent_strength_prob = new DiscreteFunction( new DiscreteVariable[] {opponent_strength}, new double[] {opponent_strength_calc[0], opponent_strength_calc[1]});
		DiscreteFunction hand_strength_prob = new DiscreteFunction( new DiscreteVariable[] {hand_strength}, new double[] {hand_strength_calc[0], hand_strength_calc[1]});
		DiscreteFunction win_round_prob = new DiscreteFunction(new DiscreteVariable[] {win_round}, new double[] {win_round_calc[0], win_round_calc[1]});

		double result = win_round_prob.get_value(0) - win_round_prob.get_value(1);
		
		if(result > 0.3) {
			return "raise";
		} else if(result > -0.3 && result < 0.3) {
			return "call";
		} else if(result < -0.3) {
			return "fold";
		}
		return "check";
	}
	
	/*
	 * Update the Baysian Network with new values for each round we play in poker
	 */
	public void update(double hand_strength, double opponents_chips, double total_chips) {
		opponent_strength_calc = calcOpponentStrength(opponents_chips, total_chips);
		hand_strength_calc = calcHandStrength(hand_strength);
		win_round_calc = probOfWinningRound(hand_strength_calc[0], opponent_strength_calc[0]);
	}
	
}
