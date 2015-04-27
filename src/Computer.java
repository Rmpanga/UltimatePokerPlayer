
public class Computer {
	
	private String name;
	public Hand hand;
	public Computer(String name) {
		this.name = name;
	}
	
	public void addHand(Hand hand){
		this.hand = hand;
	}
	
}
