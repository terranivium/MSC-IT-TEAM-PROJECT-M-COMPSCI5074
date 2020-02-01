package model;

import java.util.Collections;
import java.util.Map.Entry;

public class Bot extends Player {

	public Bot(String name) {
		super(name);
	}

	public int chooseCard() {
		System.out.println("Im " + super.name + " and it is my turn." );
		int topCardIndex = (hand.size() - 1);
		int bestStat = Collections.max(hand.get(topCardIndex).stats.values());
		System.out.println("My best stat is: " +bestStat);
		int chosenStatKey = 0;

		for (Integer i : hand.get(topCardIndex).stats.keySet()) {
			if (hand.get(topCardIndex).stats.get(i) == bestStat) {
				chosenStatKey = i;
			}
		}
		System.out.println("This is my chosen stat key number: " + chosenStatKey) ;
		return chosenStatKey;
	}
}
