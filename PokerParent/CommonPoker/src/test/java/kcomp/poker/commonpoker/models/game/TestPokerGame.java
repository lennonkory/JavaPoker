package kcomp.poker.commonpoker.models.game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import kcomp.poker.commonpoker.creators.PlayerCreater;
import kcomp.poker.commonpoker.enums.BetType;
import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.exceptions.GameAlreadyStartedException;
import kcomp.poker.commonpoker.exceptions.IncorrectBetSizeException;
import kcomp.poker.commonpoker.exceptions.NotEnoughPlayersException;
import kcomp.poker.commonpoker.exceptions.NotPlayersTurnException;
import kcomp.poker.commonpoker.factory.GameFactory;
import kcomp.poker.commonpoker.models.Player;

public class TestPokerGame {

	private Game game;
	private Player one;
	private Player two;
	private Player three;
	private Player four;

	@Before
	public void init() {

		game = GameFactory.createTexasHoldemGame();

	}

	@Test(expected = NotEnoughPlayersException.class)
	public void shouldThrowNotEnoughPlayersWhenTryToStartGameWithNoPlayers() {
		game.startGame();
	}

	@Test(expected = NotEnoughPlayersException.class)
	public void shouldThrowNotEnoughPlayersWhenTryToStartGameWithOnePlayer() {
		Player one = PlayerCreater.createPlayer("One");
		game.addPlayer(one);
		game.startGame();
	}

	@Test
	public void shouldNotThrowNotEnoughPlayersWhenTryToStartGameWithTwoPlayers() {
		Player one = PlayerCreater.createPlayer("One");
		Player two = PlayerCreater.createPlayer("Two");
		game.addPlayer(one);
		game.addPlayer(two);
		game.startGame();
	}

	@Test(expected = GameAlreadyStartedException.class)
	public void shouldThrowGameAlreadyStartedWhenTryingToStartGameTwice() {
		Player one = PlayerCreater.createPlayer("One");
		Player two = PlayerCreater.createPlayer("Two");
		game.addPlayer(one);
		game.addPlayer(two);
		game.startGame();
		game.startGame();
	}

	@Test
	public void itsDealersturnAfterStartOfGame() {

		Player one = PlayerCreater.createPlayer("One", PlayerStatus.READY, 100);
		Player two = PlayerCreater.createPlayer("Two", PlayerStatus.READY, 100);
		Player three = PlayerCreater.createPlayer("Three", PlayerStatus.READY, 100);

		game.addPlayer(one);
		game.addPlayer(two);
		game.addPlayer(three);

		game.startGame();

		Table table = game.getTable();

		Player current = table.getCurrentPlayer();

		assertEquals(one.getUserName(), current.getUserName());
		assertEquals(one.getChipCount(), 100);
		assertEquals(two.getChipCount(), 95);
		assertEquals(three.getChipCount(), 90);

	}

	@Test
	public void itsPlayer4sturnAfterStartOfGame4Players() {

		setUpGame();

		Table table = game.getTable();

		Player current = table.getCurrentPlayer();

		assertEquals(four.getUserName(), current.getUserName());
		assertEquals(one.getChipCount(), 100);
		assertEquals(two.getChipCount(), 95);
		assertEquals(three.getChipCount(), 90);

	}

	@Test
	public void playerCanCall() {

		setUpGame();

		Command command = new Command();
		command.setPlayer(four);
		command.setBetType(BetType.CALL);
		command.setBetAmount(10);

		game.executeCommand(command);

		Pot pot = game.getPot();

		assertEquals(pot.getPotSize(), 25);
		assertEquals(four.getPlayerStatus(), PlayerStatus.CALLED);

	}

	@Test
	public void playerCanRaise() {

		setUpGame();

		Command command = new Command();
		command.setPlayer(four);
		command.setBetType(BetType.RAISE);
		command.setBetAmount(20);

		game.executeCommand(command);

		Pot pot = game.getPot();

		assertEquals(pot.getPotSize(), 35);
		assertEquals(35, pot.getPlayerPotSize(four));
		assertEquals(four.getPlayerStatus(), PlayerStatus.RAISED);

	}

	@Test
	public void playerCanFold() {

		setUpGame();

		Command command = new Command();
		command.setPlayer(four);
		command.setBetType(BetType.FOLD);
		command.setBetAmount(10);

		game.executeCommand(command);

		Pot pot = game.getPot();

		assertEquals(pot.getPotSize(), 15);
		assertEquals(four.getPlayerStatus(), PlayerStatus.FOLDED);

	}

	@Test
	public void playerCanCheck() {

		setUpGame();

		Command command = createCommand(four, BetType.CALL, 10);
		game.executeCommand(command);

		command = createCommand(one, BetType.CALL, 10);
		game.executeCommand(command);

		command = createCommand(two, BetType.FOLD, 0);
		game.executeCommand(command);

		command = createCommand(three, BetType.CHECK, 0);
		game.executeCommand(command);

		Player currentPlayer = game.getTable().getCurrentPlayer();

		assertEquals(three, currentPlayer);

		Pot pot = game.getPot();

		assertEquals(35, pot.getPotSize());
		// Because the round moves to the next street player is set to ready
		assertEquals(three.getPlayerStatus(), PlayerStatus.READY);

	}

	@Test
	public void allPlayersCallGoToNextRound() {

		setUpGame();

		Command command = createCommand(four, BetType.CALL, 10);
		game.executeCommand(command);

		command = createCommand(one, BetType.CALL, 10);
		game.executeCommand(command);

		command = createCommand(two, BetType.CALL, 5);
		game.executeCommand(command);

		command = createCommand(three, BetType.CHECK, 0);
		game.executeCommand(command);
		Pot pot = game.getPot();

		assertEquals(pot.getPotSize(), 40);
		assertEquals(10, pot.getPlayerPotSize(three));
		assertEquals(four.getPlayerStatus(), PlayerStatus.READY);
		assertEquals(5, two.getHand().numberOfCardsInHand());

	}

