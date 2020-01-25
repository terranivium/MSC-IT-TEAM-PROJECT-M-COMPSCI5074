package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TTModel {
	private int playerCount;
	private int numOfCards;
	private ArrayList<Player> players;
	private ArrayList<Card> deck;
	private ArrayList<Card> discardPile;
	private String winner;
	private String[] headerNames;

	public TTModel() { // constructor
		this.playerCount = 0;
		this.players = new ArrayList<Player>();
		this.numOfCards = 0;
		this.deck = new ArrayList<Card>();
		this.winner = null;
		this.discardPile = new ArrayList<Card>();
	}

	public void addPlayers() { // This method literally just determines the number of bots, i think ill combine
								// it later when using it to assign cards
		Scanner s = new Scanner(System.in);

		System.out.println("How many AI players do you want to play against? (Max 4)");
		int botCount = s.nextInt();
		s.nextLine();
		if (botCount >= 5 || botCount <= 0) {
			System.out.println("Error, please chose between 1 and 4 AI Players.");
			addPlayers();
		} else {
			playerCount = botCount + 1;
			players.add(new Player("Player1"));
			for (int i = 0; i < botCount; i++) {
				players.add(new Bot("Player" + (i + 2)));
			}
		}
		s.close();
	}

	public void loadDeck() { // reads cards from txt file and shuffles them into random order in an arraylist
		BufferedReader br;
		File file = new File(".\\StarCitizenDeck.txt");

		try {
			br = new BufferedReader(new FileReader(file));
			String read = null;
			read = br.readLine();
			headerNames = read.split("\\s+");

			while ((read = br.readLine()) != null) {
				numOfCards++;
				String[] word = read.split("\\s+");
				deck.add(new Card(word[0], word[1], word[2], word[3], word[4], word[5], headerNames));
			}
		} catch (IOException e) { // not sure if this is bad Software Engineering...??
			System.out.println("The file you have requested, does not exist");
		}
		// possibly add more exception catching and a 'finally' catch too, input
		// mismatch etc, would have to change IOExcpetion

		Collections.shuffle(deck);
		// System.out.println(cards.get(1).getName()); //UNCOMMENT THIS TO TEST THAT IT
		// WORKS...shuffles and assigns to card objects!!
	}

	public void dealCards() {
		int cardsPerHand = numOfCards / playerCount;
		int cardsLeftOver = numOfCards % playerCount;
		//System.out.println(cardsPerHand);
		//System.out.println(cardsLeftOver);

		int insertIndex = numOfCards - 1;
		if (cardsLeftOver != 0) {
			discardPile.add(deck.remove(insertIndex--));
		}

		do {
			for (Player p : players) {
				p.addHand(deck.remove(insertIndex--));
			}
		} while (deck.isEmpty() == false);
		
		for(int i = 0;i< 40;i++) {
		System.out.println(players.get(1).getHand().get(i).getName());
		}
	}

	public void compareCards() {

	}

	public void endGame() {

	}
}