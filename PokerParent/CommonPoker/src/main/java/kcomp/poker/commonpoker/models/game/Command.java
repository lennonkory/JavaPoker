package kcomp.poker.commonpoker.models.game;

import kcomp.poker.commonpoker.enums.BetType;
import kcomp.poker.commonpoker.models.Player;

public class Command {

	private Player player;
	private BetType betType;
	private int betAmount;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BetType getBetType() {
		return betType;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	public int getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}

}
