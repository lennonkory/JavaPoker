package kcomp.poker.commonpoker.models.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kcomp.poker.commonpoker.models.Player;

public class PokerTable implements Table {

	private List<Seat> seats;
	private Player currentPlayer;
	private Player dealer;

	private int size;

	public PokerTable(int size) {
		seats = new ArrayList<>();
		this.size = size;
	}

	@Override
	public void initTable() {
		for (int i = 0; i < this.size; i++) {
			seats.add(new Seat());
		}
	}

	@Override
	public Player getNextPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getNextActivePlayerInRound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getDealer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player setNextDealer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPLayer(Player player) {

		int seatNum = 0;

		for (Seat seat : seats) {
			if (seat.isEmpty()) {
				seat.setPlayer(player);
				return;
			}
			seatNum++;
			if (seatNum > size) {
				throw new RuntimeException("No empty seats");
			}
		}
	}

	@Override
	public void addPLayer(Player player, int seatNumber) {
		if (seats.get(seatNumber).isEmpty()) {
			seats.get(seatNumber).setPlayer(player);
		} else {
			throw new RuntimeException("Can't add player at seat " + seatNumber);
		}
	}

	@Override
	public Collection<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getEmpySeats() {
		List<Integer> empty = new ArrayList<>();

		for (int i = 0; i < this.size; i++) {
			if (seats.get(i).isEmpty()) {
				empty.add(i);
			}
		}

		return empty;
	}

	private class Seat {
		Player player;

		Seat() {
			player = null;
		}

		public boolean isEmpty() {
			return player == null;
		}

		public Player getPlayer() {
			return player;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}

	}

}
