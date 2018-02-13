package kcomp.poker.commonpoker.models.rounds;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.creators.PlayerCreater;
import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.factory.HandFactory;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.StandardDeck;
import kcomp.poker.commonpoker.models.game.PokerTable;
import kcomp.poker.commonpoker.models.game.Table;
import kcomp.poker.commonpoker.models.round.FlopStreet;

public class TestFlopStreet {

	FlopStreet street = new FlopStreet();

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

		Player one = PlayerCreater.createPlayer("One", PlayerStatus.READY, 100);
		Player two = PlayerCreater.createPlayer("Two", PlayerStatus.READY, 100);

		one.setHand(HandFactory.createHand());
		two.setHand(HandFactory.createHand());

		table.addPLayer(one, 4);
		table.addPLayer(two, 5);

		street.dealCards(table, deck);

		Hand oneHand = one.getHand();
		Hand twoHand = two.getHand();

		assertEquals(3, oneHand.getCards().size());

		for (int i = 0; i < 3; i++) {
			Card oneCard = oneHand.getCards().get(i);
			Card twoCard = twoHand.getCards().get(i);
			assertEquals(oneCard, twoCard);
		}

	}

}
