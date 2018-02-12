package kcomp.poker.commonpoker.models.round;

import java.util.Collection;
import java.util.List;

import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.MoneyService;
import kcomp.poker.commonpoker.models.game.Pot;
import kcomp.poker.commonpoker.models.game.Rules;
import kcomp.poker.commonpoker.models.game.Table;

public class TexasHoldemRound implements Round {

	private List<Street> streets;
	private Street currrentStreet;

	public TexasHoldemRound(List<Street> streets) {
		this.streets = streets;
	}

	@Override
	public void start(Table table, Rules rules, Deck deck, Pot pot) {

		setToReady(table);
		collectAntees(table, rules.getAntees(), pot);
		currrentStreet = streets.get(0);
		streets.remove(0);
		currrentStreet.dealCards(table, deck);
		collectBlinds(table, pot, rules);

	}

	private void setToReady(Table table) {
		Collection<Player> players = table.getAllPlayers();

		for (Player player : players) {
			PlayerStatus status = player.getPlayerStatus();
			if (!status.equals(PlayerStatus.SITTING_OUT)) {
				player.setPlayerStatus(PlayerStatus.READY);
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
	public Player getActivePlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Player> getWinners(Table table) {
		// TODO Auto-generated method stub
		return null;
	}

}
