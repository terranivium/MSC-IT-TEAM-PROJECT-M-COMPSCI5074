package model;

public class Card{
	private String name;
	private int size;
	private int rarity;
	private int temperament;
	private int intelligence;
	private int cuteness;
	
	public Card(String name, int size, int rarity, int temperament, int intelligence, int cuteness){
		this.name = name;
		this.size = size;
		this.rarity = rarity;
		this.temperament = temperament;
		this.intelligence = intelligence;
		this.cuteness = cuteness;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public int getRarity() {
		return rarity;
	}

	public int getTemperament() {
		return temperament;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getCuteness() {
		return cuteness;
	}

	
}