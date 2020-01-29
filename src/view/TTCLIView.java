package view;

import java.util.Scanner;

import model.Card;
import model.Player;
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
		System.out.println("	3. View runtime stats") // DB
		System.out.println("	4. Quit");
		System.out.println("----------------------------");
	}

	public void drawAIMenu(){
		System.out.println("How many AI players do you want to play against? (Max 4)");
	}

	public void selectStat(){
		System.out.println("Please select a stat to play between 1 and 5.");
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
		System.out.println("///////////////////////");
		System.out.println(selectCard.getHeaderNames()[0] + " : " + selectCard.getName());
		System.out.println("1. " + selectCard.getHeaderNames()[1] + " : " + selectCard.getSize());
		System.out.println("2. " + selectCard.getHeaderNames()[2] + " : " + selectCard.getRarity());
		System.out.println("3. " + selectCard.getHeaderNames()[3] + " : " + selectCard.getTemperament());
		System.out.println("4. " + selectCard.getHeaderNames()[4] + " : " + selectCard.getIntelligence());
		System.out.println("5. " + selectCard.getHeaderNames()[5] + " : " + selectCard.getCuteness());
		System.out.println("///////////////////////\n");
	}
	
	public void playersTurn() {
		System.out.println("///////////////////////");
		System.out.println("It is " + model.getActivePlayer().getName() + "'s turn to play.");
		System.out.println("///////////////////////\n");
	}
	
	public void comapreCards() {
		
	}
	public void newGameState(){
	}
}