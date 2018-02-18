package kcomp.poker.commonpoker.rules;

import java.util.ArrayList;
import java.util.List;

import kcomp.poker.commonpoker.enums.BetType;
import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.exceptions.InvalidPlayerStatusException;
import kcomp.poker.commonpoker.models.BetSize;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Options;

public class TexasRules implements Rules {

	private BetSize blinds;
	private BetSize limits;
	private int antee;

	public TexasRules(BetSize blinds, BetSize limits, int antee) {
		super();
		this.blinds = blinds;
		this.limits = limits;
		this.antee = antee;
	}

	@Override
	public int getAntees() {
		return antee;
	}

	@Override
	public BetSize getBlindsOrOpens() {
		return blinds;
	}

	@Override
	public BetSize getLimits() {
		return limits;
	}

	public int getAntee() {
		return antee;
	}

	@Override
	public Options getOptionForPlayer(Player player, BetSize currentBetSize) {

		PlayerStatus status = player.getPlayerStatus();

		List<BetType> betTypes = new ArrayList<>();
		betTypes.add(BetType.FOLD);

		BetSize betSize = new BetSize(Math.min(player.getChipCount(), currentBetSize.getSmall()), -1);

		switch (status) {
		case BEEN_RAISED:
			betTypes.add(BetType.CALL);
			betTypes.add(BetType.RAISE);
			break;
		case READY:
			betTypes.add(BetType.RAISE);
			betTypes.add(BetType.CHECK);
			break;
		default:
			throwInvalidPlayerStatusException(player.getUserName(), status);
			break;

		}
		return new Options(betTypes, betSize);
	}

	private void throwInvalidPlayerStatusException(String username, PlayerStatus status) {
		throw new InvalidPlayerStatusException("Player " + username + " should not have status: " + status);
	}

}
