package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
	private ArrayList<Card> unshuffledCards;
	private ArrayList<Card> shuffledCards;
	int numOfCards;
	private String[] headerNames;

	public Deck() {
		this.unshuffledCards = new ArrayList<Card>();
		this.shuffledCards = new ArrayList<Card>();
	}

	void loadDeck() { // reads cards from .txt file and creates card objects
		BufferedReader br;
		String filePath = new File("DogsDeck.txt").getAbsolutePath();
		System.out.println("Loaded file from  " + filePath + "\n");
		try {
			br = new BufferedReader(new FileReader(filePath));
			String read = null;
			read = br.readLine();
			this.headerNames = read.split("\\s+");

			while ((read = br.readLine()) != null) {
				this.numOfCards++;
				String[] word = read.split("\\s+");
				this.unshuffledCards.add(new Card(word[0], word[1], word[2], word[3], word[4], word[5], this.headerNames));
				this.shuffledCards = (ArrayList<Card>) this.unshuffledCards.clone();
				Collections.shuffle(this.shuffledCards);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void dealCards(int playerCount, ArrayList<Player> players) { // shuffles and deals cards based on number of players
		int cardsLeftOver = this.numOfCards % playerCount;
		Random rand = new Random();
		int insertIndex = this.numOfCards - 1;
		
		if (cardsLeftOver != 0) {
			int i = rand.nextInt(playerCount);
			for(int j = 0;j<cardsLeftOver;j++){
				players.get(i).addHand(this.shuffledCards.remove(insertIndex--));
				if(i >= playerCount){
					i = 0;
				}
				else{
					i++;
				}
			}
		}
		
		do {
			for (Player p : players) {
				p.addHand(this.shuffledCards.remove(insertIndex--));
			}
		} while (this.shuffledCards.isEmpty() == false);
	}

	// Getter methods
	
	public ArrayList<Card> getShuffledCards() {
		return this.shuffledCards;
	}

	public ArrayList<Card> getUnshuffledCards() {
		return this.unshuffledCards;
	}

	public int getNumOfCards() {
		return this.numOfCards;
	}

	public String[] getHeaderNames() {
		return this.headerNames;
	}
}
