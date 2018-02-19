package kcomp.poker.commonpoker.models.game;

import java.util.Collection;

import kcomp.poker.commonpoker.enums.BetType;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.testarea.Pot;

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
	public static void collectMoneyAndAddToPot(Player player, BetType betType, int amount, Pot pot) {

		int playerAmount = player.getChipCount();
		int ammountToAdd = amount;

		if (playerAmount <= amount) {
			ammountToAdd = playerAmount;
		}

		player.subtractChips(ammountToAdd);

		if (betType.equals(BetType.RAISE)) {
			pot.addRaiseToPot(player, ammountToAdd);
		} else {
			pot.addCallToPot(player, ammountToAdd);
		}

	}

	public static void addToPlayerChips(Player player, Pot pot) {
		// player.addChips(pot.getPlayerPotSize(player));
	}

	public static void collectMoneyAndAddToPotFromPlayers(Table table, BetType betType, int amount, Pot pot) {

		Collection<Player> players = table.getAllPlayers();

		for (Player player : players) {

			collectMoneyAndAddToPot(player, betType, amount, pot);

		}

	}

}
