package kcomp.poker.commonpoker.models.game;

import kcomp.poker.commonpoker.models.Player;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pot.
 */
public interface Pot {

	/**
	 * Adds amount to the player pot and the overall pot.
	 *
	 * Does NOT remove chips from player.
	 * 
	 * @param player
	 *            the player
	 * @param amount
	 *            the amount
	 */
	public void addToPot(Player player, int playerAmount, int potAmount);

	/**
	 * Gets the pot size.
	 *
	 * @return the pot size
	 */
	public int getPotSize();

	/**
	 * Gets the player pot size.
	 *
	 * @param player
	 *            the player
	 * @return the player pot size. If no player pot size return 0.
	 */
	public int getPlayerPotSize(Player player);
}