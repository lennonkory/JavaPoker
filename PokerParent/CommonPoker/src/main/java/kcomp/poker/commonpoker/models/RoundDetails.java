package kcomp.poker.commonpoker.models;

import java.util.List;

import kcomp.poker.commonpoker.enums.BetType;

public class RoundDetails {

	private List<Player> players;
	private BetSize limits;
	private BetSize blinds;
	private int ante;
	private int pot;
	private int lastBet;
	private Deck deck;
	private Player dealer;
	private Player activePlayer;
	private BetType currentBetType;

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public BetSize getLimits() {
		return limits;
	}

	public void setLimits(BetSize limits) {
		this.limits = limits;
	}

	public BetSize getBlinds() {
		return blinds;
	}

	public void setBlinds(BetSize blinds) {
		this.blinds = blinds;
	}

	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}

	public int getLastBet() {
		return lastBet;
	}

	public void setLastBet(int lastBet) {
		this.lastBet = lastBet;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Player getDealer() {
		return dealer;
	}

	public void setDealer(Player dealer) {
		this.dealer = dealer;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public BetType getCurrentBetType() {
		return currentBetType;
	}

	public void setCurrentBetType(BetType currentBetType) {
		this.currentBetType = currentBetType;
	}

	public int getAnte() {
		return ante;
	}

	public void setAnte(int ante) {
		this.ante = ante;
	}

}
