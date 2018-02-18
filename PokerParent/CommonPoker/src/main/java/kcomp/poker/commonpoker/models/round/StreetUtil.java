package kcomp.poker.commonpoker.models.round;

import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Table;

/**
 * Controls all common street actions
 *
 */
public class StreetUtil {

	private StreetUtil() {
	}

	public static boolean isOver(Table table) {
		for (Player player : table.getAllPlayers()) {
			PlayerStatus status = player.getPlayerStatus();
			if (!isFinished(status)) {
				return false;
			}
		}
		return true;
	}

	public static void setToReady(Table table) {
		for (Player player : table.getAllPlayers()) {
			PlayerStatus status = player.getPlayerStatus();
			if (!status.equals(PlayerStatus.FOLDED) && !status.equals(PlayerStatus.SITTING_OUT)) {
				player.setPlayerStatus(PlayerStatus.READY);
			}
		}

	}

	private static boolean isFinished(PlayerStatus status) {

		if (status.equals(PlayerStatus.READY)) {
			return false;
		}
		if (status.equals(PlayerStatus.BEEN_RAISED)) {
			return false;
		}

		return true;
	}

}
