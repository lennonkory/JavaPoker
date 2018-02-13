package kcomp.poker.commonpoker.models.round;

import java.util.List;

import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Table;

public class FlopStreet implements Street {

	@Override
	public void dealCards(Table table, Deck deck) {

		Player dealer = table.getDealer();
		Player currentPlayer = null;

		List<Card> flop = deck.getNextCards(3);

		do {
			currentPlayer = table.getAndSetNextPlayer();
			currentPlayer.getHand().addFaceUp(flop);

		} while (!currentPlayer.getUserName().equals(dealer.getUserName()));

	}

	@Override
	public boolean isOver(Table table) {
		return StreetUtil.isOver(table);
	}

	@Override
	public void setToReady(Table table) {
		StreetUtil.setToReady(table);

	}

}
