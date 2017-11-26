package kcomp.poker.commonpoker.enums;

public enum HandRank {

	STRAIGHT_FLUSH("Straight Flush"),
	FOUR_OF_A_KIND("Four of a Kind"),
	FULL_HOUSE("Full House"),
	FLUSH("Flush"),
	STRAIGHT("Straight"),
	THREE_OF_A_KIND("Three of a Kind"),
	TWO_PAIR("Two Pair"),
	PAIR("Pair"),
	HIGH_CARD("High Card");

	private String handRank;

	private HandRank(String handRank) {
		this.handRank = handRank;
	}

	public String getHandRank() {
		return handRank;
	}

}
