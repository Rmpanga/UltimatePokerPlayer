
public class TableTestDriver {

	public static void main(String[] args) {
		Deck.populateDeck();
		Deck.shuffleDeck();
		Deck.printDeck();
		
		Table new_table = new Table();
		new_table.showCardsOnTable();
		
		new_table.flop();
		new_table.showCardsOnTable();
		
		Deck.printDeck();
		
		new_table.turn();
		new_table.showCardsOnTable();
		
		Deck.printDeck();
		
		new_table.river();
		new_table.showCardsOnTable();
		
		Deck.printDeck();
		
	}

}
