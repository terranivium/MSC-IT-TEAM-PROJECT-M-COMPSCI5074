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
	private boolean isDraw;
	private Deck deck;
	private ArrayList<Integer> allWonRounds;
	private ArrayList<Player> players;
	private ArrayList<Player> playersRemaining; // variable used to store which players remain in the game, seperate from all the players who began the game.
	private ArrayList<Player> playersToRemove;
	private ArrayList<Player> roundWinners;
	private ArrayList<Card> communalPile;
	private ArrayList<Card> playingTable;
	private ArrayList<Card> winnersCards;
	private ArrayList<Card> possibleWinningCards;
	private int categoryChosen; //instance variable set by argument supplied to compareCards, needed for use in TestLogger
	private HashMap<Player, Integer> playerStats = new HashMap<Player, Integer>(); 	//migrated from compareCards so as to give an instance variable, which can then be called as a getter
    private HashMap<Player,Card> playersCards = new HashMap<Player, Card>();
	private LogWriter logWriter;
    private String removedPlayersString;
    private String roundWinnerString;
    private String winningCard;

    public TTModel() { // constructor												//for use in TestLogger
		this.setNewGameStates();
	}

	public void setNewGameStates() { // method to initialise and reset all variables at start of new game
		this.deck = new Deck();
		this.players = new ArrayList<Player>();
		this.playersToRemove = new ArrayList<Player>();
		this.roundWinners = new ArrayList<Player>();
		this.communalPile = new ArrayList<Card>();
		this.playingTable = new ArrayList<Card>();
		this.winnersCards = new ArrayList<Card>();
		this.possibleWinningCards = new ArrayList<Card>();
		this.allWonRounds = new ArrayList<Integer>();
		this.playerStats = new HashMap<Player, Integer>();
		this.numOfDraws = 0;
		this.numOfRounds = 0; //Set as 0. Testlogger uses this to distinguish between the pre-game data and the rounds that follow.
		this.numOfGames = 0;
		this.isDraw = false;
		this.gameWinner = null;
		this.activePlayer = null;
		this.roundWinner = null; // removed to fix view status
		this.removedPlayersString = "";
		this.winningCard = "";
		this.roundWinnerString = "";
	}
	
	
	// method is called to generate a Player and Bot objects based on the botCount
	// parameter passed from the controller
	public void startGame(int botCount) { 
		
		this.playerCount = botCount + 1;
		this.players.add(new Player("Player1"));

		for (int i = 0; i < botCount; i++) {
			this.players.add(new Bot("Player" + (i + 2) + " (AI)"));
		}
		this.clonePlayers(); //new method to create a clone of this list for players remaining.
		this.deck.loadDeck(); // calls method in deck object to generate card objects
        this.logWriter = new LogWriter(this.deck);
		this.deck.shuffleDeck(); //calls method to shuffle deck
		this.logWriter.setDeckShuffle(this.deck.getCards());
        this.deck.dealCards(this.playerCount, this.players); // calls method to deal cards amongst
        this.logWriter.setPlayersHands(this.players, this.numOfRounds); //// added here as TestLogger needs to see hands on initial deal, before play, otherwise null.
	}

	//method startBotGame used for testing purposes during development, allowing developers to see whether game and program
	//were behaving as required. Removed in final stages of refinement and not updated to reflect final refactoring of model.
	
