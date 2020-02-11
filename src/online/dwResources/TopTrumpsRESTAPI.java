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
import view.TTOnlineView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import controller.TTController;
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
	private TTOnlineView view;
	private int botCount;
	
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		
		boolean writeGameLogsToFile = false;
		this.model = new TTModel(); // pass writeGameLogsToFile here
		this.view = new TTOnlineView(model);
		this.botCount = conf.getNumAIPlayers();
		this.model.setNewGameStates();
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
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public void startGame(){
		this.model.startGame(botCount);
	}
	
	@GET
	@Path("/selectPlayer")
	/**
	 * @param None
	 * @return - A String
	 * @throws IOException
	 */
	public void selectPlayer() throws IOException{
		this.model.selectPlayer();
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
		this.model.selectWinners();
		System.out.println("The winner of the round was" + model.getRoundWinnerName());
		String roundWinnerAsJSONString = oWriter.writeValueAsString(this.model.getRoundWinnerName());
		return roundWinnerAsJSONString;
	}
	
	@GET
	@Path("/hasWon")
	/**
	 * @param None
	 * @return - A String
	 * @throws IOException
	 */
	public void hasWon() throws IOException{
		this.model.hasWon();
	}
	
	@GET
	@Path("/drawMain")
	/**
	 * @param None
	 * @return - A String
	 * @throws IOException
	 */
	public String drawMain() throws IOException {
		this.view.drawMain();
		List<String> drawMainOutput = this.view.getOutputBuffer();
		String listAsJSONString = oWriter.writeValueAsString(drawMainOutput);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/drawAIMenu")
	/**
	 * @param None
	 * @return - A String
	 * @throws IOException
	 */
	public String drawAIMenu() throws IOException{
		this.view.drawAIMenu();
		List<String> drawMainOutput = this.view.getOutputBuffer();
		String listAsJSONString = oWriter.writeValueAsString(drawMainOutput);

		return listAsJSONString;
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
	@Path("/showCard")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String showCard() throws IOException {
		String topCardAsJSONString = oWriter.writeValueAsString(this.model.getActivePlayer().getTopCard().getDescription());
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
		String activePlayerAsJSONString = oWriter.writeValueAsString(this.model.getActivePlayer().getName() + " is currently the active player");
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
		String roundCounterAsJSONString = oWriter.writeValueAsString("It is currently Round: " + (this.model.getNumOfRounds()));
		return roundCounterAsJSONString;
	}
}
