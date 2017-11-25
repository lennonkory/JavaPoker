package kcomp.poker.commonpoker.enums;

public enum Rank {

	ACE("Ace", 14),
	KING("King", 13),
	QUEEN("Queen", 12),
	JACK("Jack", 11),
	TEN("Ten", 10),
	NINE("Nine", 9),
	EIGHT("Eight", 8),
	SEVEN("Seven", 7),
	SIX("Six", 6),
	FIVE("Five", 5),
	FOUR("Four", 4),
	THREE("Three", 3),
	TWO("Two", 2);

	private String rank;
	private int value;

	private Rank(String rank, int value) {
		this.rank = rank;
		this.value = value;
	}

	public String getRank() {
		return rank;
	}

	public int getValue() {
		return value;
	}

}
