package kcomp.poker.commonpoker.models.rounds;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.creators.PlayerCreater;
import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.models.BetSize;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.StandardDeck;
import kcomp.poker.commonpoker.models.game.PokerPot;
import kcomp.poker.commonpoker.models.game.PokerTable;
import kcomp.poker.commonpoker.models.game.Pot;
import kcomp.poker.commonpoker.models.game.Table;
import kcomp.poker.commonpoker.models.round.FlopStreet;
import kcomp.poker.commonpoker.models.round.PreFlopStreet;
import kcomp.poker.commonpoker.models.round.Street;
import kcomp.poker.commonpoker.models.round.TexasHoldemRound;
import kcomp.poker.commonpoker.rules.Rules;
import kcomp.poker.commonpoker.rules.TexasRules;

public class TestTexasRound {

	TexasHoldemRound round;
	private Table table;
	private static final int SIZE = 9;
	private Rules rules;
	private Pot pot;
	private Deck deck;

	@Before
	public void init() {

		Queue<Street> streets = new LinkedList<>();
		streets.add(new PreFlopStreet());
		streets.add(new FlopStreet());

		round = new TexasHoldemRound(streets);
		table = new PokerTable(SIZE);
		table.initTable();
		rules = new TexasRules(new BetSize(5, 10), new BetSize(5, 10), 0);
		pot = new PokerPot();
		deck = new StandardDeck();
	}

	@Test
	public void testBasicRound() {

		Player one = PlayerCreater.createPlayer("One", PlayerStatus.READY, 100);
		Player two = PlayerCreater.createPlayer("Two", PlayerStatus.READY, 100);

		table.addPLayer(one);
		table.addPLayer(two);

		round.start(table, rules, deck, pot);

	}

	@Test
	public void testTwoStreets() {

		Player one = PlayerCreater.createPlayer("One", PlayerStatus.READY, 100);
		Player two = PlayerCreater.createPlayer("Two", PlayerStatus.READY, 100);

		table.addPLayer(one);
		table.addPLayer(two);

		round.start(table, rules, deck, pot);

		assertEquals(2, one.getHand().getCards().size());

		table.setPlayersStatusInRoundTo(PlayerStatus.CALLED);

		round.updateRound(table, rules, deck);

		assertEquals(5, one.getHand().getCards().size());

	}

	@Test
	public void testTwoStreets3Players() {

		Player one = PlayerCreater.createPlayer("One", PlayerStatus.READY, 100);
		Player two = PlayerCreater.createPlayer("Two", PlayerStatus.READY, 100);
		Player three = PlayerCreater.createPlayer("Three", PlayerStatus.READY, 100);

		table.addPLayer(one);
		table.addPLayer(two);
		table.addPLayer(three);

		round.start(table, rules, deck, pot);

		assertEquals(2, one.getHand().getCards().size());

		table.setPlayersStatusInRoundTo(PlayerStatus.CALLED);

		round.updateRound(table, rules, deck);

		assertEquals(5, one.getHand().getCards().size());

	}

	@Test
	public void testTwoStreets3Players_player2foldsAfterPreflop() {

		Player one = PlayerCreater.createPlayer("One", PlayerStatus.READY, 100);
		Player two = PlayerCreater.createPlayer("Two", PlayerStatus.READY, 100);
		Player three = PlayerCreater.createPlayer("Three", PlayerStatus.READY, 100);

		table.addPLayer(one);
		table.addPLayer(two);
		table.addPLayer(three);

		round.start(table, rules, deck, pot);

		assertEquals(2, one.getHand().getCards().size());

		table.setPlayersStatusInRoundTo(PlayerStatus.CALLED);

		two.setPlayerStatus(PlayerStatus.FOLDED);

		round.updateRound(table, rules, deck);

		assertEquals(5, one.getHand().getCards().size());
		assertEquals(2, two.getHand().getCards().size());

	}

}
