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
		System.out.println("Loaded file from  " + filePath);
		try {
			br = new BufferedReader(new FileReader(filePath));
			String read = null;
			read = br.readLine();
			headerNames = read.split("\\s+");

			while ((read = br.readLine()) != null) {
				numOfCards++;
				String[] word = read.split("\\s+");
				unshuffledCards.add(new Card(word[0], word[1], word[2], word[3], word[4], word[5], headerNames));
				this.shuffledCards = (ArrayList<Card>) unshuffledCards.clone();
				Collections.shuffle(shuffledCards);
			}
		} catch (IOException e) {
			System.out.println("The file you have requested, does not exist");
		}
	}

	void dealCards(int playerCount, ArrayList<Player> players) { // shuffles and deals cards based on number of players
		int cardsLeftOver = numOfCards % playerCount;
		Random rand = new Random();
		int i = rand.nextInt(playerCount);
		int insertIndex = numOfCards - 1;
		
		if (cardsLeftOver != 0) {
			players.get(i).addHand(shuffledCards.remove(insertIndex--));
		}
		
		do {
			for (Player p : players) {
				p.addHand(shuffledCards.remove(insertIndex--));
			}
		} while (shuffledCards.isEmpty() == false);
	}

	// Getter methods
	public ArrayList<Card> getShuffledCards() {
		return shuffledCards;
	}

	public ArrayList<Card> getUnshuffledCards() {
		return unshuffledCards;
	}
	
	
}
