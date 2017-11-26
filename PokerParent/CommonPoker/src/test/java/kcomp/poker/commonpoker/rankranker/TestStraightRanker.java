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

public class TestStraightRanker extends BaseTestRanker {

	@Before
	public void init() {
		ranker = new StraightRanker();
	}

	@Test
	public void test_happy_path_vaild_hand() throws HandRankException {

		Hand hand = HandCreator.createStraightHand();

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.STRAIGHT));

	}

	@Test
	public void test_happy_path_vaild_hand_7_cards() throws HandRankException {

		Hand hand = HandCreator.createStraightHandStartingAt(Rank.EIGHT, 7);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.STRAIGHT));

	}

	@Test
	public void test_happy_path_vaild_hand_ace_counts_as_low() throws HandRankException {

		Hand hand = HandCreator.createStraightHandStartingAt(Rank.FIVE, 5);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.STRAIGHT));
		assertEquals(Integer.valueOf(1), hand.getRanks().get(Rank.ACE));

	}

	@Test
	public void test_happy_path_vaild_hand_ace_counts_as_low_main_is_5() throws HandRankException {

		Hand hand = HandCreator.createStraightHandStartingAt(Rank.FIVE, 5);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.STRAIGHT));
		assertEquals(Integer.valueOf(1), hand.getRanks().get(Rank.ACE));
		assertEquals(Integer.valueOf(5), Integer.valueOf(handValue.getMainCards().size()));

	}

	@Test
	public void test_happy_path_vaild_hand_main_is_5() throws HandRankException {

		Hand hand = HandCreator.createStraightHandStartingAt(Rank.ACE, 5);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.STRAIGHT));
		assertEquals(Integer.valueOf(5), Integer.valueOf(handValue.getMainCards().size()));

	}

	@Test
	public void test_happy_path_vaild_hand_main_is_5_with_7_cards() throws HandRankException {

		Hand hand = HandCreator.createStraightHandStartingAt(Rank.ACE, 7);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.STRAIGHT));
		assertEquals(Integer.valueOf(5), Integer.valueOf(handValue.getMainCards().size()));

	}

	@Test
	public void test_happy_path_vaild_hand_check_ranks() throws HandRankException {

		Hand hand = HandCreator.createEmptyHand();

		Card check = new Card(Suit.SPADES, Rank.EIGHT);
		Card last = new Card(Suit.SPADES, Rank.FIVE);

		hand.addCard(new Card(Suit.SPADES, Rank.SIX));
		hand.addCard(new Card(Suit.SPADES, Rank.ACE));
		hand.addCard(check);
		hand.addCard(new Card(Suit.SPADES, Rank.NINE));
		hand.addCard(last);
		hand.addCard(new Card(Suit.SPADES, Rank.JACK));
		hand.addCard(new Card(Suit.SPADES, Rank.SEVEN));

		HandValue handValue = ranker.getHandValue(hand);

		List<Card> mains = handValue.getMainCards();

		assertTrue(handValue.getHandRank().equals(HandRank.STRAIGHT));
		assertEquals(Integer.valueOf(5), Integer.valueOf(mains.size()));

		assertEquals(check.getRank(), mains.get(1).getRank());
		assertEquals(last.getRank(), mains.get(4).getRank());

	}

	@Test
	public void test_not_straight_should_return_high_card() throws HandRankException {
		test_valid_not_rank_should_return_high_card();
	}

}
