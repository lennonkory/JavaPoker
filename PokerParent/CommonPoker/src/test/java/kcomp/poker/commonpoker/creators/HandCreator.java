package kcomp.poker.commonpoker.creators;

import java.util.List;
import java.util.Map;

import kcomp.poker.commonpoker.enums.Rank;
import kcomp.poker.commonpoker.enums.Suit;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.utilities.CreateHandMappings;
import kcomp.poker.commonpoker.utilities.DefaultCreateHandMappings;

public class HandCreator {

	private static CreateHandMappings createHandMappings = new DefaultCreateHandMappings();

	public static Hand createTwoPairHand() {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addCard(new Card(Suit.CLUBS, Rank.EIGHT));
		hand.addCard(new Card(Suit.HEARTS, Rank.EIGHT));
		hand.addCard(new Card(Suit.CLUBS, Rank.KING));

		return hand;
	}

	public static Hand createTwoPairHand(Rank firstPair, Rank secondPair, Rank kicker) {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		hand.addCard(new Card(Suit.CLUBS, firstPair));
		hand.addCard(new Card(Suit.DIAMONDS, firstPair));
		hand.addCard(new Card(Suit.CLUBS, secondPair));
		hand.addCard(new Card(Suit.HEARTS, secondPair));
		hand.addCard(new Card(Suit.CLUBS, kicker));

		return hand;
	}

	public static Hand createHand(List<Card> cards) {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		for (Card card : cards) {
			hand.addCard(card);
		}

		return hand;
	}

	public static Hand createHighCardHand() {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
		hand.addCard(new Card(Suit.CLUBS, Rank.EIGHT));
		hand.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
		hand.addCard(new Card(Suit.CLUBS, Rank.THREE));

		return hand;
	}

	public static Map<Rank, Integer> getRankMapping() {
		return createHandMappings.createRanks();
	}

	public static Map<Suit, Integer> getSuitMapping() {
		return createHandMappings.createSuits();
	}
}
