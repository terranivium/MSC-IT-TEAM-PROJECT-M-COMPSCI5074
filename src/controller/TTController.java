package controller;

import java.util.Scanner;

//import database_testlog.DatabaseInteractor;
import model.Player;
import model.TTModel;
import view.TTCLIView;

public class TTController {

	private TTModel model; // model instance
	private TTCLIView view; // view instance
	private Scanner systemInput = new Scanner(System.in); // User input instance
	private int readInput; // Holds user input for condition checks
	// private DatabaseInteractor dbI = new DatabaseInteractor(); //instance of
	// connector to database storing game statistics

	public TTController(TTModel model, TTCLIView view) {
		this.model = model;
		this.view = view;
	}

	// Menu loop
	public void runtimeMenu() {
		this.view.drawMain(); // draw main menu
		do {
			this.readInput = this.systemInput.nextInt();
			this.systemInput.nextLine();
			if (this.readInput == 1) {
				this.view.drawAIMenu();
				this.readInput = this.systemInput.nextInt();
				this.systemInput.nextLine();
				if (this.readInput >= 5 || this.readInput <= 0) {
					do {
						this.view.notValid();
						this.view.drawAIMenu();
						this.readInput = this.systemInput.nextInt();
						this.systemInput.nextLine();
					} while (this.readInput >= 5 || this.readInput <= 0);
					this.model.startGame(this.readInput);
				} else {
					this.model.startGame(this.readInput);
				}
				this.runtimeGame();
			} else if (this.readInput == 2) { // for bot game
				this.view.drawAIMenu();
				this.readInput = this.systemInput.nextInt();
				this.systemInput.nextLine();
				if (this.readInput >= 6 || this.readInput <= 0) {
					do {
						this.view.notValid();
						this.view.drawAIMenu();
						this.readInput = this.systemInput.nextInt();
						this.systemInput.nextLine();
					} while (this.readInput >= 5 || this.readInput <= 0);
					this.model.startBotGame(this.readInput);
				} else {
					this.model.startBotGame(this.readInput);
				}
				this.runtimeGame();
			} else if (this.readInput == 4) {
				// closes scanner and runtime
				this.view.endRuntime();
				this.systemInput.close();
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
		while (this.model.hasWon() == false) {
			this.model.selectPlayer();
			this.view.selectPlayer();
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
			this.view.compareCards(this.readInput);
			this.model.compareCards(this.readInput);
		}
		this.view.gameWinner();
		// this.dbI.updateDb(this.model.getGameWinner(), this.model.getNumOfDraws(),
		// this.model.getNumOfRounds(), this.model.getAllWonRounds());//calls on model
		// methods to supply arguments to dbI for updating db.
		// this.dbI.dbRequest(); // testline - delete
		this.model.setNewGameStates();
		this.runtimeMenu();
	}

	public void runtimeStats() {
		// for drawing stats in comandline, similar to write test log feature
	}
}