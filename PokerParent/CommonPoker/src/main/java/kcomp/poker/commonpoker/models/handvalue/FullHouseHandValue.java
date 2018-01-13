package kcomp.poker.commonpoker.models.handvalue;

import kcomp.poker.commonpoker.comparehandValues.SimpleHandValueComparePoker;

public class FullHouseHandValue extends HandValue {

	@Override
	public int compareTo(HandValue o) {
		return SimpleHandValueComparePoker.fourFull(this, o);

	}

}
