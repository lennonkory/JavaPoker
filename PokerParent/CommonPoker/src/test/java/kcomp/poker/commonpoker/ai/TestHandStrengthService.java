package kcomp.poker.commonpoker.ai;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.exceptions.DeckException;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.factory.HandFactory;
import kcomp.poker.commonpoker.factory.RankHandFactory;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.StandardDeck;
import kcomp.poker.commonpoker.rankranker.RankHand;

@Ignore("These tests take too long. However they should be run if any changes are made to ranking")
public class TestHandStrengthService {

	HandStrengthService handStrengthService;

	@Before
	public void init() {

		RankHand rankHand = RankHandFactory.createPokerRankHand();
		handStrengthService = new HandStrengthServiceIMPL(rankHand);

	}

	private List<Card> createCards() {
		List<Card> cards = new ArrayList<>();

		cards.add(new Card(Suit.HEARTS, Rank.THREE));
		cards.add(new Card(Suit.CLUBS, Rank.FOUR));
		cards.add(new Card(Suit.HEARTS, Rank.JACK));

		return cards;
	}

	@Test
	public void testHandStr() throws HandRankException, DeckException {

		Hand hand = HandFactory.createHand();

		hand.addFaceDown(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addFaceDown(new Card(Suit.CLUBS, Rank.QUEEN));

		List<Card> board = createCards();

		hand.addFaceUp(board);

		handStrengthService.calculateHandStrength(hand, board);

	}

	@Test
	public void testHP() throws Exception {

		Hand hand = HandFactory.createHand();

		hand.addFaceDown(new Card(Suit.CLUBS, Rank.ACE));
		hand.addFaceDown(new Card(Suit.SPADES, Rank.FOUR));

		List<Card> board = createCards();

		hand.addFaceUp(board);

		handStrengthService.calculateHandPotential(hand, board);

	}

	@Test
	public void testEHS3ofaKind() throws Exception {

		Hand hand = HandFactory.createHand();

		hand.addFaceDown(new Card(Suit.CLUBS, Rank.ACE));
		hand.addFaceDown(new Card(Suit.SPADES, Rank.ACE));

		List<Card> board = createCards();

		hand.addFaceUp(board);

		double hs = handStrengthService.calculateHandStrength(hand, board);
		HandPotential hp = handStrengthService.calculateHandPotential(hand, board);

		double ehs = hs * (1 - hp.getNegPotential()) + (1 - hs) * hp.posPotential;

		System.out.println("EHS: " + ehs);

	}

	@Test
	public void testEHSHighCard_low() throws Exception {

		Hand hand = HandFactory.createHand();

		// A♦-Q♣ / 3♥-4♣-J♥

		hand.addFaceDown(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addFaceDown(new Card(Suit.CLUBS, Rank.QUEEN));

		List<Card> board = createCards();

		hand.addFaceUp(board);

		long startTime = System.nanoTime();

		double hs = handStrengthService.calculateHandStrength(hand, board);
		HandPotential hp = handStrengthService.calculateHandPotential(hand, board);

		double ehs = (hs * (1 - hp.getNegPotential())) + ((1 - hs) * hp.posPotential);

		long endTime = System.nanoTime();

		long duration = (endTime - startTime);

		System.out.println("EHS: " + ehs);

		System.out.println("Time: " + duration / 1000000 + "ms");

	}

	@Test
	public void testRec() throws DeckException {

		Deck deck = new StandardDeck();

		int count = 0;

		List<Card> board = createCards();

		board.add(new Card(Suit.DIAMONDS, Rank.ACE));
		board.add(new Card(Suit.CLUBS, Rank.QUEEN));

		while (deck.numberOfCardsRemaining() > 5) {

			Hand hand = HandFactory.createHand();

			hand.addCard(deck.getNextCard());

			count = createRecHand(hand, deck, 1, 0, count);

		}

		System.out.println("Count: " + count);

	}

	int createRecHand(Hand hand, Deck deck, int numCards, int count, int otherCount) throws DeckException {

		if (count >= numCards) {
			return otherCount;
		}

		Card card = deck.getNextCard();

		hand.addCard(card);

		Deck newDeck = new StandardDeck();
		newDeck.removeCards(hand.getCards());

		count++;
		otherCount++;
		otherCount = createRecHand(hand, newDeck, numCards, count, otherCount);

		return otherCount;

	}

}
