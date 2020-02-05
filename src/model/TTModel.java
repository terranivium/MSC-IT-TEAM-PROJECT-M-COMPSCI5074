package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class TTModel {
	private int playerCount; // model attributes
	private int activePlayerNum;
	private Player activePlayer;
	private String gameWinner;
	private Player roundWinner;
	private int numOfDraws;
	private int numOfRounds;
	private int numOfGames;
	private boolean writeGameLogsToFile; // passed by TopTrumpsCLIApplication to constructor
	private boolean isDraw;
	private Deck deck;
	private ArrayList<Integer> allWonRounds = new ArrayList<Integer>();
	private ArrayList<Player> players;
	private ArrayList<Player> playersToRemove;
	private ArrayList<Card> communalPile;
	private ArrayList<Card> playingTable;

	public TTModel(boolean writeGameLogsToFile) { // constructor
		this.setNewGameStates();
	}

	public void setNewGameStates() { // method to initialise and reset all variables at start of new game
		this.deck = new Deck();
		this.players = new ArrayList<Player>();
		this.playersToRemove = new ArrayList<Player>();
		this.communalPile = new ArrayList<Card>();
		this.playingTable = new ArrayList<Card>();
		this.numOfDraws = 0;
		this.isDraw = false;
		this.numOfRounds = 0;
		this.numOfGames = 0;
		this.gameWinner = null;
		this.writeGameLogsToFile = false;

	}

	public void startGame(int botCount) { // method is called to generate a Player and Bot objects based on the botCount
											// parameter passed from the controller
		this.playerCount = botCount + 1;
		this.players.add(new Player("Player1"));

		for (int i = 0; i < botCount; i++) {
			this.players.add(new Bot("Player" + (i + 2) + " (AI)"));
		}
		this.deck.loadDeck(); // calls method in deck object to generate card objects
		this.deck.dealCards(this.playerCount, this.players); // calls method to deal cards amongst
	}

	public void startBotGame(int botCount) { // method is called when a bot vs bot game is required, no player objects
												// are instantiated
		this.playerCount = botCount;

		for (int i = 0; i < botCount; i++) {
			this.players.add(new Bot("Player" + (i + 1) + " (AI)"));
		}
		this.deck.loadDeck(); // calls method to read and create card objects
		this.deck.dealCards(this.playerCount, this.players);
	}

	public void selectPlayer() {
		if (this.numOfRounds == 0) { // If it is the first round
			Random r = new Random();
			this.activePlayerNum = r.nextInt(this.playerCount); // select a random player to start first
			this.activePlayer = this.players.get(this.activePlayerNum);
		} else if (this.isDraw == true || this.activePlayer.equals(this.roundWinner)) { // if it is a draw from the
																						// previous round or the
																						// previous rounds winner has
																						// won again
			; // do nothing
		} else { // if a different player has won compared to the previous round
			this.activePlayer = this.roundWinner; // set the starting player to be the winner of the last round
		}
	}

	public void compareCards(int stat) {
		HashMap<Player, Integer> playerStats = new HashMap<Player, Integer>();
		ArrayList<Player> roundWinners = new ArrayList<Player>();

		for (Player p : this.players) {
			playerStats.put(p, p.getTopCard().getStats().get(stat));
			this.playingTable.add(p.getHand().remove(p.getTopCardIndex())); // remove all the players top cards and add
																			// the to an array list
		}
		int maxValueInMap = Collections.max(playerStats.values()); // calculate the highest value from the cards
																	// presented by the players
		for (Entry<Player, Integer> entry : playerStats.entrySet()) { // Iterate through hashmap value to find what
																		// value matches the maxValueInMap, and get the
																		// key of that entry
			if (entry.getValue() == maxValueInMap) {
				roundWinners.add(entry.getKey()); // adds all the players with the highest value to a new array list
			}
		}

		if (roundWinners.size() < 2) { // if there is only one winner
			int currentRounds = roundWinners.get(0).getRoundsWon(); // add to the players personal win tally
			roundWinners.get(0).setRoundsWon(currentRounds + 1);
			this.roundWinner = roundWinners.get(0); // set the round winning variable to the winning players name
			this.isDraw = false; // not a draw
			roundWinners.get(0).getHand().addAll(0, this.communalPile); // add all the cards from the communal pile and
																		// playing table to back of winning player's
																		// hand
			roundWinners.get(0).getHand().addAll(0, this.playingTable);
			this.playingTable.clear();
			this.communalPile.clear();
		} else { // if there are more than two winners (draw)
			this.numOfDraws++;
			this.isDraw = true;
			this.roundWinner = null;
			this.communalPile.addAll(this.playingTable); // add all cards to communal pile array list
			this.playingTable.clear();
		}
		this.numOfRounds++; // increment rounds
	}

	public boolean hasWon() { // checker method called at the end of every round
		for (Player p : this.players) {
			if (p.getHand().size() >= this.deck.getNumOfCards() + this.communalPile.size()) { // does any of the players
																								// have all the cards?
				this.gameWinner = p.getName(); // won the game
				this.numOfGames++; // increment number of games
				updateWonRounds();
				return true;
			} else if (p.getHand().isEmpty()) {
				this.playersToRemove.add(p);
			}
		}
		players.removeAll(playersToRemove);
		playersToRemove.clear();
		return false;
	}

	public void updateWonRounds() { // method updates variable to count all the rounds that have been won by all
									// players
		for (Player p : this.players) {
			int eachWonRounds = p.getRoundsWon();
			this.allWonRounds.add(eachWonRounds);
		}
	}

	// Getter and Setter Methods
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public Player getActivePlayer() {
		return this.activePlayer;
	}

	public int getNumOfDraws() {
		return this.numOfDraws;
	}

	public String getGameWinner() {
		return this.gameWinner;
	}

	public String getRoundWinnerName() {
		return this.roundWinner.getName();
	}

	public int getNumOfGames() {
		return this.numOfGames;
	}

	public int getNumOfRounds() {
		return this.numOfRounds;
	}

	public ArrayList<Integer> getAllWonRounds() {
		return this.allWonRounds;
	}
}