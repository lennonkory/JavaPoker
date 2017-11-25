package kcomp.poker.commonpoker.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.models.Card;

public class DefaultCreateHandMappings implements CreateHandMappings {

	@Override
	public Map<Rank, Integer> createRanks() {

		Map<Rank, Integer> ranks = new HashMap<>();

		for (Rank rank : Rank.values()) {
			ranks.put(rank, 0);
		}

		return ranks;

	}

	@Override
	public Map<Suit, Integer> createSuits() {

		Map<Suit, Integer> suits = new HashMap<>();

		for (Suit suit : Suit.values()) {
			suits.put(suit, 0);
		}

		return suits;

	}

	@Override
	public Map<Rank, Integer> createRanks(List<Card> cards) {

		Map<Rank, Integer> ranks = createRanks();

		for (Card card : cards) {
			Rank rank = card.getRank();
			ranks.put(rank, ranks.get(rank) + 1);
		}

		return ranks;
	}

	@Override
	public Map<Suit, Integer> createSuits(List<Card> cards) {

		Map<Suit, Integer> suits = createSuits();

		for (Card card : cards) {
			Suit suit = card.getSuit();
			suits.put(suit, suits.get(suit) + 1);
		}

		return suits;
	}

}
