package kcomp.poker.commonpoker.models.handvalue;

import kcomp.poker.commonpoker.comparehandValues.SimpleHandValueComparePoker;

public class FlushHandValue extends HandValue {

	@Override
	public int compareTo(HandValue o) {
		return SimpleHandValueComparePoker.straights(this, o);
	}

}
