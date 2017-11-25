package kcomp.poker.commonpoker.enums;

public enum Suit {

	HEARTS("Hearts"), SPADES("Spades"), CLUBS("Clubs"), DIAMONDS("Diamonds");

	private String suit;

	private Suit(String suit) {
		this.suit = suit;
	}

	public String getSuit() {
		return suit;
	}

}
