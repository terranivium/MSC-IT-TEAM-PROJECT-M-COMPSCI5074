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
	private ArrayList<Player> players;
	private ArrayList<Card> deck;
	private String winner;

	public TTModel() {
		this.playerCount = 1;
		this.deck = new ArrayList<Card>();
		this.winner = null;
	}

	public void startGame() { //This method literally just determines the playercount, i think ill combine it later when using it to assign cards
		Scanner s = new Scanner(System.in);

		System.out.println("How many AI players do you want to play against? (Max 4)");
		int botCount = s.nextInt();
		s.nextLine();
		if (botCount >= 5 || botCount <= 0) {
			System.out.println("Error, please chose between 1 and 4 AI Players.");
			startGame();
		} else {
			playerCount = +botCount;
			cardRead();
		}
		s.close();
	}

	public void cardRead() { //reads cards from txt file and shuffles them into random order in an arraylist
		BufferedReader br;
		File file = new File(".\\StarCitizenDeck.txt");

		try {
			br = new BufferedReader(new FileReader(file));
			String read = null;
			br.readLine();

			while ((read = br.readLine()) != null) {
				String[] word = read.split("\\s+");
				deck.add(new Card(word[0], Integer.parseInt(word[1]), Integer.parseInt(word[2]),
						Integer.parseInt(word[3]), Integer.parseInt(word[4]), Integer.parseInt(word[5])));
			}
		} 
		catch (IOException e) { // not sure if this is bad Software Engineering...??
			System.out.println("The file you have requested, does not exist");
		}
		// possibly add more exception catching and a 'finally' catch too, input
		// mismatch etc, would have to change IOExcpetion
		
		Collections.shuffle(deck);
		//System.out.println(cards.get(1).getName());  //UNCOMMENT THIS TO TEST THAT IT WORKS...shuffles and assigns to card objects!!
	}

	public void compareCards() {

	}

	public void endGame() {

	}
}