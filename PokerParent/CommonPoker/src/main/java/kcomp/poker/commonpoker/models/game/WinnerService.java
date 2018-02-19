package kcomp.poker.commonpoker.models.game;

import java.util.Collection;

import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.testarea.SidePot;

/**
 * The Interface WinnerService.
 */
public interface WinnerService {

	/**
	 * Determine winners of the round.
	 *
	 * @param table
	 *            the table
	 * @return the collection
	 * @throws HandRankException
	 */
	Collection<Player> determineWinners(Table table) throws HandRankException;

	/**
	 * Pay winners.
	 *
	 * Pays the winners
	 *
	 * @param winners
	 *            the winners
	 * @param pot
	 *            the pot
	 */
	void payWinners(Collection<Player> winners, SidePot pot);

}
