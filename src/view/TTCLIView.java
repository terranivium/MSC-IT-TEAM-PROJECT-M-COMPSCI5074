package view;

import java.util.Scanner;

public class TTCLIView{

	private TTModel model;

	public TTCLIView(TTModel model){
		this.model = model;
	}

	public void menuScreen(){
		System.out.println("Froggo Trumps, ver. 0.1");
		System.out.println("Select mode:");
		System.out.println("1. New Game");
		//System.out.println("2. Quit");
	}

	public void newGameState(){

	}

	public void runTime(){
		Scanner systemInput = new Scanner(System.in);
		this.menuScreen();
		int readInput = systemInput.nextInt();
		do{
			if(readInput == 1){
				this.newGameState();
			} else if(readInput == 2){

			} else{
				System.out.println("Please select a valid menu option...");
			}
		} while(readInput != 1 || readInput != 2);
	}
}