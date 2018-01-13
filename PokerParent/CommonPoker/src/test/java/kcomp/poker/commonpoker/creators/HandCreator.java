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

	public static Hand createEmptyHand() {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		return hand;
	}

	public static Hand createHighCardHand() {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
		hand.addCard(new Card(Suit.CLUBS, Rank.EIGHT));
		hand.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
		hand.addCard(new Card(Suit.CLUBS, Rank.FOUR));

		return hand;
	}

	public static Map<Rank, Integer> getRankMapping() {
		return createHandMappings.createRanks();
	}

	public static Map<Suit, Integer> getSuitMapping() {
		return createHandMappings.createSuits();
	}

	public static Hand createPairHand() {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addFaceUp(new Card(Suit.CLUBS, Rank.EIGHT));
		hand.addFaceUp(new Card(Suit.HEARTS, Rank.JACK));
		hand.addFaceUp(new Card(Suit.CLUBS, Rank.KING));

		return hand;
	}

	public static Hand createThreeKindHand() {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addCard(new Card(Suit.SPADES, Rank.ACE));
		hand.addCard(new Card(Suit.HEARTS, Rank.EIGHT));
		hand.addCard(new Card(Suit.CLUBS, Rank.KING));

		return hand;
	}

	public static Hand createThreeKindHand(Rank kind, Rank kickerOne, Rank kickerTwo) {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		hand.addCard(new Card(Suit.CLUBS, kind));
		hand.addCard(new Card(Suit.DIAMONDS, kind));
		hand.addCard(new Card(Suit.SPADES, kind));
		hand.addCard(new Card(Suit.HEARTS, kickerOne));
		hand.addCard(new Card(Suit.CLUBS, kickerTwo));

		return hand;
	}

	public static Hand createFourKindHand() {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addCard(new Card(Suit.SPADES, Rank.ACE));
		hand.addCard(new Card(Suit.HEARTS, Rank.EIGHT));
		hand.addCard(new Card(Suit.HEARTS, Rank.ACE));

		return hand;
	}

	public static Hand createFourKindHand(Rank kind, Rank kicker) {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		hand.addCard(new Card(Suit.CLUBS, kind));
		hand.addCard(new Card(Suit.DIAMONDS, kind));
		hand.addCard(new Card(Suit.SPADES, kind));
		hand.addCard(new Card(Suit.HEARTS, kicker));
		hand.addCard(new Card(Suit.HEARTS, kind));

		return hand;
	}

	public static Hand createStraightHand() {
		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		hand.addCard(new Card(Suit.SPADES, Rank.EIGHT));
		hand.addCard(new Card(Suit.CLUBS, Rank.TEN));
		hand.addCard(new Card(Suit.CLUBS, Rank.SIX));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.NINE));
		hand.addCard(new Card(Suit.HEARTS, Rank.SEVEN));

		return hand;
	}

	public static Hand createStraightHandStartingAt(Rank rank, int numCards) {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		Rank[] ranks = Rank.values();

		boolean add = false;
		int added = 0;

		for (Rank rankToAdd : ranks) {
			if (rankToAdd.equals(rank)) {
				add = true;
			}

			if (add) {
				hand.addCard(new Card(Suit.CLUBS, rankToAdd));
				added++;
			}
			if (added == numCards) {
				break;
			}
		}

		if (rank.equals(Rank.FIVE) && added < numCards) {
			hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
		}

		return hand;
	}

	public static Hand createFlushHand(Suit suit) {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		hand.addCard(new Card(suit, Rank.TWO));
		hand.addCard(new Card(suit, Rank.TEN));
		hand.addCard(new Card(suit, Rank.ACE));
		hand.addCard(new Card(suit, Rank.NINE));
		hand.addCard(new Card(suit, Rank.SEVEN));

		return hand;
	}

	public static Hand createFullHouseHand(Rank three, Rank two) {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		Suit suit = Suit.CLUBS;

		hand.addCard(new Card(suit, three));
		hand.addCard(new Card(suit, three));
		hand.addCard(new Card(suit, three));
		hand.addCard(new Card(suit, two));
		hand.addCard(new Card(suit, two));

		return hand;
	}

	public static Hand createStraightFlushHand(Suit suit) {

		Hand hand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		hand.addCard(new Card(suit, Rank.SIX));
		hand.addCard(new Card(suit, Rank.TEN));
		hand.addCard(new Card(suit, Rank.EIGHT));
		hand.addCard(new Card(suit, Rank.NINE));
		hand.addCard(new Card(suit, Rank.SEVEN));

		return hand;
	}

}
