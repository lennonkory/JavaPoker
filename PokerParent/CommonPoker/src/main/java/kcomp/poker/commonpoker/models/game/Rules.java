package kcomp.poker.commonpoker.models.game;

import kcomp.poker.commonpoker.models.BetSizes;

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
	public BetSizes getBlindsOrOpens();

	/**
	 * Gets the limits.
	 *
	 * @return the limits
	 */
	public BetSizes getLimits();

}
