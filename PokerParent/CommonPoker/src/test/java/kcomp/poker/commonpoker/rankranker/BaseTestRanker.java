package kcomp.poker.commonpoker.rankranker;

import static org.junit.Assert.assertTrue;

import kcomp.poker.commonpoker.creators.HandCreator;
import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.handvalue.HandValue;

public abstract class BaseTestRanker {

	protected HandRanker ranker;

	public void test_valid_not_rank_should_return_high_card() throws HandRankException {

		Hand hand = HandCreator.createHighCardHand();
		HandValue handValue = ranker.getHandValue(hand);

		assertTrue(handValue.getHandRank().equals(HandRank.HIGH_CARD));

	}

}
