
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
		
		System.out.println("------------- Testing pot ---------------");
		
		System.out.println("Table pot: " + new_table.retPot() + " should be 0");
		new_table.addToPot(40);
		
		System.out.println("Table pot: " + new_table.retPot() + " should be 40");
		
	}

}
