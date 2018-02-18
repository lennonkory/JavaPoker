package kcomp.poker.commonpoker.models.game;

import java.util.Collection;
import java.util.List;

import kcomp.poker.commonpoker.enums.PlayerStatus;
import kcomp.poker.commonpoker.listeners.TableGameListener;
import kcomp.poker.commonpoker.models.Player;

public interface Table {

	public void initTable();

	public Player getAndSetNextPlayer();

	public Player getNextActivePlayerInRound();

	public void setCurrentPlayer();

	public void setPlayersStatusInRoundTo(PlayerStatus playerStatus);

	public Player getDealer();

	public Player getCurrentPlayer();

	public Player getAndSetNextDealer();

	public Player getPlayerAtSeat(int seatNumber);

	// Sets current player as dealer. This is for moving to next street
	public void setCurrentPlayerAsDealer();

	// Should this return seat number?
	public void addPLayer(Player player);

	public void addPLayer(Player player, int seatNumber);

	public void removePlayer(Player player);

	public Collection<Player> getAllPlayers();

	public List<Integer> getEmpySeats();

	public List<Player> getPlayersInHand();

	public void setTableGameListener(TableGameListener tableGameListener);

	public TableGameListener getTableGameListener();

	public int getSeatNumberForPlayer(Player player);

	void setOtherPlayersStatusInRoundTo(Player currentPlayer, PlayerStatus playerStatus);

}
