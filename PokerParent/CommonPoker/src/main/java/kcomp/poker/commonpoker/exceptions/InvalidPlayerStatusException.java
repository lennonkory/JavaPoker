package kcomp.poker.commonpoker.exceptions;

public class InvalidPlayerStatusException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPlayerStatusException(String message) {
		super(message);
	}

}
