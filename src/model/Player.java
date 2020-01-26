package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private String name;
	protected ArrayList<Card> hand; 

	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<Card>();
	}
	
	public int chooseCard() {
		int topCardIndex = hand.size() - 1 ; 
			System.out.println(hand.get(topCardIndex).getHeaderNames()[0] + " : " + hand.get(topCardIndex).getName());
			System.out.println("1. " + hand.get(topCardIndex).getHeaderNames()[1] + " : " + hand.get(topCardIndex).getSize());
			System.out.println("2. " + hand.get(topCardIndex).getHeaderNames()[2] + " : " + hand.get(topCardIndex).getRarity());
			System.out.println("3. " + hand.get(topCardIndex).getHeaderNames()[3] + " : " + hand.get(topCardIndex).getTemperament());
			System.out.println("4. " + hand.get(topCardIndex).getHeaderNames()[4] + " : " + hand.get(topCardIndex).getIntelligence());
			System.out.println("5. " + hand.get(topCardIndex).getHeaderNames()[5] + " : " + hand.get(topCardIndex).getCuteness() + "\n");
			System.out.println("Please select a stat to play (1-5)");
			Scanner s = new Scanner(System.in);
			int nominatedStat = s.nextInt();
			return nominatedStat;
		}

	public void addHand(Card card) {
		this.hand.add(card);
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}
}