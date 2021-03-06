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
import kcomp.poker.commonpoker.models.handvalue.StraightHandValue;

public class StraightRanker implements HandRanker {

	@Override
	public HandValue getHandValue(Hand hand) throws HandRankException {

		Map<Rank, Integer> ranks = hand.getRanks();

		Rank startRank = null;

		int count = 0;

		for (Rank rank : Rank.values()) {

			int value = ranks.get(rank);

			if (value == 0) {
				count = 0;
				startRank = null;
			} else {
				count++;
			}

			if (count == 1) {
				startRank = rank;
			}

			if (count == 5) {
				break;
			}

		}

		// Check for the wheel
		if (Rank.FIVE.equals(startRank)) {
			if (ranks.get(Rank.ACE) > 0) {
				count++;
			}
		}

		if (count != 5) {
			HandValue handValue = new HighCardHandValue();

			return handValue;
		}

		HandValue handValue = new StraightHandValue();

		setMainCards(startRank, hand.getCards(), handValue);

		return handValue;

	}

	private void setMainCards(Rank start, List<Card> cards, HandValue handValue) throws HandRankException {
		List<Card> main = new ArrayList<>();

		Collections.sort(cards);
		Collections.reverse(cards);

		Card ace = null;

		boolean add = false;
		int count = 0;

		for (Card card : cards) {

			Rank rank = card.getRank();

			if (Rank.ACE.equals(rank)) {
				ace = card;
			}

			if (rank.equals(start)) {
				add = true;
			}
			if (add && !isCardInMain(rank, main)) {
				main.add(card);
				count++;
			}
			if (count == 5) {
				break;
			}

		}

		// Check for the wheel

		if (Rank.FIVE.equals(start) && ace != null) {
			if (main.add(ace)) {
				count++;
			}
		}

		if (main.size() != 5) {
			throw new HandRankException("Straight not found: " + start);
		}

		handValue.setMainCards(main);

	}

	private boolean isCardInMain(Rank rank, List<Card> cards) {
		for (Card card : cards) {
			Rank checkRank = card.getRank();
			if (checkRank.equals(rank)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public HandRank getHandRank() {
		return HandRank.STRAIGHT;
	}

}
