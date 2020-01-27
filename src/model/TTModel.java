package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class TTModel {
	private int playerCount;
	private int numOfCards;
	private int nextPlayer;
	private ArrayList<Player> players;
	private ArrayList<Card> deck;
	private ArrayList<Card> discardPile;
	private String[] headerNames;
	private String winner;

	public TTModel() { // constructor
		this.playerCount = 0;
		this.players = new ArrayList<Player>();
		this.numOfCards = 0;
		this.deck = new ArrayList<Card>();
		this.winner = null;
		this.discardPile = new ArrayList<Card>();
	}

	public void startGame(int botCount) {
		playerCount = botCount + 1;
		players.add(new Player("Player1"));
		for (int i = 0; i < botCount; i++) {
			players.add(new Bot("Player" + (i + 2)));
		}
		loadDeck();
		dealCards();
		selectRandomPlayer();
	}

	private void loadDeck() { // reads cards from txt file and creates card objects
		BufferedReader br;
		String filePath = new File("DogsDeck.txt").getAbsolutePath();
		System.out.println(filePath);
		try {
			br = new BufferedReader(new FileReader(filePath));
			String read = null;
			read = br.readLine();
			headerNames = read.split("\\s+");

			while ((read = br.readLine()) != null) {
				numOfCards++;
				String[] word = read.split("\\s+");
				deck.add(new Card(word[0], word[1], word[2], word[3], word[4], word[5], headerNames));
			}
		} catch (IOException e) {
			System.out.println("The file you have requested, does not exist");
		}
	}

	private void dealCards() { // shuffles and deals cards based on number of players
		Collections.shuffle(deck);
		int cardsPerHand = numOfCards / playerCount;
		int cardsLeftOver = numOfCards % playerCount;

		int insertIndex = numOfCards - 1;
		if (cardsLeftOver != 0) {
			discardPile.add(deck.remove(insertIndex--));
		}

		do {
			for (Player p : players) {
				p.addHand(deck.remove(insertIndex--));
			}
		} while (deck.isEmpty() == false);

		for (int j = 0; j < playerCount; j++) { // CHECK
			for (int i = 0; i < cardsPerHand; i++) {
				System.out.println(players.get(j).getName());
				System.out.println(players.get(j).getHand().get(i).getName());
			}
		}
		if (discardPile.isEmpty() == false) {
			System.out.println("discard pile = " + discardPile.get(0).getName());
		}
	}

	private void selectRandomPlayer() {
		Random r = new Random();
		nextPlayer = r.nextInt(playerCount);
		int chosenStat;
		if (players.get(nextPlayer).getClass() == players.get(0).getClass()) {
			chosenStat = players.get(0).chooseCard();
		} else {
			chosenStat = players.get(nextPlayer).chooseCard();
		}
		compareCards(chosenStat);
	}

	public void compareCards(int stat) {
		for (Player p : players)
			System.out.println(p.getName() + " :  " + p.getHand().get((p.getHand().size() - 1)).stats.get(stat));
	}

	public void endGame() {

	}

	// Getter methods
	public int getPlayerCount() {
		return playerCount;
	}

	public int getNumOfCards() {
		return numOfCards;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public ArrayList<Card> getDiscardPile() {
		return discardPile;
	}

	public String[] getHeaderNames() {
		return headerNames;
	}

	public String getWinner() {
		return winner;
	}
}