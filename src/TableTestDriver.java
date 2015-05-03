
public class TableTestDriver {

	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffleDeck();
		Table table = new Table();
		
		table.showCardsOnTable();
		
		table.flop();
		table.showCardsOnTable();
		
		table.turn();
		table.showCardsOnTable();
		
		table.river();
		table.showCardsOnTable();
	}

}
