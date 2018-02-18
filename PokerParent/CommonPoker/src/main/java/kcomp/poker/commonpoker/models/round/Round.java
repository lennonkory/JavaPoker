package kcomp.poker.commonpoker.models.round;

import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.game.Pot;
import kcomp.poker.commonpoker.models.game.Table;
import kcomp.poker.commonpoker.rules.Rules;

public interface Round {

	// Contains list of streets

	// select street -- update view
	// collect money
	void start(Table table, Rules rules, Deck deck, Pot pot);

	public boolean isOver();

	public void updateRound(Table table, Rules rules, Deck deck);

}
