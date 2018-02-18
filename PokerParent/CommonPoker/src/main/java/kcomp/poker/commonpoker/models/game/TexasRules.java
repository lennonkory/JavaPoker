package kcomp.poker.commonpoker.models.game;

import kcomp.poker.commonpoker.models.BetSizes;

public class TexasRules implements Rules {

	private BetSizes blinds;
	private BetSizes limits;
	private int antee;

	public TexasRules(BetSizes blinds, BetSizes limits, int antee) {
		super();
		this.blinds = blinds;
		this.limits = limits;
		this.antee = antee;
	}

	@Override
	public int getAntees() {
		return antee;
	}

	@Override
	public BetSizes getBlindsOrOpens() {
		return blinds;
	}

	@Override
	public BetSizes getLimits() {
		return limits;
	}

	public int getAntee() {
		return antee;
	}

}
