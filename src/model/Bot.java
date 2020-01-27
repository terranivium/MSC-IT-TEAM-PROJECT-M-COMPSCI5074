package model;

import java.util.Collections;
import java.util.Map.Entry;

public class Bot extends Player {

	public Bot(String name) {
		super(name);
	}

	public int chooseCard() {
		System.out.println("This is where the bot logic will go");
		int topCardIndex = (hand.size() - 1);
		int bestStat = Collections.max(hand.get(topCardIndex).stats.values());
		System.out.println(bestStat);
		int chosenStat = 0;

		for (Integer i : hand.get(topCardIndex).stats.keySet()) {
			if (hand.get(topCardIndex).stats.get(i) == bestStat) {
				chosenStat = i;
			}
		}
		System.out.println(chosenStat);
		return chosenStat;
	}
}
