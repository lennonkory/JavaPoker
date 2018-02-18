package kcomp.poker.commonpoker.models.round;

import java.util.List;

import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Table;

public class RiverStreet implements Street {

	@Override
	public void dealCards(Table table, Deck deck) {

		Card river = deck.getNextCard();

		List<Player> players = table.getPlayersInHand();

		for (Player player : players) {
			player.getHand().addFaceUp(river);
		}

	}

	@Override
	public boolean isOver(Table table) {
		return StreetUtil.isOver(table);
	}

	@Override
	public void setToReady(Table table) {
		StreetUtil.setToReady(table);

	}

	@Override
	public void setCurrentPlayer(Table table) {
		table.setCurrentPlayer();
	}
}
