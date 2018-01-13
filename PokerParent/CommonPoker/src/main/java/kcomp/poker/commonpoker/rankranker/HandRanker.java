package kcomp.poker.commonpoker.rankranker;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.HandValue;

public interface HandRanker {

	/**
	 * Return the value of a hand. Contains: HandRank, MainCards, Kickers
	 *
	 * @param hand
	 *            the hand
	 * @return the hand value
	 * @throws HandRankException
	 */
	HandValue getHandValue(Hand hand) throws HandRankException;

	HandRank getHandRank();

	int compareHandValues(HandValue one, HandValue two);

}
