package kcomp.poker.commonpoker.models.round;

import kcomp.poker.commonpoker.factory.HandFactory;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Table;

public class PreFlopStreet implements Street {

	@Override
	public void dealCards(Table table, Deck deck) {

		Player dealer = table.getDealer();
		Player currentPlayer = null;

		do {
			currentPlayer = table.getAndSetNextPlayer();
			Hand hand = HandFactory.createHand();
			hand.addFaceDown(deck.getNextCard());
			currentPlayer.setHand(hand);

		} while (!currentPlayer.getUserName().equals(dealer.getUserName()));

		do {
			currentPlayer = table.getAndSetNextPlayer();
			currentPlayer.getHand().addFaceDown(deck.getNextCard());

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
