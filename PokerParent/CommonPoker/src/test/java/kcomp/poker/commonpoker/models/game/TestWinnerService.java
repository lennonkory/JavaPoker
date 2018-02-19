package kcomp.poker.commonpoker.models.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.creators.HandCreator;
import kcomp.poker.commonpoker.creators.PlayerCreater;
import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.factory.RankHandFactory;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.rankranker.FlushRanker;
import kcomp.poker.commonpoker.rankranker.FourOfAKindRanker;
import kcomp.poker.commonpoker.rankranker.FullHouseRanker;
import kcomp.poker.commonpoker.rankranker.PairRanker;
import kcomp.poker.commonpoker.rankranker.RankHand;
import kcomp.poker.commonpoker.rankranker.StraightFlushRanker;
import kcomp.poker.commonpoker.rankranker.StraightRanker;
import kcomp.poker.commonpoker.rankranker.ThreeOfAKindRanker;
import kcomp.poker.commonpoker.rankranker.TwoPairRanker;
import kcomp.poker.commonpoker.testarea.SidePot;
import kcomp.poker.commonpoker.testarea.PokerPot;
import kcomp.poker.commonpoker.testarea.Pot;

public class TestWinnerService {

	private WinnerServiceRegular winnerService;
	private Table table;
	private static final int SIZE = 9;

	@Before
	public void init() {

		table = new PokerTable(SIZE);
		table.initTable();
		RankHand rankHand = RankHandFactory.createPokerRankHand();
		winnerService = new WinnerServiceRegular(rankHand);
		winnerService.rankHand = setRankHand();

	}

	private RankHand setRankHand() {
		RankHand rankHand = new RankHand();
		rankHand.addHandRanker(new StraightFlushRanker());
		rankHand.addHandRanker(new FourOfAKindRanker());
		rankHand.addHandRanker(new FullHouseRanker());
		rankHand.addHandRanker(new FlushRanker());
		rankHand.addHandRanker(new StraightRanker());
		rankHand.addHandRanker(new ThreeOfAKindRanker());
		rankHand.addHandRanker(new TwoPairRanker());
		rankHand.addHandRanker(new PairRanker());
		return rankHand;
	}

	@Test
	public void testOneWinner_2Players() throws HandRankException {

		Player one = PlayerCreater.createPlayer("One", PlayerStatus.READY, 100);
		Player two = PlayerCreater.createPlayer("Two", PlayerStatus.READY, 100);

		Hand oneHand = HandCreator.createFourKindHand();
		Hand twoHand = HandCreator.createPairHand();

		one.setHand(oneHand);
		two.setHand(twoHand);

		table.addPLayer(one, 0);
		table.addPLayer(two, 1);

		List<Player> winners = (List<Player>) winnerService.determineWinners(table);

		assertEquals(1, winners.size());
		assertEquals(one.getUserName(), winners.get(0).getUserName());

	}

	@Test
	public void testOneWinner_3Players() throws HandRankException {

		Player one = PlayerCreater.createPlayer("One", PlayerStatus.READY, 100);
		Player two = PlayerCreater.createPlayer("Two", PlayerStatus.READY, 100);
		Player three = PlayerCreater.createPlayer("Three", PlayerStatus.READY, 100);

		Hand oneHand = HandCreator.createFourKindHand();
		Hand twoHand = HandCreator.createPairHand();
		Hand threeHand = HandCreator.createPairHand();

		one.setHand(oneHand);
		two.setHand(twoHand);
		three.setHand(threeHand);

		table.addPLayer(one, 0);
		table.addPLayer(two, 1);
		table.addPLayer(three, 2);

		List<Player> winners = (List<Player>) winnerService.determineWinners(table);

		assertEquals(1, winners.size());
		assertEquals(one.getUserName(), winners.get(0).getUserName());

	}

	@Test
	public void testTwoWinnes_3Players() throws HandRankException {

		Player one = PlayerCreater.createPlayer("One", PlayerStatus.READY, 100);
		Player two = PlayerCreater.createPlayer("Two", PlayerStatus.READY, 100);
		Player three = PlayerCreater.createPlayer("Three", PlayerStatus.READY, 100);

		Hand oneHand = HandCreator.createFourKindHand();
		Hand twoHand = HandCreator.createPairHand();
		Hand threeHand = HandCreator.createFourKindHand();

		one.setHand(oneHand);
		two.setHand(twoHand);
		three.setHand(threeHand);

		table.addPLayer(one, 0);
		table.addPLayer(two, 1);
		table.addPLayer(three, 2);

		List<Player> winners = (List<Player>) winnerService.determineWinners(table);

		assertEquals(2, winners.size());
		assertEquals(one.getUserName(), winners.get(0).getUserName());
		assertEquals(three.getUserName(), winners.get(1).getUserName());

	}

	@Test
	public void testTwoWinnes_3Players_addToChips() throws HandRankException {

		Player one = PlayerCreater.createPlayer("One", PlayerStatus.READY, 100);
		Player two = PlayerCreater.createPlayer("Two", PlayerStatus.READY, 100);

		List<Player> winners = new ArrayList<>();
		winners.add(one);
		winners.add(two);

		Pot pot = new PokerPot();

		pot.addCallToPot(one, 10);
		pot.addCallToPot(two, 10);

		pot.finalSidePot();
		List<SidePot> sidePots = pot.getSidePots();

		winnerService.payWinners(winners, sidePots.get(0));

		assertEquals(120, one.getChipCount());
		assertEquals(120, two.getChipCount());

	}

}
