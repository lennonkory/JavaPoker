package kcomp.poker.commonpoker.rules;

import kcomp.poker.commonpoker.models.BetSize;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Options;

public interface Rules {

	/*
	 * Antees, limits. tournament info Who wins??
	 */

	/**
	 * Gets the antees.
	 *
	 * @return the antees
	 */
	public int getAntees();

	/**
	 * Gets the blinds or opens.
	 *
	 * @return the blinds or opens
	 */
	public BetSize getBlindsOrOpens();

	/**
	 * Gets the limits.
	 *
	 * @return the limits
	 */
	public BetSize getLimits();

	public Options getOptionForPlayer(Player player, BetSize currentBetSize);

}
