package model;

import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Card> hand; 

	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<Card>();
	}
	
	public void playCard() {
		
	}

	public void addHand(Card card) {
		this.hand.add(card);
	}

	public ArrayList<Card> getHand() {
		return hand;
	}
}