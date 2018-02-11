package kcomp.poker.commonpoker.models.round;

import kcomp.poker.commonpoker.models.Player;

public interface Round {

	// Contains list of streets

	// select street -- update view
	// collect money
	void start();

	// private void collectMoney();

	// Player whos turn it is
	public Player getActivePlayer();

	public boolean isOver();

}
