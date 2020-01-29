package controller;

import java.util.Scanner;

import model.TTModel;
import view.TTCLIView;

public class TTController{

	private TTModel model;
	private TTCLIView view;

	public TTController(TTModel model, TTCLIView view){
		this.model = model;
		this.view = view;
	}

	// Menu loop
	public void runtimeMenu(){
		Scanner systemInput = new Scanner(System.in); // User input instance
		int readInput; // Holds input int for conditions
		this.view.drawMain(); // draw main menu
		do {
			readInput = systemInput.nextInt();
			if (readInput == 1) {
				readInput = systemInput.nextInt();
				this.view.drawAIMenu();
				if (readInput >= 5 || readInput <= 0) {
					do {
						this.view.notValid();
						readInput = systemInput.nextInt();
						this.view.drawAIMenu();
					} while (readInput >= 5 || readInput <= 0);
				} else {
					this.model.startGame(readInput);
					// game starting
					runtimeGame();
				}
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
	public void runtimeGame(){
		Scanner systemInput = new Scanner(System.in); // User input instance
		int readInput;
		this.view.playersTurn();
		if (this.model.getActivePlayer().getClass() == model.getPlayers().get(0).getClass()) {
			readInput = systemInput.nextInt();
			this.view.viewCard(this.model.getActivePlayer().getHand().get(this.model.getActivePlayer().getTopCardIndex()));
			this.view.selectStat();
			if (readInput > 5 || readInput < 1) {
				do {
					this.view.notValid();
					readInput = systemInput.nextInt();
					this.view.selectStat();
				} while (readInput > 5 || readInput < 1);
				this.model.getActivePlayer().setActiveStat(readInput); // dan not sure about this line's position
				}
			} else {
				this.view.viewCard(this.model.getActivePlayer().getHand().get(this.model.getActivePlayer().getTopCardIndex()));
				readInput = this.model.getActivePlayer().chooseCard();
			}
		this.model.compareCards(readInput);
		this.view.newGameState();
	}

	public void runtimeStats(){
		// method to write stats call loops for view and model
	}
}