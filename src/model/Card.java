package model;

import java.util.HashMap;

public class Card {
	private String description;
	private int categoryOne;
	private int categoryTwo;
	private int categoryThree;
	private int categoryFour;
	private int categoryFive;
	HashMap<Integer, Integer> stats;

	public Card(String name, String categoryOne, String categoryTwo, String categoryThree, String categoryFour, String categoryFive,
			String[] headerNames) { // Constructor
		this.description = name;
		this.categoryOne = Integer.parseInt(categoryOne);
		this.categoryTwo = Integer.parseInt(categoryTwo);
		this.categoryThree = Integer.parseInt(categoryThree);
		this.categoryFour= Integer.parseInt(categoryFour);
		this.categoryFive = Integer.parseInt(categoryFive);

		this.stats = new HashMap<Integer, Integer>(); // hash map used to relate key inputs to selected stats
		this.stats.put(1, this.categoryOne);
		this.stats.put(2, this.categoryTwo);
		this.stats.put(3, this.categoryThree);
		this.stats.put(4, this.categoryFour);
		this.stats.put(5, this.categoryFive);
	}

	// Getter and Setter Methods

	public String getDescription() {
		return this.description;
	}

	public int getCategoryOne() {
		return this.categoryOne;
	}

	public int getCategoryTwo() {
		return this.categoryTwo;
	}

	public int getCategoryThree() {
		return this.categoryThree;
	}

	public int getCategoryFour() {
		return this.categoryFour;
	}

	public int getCategoryFive() {
		return this.categoryFive;
	}

	public HashMap<Integer, Integer> getStats() {
		return this.stats;
	}
}