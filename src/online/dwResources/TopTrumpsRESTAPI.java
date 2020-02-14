package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import controller.TTController;
import model.Bot;
import model.Card;
import model.Player;
import model.TTModel;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	
	/**
	 * Constructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	private TTModel model;
	private int botCount;
	
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		
		boolean writeGameLogsToFile = false;
		this.model = new TTModel(); // pass writeGameLogsToFile here
		this.botCount = conf.getNumAIPlayers();
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}
	
	@GET
	@Path("/startGame")
	/**
	 * @param None
	 * @return - A String
	 * @throws IOException
	 */
	public String startGame() throws IOException{
		this.model.startGame(this.botCount);
		String headerNamesAsJSONString = oWriter.writeValueAsString(this.model.getDeck().getHeaderNames());
		return headerNamesAsJSONString;
	}
	
	@GET
	@Path("/buildRoundCards")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String buildRoundCards() throws IOException{
		String topCardsAsJSONString;
		ArrayList<Card> cards = new ArrayList<Card>();
		for(Player p: this.model.getPlayers()) {
			cards.add(p.getTopCard());
		}
		topCardsAsJSONString = oWriter.writeValueAsString(cards);
		return  topCardsAsJSONString;
	}
	
	@GET
	@Path("/selectPlayer")
	/**
	 * @param None
	 * @return - A String
	 * @throws IOException
	 */
	public String selectPlayer() throws IOException{
		String isBotAsJSONString;
		this.model.selectPlayer();
		if(this.model.getActivePlayer().getClass() == Bot.class) {
			isBotAsJSONString = oWriter.writeValueAsString("true");
		}
		else {
			isBotAsJSONString = oWriter.writeValueAsString("false");
		}
		return isBotAsJSONString;
	}
	
	@GET
	@Path("/playCards")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public void playCards(@QueryParam("Stat") int Stat) throws IOException {
		System.out.println("This is the value of the stat sent from api:" + Stat);
		this.model.playCards(Stat);
	}
	
	@GET
	@Path("/selectWinners")
	/**
	 * @param None
	 * @return - A String
	 * @throws IOException
	 */
	public String selectWinners() throws IOException{
		String roundWinnerAsJSONString;
		this.model.selectWinners();
		if(this.model.isDraw()) {
			roundWinnerAsJSONString = oWriter.writeValueAsString("DRAW");
		}
		else {
		roundWinnerAsJSONString = oWriter.writeValueAsString(this.model.getRoundWinnerName() + " has won the round");
		}
		return roundWinnerAsJSONString;
	}
	
	@GET
	@Path("/hasWon")
	/**
	 * @param None
	 * @return - A String
	 * @throws IOException
	 */
	public String hasWon() throws IOException{
		String hasWonAsJSONString = oWriter.writeValueAsString(this.model.hasWon());
	return hasWonAsJSONString;
	}

	@GET
	@Path("/getBotChoice")
	/**
	 * @param None
	 * @return - A String
	 * @throws IOException
	 */
	public String getBotChoice() throws IOException{
		int choice = this.model.getActivePlayer().chooseCard();
	String botChoiceAsJSONString = oWriter.writeValueAsString(choice);
	return botChoiceAsJSONString;
	}

	@GET
	@Path("/setNewGameState")
	/**
	 * @param None
	 * @return - A String
	 * @throws IOException
	 */
	public void setNewGameStates() throws IOException{
		this.model.setNewGameStates();
	}
	
	@GET
	@Path("/showCardStats")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String showCard() throws IOException {
		String topCardAsJSONString = oWriter.writeValueAsString(this.model.getPlayers().get(0).getTopCard().getDescription());
		return topCardAsJSONString;
	}
	
	@GET
	@Path("/updateActivePlayer")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String updateActivePlayer() throws IOException {
		String activePlayerAsJSONString = oWriter.writeValueAsString(this.model.getActivePlayer().getName());
		return activePlayerAsJSONString;
	}
	
	@GET
	@Path("/updateRoundCounter")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String updateRoundCounter() throws IOException {
		String roundCounterAsJSONString = oWriter.writeValueAsString("Round " + (this.model.getNumOfRounds()));
		return roundCounterAsJSONString;
	}
}
