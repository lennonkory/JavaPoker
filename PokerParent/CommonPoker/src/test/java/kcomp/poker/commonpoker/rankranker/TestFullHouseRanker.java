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

public class TestFullHouseRanker extends BaseTestRanker {

	@Before
	public void init() {
		ranker = new FullHouseRanker();
	}

	@Test
	public void test_happy_path_vaild_hand_with_full_house() throws HandRankException {

		Hand hand = HandCreator.createFullHouseHand(Rank.ACE, Rank.KING);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.FULL_HOUSE));

	}

	@Test
	public void test_happy_path_vaild_hand_with_full_house_7_cards() throws HandRankException {

		Hand hand = HandCreator.createFullHouseHand(Rank.ACE, Rank.KING);

		hand.addCard(new Card(Suit.DIAMONDS, Rank.EIGHT));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.SEVEN));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.FULL_HOUSE));

	}

	@Test
	public void test_happy_path_vaild_hand_with_full_house_7_cards_main_is_5() throws HandRankException {

		Hand hand = HandCreator.createFullHouseHand(Rank.ACE, Rank.KING);

		hand.addCard(new Card(Suit.DIAMONDS, Rank.EIGHT));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.SEVEN));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.FULL_HOUSE));
		List<Card> mains = handValue.getMainCards();

		assertEquals(Integer.valueOf(5), Integer.valueOf(mains.size()));

	}

	@Test
	public void test_happy_path_vaild_hand_with_full_house_7_cards_check_ranks_main_is_5() throws HandRankException {

		Hand hand = HandCreator.createFullHouseHand(Rank.ACE, Rank.KING);

		hand.addCard(new Card(Suit.DIAMONDS, Rank.EIGHT));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.SEVEN));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.FULL_HOUSE));
		List<Card> mains = handValue.getMainCards();

		assertEquals(Integer.valueOf(5), Integer.valueOf(mains.size()));
		assertEquals(Rank.ACE, mains.get(2).getRank());
		assertEquals(Rank.KING, mains.get(4).getRank());

	}

	@Test
	public void test_happy_path_vaild_hand_with_full_house_7_cards_check_ranks_with_two_twos_high_two_in_main()
			throws HandRankException {

		Hand hand = HandCreator.createFullHouseHand(Rank.ACE, Rank.KING);

		hand.addCard(new Card(Suit.DIAMONDS, Rank.EIGHT));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.EIGHT));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.FULL_HOUSE));
		List<Card> mains = handValue.getMainCards();

		assertEquals(Integer.valueOf(5), Integer.valueOf(mains.size()));
		assertEquals(Rank.ACE, mains.get(2).getRank());
		assertEquals(Rank.KING, mains.get(4).getRank());

	}

	@Test
	public void test_not_full_house_should_return_high_card() throws HandRankException {
		test_valid_not_rank_should_return_high_card();
	}

}
