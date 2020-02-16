//class handles the program's interaction with the database, incorporating both updates to the data there
//and requests as to the data currently stored. 

package database_testlog;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class DatabaseInteractor {

	Connection dbConnection = null;

	//Static variables for the database location, username and password. 
	private static String location = "jdbc:postgresql://52.24.215.108:5432/";
	private static String username = "YouGitWhatYouGive";
	private static String password = "YouGitWhatYouGive";

	
	
	//constructor loads the database driver, catching error if not found
	public DatabaseInteractor() {
		
		try {
			Class.forName("org.postgresql.Driver"); 	
		}
		catch (ClassNotFoundException e) {
			System.out.println("Could not load class " +"org.postgresql.Driver");
			e.printStackTrace();
			return;
		}
				
	}


	
		
	//method to input data in to the database. Calls additional methods openConnection() and endConnection().
	//This is the method that should be called when update of data is requested, as it handles the entire procedure.
	//Needs testing to ensure that ArrayList<Integer> is working as desired.  
	//@ param allWonRounds. This arraylist stores the number of won rounds for each player of a game. As such, its size() 
	//gives the number of players who played that game.
	
	public void updateDb(String winner, int numDraws, int numRounds, ArrayList<Integer> allWonRounds)
	{
		
		String upSuffix;
		
		//conditional to set upSuffix dependent on value of allWonRounds.size(). updSuffix forms part of below string upInstruction.
		//allows for distinction to be made in database entries between non-participation (NULL) and winning zero rounds (e.g. ai2RoundWins == 0)
		if (allWonRounds.size() == 2)
		{
			upSuffix = "NULL,NULL,NULL);";
		} else if (allWonRounds.size() == 3)
		{
			upSuffix = "" + allWonRounds.get(2) + ",NULL,NULL);";
		} else if (allWonRounds.size() == 4)
		{
			upSuffix = "" + allWonRounds.get(2) + "," + allWonRounds.get(3) + ",NULL);";
		} else // only other possible value of allWonRounds.size() is 4 (representing the maximum of 5 players)
		{
			upSuffix = "" + allWonRounds.get(2) + "," + allWonRounds.get(3) + "," + allWonRounds.get(4) + ");"; 
		}
		
		String upInstruction = "INSERT into TTRESULTS (WINNER, DRAWS, ROUNDS, H_ROUNDS, AI_1_ROUNDS, AI_2_ROUNDS, AI_3_ROUNDS, AI_4_ROUNDS) "
				+ "values ('"+ winner + "'," + numDraws + "," + numRounds + "," + allWonRounds.get(0) + "," + allWonRounds.get(1) + "," + upSuffix;
		
		openConnection();
		
		try 
		{
		Statement upStmt = dbConnection.createStatement();
		upStmt.executeUpdate(upInstruction);
		}
				
		catch (SQLException e) 
		{
            System.out.println("Update unsuccessful - connection failure.");
            e.printStackTrace();
        }
		
		endConnection();
	}
	
	//method to request statistics from database server. Calls additional methods openConnection() and endConnection().
	//This is the method that should be called when stats retrieval is requested, as it handles the entire procedure.
	//returns the results as an int array.
	
	public int[] dbRequest()
		{	
			int[] results = new int[5];
			
			openConnection();
	
			try
		{
			Statement reqStmt = dbConnection.createStatement();
			ResultSet rs;
			String reqInstruction = "SELECT count(*), sum (case when winner = 'Player1' then 1 else 0 end) human, "
					+ "sum (case when winner != 'Player1' then 1 else 0 end) ai,"
					+ " round(AVG(draws),0) avgdraws, max(rounds) from ttresults;";
			
			rs = reqStmt.executeQuery(reqInstruction);
			while (rs.next())
					{
						results[0] = rs.getInt("count");
						results[1] = rs.getInt("human");
						results[2] = rs.getInt("ai");
						results[3] = rs.getInt("avgdraws");
						results[4] = rs.getInt("max");
					}
		}
		catch (SQLException e) 
		{
			System.out.println("Data retrieval unsuccessful");
			e.printStackTrace();
		}
		finally
		{
			try
			{
			dbConnection.close();
			} 
			catch (SQLException e) 
			{
				System.out.println("Connection to database has not been closed.");
			}
						
		}
		endConnection();	
		return results;
	}
	
	//method to open connection to db server
		private void openConnection()
		{
			try 
			{
				dbConnection = DriverManager.getConnection(location, username, password);
							
			} catch (SQLException e) 
			{
				System.out.println("Database access error");
				e.printStackTrace();
				return;
			}
				if (dbConnection != null) { 
					System.out.println("Controlling your database...");
			}else 
			{
				System.out.println("Failed to establish connection");
			}
		}
	
	
	//method to end connection to database server
	private void endConnection() 
	{
		try
		{
			dbConnection.close();
			
		}catch (SQLException e) 
		{
			System.out.println("Connection to database has not been closed.");
		}
					
	}
	
		
}// closes class
