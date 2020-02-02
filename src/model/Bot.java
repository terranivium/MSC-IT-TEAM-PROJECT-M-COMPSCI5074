package model;

import java.util.Collections;

public class Bot extends Player {

	public Bot(String name) {
		super(name);
	}

	public int chooseCard() {
		System.out.println("Im " + super.name + " and it is my turn." );
		int topCardIndex = (this.getHand().size() - 1);
		int bestStat = Collections.max(hand.get(topCardIndex).getStats().values());
		System.out.println("My best stat is: " +bestStat);
		int chosenStatKey = 0;

		for (Integer i : this.getHand().get(topCardIndex).stats.keySet()) {
			if (hand.get(topCardIndex).getStats().get(i) == bestStat) {
				chosenStatKey = i;
			}
		}
		System.out.println("This is my chosen stat key number: " + chosenStatKey) ;
		return chosenStatKey;
	}
}
