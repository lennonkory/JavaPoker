package kcomp.poker.commonpoker.models.handvalue;

import kcomp.poker.commonpoker.comparehandValues.SimpleHandValueComparePoker;
import kcomp.poker.commonpoker.enums.HandRank;

public class HighCardHandValue extends HandValue {

	public HighCardHandValue() {
		super(HandRank.HIGH_CARD);
	}

	@Override
	public int compareTo(HandValue o) {

		HandRank oneRank = this.getHandRank();
		HandRank twoRank = o.getHandRank();

		if (oneRank.equals(twoRank)) {
			return SimpleHandValueComparePoker.straights(this, o);
		}

		return oneRank.getRank() > twoRank.getRank() ? 1 : -1;
	}

}
