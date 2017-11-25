package kcomp.poker.commonpoker.enums;

public enum HandRank {

	TWO_PAIR("Two Pair"), PAIR("Pair"), HIGH_CARD("High Card");

	private String handRank;

	private HandRank(String handRank) {
		this.handRank = handRank;
	}

	public String getHandRank() {
		return handRank;
	}

}
