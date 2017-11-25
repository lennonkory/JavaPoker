package kcomp.poker.commonpoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.utilities.CreateHandMappings;
import kcomp.poker.commonpoker.utilities.DefaultCreateHandMappings;

public class CommonStart {

	public static void main(String[] args) {

		CreateHandMappings mappings = new DefaultCreateHandMappings();

		List<Card> cards = new ArrayList<>();

		cards.add(new Card(Suit.CLUBS, Rank.FOUR));
		cards.add(new Card(Suit.CLUBS, Rank.EIGHT));
		cards.add(new Card(Suit.SPADES, Rank.THREE));
		cards.add(new Card(Suit.DIAMONDS, Rank.KING));
		cards.add(new Card(Suit.HEARTS, Rank.EIGHT));

		Hand hand = new Hand(mappings.createRanks(cards), mappings.createSuits(cards));

		hand.printSuits();
		System.out.println();
		hand.printRanks();
		System.out.println();

		System.out.println(cards);
		Collections.sort(cards);
		System.out.println(cards);

	}

}
