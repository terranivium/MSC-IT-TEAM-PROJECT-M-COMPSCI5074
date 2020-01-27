package commandline;

import controller.TTController;
import model.TTModel;
import view.TTCLIView;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")){
			writeGameLogsToFile = true; // Command line selection
		}
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Create MVC instances
		TTModel model = new TTModel(writeGameLogsToFile);
		TTCLIView view = new TTCLIView(model);
		TTController controller = new TTController(model, view);


		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			controller.run();

			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}


	}

}
