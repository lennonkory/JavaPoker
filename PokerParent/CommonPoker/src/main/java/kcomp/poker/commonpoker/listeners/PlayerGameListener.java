package kcomp.poker.commonpoker.listeners;

import kcomp.poker.commonpoker.view.details.BlindInfo;
import kcomp.poker.commonpoker.view.details.CardInfo;
import kcomp.poker.commonpoker.view.details.DisplayInfo;

/**
 * Updates A single Player about changes to the game.
 * 
 * Example: When it is their turn.
 */
public interface PlayerGameListener {

	/**
	 * Tells the Player it is their turn
	 */
	void turnNotification();

	/**
	 * Tells the Player their turn is over.
	 */
	void turnFinished();

	/**
	 * Updates display. Normally this means chip count.
	 */
	void updateDisplay(DisplayInfo displayInfo);

	/**
	 * Updates the players cards.
	 */
	void updateCards(CardInfo cardInfo);

	/**
	 * How many chips the player has bet.
	 */
	void updateChips(int numberOfChips);

	/**
	 * Updates the player that they are the dealer
	 */
	void updateDealerButton();

	/**
	 * Updates the Player if they are in the blinds or open.
	 */
	void updateBlinds(BlindInfo blindInfo);

}
