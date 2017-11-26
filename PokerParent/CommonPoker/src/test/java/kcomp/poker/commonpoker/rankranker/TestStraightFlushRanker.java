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
import kcomp.poker.commonpoker.models.HandValue;

public class TestStraightFlushRanker extends BaseTestRanker {

	@Before
	public void init() {
		ranker = new StraightFlushRanker();
	}

	@Test
	public void test_happy_path_vaild_hand_with_straight_flush() throws HandRankException {

		Hand hand = HandCreator.createStraightFlushHand(Suit.CLUBS);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.STRAIGHT_FLUSH));

	}

	@Test
	public void test_happy_path_vaild_hand_with_straight_flush_with_7_cards() throws HandRankException {

		Hand hand = HandCreator.createStraightFlushHand(Suit.CLUBS);

		hand.addCard(new Card(Suit.DIAMONDS, Rank.THREE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.TWO));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.STRAIGHT_FLUSH));

	}

	@Test
	public void test_happy_path_vaild_hand_with_straight_flush_with_7_cards_main_has_5_cards()
			throws HandRankException {

		Hand hand = HandCreator.createStraightFlushHand(Suit.CLUBS);

		hand.addCard(new Card(Suit.DIAMONDS, Rank.THREE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.TWO));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.STRAIGHT_FLUSH));

		List<Card> mains = handValue.getMainCards();

		assertTrue(mains.size() == 5);

	}

	@Test
	public void test_happy_path_vaild_hand_with_straight_flush_with_7_cards_main_has_5_cards_check_ranks()
			throws HandRankException {

		Hand hand = HandCreator.createEmptyHand();

		Card first = new Card(Suit.SPADES, Rank.ACE);
		Card second = new Card(Suit.SPADES, Rank.KING);
		Card third = new Card(Suit.SPADES, Rank.QUEEN);
		Card fourth = new Card(Suit.SPADES, Rank.JACK);
		Card fifth = new Card(Suit.SPADES, Rank.TEN);
		Card sixth = new Card(Suit.SPADES, Rank.THREE);
		Card seventh = new Card(Suit.SPADES, Rank.TWO);

		hand.addCard(third);
		hand.addCard(sixth);
		hand.addCard(fourth);
		hand.addCard(seventh);
		hand.addCard(first);
		hand.addCard(fifth);
		hand.addCard(second);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.STRAIGHT_FLUSH));

		List<Card> mains = handValue.getMainCards();

		assertTrue(mains.size() == 5);

		assertEquals(second, mains.get(1));
		assertEquals(fifth, mains.get(4));

	}

	@Test
	public void test_happy_path_vaild_hand_with_straight_flush_with_7_cards_ace_low_check_ranks()
			throws HandRankException {

		Hand hand = HandCreator.createEmptyHand();

		Card first = new Card(Suit.SPADES, Rank.ACE);
		Card second = new Card(Suit.SPADES, Rank.KING);
		Card third = new Card(Suit.SPADES, Rank.NINE);
		Card fourth = new Card(Suit.SPADES, Rank.FIVE);
		Card fifth = new Card(Suit.SPADES, Rank.FOUR);
		Card sixth = new Card(Suit.SPADES, Rank.THREE);
		Card seventh = new Card(Suit.SPADES, Rank.TWO);

		hand.addCard(third);
		hand.addCard(sixth);
		hand.addCard(fourth);
		hand.addCard(seventh);
		hand.addCard(first);
		hand.addCard(fifth);
		hand.addCard(second);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.STRAIGHT_FLUSH));

		List<Card> mains = handValue.getMainCards();

		assertTrue(mains.size() == 5);

		assertEquals(fourth, mains.get(0));
		assertEquals(first, mains.get(4));

	}

	@Test
	public void test_not_straight_flush_should_return_high_card() throws HandRankException {
		test_valid_not_rank_should_return_high_card();
	}

}
