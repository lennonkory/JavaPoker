package kcomp.poker.commonpoker.models.round;

import kcomp.poker.commonpoker.exceptions.ChipException;
import kcomp.poker.commonpoker.models.RoundDetails;

public interface Round {

	void setup(RoundDetails roundDetails) throws ChipException;

}
