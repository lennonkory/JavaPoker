package kcomp.poker.commonpoker.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;

public class Hand {

	List<Card> cards;
	List<Card> faceDown;
	List<Card> faceUp;

	private Map<Rank, Integer> ranks;
	private Map<Suit, Integer> suits;

	public Hand(Map<Rank, Integer> ranks, Map<Suit, Integer> suits) {
		this.ranks = ranks;
		this.suits = suits;
		cards = new ArrayList<>();
		faceDown = new ArrayList<>();
		faceUp = new ArrayList<>();
	}

	public void addCard(Card card) {

		cards.add(card);
		suits.put(card.getSuit(), suits.get(card.getSuit()) + 1);
		ranks.put(card.getRank(), ranks.get(card.getRank()) + 1);

	}

	public void printSuits() {

		for (Suit suit : suits.keySet()) {
			System.out.println(suit + " " + suits.get(suit));
		}
	}

	public void printRanks() {
		for (Rank rank : ranks.keySet()) {
			System.out.println(rank + " " + ranks.get(rank));
		}
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public void addFaceDown(Card card) {
		this.faceDown.add(card);
		addCard(card);

	}

	public void addFaceUp(Card card) {
		this.faceUp.add(card);
		addCard(card);

	}

	public void addFaceUp(List<Card> cards) {
		for (Card card : cards) {
			this.faceUp.add(card);
			addCard(card);
		}

	}

	public void removeCard(Card card) {
		cards.remove(card);
		faceDown.remove(card);
		faceUp.remove(card);
	}

	public void removeCards(List<Card> cards) {
		for (Card card : cards) {
			removeCard(card);
		}
	}

	public List<Card> getFaceDown() {
		return faceDown;
	}

	public List<Card> getFaceUp() {
		return faceUp;
	}

	public Map<Rank, Integer> getRanks() {
		return ranks;
	}

	public Map<Suit, Integer> getSuits() {
		return suits;
	}

}
