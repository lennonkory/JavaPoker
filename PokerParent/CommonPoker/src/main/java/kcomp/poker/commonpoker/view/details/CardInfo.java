package kcomp.poker.commonpoker.view.details;

import kcomp.poker.commonpoker.models.Card;

public class CardInfo {

	private Card card;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public boolean isFaceUp() {
		return faceUp;
	}

	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}

	private boolean faceUp;

}
