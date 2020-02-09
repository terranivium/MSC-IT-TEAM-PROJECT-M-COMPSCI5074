//FAO RICHARD
//
// This is the desired solution, and I think should work in jdk 1.8, but see below.
//The other test classes of BufferedWriterTest, FileRead and LogTestFile all result 
//in the same outcomes - fine in jdk12.0.2, failing in jdk1.8 - see those classes for error details.

// In current this class uses a main method to implement a logger, filehandler and SimpleFormatter for quick testing of the problem.
//
//The class methods and constructor as they have currently been developed appear in lines 78 to 210
//
// both the quick main method version and the proper class version generate these same results:
// in jdk12.0.2 successfully creates log file
// in jdk1.8 results in console:
//
//"Problem opening file.
//java.nio.file.AccessDeniedException: toptrumps.log.lck
//	at sun.nio.fs.WindowsException.translateToIOException(WindowsException.java:83)
//	at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:97)
//	at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:102)
//	at sun.nio.fs.WindowsFileSystemProvider.newFileChannel(WindowsFileSystemProvider.java:115)
//	at java.nio.channels.FileChannel.open(FileChannel.java:287)
//	at java.nio.channels.FileChannel.open(FileChannel.java:335)
//	at java.util.logging.FileHandler.openFiles(FileHandler.java:478)
//	at java.util.logging.FileHandler.<init>(FileHandler.java:310)
//	at TestLogger.main(TestLogger.java:23)
//Feb 05, 2020 3:49:21 PM TestLogger main
//INFO: Test input
//Feb 05, 2020 3:49:21 PM TestLogger main
//INFO: More test input
//Exception in thread "main" java.lang.NullPointerException
//	at TestLogger.main(TestLogger.java:42)"
//

// ==========================end of comment to richard============================
//
//

//========================simple main method for testing purposes==========================
//
package database_testlog;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


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
	
//	//method to be called to write content of main deck to file upon load and then after shuffle.
//	//currently relies upon a getDeck() method
//	//waiting for confirmation of model design before completing method 
//	
//	public void writeLoadedDeck(ArrayList<Card> loaded, String[] headerNames) 
//	{
//		
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
//		
//		
//	}
//
//	//method to be called to write content of shuffled deck to file.
//	//currently relies upon a getDeck() method
//	//waiting for confirmation of model design before completing method 
//
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
//
//	//method to be called to write out each of the dealt hands and the names
//	//of their respective recipients at the opening of a game
//	
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
//	
//	//method to be called to write out all players current cards for each round
//	
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
//	
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
//	
//	public void closeLog()
//	{
//		fHandler.close();
//	}
	
//////////////////////////// developed to this point, further methods required, below	

//		
//	}
//	
//	//method to be called whenever cards are added or removed from the communal pile
//	//currently relies up a getDiscardPile() method
//	public void writePile(ArrayList<Card> pile) 
//	{
//		//go through the communal pile and list the card details
//	}
//	

//		
//	}
//	
//		
//	}
//	//method to be called when the game has been won and the winner is written to the testlog. This is the final entry of the log of each game.
//	//currently relies upon getName() of player class.
//	public void writeWinner(Player winner)
//	{
//		//will write the winner
//	}
//	
//	
	
	
}// closes class
