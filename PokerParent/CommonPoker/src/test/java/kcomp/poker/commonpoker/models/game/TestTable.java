package kcomp.poker.commonpoker.models.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.creators.PlayerCreater;
import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.models.Player;

public class TestTable {

	private Table table;
	private static final int SIZE = 9;

	@Before
	public void init() {
		table = new PokerTable(SIZE);
		table.initTable();
	}

	@Test
	public void testEmpyTableShouldReturnAllEmpySeats() {

		List<Integer> emptySeatLocations = table.getEmpySeats();

		assertEquals(SIZE, emptySeatLocations.size());

	}

	@Test
	public void testTableWith1PlayerShouldReturn8EmpySeats() {

		table.addPLayer(new Player());
		List<Integer> emptySeatLocations = table.getEmpySeats();

		assertEquals(SIZE - 1, emptySeatLocations.size());

	}

	@Test
	public void testAddPlayerToCertainSeat() {

		table.addPLayer(new Player(), 3);
		List<Integer> emptySeatLocations = table.getEmpySeats();

		assertEquals(SIZE - 1, emptySeatLocations.size());

		assertFalse(emptySeatLocations.contains(3));

	}

	@Test
	public void testGetNextDealerWithOnePlayerShouldReturnNull() {
		table.addPLayer(PlayerCreater.createPlayer("Dealer"), 3);
		Player player = table.getAndSetNextDealer();
		assertNull(player);
	}

	@Test
	public void testGetNextDealerWithTwoPlayersShouldReturnPlayerTwo() {
		table.addPLayer(PlayerCreater.createPlayer("One"), 3);
		table.addPLayer(PlayerCreater.createPlayer("Two"), 7);
		Player player = table.getAndSetNextDealer();
		assertEquals("Two", player.getUserName());
	}

	@Test
	public void testGetNextDealerWithTwoPlayersLoopedShouldReturnPlayerTwo() {
		table.addPLayer(PlayerCreater.createPlayer("One"), 3);
		table.addPLayer(PlayerCreater.createPlayer("Two"), 1);
		Player player = table.getAndSetNextDealer();
		assertEquals("Two", player.getUserName());
	}

	@Test
	public void testGetNextDealerWith5PlayersShouldReturnPlayerFour() {

		table.addPLayer(PlayerCreater.createPlayer("Three"), 3);
		table.addPLayer(PlayerCreater.createPlayer("Five"), 2);
		table.addPLayer(PlayerCreater.createPlayer("Four"), 6);
		table.addPLayer(PlayerCreater.createPlayer("One", PlayerStatus.SITTING_OUT, 10), 4);
		table.addPLayer(PlayerCreater.createPlayer("Two"), 1);

		Player player = table.getAndSetNextDealer();
		assertEquals("Four", player.getUserName());
	}

	@Test
	public void testGetNextPlayerWith5PlayersShouldReturnPlayerFour() {

		table.addPLayer(PlayerCreater.createPlayer("Three"), 3);
		table.addPLayer(PlayerCreater.createPlayer("Five"), 2);
		table.addPLayer(PlayerCreater.createPlayer("Four"), 6);
		table.addPLayer(PlayerCreater.createPlayer("One", PlayerStatus.SITTING_OUT, 10), 4);
		table.addPLayer(PlayerCreater.createPlayer("Two"), 1);

		Player player = table.getAndSetNextPlayer();
		assertEquals("Four", player.getUserName());
	}

}
