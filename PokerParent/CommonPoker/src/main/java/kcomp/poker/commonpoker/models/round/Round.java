package kcomp.poker.commonpoker.models.round;

import java.util.List;

import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Table;

public interface Round {

	// Contains list of streets

	// select street -- update view
	// collect money
	void start();

	// private void collectMoney();

	// Player who's turn it is
	public Player getActivePlayer();

	public boolean isOver();

	/**
	 * Gets the winners of the round.
	 *
	 * @param table
	 *            the table
	 * @return the winners
	 */
	public List<Player> getWinners(Table table);

}
