package model;

import java.util.ArrayList;
import java.util.HashMap;

public class LogWriter {
private ArrayList<Card> deckOnLoad;
private ArrayList<Card>	deckShuffle;
private ArrayList<Card> communalPile;
private ArrayList<Card> playingTable;
private HashMap<Integer, String> chosenCategory;
private ArrayList<String> everyoneValues;
private Player gameWinner;

public LogWriter() {
	this.deckOnLoad = new ArrayList<Card>();
	this.deckShuffle = new ArrayList<Card>();	
	this.communalPile =new ArrayList<Card>();
	this.playingTable = new ArrayList<Card>();
	this.chosenCategory = new HashMap<Integer, String>();
	this.everyoneValues = new ArrayList<String>();
	
	chosenCategory.put(1, "Size");
	chosenCategory.put(2, "Rarity");
	chosenCategory.put(3, "Temperment");
	chosenCategory.put(4, "Intelligence");
	chosenCategory.put(5, "Cuteness");
}


public String getPlayersHands(ArrayList<Player>players, int roundNum) {
	String allPlayersHands = null;
	for (Player p : players) {
		allPlayersHands = "\n_____" + p.getName() + "'s hand\n_____" + p.getHand().size() + " cards\n////////////////////////";
		for (int i = 0; i < p.getHand().size(); i++) 
			allPlayersHands += p.getHand().get(i).getDescription();
	}
	allPlayersHands += "\nRound num: " + roundNum + "\n" + "_______________";
	return allPlayersHands;
}


public ArrayList<Card> getDeckOnLoad() {
	return deckOnLoad;
}


public void setDeckOnLoad(ArrayList<Card> deckOnLoad) {
	this.deckOnLoad = deckOnLoad;
}


public ArrayList<Card> getDeckShuffle() {
	return deckShuffle;
}


public void setDeckShuffle(ArrayList<Card> deckShuffle) {
	this.deckShuffle = deckShuffle;
}


public ArrayList<Card> getPlayingTable() {
	return playingTable;
}


public void setPlayingTable(ArrayList<Card> playingTable) {
	this.playingTable = playingTable;
}


public ArrayList<Integer> getEveryoneValues() {
	return everyoneValues;
}


public void setEveryoneValues(ArrayList<Integer> everyoneValues) {
	this.everyoneValues = everyoneValues;
}


public Player getGameWinner() {
	return gameWinner;
}


public void setGameWinner(Player gameWinner) {
	this.gameWinner = gameWinner;
}


public ArrayList<Card> getCommunalPile() {
	return communalPile;
}


public void setCommunalPile(ArrayList<Card> communalPile) {
	this.communalPile = communalPile;
}
}
