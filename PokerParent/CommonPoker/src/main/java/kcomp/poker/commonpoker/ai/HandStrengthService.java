package kcomp.poker.commonpoker.ai;

import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Hand;

public interface HandStrengthService {

	int calculateHandStrength(Hand hand) throws HandRankException;

}
