package kcomp.poker.commonpoker.creators;

import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.models.Player;

public class PlayerCreater {

	public static Player createPlayer(String userName, PlayerStatus playerStatus, int numberOfChips) {
		Player player = new Player();

		player.addChips(numberOfChips);
		player.setUserName(userName);
		player.setPlayerStatus(playerStatus);

		return player;
	}

}
