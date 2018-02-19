package kcomp.poker.commonpoker.testarea;

import java.util.List;

import kcomp.poker.commonpoker.models.Player;

public interface Pot {

	int getMainPotSize();

	List<SidePot> getSidePots();

	void addCallToPot(Player player, int amount);

	void addRaiseToPot(Player player, int amount);

	void setPotForNextStreet();

	int getPlayerBetForStreet(Player player);

	int getStreetBet();

	void playerFolded(Player player);

	void playerIsAllIn();

	void finalSidePot();

}
