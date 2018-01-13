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

public class TestFlushRanker extends BaseTestRanker {

	@Before
	public void init() {
		ranker = new FlushRanker();
	}

	@Test
	public void test_happy_path_vaild_hand_with_flush() throws HandRankException {

		Hand hand = HandCreator.createFlushHand(Suit.HEARTS);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.FLUSH));

	}

	@Test
	public void test_happy_path_vaild_hand_with_7_cards() throws HandRankException {

		Hand hand = HandCreator.createFlushHand(Suit.HEARTS);

		hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
		hand.addCard(new Card(Suit.CLUBS, Rank.KING));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.FLUSH));

	}

	@Test
	public void test_happy_path_vaild_main_has_five_cards() throws HandRankException {

		Hand hand = HandCreator.createFlushHand(Suit.HEARTS);

		hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
		hand.addCard(new Card(Suit.CLUBS, Rank.KING));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.FLUSH));

		List<Card> main = handValue.getMainCards();

		assertTrue(main.size() == 5);

	}

	@Test
	public void test_happy_path_vaild_hand_check_ranks() throws HandRankException {

		Hand hand = HandCreator.createEmptyHand();

		Card first = new Card(Suit.SPADES, Rank.ACE);
		Card second = new Card(Suit.SPADES, Rank.JACK);
		Card third = new Card(Suit.SPADES, Rank.EIGHT);
		Card fourth = new Card(Suit.SPADES, Rank.SIX);
		Card fifth = new Card(Suit.SPADES, Rank.FIVE);
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

		List<Card> mains = handValue.getMainCards();

		assertTrue(handValue.getHandRank().equals(HandRank.FLUSH));
		assertEquals(Integer.valueOf(5), Integer.valueOf(mains.size()));

		assertEquals(first.getRank(), mains.get(0).getRank());
		assertEquals(fifth.getRank(), mains.get(4).getRank());

	}

	@Test
	public void test_not_flush_should_return_high_card() throws HandRankException {
		test_valid_not_rank_should_return_high_card();
	}

}
