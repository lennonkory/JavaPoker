package kcomp.poker.commonpoker.testarea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kcomp.poker.commonpoker.models.Player;

public class PokerPot implements Pot {

	private int mainPotAmount;
	private int beforeNextStreetAmount = 0;
	private List<SidePot> sidePots;
	private int streetBet = 0;
	private Map<Player, Integer> playerBets;

	public PokerPot() {
		mainPotAmount = 0;
		playerBets = new HashMap<>();
		sidePots = new ArrayList<>();
	}

	@Override
	public int getMainPotSize() {
		return mainPotAmount;
	}

	@Override
	public List<SidePot> getSidePots() {
		return sidePots;
	}

	@Override
	public void addCallToPot(Player player, int amount) {

		addToPlayerBet(player, amount);

	}

	private void addToPlayerBet(Player player, int amount) {

		Integer playerBet = playerBets.get(player);

		if (playerBet == null) {
			playerBets.put(player, amount);
		} else {
			playerBets.put(player, amount + playerBet);
		}

		mainPotAmount += amount;

	}

	@Override
	public void addRaiseToPot(Player player, int amount) {

		Integer lastBet = playerBets.get(player);

		if (lastBet != null) {
			streetBet += amount - lastBet;
		} else {
			streetBet = amount;
		}

		addToPlayerBet(player, amount);
	}

	@Override
	public void setPotForNextStreet() {

		beforeNextStreetAmount = mainPotAmount;
		streetBet = 0;

		playerBets = new HashMap<>();

	}

	@Override
	public void finalSidePot() {

		if (playerBets.isEmpty()) {
			return;
		}

		List<Side> sides = new ArrayList<>();

		for (Entry<Player, Integer> entry : playerBets.entrySet()) {
			sides.add(new Side(entry.getKey(), entry.getValue()));
		}

		Collections.sort(sides);

		SidePot sidePot = new SidePot();

		Side first = sides.get(0);

		int firstAmount = first.getAmount();

		testSide(sides, sidePot, firstAmount, beforeNextStreetAmount);

	}

	@Override
	public void playerIsAllIn() {

		List<Side> sides = new ArrayList<>();

		for (Entry<Player, Integer> entry : playerBets.entrySet()) {
			sides.add(new Side(entry.getKey(), entry.getValue()));
		}

		Collections.sort(sides);

		SidePot sidePot = new SidePot();

		Side first = sides.get(0);

		int firstAmount = first.getAmount();

		testSide(sides, sidePot, firstAmount, beforeNextStreetAmount);

	}

	private void testSide(List<Side> sides, SidePot sidePot, int amount, int extra) {
		for (Side side : sides) {
			sidePot.addPlayer(side.getPlayer());
			sidePot.addToAmount(amount + extra);
			int sideAmount = playerBets.get(side.getPlayer());
			sideAmount -= amount;
			playerBets.put(side.getPlayer(), sideAmount);
			if (playerBets.get(side.getPlayer()) == 0) {
				playerBets.remove(side.getPlayer());
			}
		}
		sidePots.add(sidePot);
	}

	@Override
	public int getPlayerBetForStreet(Player player) {
		return playerBets.get(player) == null ? 0 : playerBets.get(player);
	}

	@Override
	public int getStreetBet() {
		return streetBet;
	}

	private class Side implements Comparable<Side> {

		private Player player;
		private int amount;

		public int getAmount() {
			return amount;
		}

		Side(Player player, int amount) {
			this.setPlayer(player);
			this.amount = amount;
		}

		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}

		@Override
		public int compareTo(Side o) {

			return this.amount - o.getAmount();
		}
	}

	@Override
	public void playerFolded(Player player) {
		playerBets.remove(player);
	}

}
