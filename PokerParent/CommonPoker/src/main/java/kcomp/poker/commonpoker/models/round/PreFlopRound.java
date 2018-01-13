package kcomp.poker.commonpoker.models.round;

import java.util.List;

import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.exceptions.ChipException;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.RoundDetails;

public class PreFlopRound implements Round {

	int bigBlind = 0;

	@Override
	public void setup(RoundDetails roundDetails) throws ChipException {
		// TEST

		// Set Status
		setStatusOfPlayers(roundDetails.getPlayers());

		// Collect antes.
		getAntes(roundDetails.getPlayers(), roundDetails.getAnte());

		// Set and Collect Blinds
		setBlinds(roundDetails);

		// Set activePlayer
		Player active = findNextActivePlayerStartingAt(roundDetails.getPlayers(), bigBlind + 1);
		roundDetails.setActivePlayer(active);
		// Deal cards

	}

	private void setStatusOfPlayers(List<Player> players) {

		for (Player player : players) {
			if (!PlayerStatus.SITTING_OUT.equals(player.getPlayerStatus())) {
				player.setPlayerStatus(PlayerStatus.READY);
			}
		}

	}

	private void getAntes(List<Player> players, int ante) throws ChipException {
		for (Player player : players) {
			removeChips(player, ante);
		}
	}

	private void setBlinds(RoundDetails roundDetails) throws ChipException {

		List<Player> players = roundDetails.getPlayers();

		int index = players.indexOf(roundDetails.getDealer());
		int smallBlind = index + 1;

		Player small = findNextActivePlayerStartingAt(players, smallBlind);
		removeChips(small, roundDetails.getBlinds().getSmall());

		this.bigBlind = players.indexOf(small) + 1;

		Player big = findNextActivePlayerStartingAt(players, bigBlind);
		removeChips(big, roundDetails.getBlinds().getBig());

		this.bigBlind = players.indexOf(big);
	}

	private void removeChips(Player player, int numberOfChips) throws ChipException {
		int numChips = player.getChipCount();
		if (numberOfChips > numChips) {
			numberOfChips = numChips;
		}
		player.subtractChips(numberOfChips);
	}

	private Player findNextActivePlayerStartingAt(List<Player> players, int index) {
		Player player = null;
		while (true) {
			index = index % players.size();
			player = players.get(index);
			if (PlayerStatus.READY.equals(player.getPlayerStatus())) {
				break;
			}
			index++;
		}
		return player;
	}

}
