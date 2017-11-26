package kcomp.poker.commonpoker.rankranker;

import java.util.ArrayList;
import java.util.List;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.HandValue;

public class RankHand {

	private List<HandRanker> rankers;

	public RankHand() {
		rankers = new ArrayList<>();
	}

	public HandValue rankHand(Hand hand) throws HandRankException {

		HandValue handValue;

		for (HandRanker ranker : rankers) {
			handValue = ranker.getHandValue(hand);
			if (!HandRank.HIGH_CARD.equals(handValue.getHandRank())) {
				return handValue;
			}
		}

		return new HandValue();
	}

	public void setHandRankers(List<HandRanker> handRankers) {
		rankers = handRankers;
	}

	public void addHandRanker(HandRanker handRanker) {
		this.rankers.add(handRanker);
	}

}
