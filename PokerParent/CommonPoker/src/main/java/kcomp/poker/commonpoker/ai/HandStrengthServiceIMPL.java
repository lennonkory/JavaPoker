package kcomp.poker.commonpoker.ai;

import java.util.ArrayList;
import java.util.List;

import kcomp.poker.commonpoker.exceptions.DeckException;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.HandValue;
import kcomp.poker.commonpoker.models.StandardDeck;
import kcomp.poker.commonpoker.rankranker.RankHand;
import kcomp.poker.commonpoker.utilities.CreateHandMappings;
import kcomp.poker.commonpoker.utilities.DefaultCreateHandMappings;

public class HandStrengthServiceIMPL implements HandStrengthService {

	private static CreateHandMappings createHandMappings = new DefaultCreateHandMappings();

	private RankHand rankHand;

	public HandStrengthServiceIMPL(RankHand rankHand) {
		this.rankHand = rankHand;
	}

	@Override
	public int calculateHandStrength(Hand hand) throws HandRankException {

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
			int value = rankHand.compareHandValues(opValue, handValue);

			if (value == 1) {
				ahead++;
			} else if (value == -1) {
				behind++;
			} else {
				tied++;
			}

		}

		int handstrength = (ahead + tied / 2) / (ahead + tied + behind);

		return handstrength;
	}

	private List<Hand> createOpponentHands(Deck deck, List<Card> faceUp) throws DeckException {
		List<Hand> hands = new ArrayList<>();

		Hand opHand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		opHand.addFaceDown(deck.getNextCard());
		opHand.addFaceDown(deck.getNextCard());

		for (Card card : faceUp) {
			opHand.addFaceUp(card);
		}

		return hands;
	}

}
