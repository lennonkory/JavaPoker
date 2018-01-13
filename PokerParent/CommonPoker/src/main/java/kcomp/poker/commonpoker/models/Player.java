package kcomp.poker.commonpoker.models;

import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.exceptions.ChipException;

public abstract class Player {

	private String userName;
	private int chipCount;
	private Hand hand;
	private PlayerStatus playerStatus;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getChipCount() {
		return chipCount;
	}

	public void setChipCount(int chipCount) {
		this.chipCount = chipCount;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public PlayerStatus getPlayerStatus() {
		return playerStatus;
	}

	public void setPlayerStatus(PlayerStatus playerStatus) {
		this.playerStatus = playerStatus;
	}

	public void addChips(int numberOfChips) {
		this.chipCount += numberOfChips;
	}

	public void subtractChips(int numberOfChips) throws ChipException {
		if (numberOfChips > this.chipCount) {
			throw new ChipException("Player does not have enough chips: " + numberOfChips);
		}
		this.chipCount -= numberOfChips;
	}

	public abstract void turn();

}
