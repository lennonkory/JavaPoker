package kcomp.poker.commonpoker.models.round;

import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Table;

public class TurnStreet implements Street {

	@Override
	public void dealCards(Table table, Deck deck) {
		Player dealer = table.getDealer();
		Player currentPlayer = null;

		Card turn = deck.getNextCard();

		do {
			currentPlayer = table.getAndSetNextPlayer();
			currentPlayer.getHand().addFaceUp(turn);

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
