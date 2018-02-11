package kcomp.poker.commonpoker.models.game;

import java.util.Collection;
import java.util.List;

import kcomp.poker.commonpoker.models.Player;

public interface Table {

	public void initTable();

	public Player getNextPlayer();

	public Player getNextActivePlayerInRound();

	public Player getDealer();

	public Player setNextDealer();

	// Should this return seat number?
	public void addPLayer(Player player);

	public void addPLayer(Player player, int seatNumber);

	public Collection<Player> getAllPlayers();

	public List<Integer> getEmpySeats();

}
