package controller;

import java.util.Scanner;

import database_testlog.DatabaseInteractor;
import database_testlog.TestLogger;
import model.Player;
import model.TTModel;
import view.TTCLIView;

public class TTController {

	private TTModel model; // model instance
	private TTCLIView view; // view instance
	private boolean writeGameLogsToFile; //boolean used in constructor to determine if controller should execute text logging.
	private Scanner systemInput = new Scanner(System.in); // User input instance
	private int readInput; // Holds user input for condition checks
	private TestLogger testLogger;
	private boolean setSlowScroll;

	private DatabaseInteractor dbI = new DatabaseInteractor(); 
	//instance of connector to database storing game statistics
	
	//Constructor
	public TTController(TTModel model, TTCLIView view, boolean writeGameLogsToFile) {
		this.model = model;
		this.view = view;
		this.setSlowScroll = false; // boolean toggles slowed console print mode
		
		if (writeGameLogsToFile) { //passed in to the constructor, true if a flag -t was used on the command line.
			this.writeGameLogsToFile = writeGameLogsToFile;
			this.testLogger = new TestLogger();
		}
	}

	// Menu loop
	public void runtimeMenu() throws InterruptedException {
		this.view.drawMain(); // draw main menu
		do {
			this.readInput = this.systemInput.nextInt();
			this.systemInput.nextLine();
			if (this.readInput == 1) {
				this.model.startGame(4);
				if (this.writeGameLogsToFile)
				{
					this.logPreRoundsActivity();
				}
				this.runtimeGame();
				
				//below logic was used in beginning a bot vs bot game, that was used in testing.
				//commented out as not needed for assignment. Reintroduction would require renumbering
				//of menu. Also would require updating of the model method startBotGame to reflect
				//final dev refactoring of model post bot vs bot removal.
//			} else if (this.readInput == 2) { // for bot vs bot game, removed
//				this.view.drawAIMenu();
//				this.readInput = this.systemInput.nextInt();
//				this.systemInput.nextLine();
//				if (this.readInput >= 6 || this.readInput <= 1) {
//					do {
//						this.view.notValid();
//						Thread.sleep(500);
//						this.view.drawAIMenu();
//						this.readInput = this.systemInput.nextInt();
//						this.systemInput.nextLine();
//					} while (this.readInput >= 6 || this.readInput <= 1);
//					this.model.startBotGame(this.readInput);
//					if (this.writeGameLogsToFile)
//					{
//						this.logPreRoundsActivity();
//					}
//				} else {
//					this.model.startBotGame(this.readInput);
//				}
//				this.runtimeGame();
			} else if (this.readInput == 2) {
				this.view.dbiDraw(this.dbI.dbRequest());
				this.readInput = this.systemInput.nextInt();
				this.systemInput.nextLine();
				if(this.readInput == 1) {
					this.runtimeMenu();
				} else {
					do {
						this.view.notValid();
						this.readInput = this.systemInput.nextInt();
						this.systemInput.nextLine();
					}while(this.readInput!=1);
					this.runtimeMenu();
				}
			} else if (this.readInput == 3) {
				if(!this.setSlowScroll) {
					this.setSlowScroll = true;
					this.view.slowModeEnabled();
					Thread.sleep(500);
					this.runtimeMenu();
				} else { 
					this.setSlowScroll = false;
					this.view.slowModeDisabled();
					Thread.sleep(500);
					this.runtimeMenu();
				}
			} else if (this.readInput == 4) {
				// closes scanner, runtime
				this.view.endRuntime();
				if(this.setSlowScroll) Thread.sleep(2000);
				this.systemInput.close();
				
				if (writeGameLogsToFile) // only if writeGameLogsToFile is true will testLogger have been opened. 
				{
					this.testLogger.closeLog();	
				}
				System.exit(0);
			} else {
				this.view.notValid();
				if(this.setSlowScroll)Thread.sleep(1000);
				this.view.drawMain();
			}
		} while (this.readInput != 4);
	}

