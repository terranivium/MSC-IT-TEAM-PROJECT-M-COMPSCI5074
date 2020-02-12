package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
	private int numOfCards;
	private String[] headerNames;

	public Deck() { // Constructor
		this.cards = new ArrayList<Card>();
	}

	public void loadDeck() { // reads cards from .txt file and creates card objects
		BufferedReader br;
		String filePath = new File("DogsDeck.txt").getAbsolutePath(); // finds absolute path based off expected string
		System.out.println("Loaded file from  " + filePath + "\n");
		try {
			br = new BufferedReader(new FileReader(filePath));
			String read = null;
			read = br.readLine(); // reads first line of text into a string array
			this.headerNames = read.split("\\s+");
			while ((read = br.readLine()) != null) { // while there is another line in the txt file to read
				this.numOfCards++; // count number of cards in deck
				String[] word = read.split("\\s+");
				this.cards.add(new Card(word[0], word[1], word[2], word[3], word[4], word[5], this.headerNames)); // creates two identical arraylists for the two different states 																				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shuffleDeck() {
		Collections.shuffle(this.cards); // shuffles cards
	}

	public void dealCards(int playerCount, ArrayList<Player> players) { // shuffles and deals cards based on number of players
		int cardsLeftOver = this.numOfCards % playerCount; // checks to see if the deck is divided equally
		Random rand = new Random();
		int insertIndex = this.numOfCards - 1;

		if (cardsLeftOver != 0) { // if there is a card left over
			int i = rand.nextInt(playerCount); // select a random player to give to card to
			for (int j = 0; j < cardsLeftOver; j++) { // if there are more than one cards left over, pass the next spare
														// to the next person in the circle
				players.get(i).addHand(this.cards.remove(insertIndex--));
				if (i >= playerCount) {
					i = 0;
				} else {
					i++;
				}
			}
		}

		do { // deal cards to all the players
			for (Player p : players) {
				p.addHand(this.cards.remove(insertIndex--));
			}
		} while (this.cards.isEmpty() == false); // until the deck is empty
	}
	// Getter methods
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public int getNumOfCards() {
		return this.numOfCards;
	}

	public String[] getHeaderNames() {
		return this.headerNames;
	}
}
