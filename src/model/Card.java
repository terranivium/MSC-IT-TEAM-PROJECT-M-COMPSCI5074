package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Card{
	private String name;  
	private int size;
	private int rarity;
	private int temperament;
	private int intelligence;
	private int cuteness;
	private String[] headerNames;
	HashMap<Integer,Integer> stats;

	
	public Card(String name, String size, String rarity, String temperament, String intelligence, String cuteness, String[] headerNames){
		this.name = name;
		this.size = Integer.parseInt(size);
		this.rarity = Integer.parseInt(rarity);
		this.temperament = Integer.parseInt(temperament);
		this.intelligence = Integer.parseInt(intelligence);
		this.cuteness = Integer.parseInt(cuteness);
		this.headerNames =headerNames; 
		
		stats = new HashMap<Integer,Integer>();
		stats.put(1, this.size);
		stats.put(2, this.rarity);
		stats.put(3, this.temperament);
		stats.put(4, this.intelligence);
		stats.put(5, this.cuteness);
		
		
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

	public String[] getHeaderNames() {
		return headerNames;
	}
	
	public HashMap<Integer, Integer> getStats() {
		return stats;
	}

	
}