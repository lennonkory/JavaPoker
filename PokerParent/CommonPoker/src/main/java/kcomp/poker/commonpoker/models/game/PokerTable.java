package kcomp.poker.commonpoker.models.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.listeners.TableGameListener;
import kcomp.poker.commonpoker.models.Player;

public class PokerTable implements Table {

	private List<Seat> seats;
	private int numPlayers;
	private Player currentPlayer;
	private Player dealer;
	private TableGameListener tableGameListener;

	private int tableSize;

	public PokerTable(int size) {
		seats = new ArrayList<>();
		this.tableSize = size;
		this.numPlayers = 0;
	}

	@Override
	public void initTable() {
		for (int i = 0; i < this.tableSize; i++) {
			seats.add(new Seat(i));
		}
	}

	@Override
	public Player getAndSetNextPlayer() {
		int currentDealerLocation = findPlayerLocation(currentPlayer);
		currentPlayer = findPlayer(currentDealerLocation, PlayerStatus.SITTING_OUT, PlayerStatus.FOLDED);
		return currentPlayer;
	}

	@Override
	public void setCurrentPlayerAsDealer() {
		currentPlayer = dealer;
	}

	@Override
	public Player getNextActivePlayerInRound() {
		int currentDealerLocation = findPlayerLocation(currentPlayer);
		return findPlayer(currentDealerLocation, PlayerStatus.SITTING_OUT, PlayerStatus.FOLDED);

	}

	@Override
	public void setPlayersStatusInRoundTo(PlayerStatus playerStatus) {
		for (Seat seat : seats) {
			Player player = seat.getPlayer();

			if (player != null) {
				if (isPlayerInStatus(player.getPlayerStatus(), PlayerStatus.CALLED, PlayerStatus.RAISED,
						PlayerStatus.READY)) {
					player.setPlayerStatus(playerStatus);
				}

			}
		}
	}

	@Override
	public void setOtherPlayersStatusInRoundTo(Player currentPlayer, PlayerStatus playerStatus) {
		for (Seat seat : seats) {

			Player player = seat.getPlayer();

			if (player != null && !player.getUserName().equals(currentPlayer.getUserName())) {
				if (isPlayerInStatus(player.getPlayerStatus(), PlayerStatus.CALLED, PlayerStatus.READY,
						PlayerStatus.CHECKED)) {
					player.setPlayerStatus(playerStatus);
				}

			}
		}
	}

	@Override
	public Player getDealer() {
		return dealer;
	}

	@Override
	public Player getAndSetNextDealer() {

		// Two players are needed to play the game.
		if (numPlayers < 2) {
			return null;
		}

		int currentDealerLocation = findPlayerLocation(dealer);
		dealer = findPlayer(currentDealerLocation, PlayerStatus.SITTING_OUT);
		currentPlayer = dealer;
		return dealer;

	}

	private Player findPlayer(int currentLocation, PlayerStatus... playerStatus) {
		currentLocation += 1;
		int count = 0;

		// Don't count the current dealer
		while (count < tableSize - 1) {
			Player player = seats.get(currentLocation % tableSize).getPlayer();
			if (player != null) {

				if (!isPlayerInStatus(player.getPlayerStatus(), playerStatus)) {
					return player;
				}
			}
			currentLocation++;
			count++;
		}

		return null;

	}

	private int findPlayerLocation(Player player) {

		for (Seat seat : seats) {
			if (!seat.isEmpty()) {
				if (seat.getPlayer().getUserName().equals(player.getUserName())) {
					return seat.location;
				}
			}
		}

		return -1;

	}

	@Override
	public void addPLayer(Player player) {

		if (numPlayers == 0) {
			dealer = player;
			currentPlayer = player;
		}

		int seatNum = 0;

		for (Seat seat : seats) {
			if (seat.isEmpty()) {
				seat.setPlayer(player);
				numPlayers++;
				return;
			}
			seatNum++;
			if (seatNum > tableSize) {
				throw new RuntimeException("No empty seats");
			}
		}
	}

	@Override
	public void addPLayer(Player player, int seatNumber) {

		if (numPlayers == 0) {
			dealer = player;
			currentPlayer = player;
		}

		if (seats.get(seatNumber).isEmpty()) {
			numPlayers++;
			seats.get(seatNumber).setPlayer(player);
		} else {
			throw new RuntimeException("Can't add player at seat " + seatNumber);
		}
	}

	@Override
	public Collection<Player> getAllPlayers() {

		List<Player> players = new ArrayList<>();

		for (Seat seat : seats) {
			if (!seat.isEmpty()) {
				players.add(seat.getPlayer());
			}
		}

		return players;
	}

	@Override
	public List<Integer> getEmpySeats() {
		List<Integer> empty = new ArrayList<>();

		for (int i = 0; i < this.tableSize; i++) {
			if (seats.get(i).isEmpty()) {
				empty.add(i);
			}
		}

		return empty;
	}

	@Override
	public void removePlayer(Player player) {
		for (Seat seat : seats) {
			if (seat.getPlayer().getUserName().equals(player.getUserName())) {
				seat.setPlayer(null);
				numPlayers--;
				return;
			}
		}
	}

	private boolean isPlayerInStatus(PlayerStatus playerStatus, PlayerStatus... statuss) {

		for (PlayerStatus status : statuss) {
			if (status.equals(playerStatus)) {
				return true;
			}
		}

		return false;
	}

	private class Seat {

		Player player;
		private int location;

		Seat(int location) {
			player = null;
			this.location = location;
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

	@Override
	public Player getPlayerAtSeat(int seatNumber) {
		return seats.get(seatNumber).getPlayer();
	}

	@Override
	public void setTableGameListener(TableGameListener tableGameListener) {
		this.tableGameListener = tableGameListener;
	}

	@Override
	public TableGameListener getTableGameListener() {
		return tableGameListener;
	}

	@Override
	public int getSeatNumberForPlayer(Player player) {
		Stream<Seat> seat = seats.stream().filter(s -> s.getPlayer().getUserName().equals(player.getUserName()));
		return seat.findFirst().get().location;
	}

	@Override
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public List<Player> getPlayersInHand() {

		List<Player> inHand = new ArrayList<>();
		for (Seat seat : seats) {
			Player player = seat.getPlayer();
			if (player != null) {
				if (!isPlayerInStatus(seat.getPlayer().getPlayerStatus(), PlayerStatus.FOLDED,
						PlayerStatus.SITTING_OUT)) {
					inHand.add(player);
				}
			}
		}

		return inHand;
	}

	@Override
	public void setCurrentPlayer() {
		int currentDealerLocation = findPlayerLocation(dealer);
		currentPlayer = findPlayer(currentDealerLocation, PlayerStatus.SITTING_OUT, PlayerStatus.FOLDED);
	}

}
