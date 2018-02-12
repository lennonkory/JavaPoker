package kcomp.poker.commonpoker.models.game;

import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.round.Round;
import kcomp.poker.commonpoker.models.round.RoundContainer;

public class PokerGame implements Game {

	RoundContainer roundContainer;
	Table table;

	@Override
	public void startGame() {
		Round round = roundContainer.selectRound();
		Player dealer = table.getDealer();

	}

	@Override
	public void executeCommand() {

	}

	@Override
	public void addPlayer(Player player) {
		// do more
		table.addPLayer(player);

	}

}
