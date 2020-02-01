package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class TTModel {
	int playerCount;
	private int activePlayerNum;
	private Player activePlayer;
	private String gameWinner;
	private String roundWinner;
	private int numOfDraws;
	private boolean isDraw;
	private int numOfRounds;
	private int numOfGames;

	private Deck deck;
	private ArrayList<Player> players;
	private ArrayList<Card> communalPile;
	private ArrayList<Card> playingTable;

	public TTModel() { // constructor
		this.deck = new Deck();
		this.players = new ArrayList<Player>();
		this.communalPile = new ArrayList<Card>();
		this.playingTable = new ArrayList<Card>();
		this.numOfDraws = 0;
		this.isDraw = false;
		this.numOfRounds = 0;
		this.numOfGames = 0;
	}

	public void startGame(int botCount) {
		playerCount = botCount + 1;
		players.add(new Player("Player1"));
		for (int i = 0; i < botCount; i++) {
			players.add(new Bot("Player" + (i + 2) + " (AI)"));
		}
		deck.loadDeck();
		deck.dealCards(playerCount, players);
	}

	public void selectPlayer() {
		for (Player p : players) {
			System.out.println("\n");
			System.out.println("_____" + p.name + "'s hand");
			System.out.println("_____" + p.hand.size() + " cards");
			System.out.println("////////////////////////");
			for (int i = 0; i < p.hand.size(); i++)
				System.out.println(p.getHand().get(i).getName());
		}
		System.out.println("\n");
		System.out.println("Round num: " + this.numOfRounds + "\n_______________");
		if (numOfRounds == 0) {
			Random r = new Random();
			this.activePlayerNum = r.nextInt(playerCount);
		} else if (isDraw == true) {
			;
		} else {
			if (activePlayerNum == (playerCount - 1)) {
				this.activePlayerNum = 0;
			} else {
				this.activePlayerNum++;
			}
		}
		System.out.println("active player num : " + this.activePlayerNum);
		activePlayer = players.get(activePlayerNum);

	}

	public void compareCards(int stat) {
		isDraw = false;
		HashMap<Player, Integer> playerStats = new HashMap<Player, Integer>();
		ArrayList<Player> roundWinners = new ArrayList<Player>();

		for (Player p : players) {
			System.out.println(p.getName() + " " + p.getTopCard().stats.get(stat));
			playerStats.put(p, p.getTopCard().stats.get(stat));
			playingTable.add(p.hand.remove(p.getTopCardIndex()));
		}
		int maxValueInMap = Collections.max(playerStats.values());
		for (Entry<Player, Integer> entry : playerStats.entrySet()) { // Iterate through hashmap
			if (entry.getValue() == maxValueInMap) {
				roundWinners.add(entry.getKey());
			}
		}

		if (roundWinners.size() < 2) {
			roundWinners.get(0).roundsWon++;
			roundWinner = roundWinners.get(0).name;
			isDraw = false;
			roundWinners.get(0).hand.addAll(0, communalPile);
			roundWinners.get(0).hand.addAll(0, playingTable);
			playingTable.clear();
			communalPile.clear();
		} else {
			numOfDraws = getNumOfDraws() + 1;
			isDraw = true;
			roundWinner = null;
			communalPile.addAll(playingTable);
			playingTable.clear();
		}
		numOfRounds++;
	}

	public boolean hasWon() { //checker method called at the end of every round
		for (Player p : players) {
			if (p.hand.size() >= deck.numOfCards) { // are any of the players hand sizes = to the size of the original deck?
				this.gameWinner = p.getName();
				this.numOfGames++;
				this.numOfRounds = 0;  //write to database server before this line
				this.numOfDraws = 0;
				return true;
			}
		}
		return false;
	}
	// Getter methods

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public int getNumOfDraws() {
		return numOfDraws;
	}

	public String getGameWinner() {
		return gameWinner;
	}

	public String getRoundWinner() {
		return roundWinner;
	}

	public int getNumOfGames() {
		return numOfGames;
	}

	public int getNumOfRounds() {
		return numOfRounds;
	}

}