package kcomp.poker.commonpoker.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.exceptions.DeckException;

public class TestDeck {

	private Deck deck;

	@Before
	public void init() {
		deck = new StandardDeck();
	}

	@Test
	public void get_card() throws DeckException {
		Card card = deck.getNextCard();
		assertNotNull(card);
	}

	@Test
	public void get_cards() throws DeckException {
		int numberOfCards = 5;
		List<Card> cards = deck.getNextCards(numberOfCards);
		assertNotNull(cards);
		assertTrue(cards.size() == 5);
	}

	@Test
	public void card_is_not_in_deck_after_removed() throws DeckException {
		Card card = deck.getNextCard();
		assertNotNull(card);
		assertFalse(deck.isCardInDeck(card));

	}

	@Test
	public void card_is_added_to_deck() throws DeckException {
		Card card = deck.getNextCard();
		assertNotNull(card);
		assertFalse(deck.isCardInDeck(card));

		deck.addCard(card);

		assertTrue(deck.isCardInDeck(card));

	}

	@Test
	public void size_of_deck_changes_after_cards_removed() throws DeckException {

		int numberOfCards = 5;
		int sizeBefore = deck.numberOfCardsRemaining();
		List<Card> cards = deck.getNextCards(numberOfCards);
		assertNotNull(cards);
		assertTrue(cards.size() == 5);

		assertTrue(deck.numberOfCardsRemaining() == (sizeBefore - numberOfCards));

		Card card = cards.get(2);

		assertFalse(deck.isCardInDeck(card));

		deck.shuffle();

		assertTrue(deck.numberOfCardsRemaining() == sizeBefore);

		assertTrue(deck.isCardInDeck(card));

	}

	@Test
	public void shuffle_resets_deck() throws DeckException {

		int numberOfCards = 5;
		int sizeBefore = deck.numberOfCardsRemaining();
		List<Card> cards = deck.getNextCards(numberOfCards);
		assertNotNull(cards);
		assertTrue(cards.size() == 5);

		assertTrue(deck.numberOfCardsRemaining() == (sizeBefore - numberOfCards));

		deck.shuffle();

	}

	@Test(expected = DeckException.class)
	public void throws_exception_if_too_many_cards_removed() throws DeckException {
		int size = deck.numberOfCardsRemaining();

		deck.getNextCards(size + 1);
	}

}
