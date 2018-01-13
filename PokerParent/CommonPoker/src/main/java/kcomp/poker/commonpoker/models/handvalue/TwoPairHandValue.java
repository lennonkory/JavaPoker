package kcomp.poker.commonpoker.models.handvalue;

import kcomp.poker.commonpoker.comparehandValues.SimpleHandValueComparePoker;

public class TwoPairHandValue extends HandValue {

	@Override
	public int compareTo(HandValue o) {
		return SimpleHandValueComparePoker.twoPair(this, o);
	}

}
