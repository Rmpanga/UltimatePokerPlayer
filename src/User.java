
public class User {
	private Hand user_hand;
	private String name;
	
	public User(String name) {
		this.name = name;
	}
	
	public void addHand(Hand hand) {
		user_hand = hand;
	}
	
}
