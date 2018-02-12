package kcomp.poker.commonpoker.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.creators.PlayerCreater;
import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.models.game.PokerTable;
import kcomp.poker.commonpoker.models.game.Table;
import kcomp.poker.commonpoker.models.round.PreFlop;

public class TestPreFlop {

	PreFlop street = new PreFlop();

	private Table table;
	private static final int SIZE = 9;
	private Deck deck;

	@Before
	public void init() {
		table = new PokerTable(SIZE);
		table.initTable();
		deck = new StandardDeck();
	}

	@Test
	public void testTwoPlayers() {
		table.addPLayer(PlayerCreater.createPlayer("One", PlayerStatus.READY, 100), 4);
		table.addPLayer(PlayerCreater.createPlayer("Two", PlayerStatus.READY, 100), 5);

		street.dealCards(table, deck);

		Player one = table.getPlayerAtSeat(4);
		Hand oneHand = one.getHand();

		assertEquals(2, oneHand.getCards().size());

	}

}
