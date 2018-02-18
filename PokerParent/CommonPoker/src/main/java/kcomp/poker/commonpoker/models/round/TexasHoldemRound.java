package kcomp.poker.commonpoker.models.round;

import java.util.Collection;
import java.util.Queue;

import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.MoneyService;
import kcomp.poker.commonpoker.models.game.Pot;
import kcomp.poker.commonpoker.models.game.Rules;
import kcomp.poker.commonpoker.models.game.Table;

public class TexasHoldemRound implements Round {

	private Queue<Street> streets;
	private Street currentStreet;

	public TexasHoldemRound(Queue<Street> streets) {
		this.streets = streets;
	}

	@Override
	public void start(Table table, Rules rules, Deck deck, Pot pot) {

		setToReady(table);
		collectAntees(table, rules.getAntees(), pot);
		currentStreet = streets.poll();
		currentStreet.dealCards(table, deck);
		collectBlinds(table, pot, rules);
		table.getAndSetNextPlayer();

	}

	private void setToReady(Table table) {
		Collection<Player> players = table.getAllPlayers();

		for (Player player : players) {
			PlayerStatus status = player.getPlayerStatus();
			if (!status.equals(PlayerStatus.SITTING_OUT)) {
				player.setPlayerStatus(PlayerStatus.READY);
				updatePlayerStatus(table, player, PlayerStatus.READY);
			}
		}
	}

	private void collectAntees(Table table, int antee, Pot pot) {

		MoneyService.collectMoneyAndAddToPotFromPlayers(table, antee, pot);

	}

	private void collectBlinds(Table table, Pot pot, Rules rules) {
		Player small = table.getAndSetNextPlayer();
		MoneyService.collectMoneyAndAddToPot(small, rules.getBlindsOrOpens().getSmall(), pot);
		Player large = table.getAndSetNextPlayer();
		MoneyService.collectMoneyAndAddToPot(large, rules.getBlindsOrOpens().getBig(), pot);
	}

	@Override
	public boolean isOver() {
		if (streets.isEmpty() && currentStreet == null) {
			return true;
		}
		return false;
	}

	@Override
	public void updateRound(Table table, Rules rules, Deck deck) {

		if (currentStreet == null) {
			return;
		}

		if (currentStreet.isOver(table)) {
			currentStreet = streets.poll();
			if (currentStreet != null) {
				currentStreet.setToReady(table);
				currentStreet.dealCards(table, deck);
				currentStreet.setCurrentPlayer(table);
			}
		} else {
			table.getAndSetNextPlayer();
		}

	}

	private void updatePlayerStatus(Table table, Player player, PlayerStatus playerStatus) {
		if (player.getPlayerGameListener() == null) {
			return;
		}

		player.getPlayerGameListener().updateStatus(playerStatus);

		if (table.getTableGameListener() == null) {
			return;
		}

		table.getTableGameListener().updatePlayerStatus(player, playerStatus);
	}

}
