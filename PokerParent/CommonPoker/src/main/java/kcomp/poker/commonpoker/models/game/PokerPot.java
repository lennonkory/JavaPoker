package kcomp.poker.commonpoker.models.game;

import java.util.HashMap;
import java.util.Map;

import kcomp.poker.commonpoker.models.Player;

public class PokerPot implements Pot {

	private int potSize;
	private Map<Player, Integer> playerPotSize;

	public PokerPot() {
		potSize = 0;
		playerPotSize = new HashMap<>();
	}

	@Override
	public void addToPot(Player player, int playerAmount, int amount) {

		if (playerPotSize.containsKey(player)) {
			Integer playerAmountOld = playerPotSize.get(player);
			playerAmountOld += playerAmount;
			playerPotSize.put(player, playerAmount);
		} else {
			playerPotSize.put(player, playerAmount);
		}

		potSize += amount;
	}

	@Override
	public int getPotSize() {
		return potSize;
	}

	@Override
	public int getPlayerPotSize(Player player) {
		if (playerPotSize.containsKey(player)) {
			return playerPotSize.get(player);
		}
		return 0;
	}

}
