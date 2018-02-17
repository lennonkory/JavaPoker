package kcomp.poker.commonpoker.view;

import kcomp.poker.commonpoker.view.details.BlindInfo;
import kcomp.poker.commonpoker.view.details.CardInfo;
import kcomp.poker.commonpoker.view.details.GameInfo;
import kcomp.poker.commonpoker.view.details.PlayerInfo;

public interface TableView {

	void start();

	/**
	 * Clears blinds, pot, dealer, cards
	 */
	void nextRound();

	/**
	 * Tells the Player it is their turn
	 */
	void playerTurn(int seatNumber);

	/**
	 * Tells the Player their turn is over.
	 */
	void turnFinished(int seatNumber);

	/**
	 * Updates display. Pot.
	 */
	void updateGameInfo(GameInfo gameInfo);

	/**
	 * Updates the players cards.
	 */
	void updateCardsForPlayer(int seatNumber, CardInfo cardInfo);

	/**
	 * How many chips the player has bet.
	 */
	void updateChipsForPlayer(int seatNumber, int numberOfChips);

	/**
	 * Updates the player that they are the dealer
	 */
	void updateDealerButton(int seatNumber);

	/**
	 * Updates the Player if they are in the blinds or open.
	 */
	void updateBlindsForPlayer(int seatNumber, BlindInfo blindInfo);

	/**
	 * Adds a Player to the view.
	 * 
	 * @param seatNumber
	 * @param PlayerInfo
	 */
	void playerAdded(int seatNumber, PlayerInfo PlayerInfo);

	/**
	 * Removes player from game.
	 * 
	 * @param seatNumber
	 */
	void removePlayer(int seatNumber);

}
