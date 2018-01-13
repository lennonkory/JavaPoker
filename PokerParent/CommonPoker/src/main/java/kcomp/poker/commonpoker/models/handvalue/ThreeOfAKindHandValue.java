package kcomp.poker.commonpoker.models.handvalue;

import kcomp.poker.commonpoker.comparehandValues.SimpleHandValueComparePoker;
import kcomp.poker.commonpoker.enums.HandRank;

public class ThreeOfAKindHandValue extends HandValue {

	public ThreeOfAKindHandValue() {
		super(HandRank.THREE_OF_A_KIND);
	}

	@Override
	public int compareTo(HandValue o) {

		HandRank oneRank = this.getHandRank();
		HandRank twoRank = o.getHandRank();

		if (oneRank.equals(twoRank)) {
			return SimpleHandValueComparePoker.threeOfAKindAndPair(this, o);
		}

		return oneRank.getRank() > twoRank.getRank() ? 1 : -1;
	}

}
