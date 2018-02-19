package kcomp.poker.commonpoker.models.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.handvalue.HandValue;
import kcomp.poker.commonpoker.rankranker.RankHand;

public class WinnerServiceRegular implements WinnerService {

	RankHand rankHand;

	public WinnerServiceRegular(RankHand rankHand) {
		this.rankHand = rankHand;
	}

	@Override
	public Collection<Player> determineWinners(Table table) throws HandRankException {

		List<Player> winners = new ArrayList<>();

		HandValue bestHand = null;

		for (Player player : table.getPlayersInHand()) {

			Hand hand = player.getHand();
			HandValue handValue = rankHand.rankHand(hand);
			int compare = handValue.compareTo(bestHand);

			if (compare > 0) {
				winners.clear();
				winners.add(player);
				bestHand = handValue;
			} else if (compare == 0) {
				winners.add(player);
				bestHand = handValue;
			}

		}

		return winners;
	}

	@Override
	public void payWinners(Collection<Player> winners, Pot pot) {
		for (Player player : winners) {
			MoneyService.addToPlayerChips(player, pot);
		}

	}

}
