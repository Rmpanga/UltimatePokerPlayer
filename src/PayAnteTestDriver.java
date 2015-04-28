public class PayAnteTestDriver {
	
	public static int ante = 20; 
	public static int start_chip_amt = 5000;
	
	public static void main(String[] args) {
	
	System.out.println("----------------- TEST 1 -----------------");
	System.out.println("Ante amt: " + start_chip_amt);
	Computer george = new Computer("George", start_chip_amt);
	User user = new User("Peter", start_chip_amt);
	
	// start_chip_amt = 5000
	System.out.println("User chips: " + user.retChips() + " should be 5000");
	// player pays ante
	if(user.payAnte(ante)) {
		System.out.println("Player pays ante");
		System.out.println("User chips: " + user.retChips() + " should be 4980");
	} else {
		System.out.println("User doesn't have enough to pay ante");
	}
	
	System.out.println("Computer chips: " + george.retChips() + " should be 5000");
	// computer pays ante
	if(george.payAnte(ante)) {
		System.out.println("Computer pays ante");
		System.out.println("Computer chips: " + george.retChips() + " should be 4980");
	} else {
		System.out.println("Computer doesn't have enough to pay ante");
	}
	
	System.out.println();
	System.out.println("----------------- TEST 2 -----------------");
	// start_chip_amt = 20
	
	start_chip_amt = 20;
	System.out.println("Ante amt: " + start_chip_amt);
	Computer george2 = new Computer("George", start_chip_amt);
	User user2 = new User("Peter", start_chip_amt);
	
	System.out.println("User chips: " + user2.retChips() + " should be 20");
	// player pays ante
	if(user2.payAnte(ante)) {
		System.out.println("Player pays ante");
		System.out.println("User chips: " + user2.retChips() + " should be 0");
	} else {
		System.out.println("User doesn't have enough to pay ante");
	}
	
	System.out.println("Computer chips: " + george2.retChips() + " should be 20");
	// computer pays ante
	if(george2.payAnte(ante)) {
		System.out.println("Computer pays ante");
		System.out.println("Computer chips: " + george2.retChips() + " should be 0");
	} else {
		System.out.println("Computer doesn't have enough to pay ante");
	}
	
	System.out.println();
	System.out.println("----------------- TEST 3 -----------------");
	
	// start_chip_amt = 20
	
	start_chip_amt = 0;
	System.out.println("Ante amt: " + start_chip_amt);
	Computer george3 = new Computer("George", start_chip_amt);
	User user3 = new User("Peter", start_chip_amt);
	
	System.out.println("User chips: " + user3.retChips());
	// player pays ante
	if(user3.payAnte(ante)) {
		System.out.println("Player pays ante");
		System.out.println("User chips: " + user3.retChips() + " should be 0");
	} else {
		System.out.println("User doesn't have enough to pay ante");
	}
	
	System.out.println("Computer chips: " + george3.retChips());
	// computer pays ante
	if(george3.payAnte(ante)) {
		System.out.println("Computer pays ante");
		System.out.println("Computer chips: " + george3.retChips() + " should be 0");
	} else {
		System.out.println("Computer doesn't have enough to pay ante");
	}
	
	}
}
