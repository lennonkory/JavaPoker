package kcomp.poker.commonpoker.models;

import java.util.List;

import kcomp.poker.commonpoker.exceptions.DeckException;

public interface Deck {

	Card getNextCard() throws DeckException;

	void addCard(Card card);

	List<Card> getNextCards(int numberOfCards) throws DeckException;

	void shuffle();

	int numberOfCardsRemaining();

	boolean isCardInDeck(Card card);

}
