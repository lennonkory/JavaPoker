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

public class TestPairRanker extends BaseTestRanker {

	@Before
	public void init() {
		ranker = new PairRanker();
	}

	@Test
	public void test_happy_path_vaild_hand_with_pair() throws HandRankException {

		Hand hand = HandCreator.createPairHand();

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.PAIR));

	}

	@Test
	public void test_happy_path_vaild_hand_with_two_pair_should_return_pair() throws HandRankException {

		Hand hand = HandCreator.createTwoPairHand();

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.PAIR));

	}

	@Test
	public void test_happy_path_check_ranks() throws HandRankException {

		Hand hand = HandCreator.createEmptyHand();

		Card firstOfPair = new Card(Suit.CLUBS, Rank.ACE);
		Card secondOfPair = new Card(Suit.DIAMONDS, Rank.ACE);
		Card firstKicker = new Card(Suit.CLUBS, Rank.KING);
		Card secondKicker = new Card(Suit.HEARTS, Rank.JACK);
		Card thirdKicker = new Card(Suit.HEARTS, Rank.THREE);

		hand.addCard(secondOfPair);
		hand.addCard(thirdKicker);
		hand.addCard(firstKicker);
		hand.addCard(secondKicker);
		hand.addCard(firstOfPair);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.PAIR));

		List<Card> mainCards = handValue.getMainCards();
		List<Card> kickers = handValue.getKickers();

		assertEquals(firstOfPair.getRank(), mainCards.get(1).getRank());

		assertEquals(secondKicker.getRank(), kickers.get(1).getRank());

	}

	@Test
	public void test_kicker_is_of_size_3_5_card_hand() throws HandRankException {

		Hand hand = HandCreator.createPairHand();

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.PAIR));

		List<Card> kickers = handValue.getKickers();

		assertEquals(3, kickers.size());

	}

	@Test
	public void test_kicker_is_of_size_3_7_card_hand() throws HandRankException {

		Hand hand = HandCreator.createEmptyHand();

		Card firstOfPair = new Card(Suit.CLUBS, Rank.ACE);
		Card secondOfPair = new Card(Suit.DIAMONDS, Rank.ACE);
		Card firstKicker = new Card(Suit.CLUBS, Rank.KING);
		Card secondKicker = new Card(Suit.HEARTS, Rank.JACK);
		Card thirdKicker = new Card(Suit.HEARTS, Rank.TEN);
		Card extraOne = new Card(Suit.SPADES, Rank.EIGHT);
		Card extraTwo = new Card(Suit.SPADES, Rank.FIVE);

		hand.addCard(secondOfPair);
		hand.addCard(thirdKicker);
		hand.addCard(extraOne);
		hand.addCard(firstKicker);
		hand.addCard(secondKicker);
		hand.addCard(extraTwo);
		hand.addCard(firstOfPair);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.PAIR));

		List<Card> kickers = handValue.getKickers();

		assertEquals(3, kickers.size());

	}

	@Test
	public void test_not_pair_should_return_high_card() throws HandRankException {
		test_valid_not_rank_should_return_high_card();
	}

}
