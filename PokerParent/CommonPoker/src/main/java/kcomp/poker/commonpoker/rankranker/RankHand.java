package kcomp.poker.commonpoker.rankranker;

import java.util.List;

import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.HandValue;

public class RankHand {

	private static RankHand rankHand;

	private static List<HandRanker> rankers;

	private RankHand() {
	}

	public static RankHand getHandRanker() {
		if (rankHand == null) {
			rankHand = new RankHand();
		}
		return rankHand;
	}

	public static HandValue rankHand(Hand hand) {
		return new HandValue();
	}

	public static void setHandRankers() {
	}

}