	// Main game controller loop
	public void runtimeGame() throws InterruptedException {
		// Prints to console whether logging has been requested
		if(writeGameLogsToFile) {
			this.view.gameLogVerification();
		}
		if(this.setSlowScroll) Thread.sleep(1000);
		while (!this.model.hasWon()) {
			this.model.selectPlayer();
			this.view.currentRound();// moved after selectPlayer() to give correct starting round num as 1.
			if(this.setSlowScroll) Thread.sleep(1000);
			this.view.playersTurnHeader();
			if (this.model.getActivePlayer().getClass() == Player.class) {
				if(this.setSlowScroll) Thread.sleep(1000);
				this.view.viewCard(this.model.getActivePlayer().getTopCard());
				if(this.setSlowScroll) Thread.sleep(1000);
				this.view.selectStat();
				this.readInput = this.systemInput.nextInt();
				if (this.readInput > 5 || this.readInput < 1) {
					do {
						this.view.notValid();
						if(this.setSlowScroll) Thread.sleep(1000);
						this.view.selectStat();
						this.readInput = this.systemInput.nextInt();
					} while (this.readInput > 5 || this.readInput < 1);
				}
			} else {
				if(this.setSlowScroll) Thread.sleep(1000);
				this.view.viewCard(this.model.getActivePlayer().getTopCard());
				this.readInput = this.model.getActivePlayer().chooseCard();
			}
			this.model.playCards(this.readInput);
			this.model.selectWinners();
			this.view.chosenCategory();
			if(this.setSlowScroll) Thread.sleep(1000);
			this.view.roundWinner();
			if(this.setSlowScroll) Thread.sleep(1000);
			this.view.winningCard();
            if (this.writeGameLogsToFile)
            {
                this.logRoundReport();
            }
            this.view.removedPlayers(); 
		}
		this.view.gameWinner();
		if (this.writeGameLogsToFile)
		{
			this.testLogger.writeGameWinner(this.model.getGameWinner());
		}
		// call on model methods to supply arguments to dbI for updating db.
		this.dbI.updateDb(this.model.getGameWinner(), this.model.getNumOfDraws(), this.model.getNumOfRounds(), this.model.getAllWonRounds());
		this.view.endRuntime();
		if(this.setSlowScroll) Thread.sleep(1000);
		this.model.setNewGameStates();
		this.runtimeMenu();
	}

	// Method for generating log of the pre-round game behaviour. Calls on TestLogger with arguments of LogWriter to 
	// have TestLogger write to file the deck loaded by the program, the deck after shuffling and the hands dealt 
	// to the participating players.
	// Only called if boolean writeGameLogsToFile was set to true by argument from TopTrumpsCliApplication
	private void logPreRoundsActivity(){
		this.testLogger.writeLoadedDeck(this.model.getLogWriter().getDeckOnLoad());
		this.testLogger.writeShuffledDeck(this.model.getLogWriter().getDeckShuffle());
		this.testLogger.writeDealtHands(this.model.getLogWriter().getEveryoneHands());
	}
	
	// Method for generating log of activity in a round. Calls on TestLogger with arguments of LogWriter to 
	// have TestLogger write to file a number of outputs, allowing for the analysis of the developments of a game
	// in each round, and from round-to-round.
	// Only called if boolean writeGameLogsToFile was set to true by argument from TopTrumpsCliApplication
	private void logRoundReport(){
		this.testLogger.writeRoundNumber(this.model.getNumOfRounds());
		this.testLogger.writePlayingTable(this.model.getLogWriter().getPlayersCardsString());
		this.testLogger.writeCategoryChosen(this.model.getLogWriter().getChosenCategory());
		this.testLogger.writeValuesForCategory(this.model.getLogWriter().getEveryoneValues());
		this.testLogger.writeRoundWinner(this.model.getLogWriter().getRoundWinner());
		this.testLogger.writeResultingHands(this.model.getLogWriter().getEveryoneHands());
		this.testLogger.writeCommunalPile(this.model.getLogWriter().getCommunalPile());
	}
}