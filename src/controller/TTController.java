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

	public TTController(TTModel model, TTCLIView view, boolean writeGameLogsToFile) {
		this.model = model;
		this.view = view;
		this.setSlowScroll = false; // boolean toggles slowed console print mode

		if (writeGameLogsToFile) {
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
				this.view.drawHumanMenu();
				this.readInput = this.systemInput.nextInt();
				this.systemInput.nextLine();
				if (this.readInput >= 5 || this.readInput <= 0) {
					do {
						this.view.notValid();
						Thread.sleep(500);
						this.view.drawHumanMenu();
						this.readInput = this.systemInput.nextInt();
						this.systemInput.nextLine();
					} while (this.readInput >= 5 || this.readInput <= 0);
					this.model.startGame(this.readInput);
				} else {
					this.model.startGame(this.readInput);
					if (this.writeGameLogsToFile)
						{
							this.logPreRoundsActivity();
						}
				}
				this.runtimeGame();
			} else if (this.readInput == 2) { // for bot vs bot game
				this.view.drawAIMenu();
				this.readInput = this.systemInput.nextInt();
				this.systemInput.nextLine();
				if (this.readInput >= 6 || this.readInput <= 1) {
					do {
						this.view.notValid();
						Thread.sleep(500);
						this.view.drawAIMenu();
						this.readInput = this.systemInput.nextInt();
						this.systemInput.nextLine();
					} while (this.readInput >= 6 || this.readInput <= 1);
					this.model.startBotGame(this.readInput);
					if (this.writeGameLogsToFile)
					{
						this.logPreRoundsActivity();
					}
				} else {
					this.model.startBotGame(this.readInput);
				}
				this.runtimeGame();
			} else if (this.readInput == 3) {
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
			} else if (this.readInput == 4) {
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
			} else if (this.readInput == 5) {
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
		} while (this.readInput != 5);
	}

	// Main game controller loop
	public void runtimeGame() throws InterruptedException {
		// Prints to console whether logging has been requested
		if(writeGameLogsToFile) {
			this.view.gameLogVerification();
		}
		if(this.setSlowScroll) Thread.sleep(1000);
		while (!this.model.hasWon()) {
			this.view.currentRound();
			this.model.selectPlayer();
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
			this.view.roundWinner();
            if (this.writeGameLogsToFile)
            {
                this.logRoundReport();
            }
            //this.view.testLoggerPrints(); // calls test logger prints
            //this.view.roundWinnerCard();
            //this.view.roundWinnerCardStat();
            // number of cards in hand?
            this.view.removedPlayers(); // does not work, controller structure
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

	// methods for generating log.
	private void logPreRoundsActivity(){
		this.testLogger.writeLoadedDeck(this.model.getLogWriter().getDeckOnLoad());
		this.testLogger.writeShuffledDeck(this.model.getLogWriter().getDeckShuffle());
		this.testLogger.writeDealtHands(this.model.getLogWriter().getEveryoneHands());
	}

	private void logRoundReport(){
		this.testLogger.writeRoundNumber(this.model.getNumOfRounds());
		this.testLogger.writePlayingTable(this.model.getLogWriter().getPlayingTable());
		this.testLogger.writeCategoryChosen(this.model.getLogWriter().getChosenCategory());
		this.testLogger.writeValuesForCategory(this.model.getLogWriter().getEveryoneValues());
		this.testLogger.writeRoundWinner(this.model.getLogWriter().getRoundWinner());
		this.testLogger.writeResultingHands(this.model.getLogWriter().getEveryoneHands());
		this.testLogger.writeCommunalPile(this.model.getLogWriter().getCommunalPile());
	}
}