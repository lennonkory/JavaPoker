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

public class TestTwoPairRanker extends BaseTestRanker {

	@Before
	public void init() {
		ranker = new TwoPairRanker();
	}

	@Test
	public void test_happy_path_vaild_hand_with_two_pair() throws HandRankException {

		Hand hand = HandCreator.createTwoPairHand();

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.TWO_PAIR));

	}

	@Test
	public void test_happy_path_vaild_hand_with_pair_should_return_high_card() throws HandRankException {

		Hand hand = HandCreator.createPairHand();

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.HIGH_CARD));

	}

	@Test
	public void test_happy_path_valid_hand_check_ranks() throws HandRankException {

		Rank firstPair = Rank.EIGHT;
		Rank secondPair = Rank.FOUR;
		Rank kicker = Rank.ACE;

		Hand hand = HandCreator.createTwoPairHand(firstPair, secondPair, kicker);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.TWO_PAIR));

		List<Card> mainCards = handValue.getMainCards();

		assertTrue(mainCards.size() == 4);
		Card card = mainCards.get(0);
		assertEquals(card.getRank(), firstPair);

		List<Card> kickers = handValue.getKickers();
		assertTrue(kickers.size() == 1);

		Card kickerCard = kickers.get(0);
		assertEquals(kickerCard.getRank(), kicker);

	}

	@Test
	public void test_happy_path_valid_hand_with_seven_cards_check_ranks() throws HandRankException {

		Rank firstPair = Rank.EIGHT;
		Rank secondPair = Rank.FOUR;
		Rank kicker = Rank.ACE;

		Hand hand = HandCreator.createTwoPairHand(firstPair, secondPair, kicker);

		hand.addCard(new Card(Suit.SPADES, Rank.JACK));
		hand.addCard(new Card(Suit.SPADES, Rank.TEN));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.TWO_PAIR));

		List<Card> mainCards = handValue.getMainCards();

		assertTrue(mainCards.size() == 4);
		Card card = mainCards.get(0);
		assertEquals(card.getRank(), firstPair);

		List<Card> kickers = handValue.getKickers();
		assertTrue(kickers.size() == 1);

		Card kickerCard = kickers.get(0);
		assertEquals(kickerCard.getRank(), kicker);

	}

	@Test
	public void test_kicker_is_of_size_1_5_card_hand() throws HandRankException {

		Hand hand = HandCreator.createTwoPairHand();

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.TWO_PAIR));

		List<Card> kickers = handValue.getKickers();

		assertEquals(1, kickers.size());

	}

	@Test
	public void test_kicker_is_of_size_1_7_card_hand() throws HandRankException {

		Rank firstPair = Rank.EIGHT;
		Rank secondPair = Rank.FOUR;
		Rank kicker = Rank.ACE;

		Hand hand = HandCreator.createTwoPairHand(firstPair, secondPair, kicker);

		hand.addCard(new Card(Suit.SPADES, Rank.JACK));
		hand.addCard(new Card(Suit.SPADES, Rank.TEN));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.TWO_PAIR));

		List<Card> kickers = handValue.getKickers();

		assertEquals(1, kickers.size());

	}

	@Test
	public void test__valid_not_two_pair() throws HandRankException {

		test_valid_not_rank_should_return_high_card();

	}

}
