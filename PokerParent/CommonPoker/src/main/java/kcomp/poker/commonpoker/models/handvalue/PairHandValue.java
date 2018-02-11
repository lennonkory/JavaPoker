package kcomp.poker.commonpoker.models.handvalue;

import kcomp.poker.commonpoker.comparehandValues.SimpleHandValueComparePoker;
import kcomp.poker.commonpoker.enums.HandRank;

public class PairHandValue extends HandValue {

	public PairHandValue() {
		super(HandRank.PAIR);
	}

	@Override
	public int compareTo(HandValue o) {

		if (o == null) {
			return 1;
		}

		HandRank oneRank = this.getHandRank();
		HandRank twoRank = o.getHandRank();

		if (oneRank.equals(twoRank)) {
			return SimpleHandValueComparePoker.threeOfAKindAndPair(this, o);
		}

		return oneRank.getRank() > twoRank.getRank() ? 1 : -1;

	}

}
