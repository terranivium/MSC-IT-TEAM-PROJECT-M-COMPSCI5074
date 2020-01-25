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
	HashMap<String, String> hmap;
	
	public Card(String name, String size, String rarity, String temperament, String intelligence, String cuteness, String[] headerNames){
		this.name = name;
		this.size = Integer.parseInt(size);
		this.rarity = Integer.parseInt(rarity);
		this.temperament = Integer.parseInt(temperament);
		this.intelligence = Integer.parseInt(intelligence);
		this.cuteness = Integer.parseInt(cuteness);
		this.headerNames =headerNames;
		
		hmap = new HashMap<String, String>();  //Don't know if this is really needed but it allows the header names to be matched with the individual stats (String values) themselves
		hmap.put(headerNames[0], name);
		hmap.put(headerNames[1], size);
		hmap.put(headerNames[2], rarity);
		hmap.put(headerNames[3], temperament);
		hmap.put(headerNames[4], intelligence);
		hmap.put(headerNames[5], cuteness);
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