package kcomp.poker.commonpoker.ai;

import java.util.List;

import kcomp.poker.commonpoker.exceptions.DeckException;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Hand;

public interface HandStrengthService {

	double calculateHandStrength(Hand hand, List<Card> board) throws HandRankException;

	HandPotential calculateHandPotential(Hand hand, List<Card> board) throws HandRankException, DeckException;

}
