package view;

import model.*;
import sun.tools.tree.ThisExpression;

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
		System.out.println(" 	4. Toggle Slow Scroll");
		System.out.println("	5. Quit");
		System.out.println("--------------------------------");
	}

	// draw AI select menu
	public void drawHumanMenu() {
		System.out.println("Starting new game against 4 AI players.");
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
	
	public void slowModeEnabled() {
		System.out.println("Slow mode - enabled");
	}

	public void slowModeDisabled() {
		System.out.println("Slow mode - disabled");
	}
	
	public void viewCard(Card selectCard) {
		// View for the current card/this turn card
		System.out.println("///////////////////////");
		System.out.println(selectCard.getDescription());
		System.out.println("1. " + this.model.getDeck().getHeaderNames()[1] + " : " + selectCard.getCategoryOne());
		System.out.println("2. " + this.model.getDeck().getHeaderNames()[2] + " : " + selectCard.getCategoryTwo());
		System.out.println("3. " + this.model.getDeck().getHeaderNames()[3] + " : " + selectCard.getCategoryThree());
		System.out.println("4. " + this.model.getDeck().getHeaderNames()[4] + " : " + selectCard.getCategoryFour());
		System.out.println("5. " + this.model.getDeck().getHeaderNames()[5] + " : " + selectCard.getCategoryFive());
		System.out.println("///////////////////////\n");
	}

	public void playersTurnHeader() {
		System.out.println("///////////////////////");
		System.out.println("It's " + this.model.getActivePlayer().getName() + "'s turn to play.");
		System.out.println("///////////////////////");
		System.out.println("Card in play:");
	}

	public void endRuntime() {
		System.out.println("Thank you for playing!");
	}

	public void gameWinner() {
		System.out.println("The winner of the game was " + this.model.getGameWinner());
		System.out.println("///////////////////////");
	}

	public void dbiDraw(int[] results) {
		System.out.println("///////////////////////");
		System.out.println("Total Games: " + results[0]);
		System.out.println("Human Wins: " + results[1]);
		System.out.println("AI Wins: " + results[2]);
		System.out.println("Avg. Draws: " + results[3]);
		System.out.println("Highest No. of Rounds: " + results[4]);
		System.out.println("///////////////////////\n");
		System.out.println("Enter 1 to return to main menu...");
	}
	
	public void gameLogVerification() {
		System.out.println("Writing to .log file in game folder...\n");
	}
	
	public void testLoggerPrints() {
		// LogWriter prints
        System.out.println("The current round is " + this.model.getNumOfRounds());
        if (this.model.getNumOfRounds() == 1)
        {
            System.out.println(this.model.getLogWriter().getDeckOnLoad());
            System.out.println(this.model.getLogWriter().getDeckShuffle());
        }
		System.out.println(this.model.getLogWriter().getEveryoneHands());
		System.out.println(this.model.getLogWriter().getPlayingTable());
		System.out.println(this.model.getLogWriter().getChosenCategory());
		System.out.println(this.model.getLogWriter().getEveryoneValues());
		System.out.println(this.model.getLogWriter().getRoundWinner());
		System.out.println(this.model.getLogWriter().getCommunalPile());
	}
	
	public void currentRound() {
		System.out.println("The current round is " + this.model.getNumOfRounds() + "\n");
	}
	
	public void roundWinner() {
		if (this.model.getRoundWinnerString().equals("")) {
			System.out.println("///////////////////////");
			System.out.println("Draw - No one won the round");
		} else {
		System.out.println("///////////////////////");
		System.out.println(this.model.getRoundWinnerString() + " won the round!");
		}
	}
	
	public void roundWinnerCard() {
		System.out.println("Their winning card was: " + this.model.getRoundWinner().getTopCard().getDescription());
		System.out.println("1. " + this.model.getDeck().getHeaderNames()[1] + " : " + this.model.getRoundWinner().getTopCard().getCategoryOne());
		System.out.println("2. " + this.model.getDeck().getHeaderNames()[2] + " : " + this.model.getRoundWinner().getTopCard().getCategoryTwo());
		System.out.println("3. " + this.model.getDeck().getHeaderNames()[3] + " : " + this.model.getRoundWinner().getTopCard().getCategoryThree());
		System.out.println("4. " + this.model.getDeck().getHeaderNames()[4] + " : " + this.model.getRoundWinner().getTopCard().getCategoryFour());
		System.out.println("5. " + this.model.getDeck().getHeaderNames()[5] + " : " + this.model.getRoundWinner().getTopCard().getCategoryFive());
		System.out.println("///////////////////////\n");
	}
	
	public void roundWinnerCardStat() {
		// System.out.println(this.model.getRoundWinner().getTopCard().getStats().get()); // need a way to get the chosen stat of winner
	}
	
	public void removedPlayers() {
		
		if (this.model.getRemovedPlayersString().equals(""))
		{
			System.out.println("No players were eliminated this turn\n");
			System.out.println("///////////////////////");
		} else {
			System.out.println("Eliminated this turn:\n " + this.model.getRemovedPlayersString());
			System.out.println("///////////////////////");
		}
	}
	
	public void chosenCategory()
	{
		System.out.println(this.model.getChosenCategory());
	}
	
	public void winningCard()
	{
		if (this.model.getWinningCard().equals(""))
		{
			System.out.println("No winning card this turn");
			System.out.println("///////////////////////");
		} else {
			System.out.println("The winning card was " + this.model.getWinningCard());
			System.out.println("///////////////////////");
		}
	}

}