package kcomp.poker.commonpoker.ai;

import java.util.ArrayList;
import java.util.List;

import kcomp.poker.commonpoker.exceptions.DeckException;
import kcomp.poker.commonpoker.exceptions.HandRankException;
import kcomp.poker.commonpoker.factory.HandFactory;
import kcomp.poker.commonpoker.models.Card;
import kcomp.poker.commonpoker.models.Deck;
import kcomp.poker.commonpoker.models.Hand;
import kcomp.poker.commonpoker.models.StandardDeck;
import kcomp.poker.commonpoker.models.handvalue.HandValue;
import kcomp.poker.commonpoker.rankranker.RankHand;

public class HandStrengthServiceIMPL implements HandStrengthService {

	private RankHand rankHand;
	private int ahead = 0, tied = 1, behind = 2;

	public HandStrengthServiceIMPL(RankHand rankHand) {
		this.rankHand = rankHand;
	}

	@Override
	public double calculateHandStrength(Hand hand, List<Card> board) throws HandRankException {

		Deck deck = new StandardDeck();
		deck.removeCards(hand.getCards());

		int ahead = 0;
		int tied = 0;
		int behind = 0;

		HandValue handValue = null;

		handValue = rankHand.rankHand(hand);

		List<Hand> opHands = null;

		try {
			opHands = createOpponentHands(deck, board, hand.getCards());
		} catch (DeckException e) {
			e.printStackTrace();
		}

		for (Hand opHand : opHands) {
			HandValue opValue = rankHand.rankHand(opHand);
			int value = handValue.compareTo(opValue);

			if (value == 1) {
				ahead++;
			} else if (value == -1) {
				behind++;
			} else {
				tied++;
			}

		}

		double handstrength = (ahead + tied / 2.0) / (ahead + tied + behind);

		System.out.println("Ahead: " + ahead + " Tied: " + tied + " Behind: " + behind);

		System.out.println("Hand Strength: " + handstrength);

		return handstrength;
	}

	private List<Hand> createOpponentHands(Deck deck, List<Card> board, List<Card> mainCards) throws DeckException {

		List<Hand> hands = new ArrayList<>();
		List<Card> usedCards = new ArrayList<>();

		System.out.println("Size of deck: " + deck.numberOfCardsRemaining());

		while (deck.numberOfCardsRemaining() > 0) {

			Card cardOne = deck.getNextCard();

			Deck innerDeck = new StandardDeck();
			innerDeck.removeCards(mainCards);
			usedCards.add(cardOne);
			innerDeck.removeCards(usedCards);

			while (innerDeck.numberOfCardsRemaining() > 0) {

				Hand opHand = HandFactory.createHand();

				opHand.addFaceDown(cardOne);
				opHand.addFaceDown(innerDeck.getNextCard());

				for (Card card : board) {
					opHand.addFaceUp(card);
				}

				hands.add(opHand);
			}
		}

		System.out.println("Num op hand: " + hands.size());

		return hands;
	}

	private void hp(Deck turn, List<Card> usedCards, Hand hand, Hand opHand, int index, int[][] HP) throws Exception {

		while (turn.numberOfCardsRemaining() > 0) {

			Card turnCard = turn.getNextCard();

			usedCards.add(turnCard);

			Deck river = new StandardDeck();
			river.removeCards(hand.getCards());
			river.removeCards(opHand.getCards());
			river.removeCards(usedCards);

			while (river.numberOfCardsRemaining() > 0) {

				Card riverCard = river.getNextCard();

				Hand newHand = HandFactory.createHand();
				newHand.addFaceUp(hand.getCards());
				newHand.addFaceUp(turnCard);
				newHand.addFaceUp(riverCard);

				Hand newOpHand = HandFactory.createHand();
				newOpHand.addFaceUp(opHand.getCards());
				newOpHand.addFaceUp(turnCard);
				newOpHand.addFaceUp(riverCard);

				HandValue inner = rankHand.rankHand(newHand);
				HandValue opInner = rankHand.rankHand(newOpHand);

				int innerValue = inner.compareTo(opInner);

				if (innerValue == 1) {
					HP[index][ahead] += 1;
				} else if (innerValue == 0) {
					HP[index][tied] += 1;
				} else {
					HP[index][behind] += 1;
				}
			}
		}

	}

	@Override
	public HandPotential calculateHandPotential(Hand hand, List<Card> board) throws Exception {

		int[][] HP = new int[3][3];
		int[] HPTotal = new int[3];
		int ahead = 0, tied = 1, behind = 2;

		Deck deck = new StandardDeck();
		deck.removeCards(hand.getCards());

		HandValue handValue = rankHand.rankHand(hand);

		List<Hand> opHands = null;

		try {
			opHands = createOpponentHands(deck, board, hand.getCards());
		} catch (DeckException e) {
			e.printStackTrace();
		}

		for (Hand opHand : opHands) {

			HandValue opValue = rankHand.rankHand(opHand);
			int value = handValue.compareTo(opValue);

			int index = -1;

			if (value == 1) {
				index = ahead;
			} else if (value == 0) {
				index = tied;
			} else {
				index = behind;
			}

			HPTotal[index] += 1;

			Deck turn = new StandardDeck();
			turn.removeCards(hand.getCards());
			turn.removeCards(opHand.getCards());

			List<Card> usedCards = new ArrayList<>();

			hp(turn, usedCards, hand, opHand, index, HP);

		}

		double Ppot = (HP[behind][ahead] + (HP[behind][tied] / 2.0) + (HP[tied][ahead] / 2.0))
				/ ((HP[behind][ahead] + HP[behind][tied] + HP[behind][behind])
						+ ((HP[tied][ahead] + HP[tied][tied] + HP[tied][behind])) / 2.0);

		double Npot = (HP[ahead][behind] + (HP[tied][behind] / 2.0) + (HP[ahead][tied] / 2.0))
				/ ((HP[ahead][ahead] + HP[ahead][tied] + HP[ahead][behind])
						+ ((HP[tied][ahead] + HP[tied][tied] + HP[tied][behind]) / 2.0));

		if (Double.isNaN(Ppot)) {
			Ppot = 0;
		}

		if (Double.isNaN(Npot)) {
			Npot = 0;
		}

		System.out.println("HPTotal: " + HPTotal[0] + " " + HPTotal[1] + " " + HPTotal[2]);
		System.out.println("Pos: " + Ppot);
		System.out.println("Neg: " + Npot);

		printHP(HP);

		return new HandPotential(Ppot, Npot);
	}

	private void printHP(int[][] HP) {
		System.out.println("\nPrint HP: ");
		for (int[] row : HP) {
			System.out.println(row[0] + " " + row[1] + " " + row[2]);
		}

		System.out.println("");
	}

}
