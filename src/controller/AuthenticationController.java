package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.AuthenticationDAO;
import exception.AuthenticationException;
import model.Authentication;

public class AuthenticationController{

	private static final String COULDNT_GET_REGISTERED_PASSWORD = "Não foi possível ler os dados do banco de dados. Tente novamente.";
	private static final String WRONG_PASSWORD = "Senha incorreta.";
	
	private AuthenticationDAO authenticationDAO;
	
	public AuthenticationController(){
		authenticationDAO = new AuthenticationDAO();
	}
	
	/**
	 * Method used to check if the entered password is equal the password from database
	 * @param enteredPassword - password entered by user 
	 * @throws AuthenticationException 
	 * @throws SQLException 
	 */
	public boolean checkDataFromUser(String enteredPassword) throws AuthenticationException, SQLException{
		
		Authentication authentication = new Authentication(enteredPassword);
		
		boolean correctPassword = false;
		ResultSet resultOfTheSearch;
		
		resultOfTheSearch = authenticationDAO.get(authentication);
		
		while(resultOfTheSearch.next()){
			correctPassword = enteredPassword.equals(resultOfTheSearch.getString("password"));
		}
		
		if (correctPassword == false){
			throw new AuthenticationException("Senha incorreta");
		}
		
		return correctPassword;
	}

	public void updateDirectorPassword(String currentPassword, String newPassword) throws AuthenticationException{
		
		Authentication currentPasswordAuth = new Authentication(currentPassword);
		Authentication newPasswordAuth = new Authentication(newPassword);
		
		ResultSet result = authenticationDAO.get(currentPasswordAuth);
		
		String registeredPassword;
		try{
			if(result.first()){
				registeredPassword = result.getString("password");
				
				if(registeredPassword.equals(currentPassword)){
					authenticationDAO.update(newPassword);
				}
				else{
					throw new AuthenticationException(WRONG_PASSWORD);
				}
				
			}
			else{
				throw new AuthenticationException(WRONG_PASSWORD);
			}
		}
		catch(SQLException e){
			throw new AuthenticationException(COULDNT_GET_REGISTERED_PASSWORD);
		}
	}
	
}
