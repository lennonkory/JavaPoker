package kcomp.poker.commonpoker.rankranker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.creators.HandCreator;
import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.handvalue.HandValue;

public class TestHighCardRanker extends BaseTestRanker {

	@Before
	public void init() {
		ranker = new HighCardRanker();
	}

	@Test
	public void test_happy_path_vaild_hand_high_card() throws HandRankException {

		Hand hand = HandCreator.createHighCardHand();

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.HIGH_CARD));

	}

	@Test
	public void test_happy_path_check_ranks() throws HandRankException {

		Hand hand = HandCreator.createEmptyHand();

		Card first = new Card(Suit.CLUBS, Rank.ACE);
		Card second = new Card(Suit.DIAMONDS, Rank.KING);
		Card third = new Card(Suit.CLUBS, Rank.QUEEN);
		Card fourth = new Card(Suit.HEARTS, Rank.JACK);
		Card fifth = new Card(Suit.HEARTS, Rank.THREE);

		hand.addCard(third);
		hand.addCard(second);
		hand.addCard(fifth);
		hand.addCard(first);
		hand.addCard(fourth);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.HIGH_CARD));

		List<Card> mainCards = handValue.getMainCards();

		assertEquals(second.getRank(), mainCards.get(1).getRank());

		assertEquals(fifth.getRank(), mainCards.get(4).getRank());

	}

	@Test
	public void test_happy_path_main_should_have_5_cards() throws HandRankException {

		Hand hand = HandCreator.createHighCardHand();

		Card extraOne = new Card(Suit.HEARTS, Rank.TWO);
		Card extraTwo = new Card(Suit.HEARTS, Rank.THREE);

		hand.addCard(extraOne);
		hand.addCard(extraTwo);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.HIGH_CARD));

		List<Card> mainCards = handValue.getMainCards();

		assertEquals(5, mainCards.size());

	}

}
