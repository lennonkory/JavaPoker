package kcomp.poker.commonpoker.utilities;

import java.util.ArrayList;
import java.util.List;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.models.Card;

public class HandUtility {

	private static HandUtility handUtility;

	private HandUtility() {
	}

	public static HandUtility getHandUtility() {
		if (handUtility == null) {
			handUtility = new HandUtility();
		}
		return handUtility;
	}

	/**
	 * Gets the cards by rank.
	 *
	 * Note: This method assumes that
	 *
	 * @param the
	 *            rank to search by.
	 * @param the
	 *            cards to search
	 * @return the cards found that are of the rank. Returns an empty list if no
	 *         cards are found.
	 */
	public static List<Card> getCardsByRank(Rank rank, List<Card> cards) {

		List<Card> cardsFound = new ArrayList<>();

		for (Card card : cards) {
			Rank cardRank = card.getRank();
			if (cardRank.equals(rank)) {
				cardsFound.add(card);
			}
		}

		return cardsFound;

	}

}
