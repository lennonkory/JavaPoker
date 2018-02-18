package kcomp.poker.commonpoker.factory;

import java.util.LinkedList;
import java.util.Queue;

import kcomp.poker.commonpoker.models.BetSizes;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.StandardDeck;
import kcomp.poker.commonpoker.models.game.Game;
import kcomp.poker.commonpoker.models.game.PokerGame;
import kcomp.poker.commonpoker.models.game.PokerPot;
import kcomp.poker.commonpoker.models.game.PokerTable;
import kcomp.poker.commonpoker.models.game.Pot;
import kcomp.poker.commonpoker.models.game.Rules;
import kcomp.poker.commonpoker.models.game.Table;
import kcomp.poker.commonpoker.models.game.TexasRules;
import kcomp.poker.commonpoker.models.round.FlopStreet;
import kcomp.poker.commonpoker.models.round.PreFlopStreet;
import kcomp.poker.commonpoker.models.round.RiverStreet;
import kcomp.poker.commonpoker.models.round.RoundContainer;
import kcomp.poker.commonpoker.models.round.Street;
import kcomp.poker.commonpoker.models.round.TexasHoldemRound;
import kcomp.poker.commonpoker.models.round.TexasHoldemRoundContainer;
import kcomp.poker.commonpoker.models.round.TurnStreet;

public class GameFactory {

	private static final int DEFAULT_TABLE_SIZE = 9;

	public static Game createTexasHoldemGame() {

		Queue<Street> streets = getTexasStreets();

		TexasHoldemRound round = new TexasHoldemRound(streets);

		RoundContainer roundContainer = new TexasHoldemRoundContainer(round);

		Table table = new PokerTable(DEFAULT_TABLE_SIZE);
		table.initTable();

		Rules rules = new TexasRules(new BetSizes(5, 10), new BetSizes(5, 10), 0);
		Deck deck = new StandardDeck();
		Pot pot = new PokerPot();

		PokerGame game = new PokerGame(roundContainer, table, rules, deck, pot);

		return game;
	}

	private static Queue<Street> getTexasStreets() {
		Queue<Street> streets = new LinkedList<>();
		streets.add(new PreFlopStreet());
		streets.add(new FlopStreet());
		streets.add(new TurnStreet());
		streets.add(new RiverStreet());

		return streets;
	}

}
