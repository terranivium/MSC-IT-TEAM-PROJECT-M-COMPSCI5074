package model;

import java.util.ArrayList;
import java.util.HashMap;

public class LogWriter {
private ArrayList<Card> deckOnLoad; 
private ArrayList<Card>	deckShuffle; 
private ArrayList<Card> communalPile; 
private ArrayList<Card> playingTable;
private HashMap<Integer, String> chosenCategoryMap; 
private String chosenCategory; 
private ArrayList<String> everyoneValues;
private String everyoneHands;
private String roundWinner;
private String[] headerNames;

public LogWriter(Deck loadedDeck) {
	this.deckOnLoad = new ArrayList<Card>(loadedDeck.getCards());
	this.deckShuffle = new ArrayList<Card>();	
	this.communalPile =new ArrayList<Card>();
	this.playingTable = new ArrayList<Card>();
	this.chosenCategoryMap = new HashMap<Integer, String>();
	this.everyoneHands = new String();
	this.everyoneValues = new ArrayList<String>();
	this.roundWinner = null;
	this.headerNames = loadedDeck.getHeaderNames();
	
	chosenCategoryMap.put(1, this.headerNames[1]); //[0] is not included as it contains "Description", corresponding to the name of each card.
	chosenCategoryMap.put(2, this.headerNames[2]);
	chosenCategoryMap.put(3, this.headerNames[3]);
	chosenCategoryMap.put(4, this.headerNames[4]);
	chosenCategoryMap.put(5, this.headerNames[5]);
}


public void setPlayersHands(ArrayList<Player>players, int roundNum) {
	
	if (roundNum == 0)
	{
		this.everyoneHands = "Beginning game state \n" + "_______________\n";
	} else 
		{
		this.everyoneHands = "[End round " + roundNum + "]\n" + "_______________\n";
		}
	
	for (Player p : players) {
		this.everyoneHands += "\n__" + p.getName() + "'s hand__\n__" + p.getHand().size() + " cards__\n////////////////////////\n";
		for (int i = 0; i < p.getHand().size(); i++) 
			this.everyoneHands += (i+1) + ". " + p.getHand().get(i).getDescription() + "\n";
	}
	this.everyoneHands.concat("\n");
	
}

public String getEveryoneHands() {
	return this.everyoneHands;
}


public String getDeckOnLoad() {
	
	String deckOnLoadString = new String();
	deckOnLoadString = "__Unshuffled Cards__\n";
	
	StringBuffer sb = new StringBuffer();
	
	for (int i = 0; i < headerNames.length; i++)
		{
			sb.append(headerNames[i] + " ");
		}
		String categories = sb.toString();
	
		StringBuilder deckBuilder = new StringBuilder(deckOnLoadString);
		deckBuilder.append(categories + "\n");
	
	for(Card c:this.deckOnLoad)
		{
		deckBuilder.append(c.getDescription() + " ");
		deckBuilder.append(c.getCategoryOne() + " ");
		deckBuilder.append(c.getCategoryTwo() + " ");
		deckBuilder.append(c.getCategoryThree() + " ");
		deckBuilder.append(c.getCategoryFour() + " ");
		deckBuilder.append(c.getCategoryFive() + "\n");
	}
		
	return deckBuilder.toString();
}


//public void setDeckOnLoad(ArrayList<Card> deck, String[] headerNames) {
//	this.deckOnLoad = (ArrayList<Card>) deck.clone();
//}


public String getDeckShuffle() {
	String deckShuffleString = new String();
	deckShuffleString = "__Shuffled Cards__\n";
	for(Card c:this.deckShuffle) {
		
	deckShuffleString = deckShuffleString + c.getDescription() + "\n";
	}
	return deckShuffleString;
}


@SuppressWarnings("unchecked")
public void  setDeckShuffle(ArrayList<Card> deck) {
	this.deckShuffle = (ArrayList<Card>) deck.clone();
}


public String getPlayingTable() {
	String playingTableString = new String();
	playingTableString = "__Everyones Top Card Played__\n";
	int i = 1;
	for(Card c:this.playingTable) {
		playingTableString = playingTableString + "Player" + i + " : " + c.getDescription() + "\n";
		i++;
	}
	return playingTableString;
}


@SuppressWarnings("unchecked")
public void setPlayingTable(ArrayList<Card> playingTable) {
	this.playingTable = (ArrayList<Card>) playingTable.clone();
}


public ArrayList<String> getEveryoneValues() {
	return this.everyoneValues;
}

public void setEveryoneValues(String input) {
	this.everyoneValues.add(input);
}

public void resetEveryoneValues() {
	this.everyoneValues.clear();
}

public String getCommunalPile() {
	String communalPileString = new String();
	communalPileString = "__Communal Pile Contents__\n";
	for(Card c:this.communalPile) {
		communalPileString = communalPileString + c.getDescription() + "\n";
		}
	return communalPileString;
}

public void setCommunalPile(ArrayList<Card> communalPile) {
	this.communalPile = communalPile;
}

public String getChosenCategory() {
	return "The chosen category was: " + this.chosenCategory + ".";
}

public void setChosenCategory(int stat) {
	this.chosenCategory = this.chosenCategoryMap.get(stat);
}


public String getRoundWinner() {
	if(this.roundWinner != null) {
		return "The winner of the round was: " + this.roundWinner;
	}
	else return "Draw - No one won the round";
}

public void setRoundWinner(String roundWinner) {
	this.roundWinner = roundWinner;
}

}
