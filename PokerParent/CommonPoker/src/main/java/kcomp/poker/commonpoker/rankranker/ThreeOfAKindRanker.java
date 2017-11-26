package kcomp.poker.commonpoker.rankranker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.HandValue;
import kcomp.poker.commonpoker.utilities.HandUtility;

public class ThreeOfAKindRanker implements HandRanker {

	@Override
	public HandValue getHandValue(Hand hand) throws HandRankException {

		HandValue handValue = new HandValue();

		Map<Rank, Integer> ranks = hand.getRanks();

		Rank pair = null;

		// May need to change this loop to custom order
		for (Rank rank : Rank.values()) {

			Integer value = ranks.get(rank);

			if (value == 3) {
				pair = rank;
			}
		}

		if (pair == null) {
			handValue.setHandRank(HandRank.HIGH_CARD);
			return handValue;
		}

		// Set main cards
		setMainCards(pair, hand.getCards(), handValue);

		// Set Kicker
		setKicker(hand, handValue);

		handValue.setHandRank(HandRank.THREE_OF_A_KIND);

		return handValue;

	}

	// Must be called AFTER setMainCards
	private void setKicker(Hand hand, HandValue handValue) {
		// Do not include main cards from hand;
		List<Card> kickers = new ArrayList<>(hand.getCards());

		kickers.removeAll(handValue.getMainCards());

		Collections.sort(kickers);

		kickers.subList(2, kickers.size()).clear();

		handValue.setKickers(kickers);
	}

	private void setMainCards(Rank pair, List<Card> cards, HandValue handValue) throws HandRankException {

		List<Card> kind = HandUtility.getCardsByRank(pair, cards);

		if (kind.size() != 3) {
			throw new HandRankException("Three of a kind not found: " + pair);
		}

		handValue.setMainCards(new ArrayList<>(kind));

	}

}
