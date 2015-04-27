
public class Computer {
	private Hand computer_hand;
	private String name;
	
	public Computer(String name) {
		this.name = name;
	}
	
	public void addHand(Hand hand) {
		computer_hand = hand;
	}
}
