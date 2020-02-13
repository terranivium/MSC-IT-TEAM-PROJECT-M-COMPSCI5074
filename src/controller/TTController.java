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

	private DatabaseInteractor dbI = new DatabaseInteractor(); //instance of
	// connector to database storing game statistics

	public TTController(TTModel model, TTCLIView view, boolean writeGameLogsToFile) {
		this.model = model;
		this.view = view;

		if (writeGameLogsToFile) {
			this.writeGameLogsToFile = writeGameLogsToFile;
			testLogger = new TestLogger();
		}
	}

	// Menu loop
	public void runtimeMenu() {
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
						this.view.drawHumanMenu();
						this.readInput = this.systemInput.nextInt();
						this.systemInput.nextLine();
					} while (this.readInput >= 5 || this.readInput <= 0);
					this.model.startGame(this.readInput);

				} else {
					this.model.startGame(this.readInput);
					if (this.writeGameLogsToFile)
						{
							logPreRoundsActivity();
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
						this.view.drawAIMenu();
						this.readInput = this.systemInput.nextInt();
						this.systemInput.nextLine();
					} while (this.readInput >= 6 || this.readInput <= 1);
					this.model.startBotGame(this.readInput);
					if (this.writeGameLogsToFile)
					{
						logPreRoundsActivity();
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
						this.view.dbiDraw(this.dbI.dbRequest());
						this.readInput = this.systemInput.nextInt();
						this.systemInput.nextLine();
					}while(this.readInput!=1);
				}
			} else if (this.readInput == 4) {
				// closes scanner, runtime
				this.view.endRuntime();
				this.systemInput.close();
				this.testLogger.closeLog(); // will be used to close testLogger.
				System.exit(0);
			} else {
				// to catch invalid input
				this.view.notValid();
				this.view.drawMain();
			}
		} while (this.readInput != 1 || this.readInput != 4);
	}

	// Main game controller loop
	public void runtimeGame() {

		// Prints to console whether logging has been requested
		System.out.println(writeGameLogsToFile);
		while (this.model.hasWon() == false) {
			this.model.selectPlayer();
			this.view.playersTurnHeader();
			if (this.model.getActivePlayer().getClass() == Player.class) {
				this.view.viewCard(this.model.getActivePlayer().getTopCard());
				this.view.selectStat();
				this.readInput = this.systemInput.nextInt();
				if (this.readInput > 5 || this.readInput < 1) {
					do {
						this.view.notValid();
						this.view.selectStat();
						this.readInput = this.systemInput.nextInt();
					} while (this.readInput > 5 || this.readInput < 1);
				}
			} else {
				this.view.viewCard(this.model.getActivePlayer().getTopCard());
				this.readInput = this.model.getActivePlayer().chooseCard();
			}
			this.model.playCards(this.readInput);
			this.model.selectWinners();
            if (this.writeGameLogsToFile)
            {
                logRoundReport();
            }

            //below system.out can be removed
            System.out.println("The current round is " + this.model.getNumOfRounds());
            if (this.model.getNumOfRounds() == 1)
            {
                System.out.println(this.model.getLogWriter().getDeckOnLoad());
                System.out.println(this.model.getLogWriter().getDeckShuffle());
            }
			System.out.println(this.model.getlogWriter().getDeckOnLoad());
			System.out.println(this.model.getlogWriter().getDeckShuffle());
			System.out.println(this.model.getlogWriter().getEveryoneHands());
			System.out.println(this.model.getlogWriter().getCommunalPile());
			System.out.println(this.model.getlogWriter().getPlayingTable());
			System.out.println(this.model.getlogWriter().getChosenCategory());
			System.out.println(this.model.getlogWriter().getEveryoneValues());
			System.out.println(this.model.getlogWriter().getRoundWinner());
		}
		this.view.gameWinner();
		if (this.writeGameLogsToFile)
		{
			this.testLogger.writeGameWinner(this.model.getGameWinner());
		}

		this.dbI.updateDb(this.model.getGameWinner(), this.model.getNumOfDraws(), this.model.getNumOfRounds(), this.model.getAllWonRounds());//calls on model
																											// methods to supply arguments to dbI for updating db.

		this.model.setNewGameStates();
		this.runtimeMenu();
	}

	public void runtimeStats() {
		// for drawing stats in commandline, similar to write test log feature
	}


	// methods for generating log.
	private void logPreRoundsActivity()
	{
		this.testLogger.writeLoadedDeck(this.model.getLogWriter().getDeckOnLoad());
		this.testLogger.writeShuffledDeck(this.model.getLogWriter().getDeckShuffle());
		this.testLogger.writeDealtHands(this.model.getLogWriter().getEveryoneHands());
	}

	private void logRoundReport()
	{
		this.testLogger.writeRoundNumber(this.model.getNumOfRounds());
		this.testLogger.writePlayingTable(this.model.getLogWriter().getPlayingTable());
		this.testLogger.writeCategoryChosen(this.model.getLogWriter().getChosenCategory());
		this.testLogger.writeValuesForCategory(this.model.getLogWriter().getEveryoneValues());
		this.testLogger.writeRoundWinner(this.model.getLogWriter().getRoundWinner());
		this.testLogger.writeResultingHands(this.model.getLogWriter().getEveryoneHands());
		this.testLogger.writeCommunalPile(this.model.getLogWriter().getCommunalPile());
	}
}