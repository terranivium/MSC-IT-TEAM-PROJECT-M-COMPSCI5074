package view;

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
		System.out.println("| Doggo Trumps, test_ver. 0.12 |");
		System.out.println("--------------------------------");
		System.out.println("Select mode...--------------");
		System.out.println("	1. New Player Game");
		//System.out.println("	2. AI vs AI Autoplay") // Bot class
		//System.out.println("	3. View runtime stats") // DB
		System.out.println("	4. Quit");
		System.out.println("----------------------------");
	}

	// draw AI select menu
	public void drawAIMenu(){
		System.out.println("Select AI players (Max 1-4)...");
	}


	public void selectStat(){
		System.out.println("Please select a stat to play between 1 and 5...");
	}

	// for non valid inputs
	public void notValid(){
		System.out.println("Please select a valid menu option...");
	}

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
	
	public void playersTurnHeader(){
		System.out.println("///////////////////////");
		System.out.println("It is " + this.model.getActivePlayer().getName() + "'s turn to play.");
		System.out.println("///////////////////////\n");
	}
	
	public void selectPlayer() {
		for (Player p : this.model.getPlayers()) {
			System.out.println("\n");
			System.out.println("_____" + p.getName() + "'s hand"); // getname
			System.out.println("_____" + p.getHand().size() + " cards"); // gethand
			System.out.println("////////////////////////");
			for (int i = 0; i < p.getHand().size(); i++) // gethand
				System.out.println(p.getHand().get(i).getName());
		}
		System.out.println("\n");
		System.out.println("Round num: " + this.model.getNumOfRounds() + "\n_______________");
	}
	
	public void compareCards(int stat) {
		for (Player p : this.model.getPlayers()) {
			System.out.println(p.getName() + " " + p.getTopCard().getStats().get(stat));
		}
	}
	
	public void endRuntime() {
		System.out.println("Thank you for playing!");
	}
	
	public void gameWinner() {
		System.out.println("The winner of the game was " + this.model.getGameWinner());
	}
}