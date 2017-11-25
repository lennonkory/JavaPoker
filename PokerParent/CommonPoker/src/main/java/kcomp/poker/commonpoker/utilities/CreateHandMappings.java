package kcomp.poker.commonpoker.utilities;

import java.util.List;
import java.util.Map;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.models.Card;

public interface CreateHandMappings {

	Map<Rank, Integer> createRanks();

	Map<Suit, Integer> createSuits();

	Map<Rank, Integer> createRanks(List<Card> cards);

	Map<Suit, Integer> createSuits(List<Card> cards);

}
