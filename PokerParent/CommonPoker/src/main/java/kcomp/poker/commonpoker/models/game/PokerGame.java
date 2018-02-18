package kcomp.poker.commonpoker.models.game;

import kcomp.poker.commonpoker.enums.BetType;
import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.exceptions.GameAlreadyStartedException;
import kcomp.poker.commonpoker.exceptions.IncorrectBetSizeException;
import kcomp.poker.commonpoker.exceptions.InvalidCommandException;
import kcomp.poker.commonpoker.exceptions.NotEnoughPlayersException;
import kcomp.poker.commonpoker.exceptions.NotPlayersTurnException;
import kcomp.poker.commonpoker.models.BetSize;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.round.Round;
import kcomp.poker.commonpoker.models.round.RoundContainer;
import kcomp.poker.commonpoker.rules.Rules;

public class PokerGame implements Game {

	private RoundContainer roundContainer;
	private Table table;
	private Rules rules;
	private Deck deck;
	private Pot pot;

	private boolean isStarted = false;
	private Round currentRound;
	private BetSize currentBetSize;

	public PokerGame(RoundContainer roundContainer, Table table, Rules rules, Deck deck, Pot pot) {
		this.roundContainer = roundContainer;
		this.table = table;
		this.rules = rules;
		this.deck = deck;
		this.pot = pot;
		currentBetSize = new BetSize(0, 0);

	}

	@Override
	public void startGame() {

		if (isStarted) {
			throw new GameAlreadyStartedException();
		}

		if (table.getAllPlayers().size() < 2) {
			throw new NotEnoughPlayersException();
		}

		isStarted = true;

		currentRound = roundContainer.selectRound();

		currentRound.start(table, rules, deck, pot);

		currentBetSize = rules.getLimits();

	}

	@Override
	public void executeCommand(Command command) {

		Player player = command.getPlayer();

		if (!player.equals(table.getCurrentPlayer())) {
			throw new NotPlayersTurnException();
		}

		validateCommand(command);

		int ammount = command.getBetAmount();
		BetType betType = command.getBetType();
		PlayerStatus status = null;

		switch (betType) {
		case CALL:

			int ammountAdded = pot.getPlayerBetSizesForStreet(player).getSmall();

			if (ammount + ammountAdded != currentBetSize.getSmall()) {
				throw new IncorrectBetSizeException();
			}

			MoneyService.collectMoneyAndAddToPot(player, ammount, pot);
			player.setPlayerStatus(PlayerStatus.CALLED);
			status = PlayerStatus.CALLED;
			break;
		case CHECK:
			player.setPlayerStatus(PlayerStatus.CHECKED);
			status = PlayerStatus.CHECKED;
			break;
		case FOLD:
			player.setPlayerStatus(PlayerStatus.FOLDED);
			status = PlayerStatus.FOLDED;
			break;
		case NONE:
			break;
		case RAISE:

			if (ammount * 2 < currentBetSize.getSmall()) {
				throw new IncorrectBetSizeException();
			}

			currentBetSize.setSmall(ammount);

			MoneyService.collectMoneyAndAddToPot(player, ammount, pot);
			status = PlayerStatus.RAISED;
			player.setPlayerStatus(status);
			table.setOtherPlayersStatusInRoundTo(player, PlayerStatus.BEEN_RAISED);
			break;
		default:
			break;
		}

		updatePlayer(player, status);
		currentRound.updateRound(table, rules, deck);

		if (currentRound.isOver()) {
			roundIsOver();
		}

		updateNextPlayer();

	}

	private void validateCommand(Command command) {

		Player player = command.getPlayer();
		BetType betType = command.getBetType();

		switch (betType) {

		case CHECK:
			if (player.getPlayerStatus().equals(PlayerStatus.BEEN_RAISED)) {
				throw new InvalidCommandException("Can not Check if Raised");
			}

			if (currentBetSize.getSmall() != pot.getPlayerBetSizesForStreet(player).getSmall()) {
				throw new InvalidCommandException("Can not Check if Raised");
			}

		default:
			break;
		}

	}

	private void roundIsOver() {
		// Declare winner. Get next round.

		System.out.println("Round is over");

		currentRound = roundContainer.selectRound();

		pot = new PokerPot();
		deck.shuffle();

		for (Player player : table.getAllPlayers()) {
			player.setHand(null);
		}

	}

	@Override
	public void addPlayer(Player player) {
		pot.setPlayerBetSizesForStreet(player, new BetSize(0, 0));
		table.addPLayer(player);
	}

	@Override
	public void removePlayer(Player player) {
		pot.removePlayerBetSize(player);
		table.removePlayer(player);
	}

	@Override
	public Table getTable() {
		return table;
	}

	@Override
	public Rules getRules() {
		return rules;
	}

	@Override
	public Pot getPot() {
		return pot;
	}

	private void updatePlayer(Player player, PlayerStatus playerStatus) {
		if (player.getPlayerGameListener() == null) {
			return;
		}
		player.getPlayerGameListener().updateStatus(playerStatus);
	}

	private void updateNextPlayer() {
		Player player = table.getCurrentPlayer();

		if (player.getPlayerGameListener() == null) {
			return;
		}
		player.getPlayerGameListener().updateOptions(rules.getOptionForPlayer(player, currentBetSize));
	}

	@Override
	public Options getOptionsForPlayer(Player player) {

		if (!player.equals(table.getCurrentPlayer())) {
			throw new NotPlayersTurnException();
		}

		return null;
	}

}
