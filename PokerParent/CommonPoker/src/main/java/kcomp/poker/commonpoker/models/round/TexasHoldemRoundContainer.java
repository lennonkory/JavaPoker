package kcomp.poker.commonpoker.models.round;

public class TexasHoldemRoundContainer implements RoundContainer {

	private Round round;

	public TexasHoldemRoundContainer(Round round) {
		this.round = round;
	}

	@Override
	public Round selectRound() {
		return round;
	}

}
