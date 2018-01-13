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
import kcomp.poker.commonpoker.utilities.SimpleHandValueComparePoker;

public class TwoPairRanker implements HandRanker {

	@Override
	public HandValue getHandValue(Hand hand) throws HandRankException {

		HandValue handValue = new HandValue();

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
			handValue.setHandRank(HandRank.HIGH_CARD);
			return handValue;
		}

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

		kickers.subList(1, kickers.size()).clear();

		handValue.setKickers(kickers);

	}

	@Override
	public HandRank getHandRank() {
		return HandRank.TWO_PAIR;
	}

	@Override
	public int compareHandValues(HandValue one, HandValue two) {
		return SimpleHandValueComparePoker.twoPair(one, two);

	}
}
