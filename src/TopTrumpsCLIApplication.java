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

		boolean writeGameLogsToFile = false; // 
		if (args[0].equalsIgnoreCase("true")){
			writeGameLogsToFile = true; // Command line selection
		}
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Create MVC instances
		TTModel model = new TTModel(); // removed writeGameLogsToFile - this will be handled by the controller
		TTCLIView view = new TTCLIView(model);
		TTController controller = new TTController(model, view, writeGameLogsToFile); //


		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			try {
				controller.runtimeMenu();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			userWantsToQuit=true; // use this when the user wants to exit the game
		}
	}
}
