package kcomp.poker.commonpoker.models.round;

import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.factory.HandFactory;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Table;

public class PreFlop implements Street {

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
		for (Player player : table.getAllPlayers()) {
			PlayerStatus status = player.getPlayerStatus();
			if (!isFinished(status)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void setToReady(Table table) {
		for (Player player : table.getAllPlayers()) {
			PlayerStatus status = player.getPlayerStatus();
			if (status.equals(PlayerStatus.FOLDED) || !status.equals(PlayerStatus.SITTING_OUT)) {
				player.setPlayerStatus(PlayerStatus.READY);
			}
		}

	}

	private boolean isFinished(PlayerStatus status) {

		if (status.equals(PlayerStatus.READY)) {
			return false;
		}
		if (status.equals(PlayerStatus.BEEN_RAISED)) {
			return true;
		}

		return true;
	}

}
