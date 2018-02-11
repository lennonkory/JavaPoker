package kcomp.poker.commonpoker.models.game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.models.Player;

public class TestMoneyService {

	private Player player;
	private Pot pot;

	@Before
	public void init() {

		player = new Player();
		player.setUserName("TestPlayer");
		pot = new PokerPot();

	}

	@Test
	public void test_collectChipsWithMorePlayerChips() {

		player.setChipCount(20);
		MoneyService.collectMoneyAndAddToPot(player, 10, pot);

		assertEquals(10, pot.getPotSize());
		assertEquals(10, pot.getPlayerPotSize(player));
		assertEquals(10, player.getChipCount());

	}

	@Test
	public void test_collectChipsWithLessPlayerChips() {

		player.setChipCount(10);
		MoneyService.collectMoneyAndAddToPot(player, 20, pot);

		assertEquals(20, pot.getPotSize());
		assertEquals(10, pot.getPlayerPotSize(player));
		assertEquals(0, player.getChipCount());

	}

	@Test
	public void testSizePotsAllMoreChips() {

		Table table = new PokerTable(9);

	}

}
