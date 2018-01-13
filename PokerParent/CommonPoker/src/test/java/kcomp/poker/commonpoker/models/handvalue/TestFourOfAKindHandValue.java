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

public class TestFourOfAKindHandValue {

	private ThreeOfAKindHandValue handValue;

	@Before
	public void init() {
		handValue = new ThreeOfAKindHandValue();
		handValue.setHandRank(HandRank.FOUR_OF_A_KIND);
	}

	@Test
	public void compareSameHandReturnsEqual() {

		List<Card> mainCards = new ArrayList<>();
		List<Card> kickers = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.SPADES, Rank.ACE));
		mainCards.add(new Card(Suit.DIAMONDS, Rank.ACE));
		mainCards.add(new Card(Suit.HEARTS, Rank.ACE));

		kickers.add(new Card(Suit.SPADES, Rank.EIGHT));

		handValue.setMainCards(mainCards);
		handValue.setKickers(kickers);

		int compare = handValue.compareTo(handValue);

		assertEquals(0, compare);

	}

	@Test
	public void compareNoKicker() {

		List<Card> mainCards = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.SPADES, Rank.ACE));
		mainCards.add(new Card(Suit.DIAMONDS, Rank.ACE));
		mainCards.add(new Card(Suit.HEARTS, Rank.ACE));

		handValue.setMainCards(mainCards);

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.FOUR_OF_A_KIND);

		List<Card> opMainCards = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.SPADES, Rank.ACE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.SPADES, Rank.ACE));

		opHandValue.setMainCards(opMainCards);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(0, compare);

	}

	@Test
	public void aceShouldBeatKing() {

		List<Card> mainCards = new ArrayList<>();
		List<Card> kickers = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.SPADES, Rank.ACE));
		mainCards.add(new Card(Suit.DIAMONDS, Rank.ACE));
		mainCards.add(new Card(Suit.HEARTS, Rank.ACE));

		kickers.add(new Card(Suit.SPADES, Rank.EIGHT));

		handValue.setMainCards(mainCards);
		handValue.setKickers(kickers);

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.FOUR_OF_A_KIND);

		List<Card> opMainCards = new ArrayList<>();
		List<Card> opKickers = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.KING));
		opMainCards.add(new Card(Suit.SPADES, Rank.KING));
		opMainCards.add(new Card(Suit.CLUBS, Rank.KING));
		opMainCards.add(new Card(Suit.SPADES, Rank.KING));

		opKickers.add(new Card(Suit.SPADES, Rank.EIGHT));

		opHandValue.setMainCards(opMainCards);
		opHandValue.setKickers(opKickers);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void kingShouldLoseAcePair_5Cards() {

		List<Card> mainCards = new ArrayList<>();
		List<Card> kickers = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.KING));
		mainCards.add(new Card(Suit.SPADES, Rank.KING));
		mainCards.add(new Card(Suit.DIAMONDS, Rank.KING));
		mainCards.add(new Card(Suit.HEARTS, Rank.KING));

		kickers.add(new Card(Suit.SPADES, Rank.EIGHT));

		handValue.setMainCards(mainCards);
		handValue.setKickers(kickers);

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.FOUR_OF_A_KIND);

		List<Card> opMainCards = new ArrayList<>();
		List<Card> opKickers = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.SPADES, Rank.ACE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.SPADES, Rank.ACE));

		opKickers.add(new Card(Suit.SPADES, Rank.EIGHT));

		opHandValue.setMainCards(opMainCards);
		opHandValue.setKickers(opKickers);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

	@Test
	public void aceShouldBeatAceHighHigherKickerLastCard() {

		List<Card> mainCards = new ArrayList<>();
		List<Card> kickers = new ArrayList<>();

		mainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		mainCards.add(new Card(Suit.SPADES, Rank.ACE));
		mainCards.add(new Card(Suit.DIAMONDS, Rank.ACE));
		mainCards.add(new Card(Suit.HEARTS, Rank.ACE));

		kickers.add(new Card(Suit.SPADES, Rank.KING));

		handValue.setMainCards(mainCards);
		handValue.setKickers(kickers);

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.FOUR_OF_A_KIND);

		List<Card> opMainCards = new ArrayList<>();
		List<Card> opKickers = new ArrayList<>();

		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.SPADES, Rank.ACE));
		opMainCards.add(new Card(Suit.CLUBS, Rank.ACE));
		opMainCards.add(new Card(Suit.SPADES, Rank.ACE));

		opKickers.add(new Card(Suit.SPADES, Rank.EIGHT));

		opHandValue.setMainCards(opMainCards);
		opHandValue.setKickers(opKickers);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatHighCard() {

		HandValue opHandValue = new HighCardHandValue();
		opHandValue.setHandRank(HandRank.HIGH_CARD);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatPair() {

		HandValue opHandValue = new PairHandValue();
		opHandValue.setHandRank(HandRank.PAIR);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatTwoPair() {

		HandValue opHandValue = new TwoPairHandValue();
		opHandValue.setHandRank(HandRank.TWO_PAIR);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatthreeOfaKind() {

		HandValue opHandValue = new ThreeOfAKindHandValue();
		opHandValue.setHandRank(HandRank.THREE_OF_A_KIND);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatStraight() {

		HandValue opHandValue = new StraightHandValue();
		opHandValue.setHandRank(HandRank.STRAIGHT);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatFlush() {

		HandValue opHandValue = new FlushHandValue();
		opHandValue.setHandRank(HandRank.FLUSH);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldBeatFullHouses() {

		HandValue opHandValue = new FullHouseHandValue();
		opHandValue.setHandRank(HandRank.FULL_HOUSE);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(1, compare);

	}

	@Test
	public void aceShouldLoseStraightFlush() {

		HandValue opHandValue = new StraightFlushHandValue();
		opHandValue.setHandRank(HandRank.STRAIGHT_FLUSH);

		int compare = handValue.compareTo(opHandValue);

		assertEquals(-1, compare);

	}

}
