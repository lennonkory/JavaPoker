package kcomp.poker.commonpoker.models.game;

import java.util.Collection;

import kcomp.poker.commonpoker.enums.PlayerStatus;
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

		if (playerAmount >= amount) {
			player.subtractChips(amount);
			pot.addToPot(player, amount, amount);
		} else {
			player.subtractChips(playerAmount);
			pot.addToPot(player, playerAmount, amount);
		}
	}

	public static void addToPlayerChips(Player player, Pot pot) {
		player.addChips(pot.getPlayerPotSize(player));
	}

	public static void collectMoneyAndAddToPotFromActivePlayers(Table table, int amount, Pot pot) {

		Collection<Player> players = table.getAllPlayers();

		for (Player player : players) {
			if (!PlayerStatus.SITTING_OUT.equals(player.getPlayerStatus())) {
				collectMoneyAndAddToPot(player, amount, pot);
			}
		}

	}

}
