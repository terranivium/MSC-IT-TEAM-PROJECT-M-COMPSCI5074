
package view;



import java.util.Arrays;



import model.*;



public class TTCLIView {



	private TTModel model;



	public TTCLIView(TTModel model) {

		this.model = model;

	}



	// draw CLI main menu

	public void drawMain() {

		System.out.println("--------------------------------");

		System.out.println("|  Doggo Trumps, cli_ver. 1.0  |");

		System.out.println("--------------------------------");

		System.out.println("Select mode...");

		System.out.println("	1. New Player Game");

		System.out.println("	2. AI vs AI Autoplay"); 

		System.out.println(" 	3. View runtime stats");

		System.out.println("	4. Quit");

		System.out.println("--------------------------------");

	}



	// draw AI select menu

	public void drawHumanMenu() {

		System.out.println("Select AI players (Max 1-4)...");

	}



	// draw AI select menu

	public void drawAIMenu() {

		System.out.println("Select AI players (Max 2-5)...");

	}



	public void selectStat() {

		System.out.println("Please select a stat to play between 1 and 5...");

	}



	// for non valid inputs

	public void notValid() {

		System.out.println("Please select a valid menu option...");

	}



	public void viewCard(Card selectCard) {

		// View for the current card/this turn card

		System.out.println("///////////////////////");

		System.out.println(model.getDeck().getHeaderNames()[0] + " : " + selectCard.getDescription());

		System.out.println("1. " + model.getDeck().getHeaderNames()[1] + " : " + selectCard.getCategoryOne());

		System.out.println("2. " + model.getDeck().getHeaderNames()[2] + " : " + selectCard.getCategoryTwo());

		System.out.println("3. " + model.getDeck().getHeaderNames()[3] + " : " + selectCard.getCategoryThree());

		System.out.println("4. " + model.getDeck().getHeaderNames()[4] + " : " + selectCard.getCategoryFour());

		System.out.println("5. " + model.getDeck().getHeaderNames()[5] + " : " + selectCard.getCategoryFive());

		System.out.println("///////////////////////\n");

	}



	public void playersTurnHeader() {

		System.out.println("///////////////////////");

		System.out.println("It is " + this.model.getActivePlayer().getName() + "'s turn to play.");

		System.out.println("///////////////////////\n");

	}



	public void endRuntime() {

		System.out.println("Thank you for playing!");

	}



	public void gameWinner() {

		System.out.println("The winner of the game was " + this.model.getGameWinner());

	}

	

	public void dbiDraw(int[] results) {

		System.out.println("Total Games|Human Wins|AI Wins|Avg. Draws|Highest No. Rounds"); //testline - to delete

		System.out.println(Arrays.toString(results)); //testline - to delete

		System.out.println();

		System.out.println("Press 1 key to return to main menu");

	}

}