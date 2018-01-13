package kcomp.poker.commonpoker.models.handvalue;

import kcomp.poker.commonpoker.comparehandValues.SimpleHandValueComparePoker;

public class ThreeOfAKingHandValue extends HandValue {

	@Override
	public int compareTo(HandValue o) {
		return SimpleHandValueComparePoker.threeOfAKindAndPair(this, o);
	}

}
