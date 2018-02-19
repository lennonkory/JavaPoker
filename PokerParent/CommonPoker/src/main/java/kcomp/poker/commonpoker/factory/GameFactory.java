package kcomp.poker.commonpoker.factory;

import kcomp.poker.commonpoker.models.BetSize;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.StandardDeck;
import kcomp.poker.commonpoker.models.game.Game;
import kcomp.poker.commonpoker.models.game.PokerGame;
import kcomp.poker.commonpoker.models.game.PokerTable;
import kcomp.poker.commonpoker.models.game.Table;
import kcomp.poker.commonpoker.models.game.WinnerService;
import kcomp.poker.commonpoker.models.game.WinnerServiceRegular;
import kcomp.poker.commonpoker.models.round.RoundContainer;
import kcomp.poker.commonpoker.models.round.TexasHoldemRoundContainer;
import kcomp.poker.commonpoker.rankranker.RankHand;
import kcomp.poker.commonpoker.rules.Rules;
import kcomp.poker.commonpoker.rules.TexasRules;
import kcomp.poker.commonpoker.testarea.PokerPot;
import kcomp.poker.commonpoker.testarea.Pot;

public class GameFactory {

	private static final int DEFAULT_TABLE_SIZE = 9;

	public static Game createTexasHoldemGame() {

		RoundContainer roundContainer = new TexasHoldemRoundContainer();

		Table table = new PokerTable(DEFAULT_TABLE_SIZE);
		table.initTable();

		Rules rules = new TexasRules(new BetSize(5, 10), new BetSize(10, -1), 0);
		Deck deck = new StandardDeck();
		Pot tPot = new PokerPot();
		RankHand rankHand = RankHandFactory.createPokerRankHand();
		WinnerService winnerService = new WinnerServiceRegular(rankHand);

		PokerGame game = new PokerGame(roundContainer, table, rules, deck, tPot, winnerService);

		return game;
	}

}
