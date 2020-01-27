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

	public void setView(TTCLIView newView){
		this.view = newView;
	}

	public TTCLIView getView(){
		return this.view;
	}

	public void setModel(TTModel newModel){
		this.model = newModel;
	}

	public TTModel getModel(){
		return this.model;
	}

	public void run(){
		Scanner systemInput = new Scanner(System.in); // User input instance
		this.view.drawMain(); // draw main menu
		int readInput;
		do{
			readInput = systemInput.nextInt();
			if(readInput == 1){ 
				// @dan, might be worth creating a new game method in model that runs all of the intial 
				// setup methods together in one go
				this.model.addPlayers();
				this.model.loadDeck();
				this.model.dealCards();
				this.model.choosePlayer();

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