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
		//System.out.println("	2. AI vs AI Autoplay") // Bot class
		//System.out.println("	3. View runtime stats") // DB
		System.out.println("	4. Quit");
		System.out.println("----------------------------");
	}

	public void notValid(){
		System.out.println("Please select a valid menu option...");
	}

	// // if we want to view a players hand?
	// public void viewHand(Turn currentTurn){
	// 	ArrayList<Card> currentHand = currentTurn.getTurnHand(); // get current list of cards
	// 	for(Card card : currentHand){

	// 		//String playerName = card.getOwner().getName();
	// 		//int numHand = card.getOwner().getOwnerNumCards();
	// 		String cardName = card.getName();
	// 		//String statName = Card.getStats()[currentTurn.getSelectedStat()];
	// 		int statValue = card.getStat(currentTurn.getSelectedStat());

	// 		String turnText = String.format("Player: %s has %d in their hand.\n %-15s %s: %2d" ,playerName,inHand,cardName,statName,statvalue);
	// 		System.out.println(turnText);
	// 	}
	// }

	public void viewCard(Card selectCard){
		// View for the current card/this turn card
	}

	public void newGameState(){
		// View for the start of the game/first round
	}
}