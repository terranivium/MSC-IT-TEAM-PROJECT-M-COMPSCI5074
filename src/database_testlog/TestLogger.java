
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
	
	//public void writeLoadedDeck(ArrayList<Card> loaded, String[] headerNames) 
	
	public void writeLoadedDeck(String loadedDeck)
	{
		StringBuffer sb = new StringBuffer();
		
		String deckIntro = "The deck has been loaded, with cards bearing these attributes:\r\n";
		StringBuilder deckBuilder = new StringBuilder(deckIntro);
		deckBuilder.append(loadedDeck);
		deckBuilder.append(SEP);
		
		LOGGER.info(deckBuilder.toString());
		
		//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < headerNames.length; i++)
//		{
//			sb.append(headerNames[i] + " ");
//		}
//		String categories = sb.toString();
//		String deckIntro = "The deck has been loaded, with cards bearing these attributes:\r\n"; 
//		
//		StringBuilder deckBuilder = new StringBuilder(deckIntro);
//		deckBuilder.append(categories + "\n");
//		
//		for(Card c : loaded) 
//		{
//			deckBuilder.append(c.getDescription() + " ");
//			deckBuilder.append(c.getCategoryOne() + " ");
//			deckBuilder.append(c.getCategoryTwo() + " ");
//			deckBuilder.append(c.getCategoryThree() + " ");
//			deckBuilder.append(c.getCategoryFour() + " ");
//			deckBuilder.append(c.getCategoryFive() + "\n");
//		}
//		
//		deckBuilder.append(SEP);
//		
//		LOGGER.info(deckBuilder.toString());
		
		
	}

	//method to be called to write content of shuffled deck to file.
	//currently relies upon a getDeck() method
	//waiting for confirmation of model design before completing method 

	public void writeShuffledDeck(String shuffledDeck)
	{
		String shuffledIntro = "The deck has been shuffled in to the following order:\r\n";
		
		StringBuilder shuffledBuilder = new StringBuilder(shuffledIntro);
		shuffledBuilder.append(shuffledDeck);
		shuffledBuilder.append(SEP);
		
		LOGGER.info(shuffledBuilder.toString());
	}
	
//	public void writeShuffledDeck(ArrayList<Card> shuffled) 
//	{
//		String shuffledIntro = "The deck has been shuffled into the following order:\r\n"; 
//		
//		StringBuilder shuffledBuilder = new StringBuilder(shuffledIntro);
//		
//		for (Card c : shuffled)
//		{
//			shuffledBuilder.append(c.getDescription());
//		}
//		
//		shuffledBuilder.append(SEP);
//		
//		LOGGER.info(shuffledBuilder.toString());
//		
//	}

	//method to be called to write out each of the dealt hands and the names
	//of their respective recipients at the opening of a game
	
	
	public void writeDealtHands(String dealtHands)
	{
		String dealtIntro = "Each of the players has received the following cards and is ready to start the game:\r\n";
		
		StringBuilder dealtBuilder = new StringBuilder(dealtIntro);
		dealtBuilder.append(dealtHands);
		dealtBuilder.append(SEP);
		
		LOGGER.info(dealtBuilder.toString());
	}
	
	public void writeRoundNumber(int numRound)
	{
		String roundIntro = "Logging the details for round ";
		
		StringBuilder roundBuilder = new StringBuilder(roundIntro);
		roundBuilder.append(numRound);
		roundBuilder.append("\n");
		roundBuilder.append(SEP);
		
		LOGGER.info(roundBuilder.toString());
	}
	
//	public void writeDealtHands(ArrayList<Player> players)
//	{
//		String dealtIntro = "Each of the players has received the following cards:\r\n";
//		
//		StringBuilder dealtBuilder = new StringBuilder(dealtIntro);
//		
//		for (Player p : players)
//		{
//			dealtBuilder.append(p.getName() + "\n");
//			dealtBuilder.append(p.getHand().size() + " cards\n");
//			for (Card c : p.getHand())
//			{
//				dealtBuilder.append(c.getDescription() + "\n");
//			}
//		
//		dealtBuilder.append("\n");	
//		}
//		
//		dealtBuilder.append(SEP);
//		
//		LOGGER.info(dealtBuilder.toString());
//	}
	
	//method to be called to write out all players current cards for each round
	
	public void writePlayingTable(String cardsOnTable)
	{
		StringBuilder tableBuilder = new StringBuilder(cardsOnTable);
		tableBuilder.append(SEP);
		
		LOGGER.info(tableBuilder.toString());
	}
	
	
//	public void writePlayingTable(ArrayList<Player> players, ArrayList<Card> cardsInPlay, String[] headerNames, int numRounds)
//	{
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < headerNames.length; i++)
//		{
//			sb.append(headerNames[i] + " ");
//		}
//		String categories = sb.toString();
//		String tableIntro = "In round " + numRounds + " the current cards of all players are:\r\n"; 
//		
//		StringBuilder tableBuilder = new StringBuilder(tableIntro);
//				
//		for (Player p : players) 
//		{
//			tableBuilder.append(p.getName() + " has\n");
//			tableBuilder.append(categories +"\n");
//		}
//			for (Card c : cardsInPlay) 
//			{
//				tableBuilder.append(c.getDescription());
//				tableBuilder.append(c.getCategoryOne());
//				tableBuilder.append(c.getCategoryTwo());
//				tableBuilder.append(c.getCategoryThree());
//				tableBuilder.append(c.getCategoryFour());
//				tableBuilder.append(c.getCategoryFive());
//			}	
//		
//		tableBuilder.append(SEP);
//		
//		LOGGER.info(tableBuilder.toString());
//	}
	
	public void writeCategoryChosen(String category)
	{
		StringBuilder chosenBuilder = new StringBuilder(category);
		chosenBuilder.append("\n");
		chosenBuilder.append(SEP);
		
		LOGGER.info(chosenBuilder.toString());
	}
	
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
	
	public void writeRoundWinner(String winner)
	{
		StringBuilder roundWinnerBuilder = new StringBuilder(winner);
		roundWinnerBuilder.append("\n");
		roundWinnerBuilder.append(SEP);
		
		LOGGER.info(roundWinnerBuilder.toString());
	}
	
	
//	public void writeCategoryChosen(HashMap playerStats, Player activePlayer, int categoryChosen, String[] headerNames)
//	{
//		String category = headerNames[categoryChosen];  
//		String chosenIntro = "The active player is " +activePlayer.getName() + " and they have chosen " + category + "\n";
//		
//		StringBuilder chosenBuilder = new StringBuilder(chosenIntro);
//		chosenBuilder.append("The corresponding values of each players' cards are: " + playerStats.values() +"\n");
//		
//		chosenBuilder.append(SEP);
//		
//		LOGGER.info(chosenBuilder.toString());
//	}
	
	public void writeResultingHands(String dealtHands)
	{
		String dealtIntro = "At the end of this round each of the players now has the following cards:\r\n";
		
		StringBuilder dealtBuilder = new StringBuilder(dealtIntro);
		dealtBuilder.append(dealtHands);
		dealtBuilder.append(SEP);
		
		LOGGER.info(dealtBuilder.toString());
	}
	
	public void writeCommunalPile(String communalPile)
	{
		StringBuilder communalBuilder = new StringBuilder(communalPile);
		communalBuilder.append(SEP);
		
		LOGGER.info(communalBuilder.toString());
	}
	
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
	
	public void closeLog()
	{
		fHandler.close();
	}
	

	
	
}// closes class
