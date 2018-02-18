package kcomp.poker.commonpoker.models.game;

import java.util.Collection;

import kcomp.poker.commonpoker.models.BetSize;
import kcomp.poker.commonpoker.models.Player;

public class MoneyService {

	private MoneyService() {
	}

	/**
	 * Adds the to pot.
	 *
	 * This method modifies BOTH the Player and the Pot!! Players can not have
	 * negative chips
	 *
	 * @param player
	 *            the player
	 * @param amount
	 *            the amount
	 * @param pot
	 *            the pot
	 */
	public static void collectMoneyAndAddToPot(Player player, int amount, Pot pot) {

		int playerAmount = player.getChipCount();
		int ammountToAdd = amount;

		if (playerAmount <= amount) {
			ammountToAdd = playerAmount;
		}

		player.subtractChips(ammountToAdd);
		pot.addToPot(player, ammountToAdd, amount);

		BetSize currentBetSize = pot.getPlayerBetSizesForStreet(player);

		if (currentBetSize == null) {
			currentBetSize = new BetSize(ammountToAdd, -1);
		} else {
			currentBetSize.setSmall(currentBetSize.getSmall() + ammountToAdd);
		}

		pot.setPlayerBetSizesForStreet(player, currentBetSize);

	}

	public static void addToPlayerChips(Player player, Pot pot) {
		player.addChips(pot.getPlayerPotSize(player));
	}

	public static void collectMoneyAndAddToPotFromPlayers(Table table, int amount, Pot pot) {

		Collection<Player> players = table.getAllPlayers();

		for (Player player : players) {

			collectMoneyAndAddToPot(player, amount, pot);

		}

	}

}
