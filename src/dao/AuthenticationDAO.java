package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Authentication;

public class AuthenticationDAO extends DAO {
	
	private final static String TABLE_NAME = "User";
	private final static String COLUMN_NAME = "password";

	/**
	 * Method used to consult the password in the database 
	 * @param authentication - Object that contains the entered password
	 * @return - the values from database
	 */
	public ResultSet get(Authentication authentication){
		
		ResultSet resultOfTheSearch = null;
		String enteredPassword = authentication.getPassword();
		String query = ("SELECT * FROM "+ TABLE_NAME + " WHERE " + COLUMN_NAME + "='"  + enteredPassword + "'");

		try{
			resultOfTheSearch = this.search(query);
		}
		catch(SQLException caughtException){
			caughtException.printStackTrace();
		}
		
		return resultOfTheSearch;
	}

}
