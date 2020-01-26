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
			players.add( new Player("Player1"));
			for (int i = 0; i < botCount; i++) {
				players.add(new Bot("Player" + (i + 2)));
			}
		}
	}

	public void loadDeck() { // reads cards from txt file and shuffles them into random order in an arraylist
		BufferedReader br;
		File file = new File(".\\DogsDeck.txt");

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
		/*System.out.println(players.get(1).getName());
		for(Card c:deck) {  
			System.out.println(c.getHeaderNames()[0] + " : " + c.getName());
			System.out.println(c.getHeaderNames()[1]+ " : " + c.getSize());
			System.out.println(c.getHeaderNames()[2]+ " : " + c.getRarity());
			System.out.println(c.getHeaderNames()[3]+ " : " + c.getTemperament());
			System.out.println(c.getHeaderNames()[4]+ " : " + c.getIntelligence());
			System.out.println(c.getHeaderNames()[5]+ " : " + c.getCuteness() + "\n");
			
		}
		*/
	}

	public void dealCards() {
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
		
		for(int j = 0; j < playerCount;j++) {  //CHECK
		for(int i = 0;i< cardsPerHand;i++) {
			System.out.println(players.get(j).getName());
		System.out.println(players.get(j).getHand().get(i).getName());  
		}
	}
		if(discardPile.isEmpty() == false) {
		System.out.println("discard pile = " + discardPile.get(0).getName());
		}
	}

	public void choosePlayer() {
		Random r = new Random();
		int nextPlayer = r.nextInt(playerCount);
		int chosenStat;
		System.out.println("////" + players.get(nextPlayer).getClass());
		if(players.get(nextPlayer).getClass() == players.get(0).getClass()) {
			chosenStat = players.get(0).chooseCard();
		}
		else {
			chosenStat = players.get(nextPlayer).chooseCard();
		}
		compareCards(chosenStat);
	}

	public void compareCards(int stat) {
		for(Player p:players)
		System.out.println(p.getName() + " :  " + p.getHand().get((p.getHand().size()-1)).stats.get(stat));
	}
	
	public void endGame() {

	}
}