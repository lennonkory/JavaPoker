package kcomp.poker.commonpoker.factory;

import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.utilities.CreateHandMappings;
import kcomp.poker.commonpoker.utilities.DefaultCreateHandMappings;

public class HandFactory {

	private static CreateHandMappings createHandMappings = new DefaultCreateHandMappings();

	public static Hand createHand() {
		return new Hand(createHandMappings.createRanks(), createHandMappings.createSuits());
	}

}