	@Test
	public void threePlayersCall1FoldsGoToNextRound() {

		setUpGame();

		Command command = createCommand(four, BetType.CALL, 10);
		game.executeCommand(command);

		command = createCommand(one, BetType.FOLD, 0);
		game.executeCommand(command);

		command = createCommand(two, BetType.CALL, 5);
		game.executeCommand(command);

		command = createCommand(three, BetType.CHECK, 0);
		game.executeCommand(command);
		Pot pot = game.getPot();

		assertEquals(pot.getPotSize(), 30);
		assertEquals(pot.getPlayerPotSize(three), 10);
		assertEquals(four.getPlayerStatus(), PlayerStatus.READY);
		assertEquals(5, two.getHand().numberOfCardsInHand());
		assertEquals(2, one.getHand().numberOfCardsInHand());

	}

	@Test
	public void allPlayersCallGoToNextRoundItsPlayersTwoTurn() {

		setUpGame();

		Command command = createCommand(four, BetType.CALL, 10);
		game.executeCommand(command);

		command = createCommand(one, BetType.CALL, 10);
		game.executeCommand(command);

		command = createCommand(two, BetType.CALL, 5);
		game.executeCommand(command);

		command = createCommand(three, BetType.CHECK, 0);
		game.executeCommand(command);

		Player currentPlayer = game.getTable().getCurrentPlayer();

		assertEquals(two, currentPlayer);

	}

	@Test
	public void Player2foldersGoToNextRoundItsPlayersThreeTurn() {

		setUpGame();

		Command command = createCommand(four, BetType.CALL, 10);
		game.executeCommand(command);

		command = createCommand(one, BetType.CALL, 10);
		game.executeCommand(command);

		command = createCommand(two, BetType.FOLD, 0);
		game.executeCommand(command);

		command = createCommand(three, BetType.CHECK, 0);
		game.executeCommand(command);

		Player currentPlayer = game.getTable().getCurrentPlayer();

		assertEquals(three, currentPlayer);

	}

	@Test
	public void playerCanCallAfterRaise() {

		setUpGame();

		Command command = createCommand(four, BetType.CALL, 10);
		game.executeCommand(command);

		command = createCommand(one, BetType.RAISE, 20);
		game.executeCommand(command);

		command = createCommand(two, BetType.FOLD, 0);
		game.executeCommand(command);

		command = createCommand(three, BetType.FOLD, 0);
		game.executeCommand(command);

		Player currentPlayer = game.getTable().getCurrentPlayer();

		assertEquals(four, currentPlayer);

		command = createCommand(four, BetType.CALL, 10);
		game.executeCommand(command);

	}

	@Test
	public void aWinnerCanBeSelected() {

		setUpGame();

		// PreFlop
		Command command = createCommand(four, BetType.CALL, 10);
		game.executeCommand(command);

		command = createCommand(one, BetType.RAISE, 20);
		game.executeCommand(command);

		command = createCommand(two, BetType.FOLD, 0);
		game.executeCommand(command);

		command = createCommand(three, BetType.FOLD, 0);
		game.executeCommand(command);

		Player currentPlayer = game.getTable().getCurrentPlayer();

		assertEquals(four, currentPlayer);

		command = createCommand(four, BetType.CALL, 10);
		game.executeCommand(command);

		currentPlayer = game.getTable().getCurrentPlayer();

		assertEquals(four, currentPlayer);

		// Flop
		command = createCommand(four, BetType.CHECK, 0);
		game.executeCommand(command);

		command = createCommand(one, BetType.CHECK, 0);
		game.executeCommand(command);

		currentPlayer = game.getTable().getCurrentPlayer();

		assertEquals(four, currentPlayer);

		// Turn
		command = createCommand(four, BetType.CHECK, 0);
		game.executeCommand(command);

		command = createCommand(one, BetType.CHECK, 0);
		game.executeCommand(command);

		// River
		command = createCommand(four, BetType.CHECK, 0);
		game.executeCommand(command);

		command = createCommand(one, BetType.CHECK, 0);
		game.executeCommand(command);

		Pot pot = game.getPot();

		assertEquals(0, pot.getPotSize());

	}

	@Test(expected = IncorrectBetSizeException.class)
	public void playerCallsWrongAmountShouldExcept() {

		setUpGame();

		Command command = createCommand(four, BetType.CALL, 20);
		game.executeCommand(command);

	}

	@Test(expected = NotPlayersTurnException.class)
	public void onlyCurrentPlayerCanMakeCommand() {

		setUpGame();

		Command command = createCommand(two, BetType.CALL, 10);
		game.executeCommand(command);

	}

	private Command createCommand(Player player, BetType betType, int ammount) {
		Command command = new Command();
		command.setPlayer(player);
		command.setBetType(betType);
		command.setBetAmount(ammount);
		return command;
	}

	private void setUpGame() {

		one = PlayerCreater.createPlayer("One", PlayerStatus.READY, 100);
		two = PlayerCreater.createPlayer("Two", PlayerStatus.READY, 100);
		three = PlayerCreater.createPlayer("Three", PlayerStatus.READY, 100);
		four = PlayerCreater.createPlayer("Four", PlayerStatus.READY, 100);

		game.addPlayer(one);
		game.addPlayer(two);
		game.addPlayer(three);
		game.addPlayer(four);

		game.startGame();
	}

}
