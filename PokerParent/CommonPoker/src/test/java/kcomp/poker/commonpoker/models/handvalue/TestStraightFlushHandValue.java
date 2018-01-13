package kcomp.poker.commonpoker.models.handvalue;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.factory.HandFactory;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.rankranker.StraightFlushRanker;

public class TestStraightFlushHandValue {

	private StraightFlushHandValue handValue;

	@Before
	public void init() {
		handValue = new StraightFlushHandValue();
	}

	@Test
	public void compareSameHandReturnsEqual() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.NINE));
		mainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		mainCards.add(new Card(Suit.CLUBS, Rank.SEVEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.SIX));

		handValue.setMainCards(mainCards);

		int compare = handValue.compareTo(handValue);

		assertEquals(0, compare);

	}

	@Test
	public void tenShouldBeatNine() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.NINE));
		mainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		mainCards.add(new Card(Suit.CLUBS, Rank.SEVEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.SIX));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new StraightFlushHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.DIAMONDS, Rank.NINE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		opMainCards.add(new Card(Suit.HEARTS, Rank.SEVEN));
		opMainCards.add(new Card(Suit.SPADES, Rank.SIX));
		opMainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void tenShouldLoseJack() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.NINE));
		mainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		mainCards.add(new Card(Suit.CLUBS, Rank.SEVEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.SIX));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new StraightFlushHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.SPADES, Rank.JACK));
		opMainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		opMainCards.add(new Card(Suit.DIAMONDS, Rank.NINE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		opMainCards.add(new Card(Suit.HEARTS, Rank.SEVEN));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldBeatNine() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.KING));
		mainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.JACK));
		mainCards.add(new Card(Suit.CLUBS, Rank.TEN));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new StraightFlushHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.NINE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		opMainCards.add(new Card(Suit.CLUBS, Rank.SEVEN));
		opMainCards.add(new Card(Suit.CLUBS, Rank.SIX));
		opMainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatKing() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.KING));
		mainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.JACK));
		mainCards.add(new Card(Suit.CLUBS, Rank.TEN));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new StraightFlushHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.KING));
		opMainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		opMainCards.add(new Card(Suit.CLUBS, Rank.JACK));
		opMainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		opMainCards.add(new Card(Suit.CLUBS, Rank.NINE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void sixShouldBeatWheel() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.SIX));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		mainCards.add(new Card(Suit.CLUBS, Rank.FOUR));
		mainCards.add(new Card(Suit.CLUBS, Rank.THREE));
		mainCards.add(new Card(Suit.CLUBS, Rank.TWO));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new StraightFlushHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.FOUR));
		opMainCards.add(new Card(Suit.CLUBS, Rank.THREE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.TWO));
		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void sixShouldBeatWheelTestWithRanker() throws Exception {

		StraightFlushRanker ranker = new StraightFlushRanker();

		Hand mainHand = HandFactory.createHand();

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.SIX));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		mainCards.add(new Card(Suit.CLUBS, Rank.FOUR));
		mainCards.add(new Card(Suit.CLUBS, Rank.THREE));
		mainCards.add(new Card(Suit.CLUBS, Rank.TWO));

		for (Card card : mainCards) {
			mainHand.addCard(card);
		}

		HandValue handValue = ranker.getHandValue(mainHand);

		Hand opHand = HandFactory.createHand();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.FOUR));
		opMainCards.add(new Card(Suit.CLUBS, Rank.THREE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.TWO));
		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));

		for (Card card : opMainCards) {
			opHand.addCard(card);
		}

		HandValue opHandValue = ranker.getHandValue(opHand);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatHighCard() {

		HandValue opHandValue = new HighCardHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatPair() {

		HandValue opHandValue = new PairHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatTwoPair() {

		HandValue opHandValue = new TwoPairHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatFlsh() {

		HandValue opHandValue = new FlushHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatFullHouses() {

		HandValue opHandValue = new FullHouseHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatFourKind() {

		HandValue opHandValue = new FourOfAKindHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

}
