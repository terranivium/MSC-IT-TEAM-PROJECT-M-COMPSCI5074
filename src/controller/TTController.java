package controller;

import java.util.Scanner;

import model.Player;
import model.TTModel;
import view.TTCLIView;

public class TTController {

	private TTModel model;
	private TTCLIView view;

	public TTController(TTModel model, TTCLIView view) {
		this.model = model;
		this.view = view;
	}

	// Menu loop
	public void runtimeMenu() {
		Scanner systemInput = new Scanner(System.in); // User input instance
		int readInput; // Holds input int for conditions
		this.view.drawMain(); // draw main menu
		do {
			readInput = systemInput.nextInt();
			systemInput.nextLine();
			if (readInput == 1) {
				this.view.drawAIMenu();
				readInput = systemInput.nextInt();
				systemInput.nextLine();
				if (readInput >= 5 || readInput <= 0) {
					do {
						this.view.notValid();
						this.view.drawAIMenu();
						readInput = systemInput.nextInt();
						systemInput.nextLine();
					} while (readInput >= 5 || readInput <= 0);
					this.model.startGame(readInput);
				} else {
					this.model.startGame(readInput);
				}
				runtimeGame();
			} else if (readInput == 4) {
				// closes scanner
				systemInput.close();
			} else {
				// to catch invalid input
				this.view.notValid();
				this.view.drawMain();
			}
		} while (readInput != 1 || readInput != 4);
	}

	// Main game controller loop
	public void runtimeGame() {
		while (model.hasWon() == false) {
			this.model.selectPlayer();
			Scanner systemInput = new Scanner(System.in); // User input instance
			int readInput;
			this.view.playersTurnHeader();
			if (this.model.getActivePlayer().getClass() == Player.class) {
				this.view.viewCard(this.model.getActivePlayer().getTopCard());
				this.view.selectStat();
				readInput = systemInput.nextInt();
				if (readInput > 5 || readInput < 1) {
					do {
						this.view.notValid();
						this.view.selectStat();
						readInput = systemInput.nextInt();
					} while (readInput > 5 || readInput < 1);
				}
			} else {
				this.view.viewCard(this.model.getActivePlayer().getTopCard());
				readInput = this.model.getActivePlayer().chooseCard();
			}
			this.model.compareCards(readInput);
			this.view.newGameState();
		} 
		//System.out.println("The winner of the game was " + model.getGameWinner()); //game winner view goes here
	}

	public void runtimeStats() {
		// method to write stats call loops for view and model
	}
}