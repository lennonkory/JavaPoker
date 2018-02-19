package kcomp.poker.commonpoker.testarea;

import java.util.ArrayList;
import java.util.List;

import kcomp.poker.commonpoker.models.Player;

public class SidePot {

	private List<Player> players;
	private int amount;

	public SidePot() {
		players = new ArrayList<>();
		amount = 0;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void addToAmount(int amount) {
		this.amount += amount;
	}

	public boolean isPlayerInPot(Player player) {
		return players.stream().filter(p -> p.getUserName().equals(player.getUserName())).findFirst().isPresent();
	}

}
