package kcomp.poker.commonpoker.ai;

import java.util.ArrayList;
import java.util.List;

import kcomp.poker.commonpoker.comparehandValues.SimpleHandValueComparePoker;
import kcomp.poker.commonpoker.exceptions.DeckException;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.factory.HandFactory;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.StandardDeck;
import kcomp.poker.commonpoker.models.handvalue.HandValue;
import kcomp.poker.commonpoker.rankranker.RankHand;

public class HandStrengthServiceIMPL implements HandStrengthService {

	private RankHand rankHand;

	public HandStrengthServiceIMPL(RankHand rankHand) {
		this.rankHand = rankHand;
	}

	@Override
	public double calculateHandStrength(Hand hand) throws HandRankException {

		Deck deck = new StandardDeck();
		deck.removeCards(hand.getCards());

		int ahead = 0;
		int tied = 0;
		int behind = 0;

		HandValue handValue = null;

		handValue = rankHand.rankHand(hand);

		List<Hand> opHands = null;

		try {
			opHands = createOpponentHands(deck, hand.getFaceUp());
		} catch (DeckException e) {
			e.printStackTrace();
		}

		for (Hand opHand : opHands) {
			HandValue opValue = rankHand.rankHand(opHand);
			int value = SimpleHandValueComparePoker.compare(handValue, opValue);

			System.out.println("HandValue: " + handValue.getHandRank() + " OpValue: " + opValue.getHandRank()
					+ " Value: " + value);

			if (value == 1) {
				ahead++;
			} else if (value == -1) {
				behind++;
			} else {
				tied++;
			}

		}

		double handstrength = (ahead + tied / 2.0) / (ahead + tied + behind);

		System.out.println("Hand Strength: " + handstrength);

		return handstrength;
	}

	private List<Hand> createOpponentHands(Deck deck, List<Card> faceUp) throws DeckException {
		List<Hand> hands = new ArrayList<>();

		while (deck.numberOfCardsRemaining() > 1) {

			Hand opHand = HandFactory.createHand();

			opHand.addFaceDown(deck.getNextCard());
			opHand.addFaceDown(deck.getNextCard());

			for (Card card : faceUp) {
				opHand.addFaceUp(card);
			}

			hands.add(opHand);
		}

		return hands;
	}

}
