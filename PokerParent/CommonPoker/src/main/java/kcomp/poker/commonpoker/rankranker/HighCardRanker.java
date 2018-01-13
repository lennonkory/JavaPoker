package kcomp.poker.commonpoker.rankranker;

import java.util.Collections;
import java.util.List;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.HandValue;
import kcomp.poker.commonpoker.utilities.SimpleHandValueComparePoker;

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

	@Override
	public HandRank getHandRank() {
		return HandRank.HIGH_CARD;
	}

	@Override
	public int compareHandValues(HandValue one, HandValue two) {
		return SimpleHandValueComparePoker.straights(one, two);

	}

}
