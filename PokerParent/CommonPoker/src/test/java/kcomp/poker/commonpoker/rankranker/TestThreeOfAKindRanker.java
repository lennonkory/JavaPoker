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

public class TestThreeOfAKindRanker extends BaseTestRanker {

	@Before
	public void init() {
		ranker = new ThreeOfAKindRanker();
	}

	@Test
	public void test_happy_path() throws HandRankException {
		Hand hand = HandCreator.createThreeKindHand();

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.THREE_OF_A_KIND));
	}

	@Test
	public void test_happy_path_7_cards() throws HandRankException {

		Hand hand = HandCreator.createThreeKindHand(Rank.ACE, Rank.KING, Rank.QUEEN);

		hand.addCard(new Card(Suit.SPADES, Rank.TEN));
		hand.addCard(new Card(Suit.SPADES, Rank.NINE));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.THREE_OF_A_KIND));
	}

	@Test
	public void test_happy_path_5_cards_rank_check() throws HandRankException {

		Hand hand = HandCreator.createThreeKindHand(Rank.ACE, Rank.KING, Rank.QUEEN);

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.THREE_OF_A_KIND));

		List<Card> mainCards = handValue.getMainCards();
		List<Card> kickers = handValue.getKickers();

		assertEquals(Rank.ACE, mainCards.get(1).getRank());

		assertEquals(Rank.QUEEN, kickers.get(1).getRank());
	}

	@Test
	public void test_happy_path_7_cards_rank_check() throws HandRankException {

		Hand hand = HandCreator.createThreeKindHand(Rank.ACE, Rank.KING, Rank.QUEEN);

		hand.addCard(new Card(Suit.SPADES, Rank.TEN));
		hand.addCard(new Card(Suit.SPADES, Rank.NINE));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.THREE_OF_A_KIND));

		List<Card> mainCards = handValue.getMainCards();
		List<Card> kickers = handValue.getKickers();

		assertEquals(Rank.ACE, mainCards.get(1).getRank());

		assertEquals(Rank.QUEEN, kickers.get(1).getRank());
	}

	@Test
	public void test_happy_path_7_cards_main_kicker_size() throws HandRankException {

		Hand hand = HandCreator.createThreeKindHand(Rank.ACE, Rank.KING, Rank.QUEEN);

		hand.addCard(new Card(Suit.SPADES, Rank.TEN));
		hand.addCard(new Card(Suit.SPADES, Rank.NINE));

		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.THREE_OF_A_KIND));

		List<Card> mainCards = handValue.getMainCards();
		List<Card> kickers = handValue.getKickers();

		assertEquals(3, mainCards.size());

		assertEquals(2, kickers.size());
	}

	@Test
	public void test_not_3_kind_should_return_high_card() throws HandRankException {
		test_valid_not_rank_should_return_high_card();
	}

}
