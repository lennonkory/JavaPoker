package kcomp.poker.commonpoker.models.handvalue;

import kcomp.poker.commonpoker.comparehandValues.SimpleHandValueComparePoker;
import kcomp.poker.commonpoker.enums.HandRank;

public class TwoPairHandValue extends HandValue {

	public TwoPairHandValue() {
		super(HandRank.TWO_PAIR);
	}

	@Override
	public int compareTo(HandValue o) {

		HandRank oneRank = this.getHandRank();
		HandRank twoRank = o.getHandRank();

		if (oneRank.equals(twoRank)) {
			return SimpleHandValueComparePoker.twoPair(this, o);
		}

		return oneRank.getRank() > twoRank.getRank() ? 1 : -1;
	}

}
