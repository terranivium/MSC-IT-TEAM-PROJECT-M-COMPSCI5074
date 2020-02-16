// class handles the creation and updating of a .log file for logging test information about the state of command line mode game as it progresses.
// file created is toptrumps.log
// its methods are passed arguments from the controller relating to data stored and handled by the model class LogWriter.

package database_testlog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import model.Card;
import model.Player;


public class TestLogger {

	//Static variables. 
	private static Logger LOGGER = Logger.getLogger(TestLogger.class.getName());
	private static FileHandler fHandler;
	private static SimpleFormatter formatter = new SimpleFormatter();
	private static final String SEP = "----------\r\n";
	
	//Constructor handles assignment and configuration of formatter, handler and logger.
	//catches errors and prints to console in their event.
	public TestLogger() 
	{
		
		try
		{
			fHandler = new FileHandler("toptrumps.log");
			fHandler.setFormatter(formatter);
			LOGGER.addHandler(fHandler);
			LOGGER.setUseParentHandlers(false);// set as false so won't also log to console
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
	
		
	//Method is passed a string which contains details of the deck loaded by the program.
	//The string is written to file with an introduction and an ending separator.
	public void writeLoadedDeck(String loadedDeck)
	{
		StringBuffer sb = new StringBuffer();
		
		String deckIntro = "The deck has been loaded, with cards bearing these attributes:\r\n";
		StringBuilder deckBuilder = new StringBuilder(deckIntro);
		deckBuilder.append(loadedDeck);
		deckBuilder.append(SEP);
		
		LOGGER.info(deckBuilder.toString());
					
	}

	//method is passed a string which contains the names of the cards following their shuffling
	//in the model.
	//The string is written to file with an introduction and an ending separator.
	public void writeShuffledDeck(String shuffledDeck)
	{
		String shuffledIntro = "The deck has been shuffled in to the following order:\r\n";
		
		StringBuilder shuffledBuilder = new StringBuilder(shuffledIntro);
		shuffledBuilder.append(shuffledDeck);
		shuffledBuilder.append(SEP);
		
		LOGGER.info(shuffledBuilder.toString());
	}
	
	//method is passed a string which contains the dealt hands of the players
	//along with the names of the players that hold them.
	//The string is written to file with an introduction and an ending separator.
	public void writeDealtHands(String dealtHands)
	{
		String dealtIntro = "Each of the players has received the following cards and is ready to start the game:\r\n";
		
		StringBuilder dealtBuilder = new StringBuilder(dealtIntro);
		dealtBuilder.append(dealtHands);
		dealtBuilder.append(SEP);
		
		LOGGER.info(dealtBuilder.toString());
	}
	
	//method is passed an integer representing the round number of the game,
	//giving clarity to which stage of the game is being logged.
	//the integer is appended to a string, which is written to file with an
	//introduction and an ending separator.
	public void writeRoundNumber(int numRound)
	{
		String roundIntro = "Logging the details for round ";
		
		StringBuilder roundBuilder = new StringBuilder(roundIntro);
		roundBuilder.append(numRound);
		roundBuilder.append("\n");
		roundBuilder.append(SEP);
		
		LOGGER.info(roundBuilder.toString());
	}
	

	
	//method is passed a string, which is then written to file with an
	//ending separator.
	//String has details on the cards played by each player in
	//the current round.
	
	public void writePlayingTable(String cardsOnTable)
	{
		StringBuilder tableBuilder = new StringBuilder(cardsOnTable);
		tableBuilder.append(SEP);
		
		LOGGER.info(tableBuilder.toString());
	}
	
	
	//method is passed a string, containing the name of the
	//category the active user has chosen. The String is written to file
	//with an ending separator.
	public void writeCategoryChosen(String category)
	{
		StringBuilder chosenBuilder = new StringBuilder(category);
		chosenBuilder.append("\n");
		chosenBuilder.append(SEP);
		
		LOGGER.info(chosenBuilder.toString());
	}
	
	//method is passed an ArrayList containing Strings which contain details on each
	//player's card's value in the chosen category for that round. method works out the
	//number of elements and adds each of them in turn in to a string.
	//String is then added to an introductory string and given an ending separator,
	//then written to file.
	public void writeValuesForCategory(ArrayList<String> values)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < values.size(); i++)
		{
			sb.append(values.get(i));
		}
		String scores = sb.toString();
		String valuesIntro = "The scores of the players' cards in that category are:\r\n";

		StringBuilder valuesBuilder = new StringBuilder(valuesIntro);
		valuesBuilder.append(scores + "\n");
		valuesBuilder.append(SEP);
		
		LOGGER.info(valuesBuilder.toString());
	}
	
	//method is passed a string containing the name of the round winner. String is written to file
	//with an end separator.
	public void writeRoundWinner(String winner)
	{
		StringBuilder roundWinnerBuilder = new StringBuilder(winner);
		roundWinnerBuilder.append("\n");
		roundWinnerBuilder.append(SEP);
		
		LOGGER.info(roundWinnerBuilder.toString());
	}
	

	//method is passed a string containing the names of the players and the cards
	//currently in each of their hands at the end of a round.
	//String is written to file with an introduction and an ending separator.
	public void writeResultingHands(String resultingHands)
	{
		String resultingIntro = "At the end of this round each of the players now has the following cards:\r\n";
		
		StringBuilder resultingBuilder = new StringBuilder(resultingIntro);
		resultingBuilder.append(resultingHands);
		resultingBuilder.append(SEP);
		
		LOGGER.info(resultingBuilder.toString());
	}
	//method is passed a string that contains the "Communal Pile Contents", followed by
	//the names of all cards in a communal pile. String is written to file with
	//an end separator.
	public void writeCommunalPile(String communalPile)
	{
		StringBuilder communalBuilder = new StringBuilder(communalPile);
		communalBuilder.append(SEP);
		
		LOGGER.info(communalBuilder.toString());
	}
	
	//method is passed a string with the name of the game winning player.
	//an intro and outro string sandwich this name of the player in a new string
	//which is written to file with an end separator.
	public void writeGameWinner(String gameWinner)
	{
		String winningIntro = "The game was won by ";
		String winningOutro = "END OF GAME \n";
		
		StringBuilder winBuilder = new StringBuilder(winningIntro);
		winBuilder.append(gameWinner + "\n");
		winBuilder.append(winningOutro);
		
		winBuilder.append(SEP);
		
		LOGGER.info(winBuilder.toString());
		
	}
	//method to close the FileHandler. 
	public void closeLog()
	{
		fHandler.close();
	}
	

	
	
}// closes class
