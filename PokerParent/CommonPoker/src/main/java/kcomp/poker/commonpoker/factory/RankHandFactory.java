package kcomp.poker.commonpoker.factory;

import kcomp.poker.commonpoker.rankranker.FlushRanker;
import kcomp.poker.commonpoker.rankranker.FourOfAKindRanker;
import kcomp.poker.commonpoker.rankranker.FullHouseRanker;
import kcomp.poker.commonpoker.rankranker.HighCardRanker;
import kcomp.poker.commonpoker.rankranker.PairRanker;
import kcomp.poker.commonpoker.rankranker.RankHand;
import kcomp.poker.commonpoker.rankranker.StraightFlushRanker;
import kcomp.poker.commonpoker.rankranker.StraightRanker;
import kcomp.poker.commonpoker.rankranker.ThreeOfAKindRanker;
import kcomp.poker.commonpoker.rankranker.TwoPairRanker;

public class RankHandFactory {

	public static RankHand createPokerRankHand() {
		RankHand rankHand = new RankHand();
		rankHand.addHandRanker(new StraightFlushRanker());
		rankHand.addHandRanker(new FourOfAKindRanker());
		rankHand.addHandRanker(new FullHouseRanker());
		rankHand.addHandRanker(new FlushRanker());
		rankHand.addHandRanker(new StraightRanker());
		rankHand.addHandRanker(new ThreeOfAKindRanker());
		rankHand.addHandRanker(new TwoPairRanker());
		rankHand.addHandRanker(new PairRanker());
		rankHand.addHandRanker(new HighCardRanker());
		return rankHand;
	}

}
