package kcomp.poker.commonpoker.listeners;

import kcomp.poker.commonpoker.view.details.PlayerInfo;

/**
 * Updates the table of changes made.
 * 
 * Sets Pot, Players added/removed.
 *
 */
public interface TableGameListener {

	void updatePot();

	void addPlayer(int seatNumber, PlayerInfo playerInfo);

	void removePlayer(int seatNumber);

}
