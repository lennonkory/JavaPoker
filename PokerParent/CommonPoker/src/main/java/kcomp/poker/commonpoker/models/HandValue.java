package kcomp.poker.commonpoker.models;

import java.util.List;

import kcomp.poker.commonpoker.enums.HandRank;

public class HandValue {

	private HandRank handRank;
	private List<Card> mainCards;
	private List<Card> kickers;

	public HandRank getHandRank() {
		return handRank;
	}

	public void setHandRank(HandRank handRank) {
		this.handRank = handRank;
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
