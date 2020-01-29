package controller;

import java.util.Scanner;

import model.TTModel;
import view.TTCLIView;

public class TTController {

	private TTModel model;
	private TTCLIView view;

	public TTController(TTModel model, TTCLIView view) {
		this.model = model;
		this.view = view;
	}

	public void runtimeMenu(){
		Scanner systemInput = new Scanner(System.in); // User input instance
		this.view.drawMain(); // draw main menu
		int readInput;
		do {
			readInput = systemInput.nextInt();
			if (readInput == 1) {
				this.view.drawAIMenu();
				readInput = systemInput.nextInt();
				if (readInput >= 5 || readInput <= 0) {
					do {
						this.view.notValid();
						System.out.println("Error, please chose between 1 and 4 AI Players.");
						readInput = systemInput.nextInt();
					} while (readInput >= 5 || readInput <= 0);
				} else {
					this.model.startGame(readInput);
				}
				// game starting
				this.runtimeGame();
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

	public void runtimeGame(){
		this.view.playersTurn();
		if (this.model.getActivePlayer().getClass() == model.getPlayers().get(0).getClass()) {
			this.view.viewCard(this.model.getActivePlayer().getHand().get(this.model.getActivePlayer().getTopCardIndex()));
			this.view.selectStat();
			int readInput = systemInput.nextInt();
			if (readInput > 5 || readInput < 1) {
				do {
					this.view.notValid();
					System.out.println("Error. Please select a stat to play between 1 and 5 to continue.");
					readInput = systemInput.nextInt();
				} while (readInput > 5 || readInput < 1);
					this.model.getActivePlayer().setActiveStat(readInput);
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