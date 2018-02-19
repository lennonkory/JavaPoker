package kcomp.poker.commonpoker.models.round;

import java.util.LinkedList;
import java.util.Queue;

public class TexasHoldemRoundContainer implements RoundContainer {

	@Override
	public Round selectRound() {
		Queue<Street> streets = getTexasStreets();

		return new TexasHoldemRound(streets);

	}

	private Queue<Street> getTexasStreets() {
		Queue<Street> streets = new LinkedList<>();
		streets.add(new PreFlopStreet());
		streets.add(new FlopStreet());
		streets.add(new TurnStreet());
		streets.add(new RiverStreet());

		return streets;
	}

}
