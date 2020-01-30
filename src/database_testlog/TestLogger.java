//check main below, which does the same as the constructor will.

package database_testlog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import model.*;


public class TestLogger {


	private static Logger LOGGER = Logger.getLogger(TestLogger.class.getName());
	private static FileHandler fHandler;
	private static SimpleFormatter formatter = new SimpleFormatter();
	private static final String SEP = "----------\r\n";
	
	public TestLogger() 
	{
		//Assignment and configuration of formatter, handler and logger.
		try
		{
			fHandler = new FileHandler("toptrumps.log");
			fHandler.setFormatter(formatter);
			LOGGER.addHandler(fHandler);
			LOGGER.setUseParentHandlers(false);// won't also log to console
		}
		catch (SecurityException e)
		{
			System.out.println("Unexpected security exception.");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("Problem opening file.");
			e.printStackTrace();
		}
	}
	
	//method to be called to write content of main deck to file upon load and then after shuffle.
	//currently relies upon a getDeck() method
	//waiting for confirmation of model design before completing method 
	
	public void writeMainDeck(ArrayList<Card> deck) 
	{
		String deckIntro = "The deck has been loaded and contains the following cards:\r\n"; //msg will have to be changed if reused with a shuffled deck.
		
		StringBuilder deckBuilder = new StringBuilder(deckIntro);
	}
	
	//method to be called once cards have been dealt, and then after each round, INC THE LAST ROUND.
	//currently relies upon a getPlayers() method and getHeaders() method of model and a getHand() method of players class
	public void writeHands(ArrayList<Player> players) 
	{
		
		//go through players and get card details for their hands.
		
	}
	
	//method to be called whenever cards are added or removed from the communal pile
	//currently relies up a getDiscardPile() method
	public void writePile(ArrayList<Card> pile) 
	{
		//go through the communal pile and list the card details
	}
	
	//method to be called at beginning of each round, to show all the details of all the cards to be used in that round
	//currently relies upon getPlayers() method and getHeaders() method of model and could benefit from a new method to get the top card of an array
	public void writeCurrentCard(ArrayList<Player> players)
	{
		//will write card info for each top card for each player
		
	}
	
	//method to be called each round, to show the choice made by the active player and the scores of each player for that category.
	//will reply upon model method getActivePlayer() and then some model methods to get activeCategory and then the corresponding score for each card
	public void writeChoiceValues(ArrayList<Player> players) {
		
		//will write the choice selected and the corresponding values
		
	}
	//method to be called when the game has been won and the winner is written to the testlog. This is the final entry of the log of each game.
	//currently relies upon getName() of player class.
	public void writeWinner(Player winner)
	{
		//will write the winner
	}
	
	
public static void main(String[] args) {
	
    //retained to use as testing
	
	//For me this seems to generate an error of java.nio.file.AccessDeniedException: toptrumps.log.lck
	//it is failing to create a file when used in this class
	//compiling the exact same code in a separate class\project allows it to work.
	
	try
	{
		fHandler = new FileHandler("toptrumps.log");
		fHandler.setFormatter(formatter);
		LOGGER.addHandler(fHandler);
		LOGGER.setUseParentHandlers(false);// won't also log to console
		
	}
	catch (SecurityException e)
	{
		System.out.println("Unexpected security exception.");
		e.printStackTrace();
	}
	catch (IOException e)
	{
		System.out.println("Problem opening file.");
		e.printStackTrace();
	}
	
	//LOGGER.info("Test input");
	fHandler.close();
	
	}//closes main

		
	
	
}// closes class
