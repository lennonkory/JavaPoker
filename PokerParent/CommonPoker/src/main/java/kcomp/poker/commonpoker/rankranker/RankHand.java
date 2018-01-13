package kcomp.poker.commonpoker.rankranker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.HandValue;

public class RankHand {

	private Map<HandRank, HandRanker> rankers;
	private List<HandRank> handRankerOrder;

	public RankHand() {
		rankers = new HashMap<>();
		handRankerOrder = new ArrayList<>();
	}

	public HandValue rankHand(Hand hand) throws HandRankException {

		HandValue handValue;

		for (HandRank rank : handRankerOrder) {
			HandRanker ranker = rankers.get(rank);
			handValue = ranker.getHandValue(hand);
			if (!HandRank.HIGH_CARD.equals(handValue.getHandRank())) {
				return handValue;
			}
		}

		return new HandValue();
	}

	/**
	 * Compares two hand values.
	 * 
	 * 1 means handvalue one is higher, -1 means handvalue two. 0 means the
	 * same.
	 */
	public int compareHandValues(HandValue one, HandValue two) {

		HandRank oneRank = one.getHandRank();
		HandRank twoRank = two.getHandRank();

		if (oneRank.getRank() != twoRank.getRank()) {
			return oneRank.getRank() > twoRank.getRank() ? 1 : -1;
		}

		HandRanker ranker = rankers.get(oneRank);

		return ranker.compareHandValues(one, two);

	}

	public void setHandRankers(Map<HandRank, HandRanker> handRankers) {
		rankers = handRankers;

	}

	public void addHandRanker(HandRanker handRanker) {
		this.handRankerOrder.add(handRanker.getHandRank());
		this.rankers.put(handRanker.getHandRank(), handRanker);
	}

}
