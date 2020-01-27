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

	public void run(){
		Scanner systemInput = new Scanner(System.in); // User input instance
		this.view.drawMain(); // draw main menu
		int readInput;
		do{
			readInput = systemInput.nextInt();
			if(readInput == 1){ 
				System.out.println("How many AI players do you want to play against? (Max 4)");
				int botCount = systemInput.nextInt();
				systemInput.nextLine();
				if (botCount >= 5 || botCount <= 0) {
					System.out.println("Error, please chose between 1 and 4 AI Players.");
					botCount = systemInput.nextInt();
					systemInput.nextLine();
				}
				else {
					this.model.startGame(botCount);
				}
			
				if(model.getPlayers().get(model.)

				this.view.newGameState();


			} else if(readInput == 4){
				// closes scanner
				systemInput.close();
			} else{
				// to catch invalid input
				this.view.notValid();
				this.view.drawMain();
			}
		} while(readInput != 1 || readInput != 4);
	}
}