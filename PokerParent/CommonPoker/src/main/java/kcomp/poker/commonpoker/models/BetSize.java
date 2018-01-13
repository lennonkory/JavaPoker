package kcomp.poker.commonpoker.models;

/**
 * The Class BetSize.
 * 
 * Can be used for blinds and limits.
 * 
 */
public class BetSize {

	/** The small. */
	private int small;

	/** The big. */
	private int big;

	public BetSize(int small, int big) {
		super();
		this.small = small;
		this.big = big;
	}

	public int getSmall() {
		return small;
	}

	public void setSmall(int small) {
		this.small = small;
	}

	public int getBig() {
		return big;
	}

	public void setBig(int big) {
		this.big = big;
	}

}
