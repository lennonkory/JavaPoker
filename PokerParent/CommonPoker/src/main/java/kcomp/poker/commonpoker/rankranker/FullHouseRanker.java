package kcomp.poker.commonpoker.rankranker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.handvalue.FullHouseHandValue;
import kcomp.poker.commonpoker.models.handvalue.HandValue;
import kcomp.poker.commonpoker.models.handvalue.HighCardHandValue;

public class FullHouseRanker implements HandRanker {

	@Override
	public HandValue getHandValue(Hand hand) throws HandRankException {

		Map<Rank, Integer> ranks = hand.getRanks();

		Rank three = null;
		Rank two = null;

		// May need to change this loop to custom order
		for (Rank rank : Rank.values()) {

			Integer value = ranks.get(rank);

			if (value == 3) {
				three = rank;
			}
			if (value == 2) {
				two = rank;
			}

			if (three != null && two != null) {
				break;
			}

		}

		if (three == null || two == null) {
			HandValue handValue = new HighCardHandValue();

			return handValue;
		}

		HandValue handValue = new FullHouseHandValue();

		setMains(three, two, hand.getCards(), handValue);

		return handValue;

	}

	private void setMains(Rank three, Rank two, List<Card> cards, HandValue handValue) {

		List<Card> mains = new ArrayList<>();

		for (Card card : cards) {
			Rank rank = card.getRank();

			if (rank.equals(three)) {
				mains.add(0, card);
			}
			if (rank.equals(two)) {
				mains.add(mains.size(), card);
			}

		}

		handValue.setMainCards(mains);

	}

	@Override
	public HandRank getHandRank() {
		return HandRank.FULL_HOUSE;
	}

}
