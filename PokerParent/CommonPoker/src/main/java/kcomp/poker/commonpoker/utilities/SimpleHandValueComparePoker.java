package kcomp.poker.commonpoker.utilities;

import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.HandValue;

public class SimpleHandValueComparePoker {

	public static int fourFull(HandValue one, HandValue two) {

		Card oneMainCard = one.getMainCards().get(0);
		Card twoMainCard = two.getMainCards().get(0);

		int compare = oneMainCard.compareTo(twoMainCard);

		if (compare == 0) {
			Card oneKickerCard = one.getKickers().get(0);
			Card twoKickerCard = two.getKickers().get(0);
			return oneKickerCard.compareTo(twoKickerCard);
		}

		return compare;
	}

	public static int straights(HandValue one, HandValue two) {

		int size = Math.min(one.getMainCards().size(), two.getMainCards().size());

		for (int i = 0; i < size; i++) {
			Card oneCard = one.getMainCards().get(i);
			Card twoCard = two.getMainCards().get(i);
			int compare = oneCard.compareTo(twoCard);
			if (compare != 0) {
				return compare;
			}
		}

		return 0;

	}

	public static int threeOfAKindAndPair(HandValue one, HandValue two) {

		Card oneMainCard = one.getMainCards().get(0);
		Card twoMainCard = two.getMainCards().get(0);

		int compare = oneMainCard.compareTo(twoMainCard);

		if (compare == 0) {

			int size = Math.min(one.getKickers().size(), two.getKickers().size());

			for (int i = 0; i < size; i++) {
				Card oneKickerCard = one.getKickers().get(i);
				Card twoKickerCard = two.getKickers().get(i);
				int value = oneKickerCard.compareTo(twoKickerCard);
				if (value != 0) {
					return value;
				}
			}

		}

		return compare;

	}

	public static int twoPair(HandValue one, HandValue two) {

		Card oneMainCard = one.getMainCards().get(0);
		Card twoMainCard = two.getMainCards().get(0);

		int compare = oneMainCard.compareTo(twoMainCard);

		if (compare != 0) {
			return compare;
		}

		oneMainCard = one.getMainCards().get(2);
		twoMainCard = two.getMainCards().get(2);

		compare = oneMainCard.compareTo(twoMainCard);

		if (compare != 0) {
			return compare;
		}

		Card oneKickerCard = one.getKickers().get(0);
		Card twoKickerCard = two.getKickers().get(0);

		return oneKickerCard.compareTo(twoKickerCard);

	}

}
