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
import kcomp.poker.commonpoker.models.handvalue.FourOfAKindHandValue;
import kcomp.poker.commonpoker.models.handvalue.HandValue;
import kcomp.poker.commonpoker.models.handvalue.HighCardHandValue;
import kcomp.poker.commonpoker.utilities.HandUtility;

public class FourOfAKindRanker implements HandRanker {

	@Override
	public HandValue getHandValue(Hand hand) throws HandRankException {

		Map<Rank, Integer> ranks = hand.getRanks();

		Rank pair = null;

		// May need to change this loop to custom order
		for (Rank rank : Rank.values()) {

			Integer value = ranks.get(rank);

			if (value == 4) {
				pair = rank;
			}
		}

		if (pair == null) {
			HandValue handValue = new HighCardHandValue();
			handValue.setHandRank(HandRank.HIGH_CARD);
			return handValue;
		}

		HandValue handValue = new FourOfAKindHandValue();

		// Set main cards
		setMainCards(pair, hand.getCards(), handValue);

		// Set Kicker
		setKicker(hand, handValue);

		handValue.setHandRank(HandRank.FOUR_OF_A_KIND);

		return handValue;

	}

	// Must be called AFTER setMainCards
	private void setKicker(Hand hand, HandValue handValue) {
		// Do not include main cards from hand;
		List<Card> kickers = new ArrayList<>(hand.getCards());

		kickers.removeAll(handValue.getMainCards());

		Collections.sort(kickers);
		Collections.reverse(kickers);

		kickers.subList(1, kickers.size()).clear();

		handValue.setKickers(kickers);
	}

	private void setMainCards(Rank pair, List<Card> cards, HandValue handValue) throws HandRankException {

		List<Card> kind = HandUtility.getCardsByRank(pair, cards);

		if (kind.size() != 4) {
			throw new HandRankException("Four of a kind not found: " + pair);
		}

		handValue.setMainCards(new ArrayList<>(kind));

	}

	@Override
	public HandRank getHandRank() {
		return HandRank.FOUR_OF_A_KIND;
	}

}
