package kcomp.poker.commonpoker.models.round;

import java.util.List;

import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Table;

public class FlopStreet implements Street {

	@Override
	public void dealCards(Table table, Deck deck) {

		List<Player> players = table.getPlayersInHand();

		List<Card> flop = deck.getNextCards(3);

		for (Player player : players) {
			player.getHand().addFaceUp(flop);
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
