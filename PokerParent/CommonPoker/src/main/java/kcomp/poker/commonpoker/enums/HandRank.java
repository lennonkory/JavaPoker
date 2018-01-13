package kcomp.poker.commonpoker.enums;

public enum HandRank {

	STRAIGHT_FLUSH(9, "Straight Flush"),
	FOUR_OF_A_KIND(8, "Four of a Kind"),
	FULL_HOUSE(7, "Full House"),
	FLUSH(6, "Flush"),
	STRAIGHT(5, "Straight"),
	THREE_OF_A_KIND(4, "Three of a Kind"),
	TWO_PAIR(3, "Two Pair"),
	PAIR(2, "Pair"),
	HIGH_CARD(1, "High Card");

	private String handRank;
	private int rank;

	private HandRank(int rank, String handRank) {
		this.rank = rank;
		this.handRank = handRank;
	}

	public String getHandRank() {
		return handRank;
	}

	public int getRank() {
		return this.rank;
	}

}
