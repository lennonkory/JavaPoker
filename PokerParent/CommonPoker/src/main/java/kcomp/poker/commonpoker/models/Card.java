package kcomp.poker.commonpoker.models;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;

public class Card implements Comparable<Card> {

	private Suit suit;
	private Rank rank;

	public Card(Suit suit, Rank rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(Card o) {
		return o.rank.getValue() - this.rank.getValue();
	}

	@Override
	public String toString() {
		return rank.getRank() + " of " + suit.getSuit();
	}

}
