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
import kcomp.poker.commonpoker.models.handvalue.PairHandValue;
import kcomp.poker.commonpoker.utilities.HandUtility;

public class PairRanker implements HandRanker {

	@Override
	public HandValue getHandValue(Hand hand) throws HandRankException {

		Map<Rank, Integer> ranks = hand.getRanks();

		Rank pair = null;

		// May need to change this loop to custom order
		for (Rank rank : Rank.values()) {

			Integer value = ranks.get(rank);

			if (value == 2) {
				pair = rank;
			}
		}

		if (pair == null) {
			HandValue handValue = new HighCardHandValue();
			return handValue;
		}

		HandValue handValue = new PairHandValue();

		// Set main cards
		setMainCards(pair, hand.getCards(), handValue);

		// Set Kicker
		setKicker(hand, handValue);

		return handValue;

	}

	private void setKicker(Hand hand, HandValue handValue) {
		// Do not include main cards from hand;
		List<Card> tempKickers = new ArrayList<>(hand.getCards());

		tempKickers.removeAll(handValue.getMainCards());

		Collections.sort(tempKickers);
		Collections.reverse(tempKickers);

		int number = tempKickers.size() > 3 ? 3 : tempKickers.size();

		List<Card> kickers = new ArrayList<>();

		for (int i = 0; i < number; i++) {
			kickers.add(tempKickers.get(i));
		}

		handValue.setKickers(kickers);
	}

	private void setMainCards(Rank pair, List<Card> cards, HandValue handValue) throws HandRankException {

		List<Card> pairs = HandUtility.getCardsByRank(pair, cards);

		if (pairs.size() != 2) {
			throw new HandRankException("Pair not found: " + pair);
		}

		handValue.setMainCards(new ArrayList<>(pairs));

	}

	@Override
	public HandRank getHandRank() {
		return HandRank.PAIR;
	}

}
