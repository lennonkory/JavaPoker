package kcomp.poker.commonpoker.models.round;

import java.util.Collection;

import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Pot;
import kcomp.poker.commonpoker.models.game.Table;

public class TexasHoldemRound implements Round {

	@Override
	public void start() {

	}

	private void collectAntees(Table table, int antee, Pot pot) {
		Collection<Player> players = table.getAllPlayers();

	}

	@Override
	public Player getActivePlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOver() {
		// TODO Auto-generated method stub
		return false;
	}

}
