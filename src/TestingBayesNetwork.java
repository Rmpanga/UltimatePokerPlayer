import BayesianNetworks.*;



public class TestingBayesNetwork {

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
		
		
		
		DiscreteVariable init_round = new DiscreteVariable("init-round", DiscreteVariable.CHANCE, new String[] {"true", "false"});
		DiscreteVariable flop_round = new DiscreteVariable("flop-round", DiscreteVariable.CHANCE, new String[] {"true", "false"});
		DiscreteVariable turn_round = new DiscreteVariable("turn-round", DiscreteVariable.CHANCE, new String[] {"true", "false"});
		DiscreteVariable river_round = new DiscreteVariable("river-round", DiscreteVariable.CHANCE, new String[] {"true", "false"});
		
		
	}

}
