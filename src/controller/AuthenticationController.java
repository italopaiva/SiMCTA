package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.AuthenticationDAO;
import exception.AuthenticationException;
import model.Authentication;

public class AuthenticationController{

	public AuthenticationController(){
		
	}
	
	/**
	 * Method used to check if the entered password is equal the password from database
	 * @param enteredPassword - password entered by user 
	 * @throws AuthenticationException 
	 * @throws SQLException 
	 */
	public boolean checkDataFromUser(String enteredPassword) throws AuthenticationException, SQLException{
		
		Authentication authentication = new Authentication(enteredPassword);
		AuthenticationDAO authenticationDao = new AuthenticationDAO();
		boolean correctPassword = false;
		ResultSet resultOfTheSearch;
		
		resultOfTheSearch = authenticationDao.get(authentication);
		
		while(resultOfTheSearch.next()){
			correctPassword = enteredPassword.equals(resultOfTheSearch.getString("password"));
		}
		
		if (correctPassword == false){
			throw new AuthenticationException("Senha incorreta");
		}
		
		return correctPassword;
	}
	
}
