package kcomp.poker.commonpoker.rules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.creators.PlayerCreater;
import kcomp.poker.commonpoker.enums.BetType;
import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.models.BetSize;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.game.Options;

public class TestTexasRules {

	private TexasRules rules;

	private BetSize blinds = new BetSize(5, 10);
	private BetSize limits = new BetSize(10, -1);
	private int antee = 0;
	private Player player;
	private BetSize currentBetSize;

	@Before
	public void init() {
		rules = new TexasRules(blinds, limits, antee);
		player = PlayerCreater.createPlayer("Player", PlayerStatus.READY, 100);
		currentBetSize = new BetSize(0, -1);
	}

	@Test
	public void playerCanGetOptionsWhenReady() {

		Options options = rules.getOptionForPlayer(player, currentBetSize);

		List<BetType> betTypes = options.getBetTypes();

		assertEquals(3, betTypes.size());
		assertTrue(betTypeInList(betTypes, BetType.FOLD));
		assertTrue(betTypeInList(betTypes, BetType.RAISE));
		assertTrue(betTypeInList(betTypes, BetType.CHECK));

	}

	@Test
	public void playerCanGetOptionsWhenBeenRaised() {

		player.setPlayerStatus(PlayerStatus.BEEN_RAISED);

		Options options = rules.getOptionForPlayer(player, currentBetSize);

		List<BetType> betTypes = options.getBetTypes();

		assertEquals(3, betTypes.size());
		assertTrue(betTypeInList(betTypes, BetType.CALL));
		assertTrue(betTypeInList(betTypes, BetType.FOLD));
		assertTrue(betTypeInList(betTypes, BetType.RAISE));
		assertFalse(betTypeInList(betTypes, BetType.CHECK));

	}

	@Test
	public void playerSetCorrectBetSizesWhenReady() {

		currentBetSize.setSmall(10);

		Options options = rules.getOptionForPlayer(player, currentBetSize);

		BetSize betSize = options.getBetSizes();

		assertNotNull(betSize);
		assertEquals(currentBetSize.getSmall(), betSize.getSmall());

	}

	@Test
	public void playerSetCorrectBetSizesWhenBeenRaised() {

		currentBetSize.setSmall(20);
		player.setPlayerStatus(PlayerStatus.BEEN_RAISED);

		Options options = rules.getOptionForPlayer(player, currentBetSize);

		BetSize betSize = options.getBetSizes();

		assertNotNull(betSize);
		assertEquals(currentBetSize.getSmall(), betSize.getSmall());

	}

	@Test
	public void playerSetCorrectBetSizesWhenReadyButNotEnoughChips() {

		currentBetSize.setSmall(10);
		player.setChipCount(5);

		Options options = rules.getOptionForPlayer(player, currentBetSize);

		BetSize betSize = options.getBetSizes();

		assertNotNull(betSize);
		assertEquals(player.getChipCount(), betSize.getSmall());

	}

	@Test
	public void playerSetCorrectBetSizesWhenBeenRaisedButNotEnoughChips() {

		currentBetSize.setSmall(10);
		player.setChipCount(5);

		player.setPlayerStatus(PlayerStatus.BEEN_RAISED);

		Options options = rules.getOptionForPlayer(player, currentBetSize);

		BetSize betSize = options.getBetSizes();

		assertNotNull(betSize);
		assertEquals(player.getChipCount(), betSize.getSmall());

	}

	@Test
	public void betSizeBigShouldBeMinRaiseAmount() {

		currentBetSize.setSmall(10);

		player.setPlayerStatus(PlayerStatus.READY);

		Options options = rules.getOptionForPlayer(player, currentBetSize);

		BetSize betSize = options.getBetSizes();

		assertNotNull(betSize);
		assertEquals(currentBetSize.getSmall() * 2, betSize.getBig());

	}

	private boolean betTypeInList(List<BetType> betTypes, BetType betTypeToCheck) {

		for (BetType betType : betTypes) {
			if (betTypeToCheck.equals(betType)) {
				return true;
			}
		}

		return false;
	}

}
