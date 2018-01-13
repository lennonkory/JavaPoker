package kcomp.poker.commonpoker.factory;

import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.utilities.CreateHandMappings;
import kcomp.poker.commonpoker.utilities.DefaultCreateHandMappings;

public class HandFactory {

	private static CreateHandMappings createHandMappings = new DefaultCreateHandMappings();

	public static Hand createHand() {
		return new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());
	}

	public static Hand cloneHand(Hand hand) {
		Hand newHand = new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());

		for (Card card : hand.getFaceDown()) {
			newHand.addFaceDown(card);
		}

		newHand.addFaceUp(hand.getFaceUp());

		return newHand;
	}

}
