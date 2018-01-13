package kcomp.poker.commonpoker.models.handvalue;

import java.util.List;

import kcomp.poker.commonpoker.enums.HandRank;
import kcomp.poker.commonpoker.models.Card;

public abstract class HandValue implements Comparable<HandValue> {

	private HandRank handRank;
	private List<Card> mainCards;
	private List<Card> kickers;

	public HandValue() {
		this.handRank = HandRank.HIGH_CARD;
	}

	public HandValue(HandRank handRank) {
		this.handRank = handRank;
	}

	public HandRank getHandRank() {
		return handRank;
	}

	public List<Card> getMainCards() {
		return mainCards;
	}

	public void setMainCards(List<Card> mainCards) {
		this.mainCards = mainCards;
	}

	public List<Card> getKickers() {
		return kickers;
	}

	public void setKickers(List<Card> kickers) {
		this.kickers = kickers;
	}

}
