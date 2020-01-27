package view;

import java.util.Scanner;

import model.Card;
import model.TTModel;

public class TTCLIView{

	private TTModel model;

	public TTCLIView(TTModel model){
		this.model = model;
	}

	// draw CLI main menu
	public void drawMain(){
		System.out.println("--------------------------------");
		System.out.println("| Froggo Trumps, test_ver. 0.1 |");
		System.out.println("--------------------------------");
		System.out.println("Select mode...--------------");
		System.out.println("	1. New Player Game");
		//System.out.println("	2. AI vs AI Autoplay")
		//System.out.println("	3. View runtime stats")
		System.out.println("	4. Quit");
		System.out.println("----------------------------");
		this.drawMenu = true;
	}

	// main hand view for CLI
	public void viewHand(Turn currentTurn){
		ArrayList<Card> currentHand = currentTurn.getTurnHand(); // get current list of cards
		for(Card card : currentHand){

			// put these in Card.toString()?
			// See Dan regarding Card class

			String playerName = card.getOwner().getName();
			int numHand = card.getOwner().getOwnerNumCards();
			String cardName = card.getName();
			String statName = Card.getStats()[currentTurn.getSelectedStat()];
			int statValue = card.getStat(currentTurn.getSelectedStat());

			String turnText = String.format("Player: %s has %d in their hand.\n %-15s %s: %2d" ,playerName,inHand,cardName,statName,statvalue);
			System.out.println(turnText);
		}
	}

	public void viewCard(Card selectCard){

	}

	public void newGameState(){
		
	}

	public void runTime(){
		Scanner systemInput = new Scanner(System.in); // User input instance
		this.drawMain(); // draw main menu options
		int readInput = "";
		do{
			if(this.readInput == 1){
				this.newGameState();
			} else if(readInput == 4){
				this.systemInput.close();
			} else{
				System.out.println("Please select a valid menu option...");
				this.drawMain();
			}
		} while(this.readInput != 1 || this.readInput != 4);
		this.systemInput.close();
	}
}