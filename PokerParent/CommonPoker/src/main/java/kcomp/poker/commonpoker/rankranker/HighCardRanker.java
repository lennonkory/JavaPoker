package kcomp.poker.commonpoker.rankranker;

import java.util.Collections;
import java.util.List;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.HandValue;

public class HighCardRanker implements HandRanker {

	@Override
	public HandValue getHandValue(Hand hand) throws HandRankException {
		HandValue handValue = new HandValue();

		List<Card> cards = hand.getCards();

		Collections.sort(cards);

		cards = cards.subList(0, 5);

		handValue.setHandRank(HandRank.HIGH_CARD);

		handValue.setMainCards(cards);

		return handValue;
	}

}
