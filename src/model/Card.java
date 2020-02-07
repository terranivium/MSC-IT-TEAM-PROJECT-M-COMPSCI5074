package model;

import java.util.HashMap;

public class Card {
	private String name;
	private int size;
	private int rarity;
	private int temperament;
	private int intelligence;
	private int cuteness;
	private HashMap<Integer, Integer> stats;

	public Card(String name, String size, String rarity, String temperament, String intelligence, String cuteness,
			String[] headerNames) { // Constructor
		this.name = name;
		this.size = Integer.parseInt(size);
		this.rarity = Integer.parseInt(rarity);
		this.temperament = Integer.parseInt(temperament);
		this.intelligence = Integer.parseInt(intelligence);
		this.cuteness = Integer.parseInt(cuteness);

		this.stats = new HashMap<Integer, Integer>(); // hash map used by bot players to navigate the stats and return a key value like the human 
		this.stats.put(1, this.size);
		this.stats.put(2, this.rarity);
		this.stats.put(3, this.temperament);
		this.stats.put(4, this.intelligence);
		this.stats.put(5, this.cuteness);
	}

	// Getter and Setter Methods

	public String getName() {
		return this.name;
	}

	public int getSize() {
		return this.size;
	}

	public int getRarity() {
		return this.rarity;
	}

	public int getTemperament() {
		return this.temperament;
	}

	public int getIntelligence() {
		return this.intelligence;
	}

	public int getCuteness() {
		return this.cuteness;
	}

	public HashMap<Integer, Integer> getStats() {
		return this.stats;
	}
}