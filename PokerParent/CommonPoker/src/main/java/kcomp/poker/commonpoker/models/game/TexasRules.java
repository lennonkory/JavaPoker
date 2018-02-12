package kcomp.poker.commonpoker.models.game;

import kcomp.poker.commonpoker.models.BetSize;

public class TexasRules implements Rules {

	@Override
	public int getAntees() {
		return 10;
	}

	@Override
	public BetSize getBlindsOrOpens() {
		BetSize size = new BetSize(5, 10);
		return size;
	}

	@Override
	public BetSize getLimits() {
		// TODO Auto-generated method stub
		return null;
	}

}
