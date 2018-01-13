package kcomp.poker.commonpoker.models.handvalue;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.models.Card;

public class TestFlushHandValue {

	private FlushHandValue handValue;

	@Before
	public void init() {
		handValue = new FlushHandValue();
	}

	@Test
	public void compareSameHandReturnsEqual() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		handValue.setMainCards(mainCards);

		int compare = handValue.compareTo(handValue);

		assertEquals(0, compare);

	}

	@Test
	public void aceShouldBeatKing() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new FlushHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.KING));
		opMainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		opMainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		opMainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		opMainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void kingShouldLoseAce() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.KING));
		mainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new FlushHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		opMainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		opMainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		opMainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldBeatAceHighHigherKickerCard() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		mainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new FlushHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		opMainCards.add(new Card(Suit.CLUBS, Rank.NINE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		opMainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		opHandValue.setMainCards(opMainCards);

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
	public void aceShouldBeatStraight() {

		HandValue opHandValue = new StraightHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldLoseFullHouses() {

		HandValue opHandValue = new FullHouseHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLoseFourKind() {

		HandValue opHandValue = new FourOfAKindHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLoseStraightFlush() {

		HandValue opHandValue = new StraightFlushHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

}
