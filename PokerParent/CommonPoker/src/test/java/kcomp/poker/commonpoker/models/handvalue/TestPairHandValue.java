package kcomp.poker.commonpoker.models.handvalue;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.models.Card;

public class TestPairHandValue {

	private PairHandValue handValue;

	@Before
	public void init() {
		handValue = new PairHandValue();
		handValue.setHandRank(HandRank.PAIR);
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

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.PAIR);

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

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.PAIR);

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

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.PAIR);

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

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.PAIR);

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

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.PAIR);

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

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.PAIR);

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
	public void aceShouldBeatHighCard_2Cards() {

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.HIGH_CARD);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldLoseTwoPair_2Cards() {

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.TWO_PAIR);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLose3Kind_2Cards() {

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.THREE_OF_A_KIND);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLoseStraight_2Cards() {

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.STRAIGHT);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLoseFlush_2Cards() {

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.FLUSH);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLoseFullHouse_2Cards() {

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.FULL_HOUSE);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLoseFourKind_2Cards() {

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.FOUR_OF_A_KIND);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldLoseStraightFlush_2Cards() {

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.STRAIGHT_FLUSH);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

}
