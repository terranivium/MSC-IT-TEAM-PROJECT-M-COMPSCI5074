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

	public void run() {
		Scanner systemInput = new Scanner(System.in); // User input instance
		this.view.drawMain(); // draw main menu
		int readInput;
		int activeStat;
		do {
			readInput = systemInput.nextInt();
			if (readInput == 1) {
				System.out.println("How many AI players do you want to play against? (Max 4)");
				int botCount = systemInput.nextInt();
				systemInput.nextLine();
				if (botCount >= 5 || botCount <= 0) {
					do {
						System.out.println("Error, please chose between 1 and 4 AI Players.");
						botCount = systemInput.nextInt();
						systemInput.nextLine();
					} while (botCount >= 5 || botCount <= 0);
				} else {
					this.model.startGame(botCount);
				}
				view.playersTurn();
				if (model.getActivePlayer().getClass() == model.getPlayers().get(0).getClass()) {
					view.viewCard(model.getActivePlayer().getHand().get(model.getActivePlayer().getTopCardIndex()));
					System.out.println("Please select a stat to play between 1 and 5.");
					activeStat = systemInput.nextInt();
					systemInput.nextLine();
					if (activeStat > 5 || activeStat < 1) {
						do {
							System.out.println("Error. Please select a stat to play between 1 and 5 to continue.");
							activeStat = systemInput.nextInt();
							systemInput.nextLine();
						} while (activeStat > 5 || activeStat < 1);
						model.getActivePlayer().setActiveStat(activeStat);
					}

				} else {
					view.viewCard(model.getActivePlayer().getHand().get(model.getActivePlayer().getTopCardIndex()));
					activeStat = model.getActivePlayer().chooseCard();
				}

				model.compareCards(activeStat);
				this.view.newGameState();

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
}