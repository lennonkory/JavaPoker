package kcomp.poker.commonpoker.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.creators.PlayerCreater;
import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.models.game.PokerPot;
import kcomp.poker.commonpoker.models.game.PokerTable;
import kcomp.poker.commonpoker.models.game.Pot;
import kcomp.poker.commonpoker.models.game.Rules;
import kcomp.poker.commonpoker.models.game.Table;
import kcomp.poker.commonpoker.models.game.TexasRules;
import kcomp.poker.commonpoker.models.round.PreFlop;
import kcomp.poker.commonpoker.models.round.Street;
import kcomp.poker.commonpoker.models.round.TexasHoldemRound;

public class TestTexasRound {

	TexasHoldemRound round;
	private Table table;
	private static final int SIZE = 9;
	private Rules rules;
	private Pot pot;
	private Deck deck;

	@Before
	public void init() {
		Street street = new PreFlop();
		List<Street> streets = new ArrayList<>();
		streets.add(street);

		round = new TexasHoldemRound(streets);
		table = new PokerTable(SIZE);
		table.initTable();
		rules = new TexasRules();
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

		System.out.println("Cat");
	}

}
