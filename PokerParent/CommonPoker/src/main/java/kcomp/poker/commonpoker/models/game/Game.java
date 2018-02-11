package kcomp.poker.commonpoker.models.game;

import kcomp.poker.commonpoker.models.Player;

public interface Game {

	/*
	 * Are there enough players to play? Has a Round Container, get round ->
	 * Round Container, select Dealer, start round -> round
	 */
	public void startGame();

	public void executeCommand();

	public void addPlayer(Player player);

}
