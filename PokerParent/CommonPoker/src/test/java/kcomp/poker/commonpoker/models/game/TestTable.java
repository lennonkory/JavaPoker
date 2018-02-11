package kcomp.poker.commonpoker.models.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

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

}
