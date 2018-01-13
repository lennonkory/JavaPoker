package kcomp.poker.commonpoker.rankranker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.handvalue.HandValue;

public class RankHand {

	private Map<HandRank, HandRanker> rankers;
	private List<HandRank> handRankerOrder;

	public RankHand() {
		rankers = new HashMap<>();
		handRankerOrder = new ArrayList<>();
	}

	public HandValue rankHand(Hand hand) throws HandRankException {

		HandValue handValue = null;

		for (HandRank rank : handRankerOrder) {
			HandRanker ranker = rankers.get(rank);
			handValue = ranker.getHandValue(hand);
			if (!HandRank.HIGH_CARD.equals(handValue.getHandRank())) {
				return handValue;
			}
		}

		return handValue;
	}

	public void setHandRankers(Map<HandRank, HandRanker> handRankers) {
		rankers = handRankers;

	}

	public void addHandRanker(HandRanker handRanker) {
		this.handRankerOrder.add(handRanker.getHandRank());
		this.rankers.put(handRanker.getHandRank(), handRanker);
	}

}
