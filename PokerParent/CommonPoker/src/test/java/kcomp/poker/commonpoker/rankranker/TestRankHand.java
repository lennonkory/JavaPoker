package kcomp.poker.commonpoker.rankranker;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.creators.HandCreator;
import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.handvalue.HandValue;

public class TestRankHand {

	private RankHand rankHand;

	@Before
	public void init() {
		rankHand = new RankHand();
		rankHand.addHandRanker(new StraightFlushRanker());
		rankHand.addHandRanker(new FourOfAKindRanker());
		rankHand.addHandRanker(new FullHouseRanker());
		rankHand.addHandRanker(new FlushRanker());
		rankHand.addHandRanker(new StraightRanker());
		rankHand.addHandRanker(new ThreeOfAKindRanker());
		rankHand.addHandRanker(new TwoPairRanker());
		rankHand.addHandRanker(new PairRanker());
	}

	@Test
	public void test_high_card() throws HandRankException {
		Hand hand = HandCreator.createHighCardHand();

		HandValue handValue = rankHand.rankHand(hand);

		assertTrue(HandRank.HIGH_CARD.equals(handValue.getHandRank()));

	}

	@Test
	public void test_pair() throws HandRankException {
		Hand hand = HandCreator.createPairHand();

		HandValue handValue = rankHand.rankHand(hand);

		assertTrue(HandRank.PAIR.equals(handValue.getHandRank()));

	}

	@Test
	public void test_two_pair() throws HandRankException {
		Hand hand = HandCreator.createTwoPairHand();

		HandValue handValue = rankHand.rankHand(hand);

		assertTrue(HandRank.TWO_PAIR.equals(handValue.getHandRank()));

	}

	@Test
	public void test_three_of_a_kind() throws HandRankException {
		Hand hand = HandCreator.createThreeKindHand();

		HandValue handValue = rankHand.rankHand(hand);

		assertTrue(HandRank.THREE_OF_A_KIND.equals(handValue.getHandRank()));

	}

	@Test
	public void test_straight() throws HandRankException {
		Hand hand = HandCreator.createStraightHand();

		HandValue handValue = rankHand.rankHand(hand);

		assertTrue(HandRank.STRAIGHT.equals(handValue.getHandRank()));

	}

	@Test
	public void test_flush() throws HandRankException {
		Hand hand = HandCreator.createFlushHand(Suit.SPADES);

		HandValue handValue = rankHand.rankHand(hand);

		assertTrue(HandRank.FLUSH.equals(handValue.getHandRank()));

	}

	@Test
	public void test_full_house() throws HandRankException {
		Hand hand = HandCreator.createFullHouseHand(Rank.ACE, Rank.EIGHT);

		HandValue handValue = rankHand.rankHand(hand);

		assertTrue(HandRank.FULL_HOUSE.equals(handValue.getHandRank()));

	}

	@Test
	public void test_four_of_a_kind() throws HandRankException {
		Hand hand = HandCreator.createFourKindHand();

		HandValue handValue = rankHand.rankHand(hand);

		assertTrue(HandRank.FOUR_OF_A_KIND.equals(handValue.getHandRank()));

	}

	@Test
	public void test_straight_flush() throws HandRankException {
		Hand hand = HandCreator.createStraightFlushHand(Suit.HEARTS);

		HandValue handValue = rankHand.rankHand(hand);

		assertTrue(HandRank.STRAIGHT_FLUSH.equals(handValue.getHandRank()));

	}

}
