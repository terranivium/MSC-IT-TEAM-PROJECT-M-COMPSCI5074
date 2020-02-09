package model;

import java.util.Collections;

public class Bot extends Player { // Constructor

	public Bot(String name) {
		super(name);
	}

	public int chooseCard() { // Method called by AI Players to determine their choice of stat
		int topCardIndex = (this.getHand().size() - 1);
		int bestStat = Collections.max(getHand().get(topCardIndex).getStats().values()); // scans the Bot object's top card
																							// stats to see what one has
																							// the highest value
		int chosenStatKey = 0;

		for (Integer i : this.getHand().get(topCardIndex).getStats().keySet()) {
			if (getHand().get(topCardIndex).getStats().get(i) == bestStat) { // returns the value of the key (1-5) that
																				// matches the highest value found
				chosenStatKey = i;
			}
		}
		return chosenStatKey; // returns selected key value to the model
	}
}
