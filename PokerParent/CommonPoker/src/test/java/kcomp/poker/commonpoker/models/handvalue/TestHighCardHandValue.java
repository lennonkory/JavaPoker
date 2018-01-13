package kcomp.poker.commonpoker.models.handvalue;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.models.Card;

public class TestHighCardHandValue {

	private HighCardHandValue handValue;

	@Before
	public void init() {
		handValue = new HighCardHandValue();

	}

	@Test
	public void compareSameHandReturnsEqual() {
		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		handValue.setMainCards(mainCards);

		int compare = handValue.compareTo(handValue);

		assertEquals(0, compare);

	}

	@Test
	public void aceHighShouldBeatKingHigh_2Cards() {
		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new HighCardHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.KING));
		opMainCards.add(new Card(Suit.CLUBS, Rank.SIX));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceHighShouldBeatKingHigh_5Cards() {
		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		mainCards.add(new Card(Suit.HEARTS, Rank.SIX));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		mainCards.add(new Card(Suit.DIAMONDS, Rank.TWO));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new HighCardHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.KING));
		opMainCards.add(new Card(Suit.HEARTS, Rank.QUEEN));
		opMainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		opMainCards.add(new Card(Suit.SPADES, Rank.SIX));
		opMainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		opMainCards.add(new Card(Suit.SPADES, Rank.THREE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void kingShouldLoseAceHigh_5Cards() {
		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.KING));
		mainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		mainCards.add(new Card(Suit.HEARTS, Rank.SIX));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		mainCards.add(new Card(Suit.DIAMONDS, Rank.TWO));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new HighCardHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.HEARTS, Rank.QUEEN));
		opMainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		opMainCards.add(new Card(Suit.SPADES, Rank.SIX));
		opMainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		opMainCards.add(new Card(Suit.SPADES, Rank.THREE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void kingShouldLoseAceHigh_2Cards() {
		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.KING));
		mainCards.add(new Card(Suit.CLUBS, Rank.EIGHT));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new HighCardHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.HEARTS, Rank.QUEEN));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldBeatAceHighHigherNextCard_5Cards() {
		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		mainCards.add(new Card(Suit.HEARTS, Rank.JACK));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		mainCards.add(new Card(Suit.DIAMONDS, Rank.TWO));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new HighCardHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.HEARTS, Rank.QUEEN));
		opMainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		opMainCards.add(new Card(Suit.SPADES, Rank.SIX));
		opMainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		opMainCards.add(new Card(Suit.SPADES, Rank.THREE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatAceHighHigherLastCard_5Cards() {
		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		mainCards.add(new Card(Suit.HEARTS, Rank.JACK));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		mainCards.add(new Card(Suit.DIAMONDS, Rank.THREE));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new HighCardHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.HEARTS, Rank.QUEEN));
		opMainCards.add(new Card(Suit.CLUBS, Rank.JACK));
		opMainCards.add(new Card(Suit.SPADES, Rank.FIVE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.TWO));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldLosePair_2Cards() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new PairHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));
		opMainCards.add(new Card(Suit.HEARTS, Rank.QUEEN));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLoseTwoPair_2Cards() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new TwoPairHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLose3Kind_2Cards() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new ThreeOfAKindHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLoseStraight_2Cards() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.CLUBS, Rank.QUEEN));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new StraightHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLoseFlush() {

		HandValue opHandValue = new FlushHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLoseFullHouse() {

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
	public void aceShouldLoseStraightFlush_2Cards() {

		HandValue opHandValue = new StraightFlushHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

}
