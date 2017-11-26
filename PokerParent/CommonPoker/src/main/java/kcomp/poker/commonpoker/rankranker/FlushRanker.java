package kcomp.poker.commonpoker.rankranker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.HandValue;

public class FlushRanker implements HandRanker {

	@Override
	public HandValue getHandValue(Hand hand) throws HandRankException {

		HandValue handValue = new HandValue();

		Map<Suit, Integer> suits = hand.getSuits();

		Suit flush = null;

		for (Suit suit : suits.keySet()) {
			int value = suits.get(suit);
			if (value >= 5) {
				flush = suit;
				break;
			}
		}

		if (flush == null) {
			handValue.setHandRank(HandRank.HIGH_CARD);
			return handValue;
		}

		handValue.setHandRank(HandRank.FLUSH);

		setMains(flush, hand.getCards(), handValue);

		return handValue;
	}

	private void setMains(Suit suit, List<Card> cards, HandValue handValue) {

		Collections.sort(cards);

		List<Card> mains = new ArrayList<>();
		int count = 0;

		for (Card card : cards) {
			if (card.getSuit().equals(suit)) {
				mains.add(card);
				count++;
			}
			if (count == 5) {
				break;
			}
		}

		handValue.setMainCards(mains);

	}

}
