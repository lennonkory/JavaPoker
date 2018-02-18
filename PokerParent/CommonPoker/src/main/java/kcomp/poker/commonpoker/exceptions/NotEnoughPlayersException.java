package kcomp.poker.commonpoker.exceptions;

public class NotEnoughPlayersException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughPlayersException() {
		super("Not enough players to start game");
	}

}
