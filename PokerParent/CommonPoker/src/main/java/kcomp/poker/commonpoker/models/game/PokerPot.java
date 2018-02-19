package kcomp.poker.commonpoker.models.game;

import java.util.HashMap;
import java.util.Map;

import kcomp.poker.commonpoker.listeners.PotGameListener;
import kcomp.poker.commonpoker.models.BetSize;
import kcomp.poker.commonpoker.models.Player;

public class PokerPot implements Pot {

	private int potSize;
	private Map<Player, Integer> playerPotSize;
	private Map<Player, BetSize> playerBetSizes;
	private PotGameListener potGameListener;

	public PokerPot() {
		potSize = 0;
		playerPotSize = new HashMap<>();
		playerBetSizes = new HashMap<>();
	}

	@Override
	public void addToPot(Player player, int amount) {

		potSize += amount;

		if (potGameListener != null) {
			potGameListener.upDatePot(amount);
		}

	}

	@Override
	public int getPotSize() {
		return potSize;
	}

	@Override
	public int getPlayerPotSize(Player player) {
		if (playerPotSize.containsKey(player)) {
			int value = playerPotSize.get(player);
			return playerPotSize.get(player);
		}
		return 0;
	}

	@Override
	public PotGameListener getPotGameListener() {
		return potGameListener;
	}

	@Override
	public void setPotGameListener(PotGameListener potGameListener) {
		this.potGameListener = potGameListener;
	}

	@Override
	public void setPlayerBetSizesForStreet(Player player, BetSize betSize) {
		playerBetSizes.put(player, betSize);
	}

	@Override
	public BetSize getPlayerBetSizesForStreet(Player player) {
		return playerBetSizes.get(player);
	}

	@Override
	public void removePlayerBetSize(Player player) {
		playerBetSizes.remove(player);
	}

	private class SidePot {

	}
}
