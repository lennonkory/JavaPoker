package kcomp.poker.commonpoker.rankranker;

import java.util.Collections;
import java.util.List;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.handvalue.HandValue;
import kcomp.poker.commonpoker.models.handvalue.HighCardHandValue;

public class HighCardRanker implements HandRanker {

	@Override
	public HandValue getHandValue(Hand hand) throws HandRankException {
		HandValue handValue = new HighCardHandValue();

		List<Card> cards = hand.getCards();

		Collections.sort(cards);
		Collections.reverse(cards);

		cards = cards.subList(0, cards.size() > 5 ? 5 : cards.size());

		handValue.setMainCards(cards);

		return handValue;
	}

	@Override
	public HandRank getHandRank() {
		return HandRank.HIGH_CARD;
	}

}
