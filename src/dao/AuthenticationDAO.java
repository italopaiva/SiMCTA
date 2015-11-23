package dao;

import java.sql.ResultSet;

import java.sql.SQLException;

import model.Authentication;

import exception.AuthenticationException;

public class AuthenticationDAO extends DAO {
	
	private static final String COULDNT_UPDATE_PASSWORD = "Não foi possível atualizar a senha informada. Tente novamente.";
	
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

	public void update(String newPassword) throws AuthenticationException{
		
		String query = "UPDATE "+ TABLE_NAME +" SET "+ COLUMN_NAME + " = '"+newPassword+"'";
		
		try{
			this.execute(query);
		}
		catch(SQLException e){
			throw new AuthenticationException(COULDNT_UPDATE_PASSWORD);
		}
	}

}
