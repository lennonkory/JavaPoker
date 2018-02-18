package kcomp.poker.commonpoker.models.game;

import kcomp.poker.commonpoker.enums.BetType;
import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.exceptions.GameAlreadyStartedException;
import kcomp.poker.commonpoker.exceptions.NotEnoughPlayersException;
import kcomp.poker.commonpoker.exceptions.NotPlayersTurnException;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.models.round.Round;
import kcomp.poker.commonpoker.models.round.RoundContainer;

public class PokerGame implements Game {

	private RoundContainer roundContainer;
	private Table table;
	private Rules rules;
	private Deck deck;
	private Pot pot;

	private boolean isStarted = false;
	private Round currentRound;

	public PokerGame(RoundContainer roundContainer, Table table, Rules rules, Deck deck, Pot pot) {
		this.roundContainer = roundContainer;
		this.table = table;
		this.rules = rules;
		this.deck = deck;
		this.pot = pot;

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

	}

	@Override
	public void executeCommand(Command command) {

		Player player = command.getPlayer();

		if (!player.equals(table.getCurrentPlayer())) {
			throw new NotPlayersTurnException();
		}

		int ammount = command.getBetAmount();
		BetType betType = command.getBetType();
		PlayerStatus status = null;

		switch (betType) {
		case CALL:
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
			MoneyService.collectMoneyAndAddToPot(player, ammount, pot);
			status = PlayerStatus.RAISED;
			player.setPlayerStatus(status);
			table.setOtherPlayersStatusInRoundTo(player, status);

			break;
		default:
			break;
		}

		updatePlayer(player, status);
		currentRound.updateRound(table, rules, deck);

	}

	@Override
	public void addPlayer(Player player) {
		table.addPLayer(player);
	}

	@Override
	public void removePlayer(Player player) {
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

}
