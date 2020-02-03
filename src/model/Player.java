	package model;

import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Card> hand; 
	private Card topCard;
	private int roundsWon;
	
	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<Card>();
		this.roundsWon = 0;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public void setTopCard(Card topCard) {
		this.topCard = topCard;
	}

	public void setRoundsWon(int roundsWon) {
		this.roundsWon = roundsWon;
	}
	
	public int chooseCard() {
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

	public Card getTopCard() {
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