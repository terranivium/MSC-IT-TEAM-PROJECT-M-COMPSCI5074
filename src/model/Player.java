package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private String name;
	protected ArrayList<Card> hand; 
	private int topCardIndex;
	private int activeStat;

	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<Card>();
	}
	
	public int chooseCard() {
		return activeStat;
		}

	public void setActiveStat(int activeStat) {
		this.activeStat = activeStat;
	}

	public void addHand(Card card) {
		this.hand.add(card);
		topCardIndex = this.hand.size() - 1;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}

	public int getTopCardIndex() {
		topCardIndex = this.hand.size() - 1;
		return topCardIndex;
	}
}