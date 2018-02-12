package kcomp.poker.commonpoker.models.round;

import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.game.Table;

public interface Street {

	public void dealCards(Table table, Deck deck);

	public boolean isOver(Table table);

	public void setToReady(Table table);

}
