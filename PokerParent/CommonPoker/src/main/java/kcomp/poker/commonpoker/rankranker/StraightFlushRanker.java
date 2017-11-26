package kcomp.poker.commonpoker.rankranker;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.HandValue;
import kcomp.poker.commonpoker.utilities.CreateHandMappings;
import kcomp.poker.commonpoker.utilities.DefaultCreateHandMappings;

public class StraightFlushRanker implements HandRanker {

	CreateHandMappings mappings = new DefaultCreateHandMappings();

	@Override
	public HandValue getHandValue(Hand hand) throws HandRankException {

		HandValue handValue;

		HandRanker flushRanker = new FlushRanker();

		handValue = flushRanker.getHandValue(hand);

		if (HandRank.HIGH_CARD.equals(handValue.getHandRank())) {
			return handValue;
		}

		Hand newHand = new Hand(mappings.createRanks(), mappings.createSuits());

		Suit flush = handValue.getMainCards().get(0).getSuit();

		for (Card card : hand.getCards()) {
			if (card.getSuit().equals(flush)) {
				newHand.addCard(card);
			}
		}

		HandRanker straightRanker = new StraightRanker();

		handValue = straightRanker.getHandValue(newHand);

		if (HandRank.HIGH_CARD.equals(handValue.getHandRank())) {
			return handValue;
		}

		handValue.setHandRank(HandRank.STRAIGHT_FLUSH);

		return handValue;

	}

}
