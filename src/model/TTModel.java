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
	private Player roundWinner;
	private int numOfDraws;
	private boolean isDraw;
	private int numOfRounds;
	private int numOfGames;
	//added by simon
	private ArrayList<Integer> allWonRounds = new ArrayList<Integer>(); 
	
	
	private boolean writeGameLogsToFile; // passed by TopTrumpsCLIApplication to constructor
	// Simon, i'll leave implementing this to you for now

	private Deck deck;
	private ArrayList<Player> players;
	private ArrayList<Card> communalPile;
	private ArrayList<Card> playingTable;

	//previous constructor
//	public TTModel(boolean writeGameLogsToFile) { // constructor
//		this.deck = new Deck();
//		this.players = new ArrayList<Player>();
//		this.communalPile = new ArrayList<Card>();
//		this.playingTable = new ArrayList<Card>();
//		this.numOfDraws = 0;
//		this.isDraw = false;
//		this.numOfRounds = 0;
//		this.numOfGames = 0;
//		this.gameWinner = null;
//		this.writeGameLogsToFile = false;
//		
//	}
	
	//new proposed constructor added by simon
	//fixes bug where subsequent games would see players' hands retained from previous games.
	//all variable initialisations placed in new method setNewGameStates;
	public TTModel(boolean writeGameLogsToFile) { // constructor
		setNewGameStates();
	}
	
	//method to initialise all variables at start of new game
	public void setNewGameStates() {
		this.deck = new Deck();
		this.players = new ArrayList<Player>();
		this.communalPile = new ArrayList<Card>();
		this.playingTable = new ArrayList<Card>();
		this.numOfDraws = 0;
		this.isDraw = false;
		this.numOfRounds = 0;
		this.numOfGames = 0;
		this.gameWinner = null;
		this.writeGameLogsToFile = false;
	
	}
	
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
	//method added by simon
	public ArrayList<Integer> getAllWonRounds(){
		return this.allWonRounds;
	}

	public void startGame(int botCount) {
		this.playerCount = botCount + 1;
		this.players.add(new Player("Player1"));
		for (int i = 0; i < botCount; i++) {
			this.players.add(new Bot("Player" + (i + 2) + " (AI)"));
		}
		this.deck.loadDeck();
		this.deck.dealCards(this.playerCount, this.players);
	}

	public void selectPlayer() {
		if (this.numOfRounds == 0) {
			Random r = new Random();
			this.activePlayerNum = r.nextInt(this.playerCount);
			this.activePlayer = this.players.get(this.activePlayerNum);
		} else if (this.isDraw == true || this.activePlayer.equals(this.roundWinner)) {
			;
		} else {
			this.activePlayer = this.roundWinner;
		}
	}

	public void compareCards(int stat) {
		HashMap<Player, Integer> playerStats = new HashMap<Player, Integer>();
		ArrayList<Player> roundWinners = new ArrayList<Player>();

		for (Player p : this.players) {
			// System.out.println(p.getName() + " " + p.getTopCard().getStats().get(stat));  moved to view
			playerStats.put(p, p.getTopCard().getStats().get(stat));
			this.playingTable.add(p.getHand().remove(p.getTopCardIndex()));
		}
		int maxValueInMap = Collections.max(playerStats.values());
		for (Entry<Player, Integer> entry : playerStats.entrySet()) { // Iterate through hashmap
			if (entry.getValue() == maxValueInMap) {
				roundWinners.add(entry.getKey());
			}
		}

		if (roundWinners.size() < 2) {
//			int currentRounds = roundWinners.get(0).getRounds(); // using getters/setters
//			roundWinners.get(0).setRounds(currentRounds+1);
			roundWinners.get(0).roundsWon++; // replace with getter and setter values?
			this.roundWinner = roundWinners.get(0);
			this.isDraw = false;
			roundWinners.get(0).getHand().addAll(0, this.communalPile); 
			roundWinners.get(0).getHand().addAll(0, this.playingTable); 
			this.playingTable.clear();
			this.communalPile.clear();
		} else {
			this.numOfDraws++;
			this.isDraw = true;
			this.roundWinner = null;
			this.communalPile.addAll(this.playingTable);
			this.playingTable.clear();
		}
		this.numOfRounds++;
	}

	public boolean hasWon() { // checker method called at the end of every round
		for (Player p : this.players) {
//			OLD - see below
//			 (p.getHand().size() >= this.deck.getNumOfCards()) { // are any of the players hand sizes = to the size of the original
//													// deck?
			
			// NEW - handles instances where final round is a draw
			// are any of the players hand sizes + whatever is in the communal pile = to the size of the original deck
			
			if ((p.getHand().size() + communalPile.size()) >= this.deck.getNumOfCards()) { 
//				
				this.gameWinner = p.getName();
				this.numOfGames++;
				updateWonRounds(); //added by simon
//				this.numOfRounds = 0; //  delete - now handled by setNewGameStates()
//				this.numOfDraws = 0; // 
				return true;		
			}
		}
		for (int i = 0; i < this.players.size(); i++) {
			if (this.players.get(i).getHand().isEmpty()) {
				this.players.remove(i);
			}
		}
		return false;
	}
	
			//method to get won rounds per player at end of game as needed by database. added by simon
	public void updateWonRounds() {
		 for (Player p : this.players) {
			 int eachWonRounds = p.getRoundsWon();
			 this.allWonRounds.add(eachWonRounds);
 
		 }
		 System.out.println("Array size is " +allWonRounds.size()); // temp debugging line
		
	}
}