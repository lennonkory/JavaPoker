package kcomp.poker.commonpoker.models.game;

import kcomp.poker.commonpoker.exceptions.GameAlreadyStartedException;
import kcomp.poker.commonpoker.exceptions.NotEnoughPlayersException;
import kcomp.poker.commonpoker.models.Player;
import kcomp.poker.commonpoker.rules.Rules;
import kcomp.poker.commonpoker.testarea.Pot;

public interface Game {

	/**
	 * Starts a game. This method should only be called once as long as the game
	 * has started.
	 * 
	 * @throws NotEnoughPlayersException
	 *             if Not enough players (2 most likely) are in the game
	 * @throws GameAlreadyStartedException
	 *             If game is already started
	 */
	public void startGame() throws NotEnoughPlayersException, GameAlreadyStartedException;

	public void executeCommand(Command command);

	public Options getOptionsForPlayer(Player player);

	public void addPlayer(Player player);

	public void removePlayer(Player player);

	public Table getTable();

	public Rules getRules();

	public Pot getPot();

}
