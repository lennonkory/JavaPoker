package kcomp.poker.commonpoker.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.exceptions.DeckException;

public class StandardDeck implements Deck {

	private List<Card> cards;
	private Random random;

	public StandardDeck() {
		create();
		random = new Random();

	}

	@Override
	public Card getNextCard() throws DeckException {

		if (cards == null || cards.isEmpty()) {
			throw new DeckException("No cards left in deck");
		}

		int next = random.nextInt(cards.size());

		Card card = cards.get(next);
		cards.remove(next);

		return card;
	}

	@Override
	public void addCard(Card card) {
		cards.add(card);
	}

	@Override
	public List<Card> getNextCards(int numberOfCards) throws DeckException {
		List<Card> cards = new ArrayList<>();

		for (int i = 0; i < numberOfCards; i++) {
			cards.add(getNextCard());
		}

		return cards;
	}

	@Override
	public void shuffle() {
		create();
	}

	private void create() {

		Rank[] ranks = Rank.values();
		Suit[] suits = Suit.values();

		cards = new ArrayList<>();

		for (Rank rank : ranks) {
			for (Suit suit : suits) {
				cards.add(new Card(suit, rank));
			}
		}

	}

	@Override
	public int numberOfCardsRemaining() {
		return cards.size();
	}

	@Override
	public boolean isCardInDeck(Card card) {
		for (Card c : cards) {
			if (c.equals(card)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void removeCard(Card card) {
		cards.remove(card);
	}

	@Override
	public void removeCards(Collection<Card> cards) {
		this.cards.removeAll(cards);
	}

}
