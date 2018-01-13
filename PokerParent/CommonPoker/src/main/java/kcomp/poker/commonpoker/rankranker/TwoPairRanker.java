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
import kcomp.poker.commonpoker.models.handvalue.HandValue;
import kcomp.poker.commonpoker.models.handvalue.HighCardHandValue;
import kcomp.poker.commonpoker.models.handvalue.TwoPairHandValue;
import kcomp.poker.commonpoker.utilities.HandUtility;

public class TwoPairRanker implements HandRanker {

	@Override
	public HandValue getHandValue(Hand hand) throws HandRankException {

		Map<Rank, Integer> ranks = hand.getRanks();

		Rank firstPair = null;
		Rank secondPair = null;

		// May need to change this loop to custom order
		for (Rank rank : Rank.values()) {

			Integer value = ranks.get(rank);

			if (value == 2) {
				if (firstPair == null) {
					firstPair = rank;
				} else {
					secondPair = rank;
					break;
				}
			}
		}

		if (firstPair == null || secondPair == null) {
			HandValue handValue = new HighCardHandValue();
			handValue.setHandRank(HandRank.HIGH_CARD);
			return handValue;
		}

		HandValue handValue = new TwoPairHandValue();

		// Set main cards
		setMainCards(firstPair, secondPair, hand.getCards(), handValue);

		// Set Kicker
		setKicker(hand, handValue);

		handValue.setHandRank(HandRank.TWO_PAIR);

		return handValue;
	}

	private void setMainCards(Rank firstRank, Rank secondRank, List<Card> cards, HandValue handValue)
			throws HandRankException {

		List<Card> firstPairs = HandUtility.getCardsByRank(firstRank, cards);

		if (firstPairs.size() != 2) {
			throw new HandRankException("Pair not found: " + firstRank);
		}

		List<Card> secondPairs = HandUtility.getCardsByRank(secondRank, cards);

		if (secondPairs.size() != 2) {
			throw new HandRankException("Pair not found: " + secondRank);
		}

		firstPairs.addAll(secondPairs);

		handValue.setMainCards(new ArrayList<>(firstPairs));

	}

	private void setKicker(Hand hand, HandValue handValue) {

		// Do not include main cards from hand;

		List<Card> kickers = new ArrayList<>(hand.getCards());

		kickers.removeAll(handValue.getMainCards());

		Collections.sort(kickers);
		Collections.reverse(kickers);

		kickers.subList(1, kickers.size()).clear();

		handValue.setKickers(kickers);

	}

	@Override
	public HandRank getHandRank() {
		return HandRank.TWO_PAIR;
	}

}
