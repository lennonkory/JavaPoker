package kcomp.poker.commonpoker.testarea;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.creators.PlayerCreater;
import kcomp.poker.commonpoker.models.Player;

public class TestTestPot {

	private PokerPot pot;

	@Before
	public void init() {
		pot = new PokerPot();
	}

	@Test
	public void playerCanCallOnce() {
		Player player = PlayerCreater.createPlayer("Player");
		pot.addCallToPot(player, 10);

		assertEquals(10, pot.getPlayerBetForStreet(player));
	}

	@Test
	public void playerCanCallTwice() {
		Player player = PlayerCreater.createPlayer("Player");
		pot.addCallToPot(player, 10);
		pot.addCallToPot(player, 20);

		assertEquals(30, pot.getPlayerBetForStreet(player));
		assertEquals(0, pot.getStreetBet());
	}

	@Test
	public void playerCanRaiseOnce() {
		Player player = PlayerCreater.createPlayer("Player");
		pot.addRaiseToPot(player, 10);

		assertEquals(10, pot.getPlayerBetForStreet(player));
	}

	@Test
	public void playerRaiseThenOtherCalls() {

		Player player1 = PlayerCreater.createPlayer("Player1");
		Player player2 = PlayerCreater.createPlayer("Player2");
		pot.addRaiseToPot(player1, 20);
		pot.addCallToPot(player2, 20);

		assertEquals(20, pot.getPlayerBetForStreet(player1));
		assertEquals(20, pot.getStreetBet());

	}

	@Test
	public void playerCallsIsRaiseCalls() {

		Player player1 = PlayerCreater.createPlayer("Player1");
		Player player2 = PlayerCreater.createPlayer("Player2");

		pot.addCallToPot(player1, 10);
		pot.addRaiseToPot(player2, 20);
		pot.addCallToPot(player1, 10);

		assertEquals(20, pot.getPlayerBetForStreet(player1));
		assertEquals(20, pot.getStreetBet());

	}

	@Test
	public void playerCallsIsRaiseRaises() {

		Player player1 = PlayerCreater.createPlayer("Player1");
		Player player2 = PlayerCreater.createPlayer("Player2");

		pot.addCallToPot(player1, 10);
		pot.addRaiseToPot(player2, 20);
		pot.addRaiseToPot(player1, 40);

		assertEquals(50, pot.getPlayerBetForStreet(player1));
		assertEquals(50, pot.getStreetBet());

	}

	@Test
	public void oneRaisesTwoCallsThreeRaisesOneCallsTwoCalls() {

		Player player1 = PlayerCreater.createPlayer("Player1");
		Player player2 = PlayerCreater.createPlayer("Player2");
		Player player3 = PlayerCreater.createPlayer("Player3");

		pot.addRaiseToPot(player1, 10);
		pot.addCallToPot(player2, 10);
		pot.addRaiseToPot(player3, 20);
		pot.addCallToPot(player1, 10);
		pot.addCallToPot(player2, 10);

		assertEquals(20, pot.getPlayerBetForStreet(player1));
		assertEquals(20, pot.getStreetBet());
		assertEquals(60, pot.getMainPotSize());

	}

	@Test
	public void oneRaisesTwoCallsThreeRaisesOneRaisesTwoCalls3calls() {

		Player player1 = PlayerCreater.createPlayer("Player1");
		Player player2 = PlayerCreater.createPlayer("Player2");
		Player player3 = PlayerCreater.createPlayer("Player3");

		pot.addRaiseToPot(player1, 10);
		pot.addCallToPot(player2, 10);
		pot.addRaiseToPot(player3, 20);
		pot.addRaiseToPot(player1, 40);
		pot.addCallToPot(player2, 40);
		pot.addCallToPot(player3, 30);

		assertEquals(50, pot.getPlayerBetForStreet(player1));
		assertEquals(50, pot.getStreetBet());
		assertEquals(150, pot.getMainPotSize());

	}

	@Test
	public void testSides3PlayersOneSidePot() {

		Player player1 = PlayerCreater.createPlayer("Player1");
		Player player2 = PlayerCreater.createPlayer("Player2");
		Player player3 = PlayerCreater.createPlayer("Player3");

		pot.addRaiseToPot(player1, 10);
		pot.addCallToPot(player2, 10);
		pot.addCallToPot(player3, 10);

		pot.finalSidePot();

		List<SidePot> sidePots = pot.getSidePots();

		assertEquals(1, sidePots.size());
		assertEquals(30, pot.getMainPotSize());

		int totalPot = sidePots.stream().map(s -> s.getAmount()).reduce(0, Integer::sum);

		assertEquals(30, totalPot);

	}

	@Test
	public void testSides4Players() {

		Player player1 = PlayerCreater.createPlayer("Player1");
		Player player2 = PlayerCreater.createPlayer("Player2");
		Player player3 = PlayerCreater.createPlayer("Player3");
		Player player4 = PlayerCreater.createPlayer("Player4");

		pot.addRaiseToPot(player4, 40);
		pot.addCallToPot(player3, 40);
		pot.addCallToPot(player2, 20);
		pot.addCallToPot(player1, 10);

		assertEquals(10, pot.getPlayerBetForStreet(player1));
		assertEquals(40, pot.getStreetBet());
		assertEquals(110, pot.getMainPotSize());

		pot.playerIsAllIn();
		pot.playerIsAllIn();
		pot.finalSidePot();

		List<SidePot> sidePots = pot.getSidePots();

		assertEquals(3, sidePots.size());
		assertEquals(110, pot.getMainPotSize());

		int totalPot = sidePots.stream().map(s -> s.getAmount()).reduce(0, Integer::sum);

		assertEquals(110, totalPot);

	}

}
