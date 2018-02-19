package kcomp.poker.commonpoker.models.round;

import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.game.Table;
import kcomp.poker.commonpoker.rules.Rules;
import kcomp.poker.commonpoker.testarea.Pot;

public interface Round {

	// Contains list of streets

	// select street -- update view
	// collect money
	void start(Table table, Rules rules, Deck deck, Pot pot);

	public boolean isOver();

	boolean updateRound(Table table, Rules rules, Deck deck);

}
