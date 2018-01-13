package kcomp.poker.commonpoker.ai;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.creators.HandCreator;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.factory.RankHandFactory;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.rankranker.RankHand;

public class TestHandStrengthService {

	HandStrengthService handStrengthService;

	@Before
	public void init() {

		RankHand rankHand = RankHandFactory.createPokerRankHand();
		handStrengthService = new HandStrengthServiceIMPL(rankHand);

	}

	@Test
	public void test() throws HandRankException {

		Hand hand = HandCreator.createPairHand();

		handStrengthService.calculateHandStrength(hand);

	}

}
