package kcomp.poker.commonpoker.models.handvalue;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.models.Card;

public class TestPairHandValue {

	private PairHandValue handValue;

	@Before
	public void init() {
		handValue = new PairHandValue();
	}

	@Test
	public void compareSameHandReturnsEqual() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.SPADES, Rank.ACE));

		handValue.setMainCards(mainCards);

		int compare = handValue.compareTo(handValue);

		assertEquals(0, compare);

	}

	@Test
	public void acePairShouldBeatKingPair_2Cards() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.SPADES, Rank.ACE));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new PairHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.KING));
		opMainCards.add(new Card(Suit.SPADES, Rank.KING));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void acePairShouldBeatKingPair_5Cards() {
		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.SPADES, Rank.ACE));
		mainCards.add(new Card(Suit.HEARTS, Rank.SIX));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		mainCards.add(new Card(Suit.DIAMONDS, Rank.TWO));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new PairHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.KING));
		opMainCards.add(new Card(Suit.SPADES, Rank.KING));
		opMainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		opMainCards.add(new Card(Suit.SPADES, Rank.SIX));
		opMainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void kingShouldLoseAcePair_5Cards() {
		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.KING));
		mainCards.add(new Card(Suit.SPADES, Rank.KING));
		mainCards.add(new Card(Suit.HEARTS, Rank.SIX));
		mainCards.add(new Card(Suit.CLUBS, Rank.FIVE));
		mainCards.add(new Card(Suit.DIAMONDS, Rank.TWO));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new PairHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.HEARTS, Rank.ACE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.TEN));
		opMainCards.add(new Card(Suit.SPADES, Rank.SIX));
		opMainCards.add(new Card(Suit.CLUBS, Rank.FIVE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void kingShouldLoseAceHigh_2Cards() {
		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.KING));
		mainCards.add(new Card(Suit.SPADES, Rank.KING));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new PairHandValue();

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.HEARTS, Rank.ACE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldBeatAceHighHigherNextCard_5Cards() {

		List<Card> mainCards = new ArrayList<>();
		List<Card> kickers = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.SPADES, Rank.ACE));
		kickers.add(new Card(Suit.HEARTS, Rank.JACK));
		kickers.add(new Card(Suit.CLUBS, Rank.FIVE));
		kickers.add(new Card(Suit.DIAMONDS, Rank.TWO));

		handValue.setMainCards(mainCards);
		handValue.setKickers(kickers);

		HandValue opHandValue = new PairHandValue();

		List<Card> opMainCards = new ArrayList<>();
		List<Card> opKickers = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.HEARTS, Rank.ACE));
		opKickers.add(new Card(Suit.CLUBS, Rank.TEN));
		opKickers.add(new Card(Suit.SPADES, Rank.SIX));
		opKickers.add(new Card(Suit.CLUBS, Rank.FIVE));

		opHandValue.setMainCards(opMainCards);
		opHandValue.setKickers(opKickers);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatAceHighHigherLastCard_5Cards() {

		List<Card> mainCards = new ArrayList<>();
		List<Card> kickers = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.SPADES, Rank.ACE));
		kickers.add(new Card(Suit.HEARTS, Rank.JACK));
		kickers.add(new Card(Suit.CLUBS, Rank.FIVE));
		kickers.add(new Card(Suit.DIAMONDS, Rank.FOUR));

		handValue.setMainCards(mainCards);
		handValue.setKickers(kickers);

		HandValue opHandValue = new PairHandValue();

		List<Card> opMainCards = new ArrayList<>();
		List<Card> opKickers = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.HEARTS, Rank.ACE));
		opKickers.add(new Card(Suit.CLUBS, Rank.JACK));
		opKickers.add(new Card(Suit.SPADES, Rank.FIVE));
		opKickers.add(new Card(Suit.CLUBS, Rank.THREE));

		opHandValue.setMainCards(opMainCards);
		opHandValue.setKickers(opKickers);

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
	public void aceShouldLoseTwoPair() {

		HandValue opHandValue = new TwoPairHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLose3Kind() {

		HandValue opHandValue = new ThreeOfAKindHandValue();

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLoseStraight() {

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
	public void aceShouldLoseFullHouse_2Cards() {

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
