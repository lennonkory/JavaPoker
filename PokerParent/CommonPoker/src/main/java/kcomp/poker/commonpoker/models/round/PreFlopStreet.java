package kcomp.poker.commonpoker.models.round;

import kcomp.poker.commonpoker.factory.HandFactory;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Table;
import kcomp.poker.commonpoker.view.details.CardInfo;

public class PreFlopStreet implements Street {

	@Override
	public void dealCards(Table table, Deck deck) {

		Player dealer = table.getDealer();
		Player currentPlayer = null;

		do {
			currentPlayer = table.getAndSetNextPlayer();
			Hand hand = HandFactory.createHand();
			Card card = deck.getNextCard();
			hand.addFaceDown(card);
			updateListener(table, currentPlayer, card);
			currentPlayer.setHand(hand);

		} while (!currentPlayer.getUserName().equals(dealer.getUserName()));

		do {
			currentPlayer = table.getAndSetNextPlayer();
			Card card = deck.getNextCard();
			currentPlayer.getHand().addFaceDown(card);
			updateListener(table, currentPlayer, card);

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

	private void updateListener(Table table, Player player, Card card) {
		if (table.getTableGameListener() == null) {
			return;
		}
		CardInfo info = new CardInfo();
		info.setFaceUp(false);
		table.getTableGameListener().dealCard(table.getSeatNumberForPlayer(player), info);
	}

	@Override
	public void setCurrentPlayer(Table table) {

	}

}
