package model;

import java.util.ArrayList;

public class Player { // Player attributes
	private String name;
	private ArrayList<Card> hand;
	private Card topCard;
	private int roundsWon; // personal stat for tracking number of rounds won

	public Player(String name) { // Constructor
		this.name = name;
		this.hand = new ArrayList<Card>();
		this.topCard = null;
		this.roundsWon = 0;
	}

	// Getter and Setter Methods

	public void setName(String name) {
		this.name = name;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public void setTopCard(Card topCard) {
		this.topCard = topCard;
	}

	public void incrementRoundsWon() {
		this.roundsWon++;
	}

	public int chooseCard() { // redundant code , method is never called
		return 0;
	}

	public void addHand(Card card) {
		this.hand.add(card);
	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public String getName() {
		return this.name;
	}

	public Card getTopCard() { // returns the top card in a Players hand arraylists
		int topCardIndex = this.hand.size() - 1;
		this.topCard = this.hand.get(topCardIndex);
		return topCard;
	}

	public int getTopCardIndex() {
		int topCardIndex = this.hand.size() - 1;
		return topCardIndex;
	}

	public int getRoundsWon() {
		return this.roundsWon;
	}
}