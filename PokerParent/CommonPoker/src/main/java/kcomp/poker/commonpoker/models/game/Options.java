package kcomp.poker.commonpoker.models.game;

import java.util.List;

import kcomp.poker.commonpoker.enums.BetType;
import kcomp.poker.commonpoker.models.BetSize;

/**
 * Returns the options a player can make for that hand. Also returns how much or
 * little a player can bet
 *
 */
public class Options {

	private List<BetType> betTypes;
	private BetSize betSizes;

	public Options(List<BetType> betTypes, BetSize betSizes) {
		this.betTypes = betTypes;
		this.betSizes = betSizes;
	}

	public List<BetType> getBetTypes() {
		return betTypes;
	}

	public void setBetTypes(List<BetType> betTypes) {
		this.betTypes = betTypes;
	}

	public BetSize getBetSizes() {
		return betSizes;
	}

	public void setBetSizes(BetSize betSizes) {
		this.betSizes = betSizes;
	}

}
