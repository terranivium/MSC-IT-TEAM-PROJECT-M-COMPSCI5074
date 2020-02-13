package database_testlog;

//tested and works for contacting db, updating and retrieving data.

//currently setup with main method. next revision will replace this with a constructor.
//all needed methods developed, commented out below.

//relies upon pre-created table in postgresql (see txt in slack)
//replace "INSERTUSERNAME" and "INSERTPASSWORD" with your local postgresql credentials.

//the jdbc driver postgresql-42.2.9.jar is required for this to work
//the driver needs to be in the referenced library. In the Project choose Properties\Java Build Path\ClassPath\ then add external jars and navigate to it.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseInteractor {

	Connection dbConnection = null;
	

	
	//Richard server details for when we get round to that.
	private String location = "jdbc:postgresql://52.24.215.108:5432/";
	private String username = "YouGitWhatYouGive";
	private String password = "YouGitWhatYouGive";

	
	
	//constructor
	public DatabaseInteractor() {
		
		//this.model = model;
		
		//load the JDBC driver
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
				if (dbConnection != null) { //perhaps this whole conditional can be removed.
					System.out.println("Controlling your database...");
			}else // will the else ever be arrived at? If so, change message to give clearer description [if connection was still null at this point wouldn't it have thrown the error caught above?]
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
	
	
	//to be removed pending final checks.
	
//	public static void main (String[] args) {
//		
//		Connection dbConnection = null;
//		
//		//load the JDBC driver
//		try {
//			Class.forName("org.postgresql.Driver"); 	
//		}
//		catch (ClassNotFoundException e) {
//			System.out.println("Could not load class " +"org.postgresql.Driver");
//			e.printStackTrace();
//			return;
//		}
//		
//		// attempt connection and catch any failure
//		try {
//			
//			dbConnection = DriverManager.getConnection(location, username, password);
//		
//		} catch (SQLException e) {
//			System.out.println("Connection Failed!");
//		e.printStackTrace();
//		return;
//		}
//		if (dbConnection != null) {
//		System.out.println("Controlling your database...");
//		}else {
//			System.out.println("Failed to establish connection");
//		}
//		
//		
//
//		//test code for inputing to and retrieving data from the DB. Superseded by methods below.
//		String trialInsert = "insert into ttresults (winner, draws, rounds, h_rounds, ai_1_rounds, ai_2_rounds, ai_3_rounds, ai_4_rounds)"
//				+ " values ('human',5,66,44,16,0,NULL,NULL)";
//		
//		//attempt input and catch failure
//		try 
//		{
//		Statement statement = dbConnection.createStatement();
//		statement.executeUpdate(trialInsert);
//		}
//				
//		catch (SQLException e) 
//		{
//            System.out.println("Update unsucessful - connection failure.");
//            e.printStackTrace();
//        }
//		
//		
//		int[] results = new int[5]; //int array proposed as return from request method, below
//		try
//		{
//			Statement reqStmt = dbConnection.createStatement();
//			ResultSet rs;
//			String reqInstruction = "SELECT count(*), sum (case when winner = 'human' then 1 else 0 end) human, "
//					+ "sum (case when winner != 'human' then 1 else 0 end) ai,"
//					+ " round(AVG(draws),0) avgdraws, max(rounds) from ttresults;";
//			
//			rs = reqStmt.executeQuery(reqInstruction);
//			while (rs.next())
//					{
//						results[0] = rs.getInt("count");
//						results[1] = rs.getInt("human");
//						results[2] = rs.getInt("ai");
//						results[3] = rs.getInt("avgdraws");
//						results[4] = rs.getInt("max");
//					}
//			
//			System.out.println(Arrays.toString(results));
//			
//		}
//		catch (SQLException e) 
//		{
//			System.out.println("Data retrieval unsuccessful");
//		}
//		finally
//		{
//			try
//			{
//			dbConnection.close();
//			} 
//			catch (SQLException e) 
//			{
//				System.out.println("Connection to database has not been closed.");
//			}
//						
//		}
//	}
	
		
}// closes class