//	public void startBotGame(int botCount) { // method is called when a bot vs bot game is required, no player objects
//												// are instantiated
//		this.playerCount = botCount;
//
//		for (int i = 0; i < botCount; i++) {
//			this.players.add(new Bot("Player" + (i + 1) + " (AI)"));
//		}
//		this.deck.loadDeck(); // calls method to read and create card objects
//		this.logWriter = new LogWriter(this.deck);
//		this.deck.shuffleDeck(); //calls method to shuffle deck
//		this.logWriter.setDeckShuffle(this.deck.getCards());
//		this.deck.dealCards(this.playerCount, this.players);
//		this.logWriter.setPlayersHands(this.players, this.numOfRounds); 
//	}

	public void selectPlayer() {
		this.numOfRounds++;
		if (this.numOfRounds == 1) { // As numOfRounds is initialised to 0 will only be reached if it is the first round of a game
			Random r = new Random();
			this.activePlayerNum = r.nextInt(this.playerCount); // select a random player to start first
			this.activePlayer = this.players.get(this.activePlayerNum);
		} else if (this.isDraw == true || this.activePlayer.equals(this.roundWinner)) { 
			// if it is a draw from the previous round or the previous rounds winner has
			// won again do nothing
			;
		} else { // if a different player has won compared to the previous round
			this.activePlayer = this.roundWinner; // set the starting player to be the winner of the last round
		}

	}
	//method to identify possible winners in a round, based on the values on their cards in the compared category.
	public void playCards(int stat) {
		this.logWriter.setChosenCategory(stat);
		// Then clears the instance variables at the beginning of each comparison
		this.playerStats.clear(); 
		this.playersCards.clear();
		this.logWriter.resetEveryoneValues();
		this.logWriter.resetPlayersCards();
		this.roundWinnerString = "";
		this.winningCard = "";

		for (Player p : this.playersRemaining) { 
			this.playerStats.put(p, p.getTopCard().getStats().get(stat));
			this.logWriter.setEveryoneValues(p.getName() + "'s card " + p.getTopCard().getDescription() + " has the value: " + p.getTopCard().getStats().get(stat) + "\n");

			this.playersCards.put(p, p.getTopCard()); //places a player and their card in a HashMap, for use with logWriter and retrieval of the winning card
			this.logWriter.setPlayersCards(playersCards);

			this.playingTable.add(p.getHand().remove(p.getTopCardIndex())); // remove all the players top cards and add
																			// the to an array list
		}
		this.logWriter.setPlayingTable(this.playingTable);
		int maxValueInMap = Collections.max(this.playerStats.values()); // calculate the highest value from the cards
																	// presented by the players
		for (Entry<Player, Integer> entry : this.playerStats.entrySet()) { // Iterate through hashmap value to find what
																		// value matches the maxValueInMap, and get the
																		// key of that entry
			if (entry.getValue() == maxValueInMap) {
				Player possibleWinner;

				possibleWinner = entry.getKey(); //Possible winner set as the key in instances where the value is the highest in playerStats

				this.roundWinners.add(possibleWinner); // adds all the players with the highest value to a new array list

				this.possibleWinningCards.add(playersCards.get(possibleWinner)); //uses a possibleWinner person as a key to get an associated value from playersCards. The possible winning cards are thus identifiable (needed for view)
			}
		}
	}
	//method to determine whether or not the round was won or drawn depending on the number of possible winners (stored in roundWinners).
	//Depending on the result, cards are either added to a winner's hand, or put in to a communal pile in the event of a draw.
	public void selectWinners() {
		if (this.roundWinners.size() < 2) { // if there is only one winner
			this.roundWinners.get(0).incrementRoundsWon();
			ArrayList<Card> winnersCards = new ArrayList<Card>();
			this.roundWinner = this.roundWinners.get(0); // set the round winning variable to the winning players name
			this.logWriter.setRoundWinner(this.roundWinner.getName());
			this.roundWinnerString = this.roundWinner.getName();
			this.isDraw = false; // not a draw
			this.winnersCards.addAll(this.communalPile);
			this.winnersCards.addAll(this.playingTable);
			Collections.shuffle(this.winnersCards); // shuffle won cards before adding to winner's hand.
			this.roundWinners.get(0).getHand().addAll(0, this.winnersCards); // add all the cards from the communal pile and
																		// playing table to back of winning player's
																		// hand in a random order
			this.logWriter.setPlayersHands(this.players, this.numOfRounds); //moved here as testlogger needs to see this at the end of a game loop.
			this.playingTable.clear(); // clear all array lists for new round
			this.communalPile.clear();
			this.logWriter.setCommunalPile(communalPile);
			this.winnersCards.clear();

			//provide a means for view to access the winning card. Only reached when there is one winner.
			winningCard = possibleWinningCards.get(0).getDescription();
			possibleWinningCards.clear();

		} else { // if there are two or more winners (draw)
			this.numOfDraws++;
			this.isDraw = true;
			this.roundWinner = null; // must remove to get roundWinner

			this.logWriter.setRoundWinner(null); //added due to bug where LogWriter never learnt of draws.
			this.communalPile.addAll(this.playingTable); // add all cards to communal pile array list
			this.logWriter.setCommunalPile(this.communalPile);
			this.playingTable.clear();
			this.logWriter.setPlayersHands(this.players, this.numOfRounds);////moved here as testlogger needs to see this at the end of a game loop.
			possibleWinningCards.clear();  // will need empty as possibly has >2= elements.
		}

		
		removedPlayersString = ""; //resets value, before checking for players to remove at the end of a a round. Enables a checking value in view.
		checkForRemovablePlayers(); //this method will update removedPlayersString if there are eliminated this round.
		this.roundWinners.clear();
	}

	public boolean hasWon() { // checker method called at the end of every round
		for (Player p : this.players)
		{
			if ((p.getHand().size() + this.communalPile.size()) >= this.deck.getNumOfCards()) { // If a player's hand + any cards in the communal pile is at least 40, that player must have won the game.

				this.gameWinner = p.getName(); // won the game
				this.numOfGames++; // increment number of games
				this.updateWonRounds();
				return true;

			} 
		}

		return false;
	}

	public void updateWonRounds() { 
		// method updates variable to count all the rounds that have been won by all players
		for (Player p : this.players) {
			int eachWonRounds = p.getRoundsWon();
			this.allWonRounds.add(eachWonRounds);
		}
	}

	//method used to crate a cloned ArrayList of the players in the game. Players will be removed from this as they are removed from the game.
	@SuppressWarnings("unchecked")	//Eclipse suggests potential for unchecked Object cast to type Player. As players is also of type Player this
									//has been suppressed.
	private void clonePlayers()
	{
		this.playersRemaining = (ArrayList<Player>) this.players.clone();
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

	public Player getRoundWinner() {
		return this.roundWinner;
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

	
	public Deck getDeck() {
		return this.deck;
	}

    public LogWriter getLogWriter() {
        return this.logWriter;
    }

	public ArrayList<Card> getCommunalPile(){
		return this.communalPile;
	}

	public ArrayList<Card> getPlayingTable(){
		return this.playingTable;
	}
	
	public HashMap<Player, Integer> getPlayerStats(){
		return this.playerStats;
	}
	
	public int getCategoryChosen() {
		return this.categoryChosen;
	}

	
	public String getChosenCategory()
	{
		return this.logWriter.getChosenCategory();
	}

	public boolean isDraw() {
		return this.isDraw;
	}

	public String getRemovedPlayersString(){
		return this.removedPlayersString;
	}

	public String getRoundWinnerString(){
		return this.roundWinnerString;
	}
	
	
	public ArrayList<Player> getPlayersRemaining() {
		return playersRemaining;
	}

	public String getWinningCard() {
		return this.winningCard;
	}
	
	//method works out whether or not to remove players from the ArrayList playersRemaining based on
	//whether or not they have any cards remaining. Also updates a String for use in the CLM view 
	//providing information that a player has been removed.
	private void checkForRemovablePlayers()
	{
		for (Player p : this.playersRemaining)
		{
			if (p.getHand().isEmpty())
			{
				this.playersToRemove.add(p);
				removedPlayersString = p.getName() + " has been eliminated.\n";
			}
		}
		this.playersRemaining.removeAll(this.playersToRemove);
	}

}