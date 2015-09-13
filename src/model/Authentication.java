package model;

import exception.AuthenticationException;

public class Authentication{
	
	// Error constants
	private static final String PASSWORD_CANT_BE_NULL = "A senha não pode estar em branco.";
	private static final String PASSWORD_CANT_BE_LESS_THAN_MINIMUM = "A senha não pode ter menos de 4 caracteres";
	private static final String PASSWORD_CANT_BE_MORE_THAN_MAXIMUM = "A senha não pode ter mais de 10 caracteres";

	// Constants that represent the minimum and maximum length permitted of the password
	private static final int MININUM_LENGTH_PASSWORD = 4;
	private static final int MAXIMUM_LENGTH_PASSWORD = 10;
	
	private String password;
	
	public Authentication(){
		
	}
	
	public Authentication(String password) throws AuthenticationException{
		setPassword(password);
	}
	
	/** Setters */
	private void setPassword(String password) throws AuthenticationException{
		
		boolean passwordValid = password != null && !password.isEmpty();
		boolean passwordLengthValidMinimum = password.length() >= MININUM_LENGTH_PASSWORD;
		boolean passwordLengthValidMaximum = password.length() <= MAXIMUM_LENGTH_PASSWORD;
		
		if(passwordValid){
			if(passwordLengthValidMinimum && passwordLengthValidMaximum){
				this.password = password;
			}
			else if(!passwordLengthValidMinimum){
				throw new AuthenticationException(PASSWORD_CANT_BE_LESS_THAN_MINIMUM);
			}
			else{
				throw new AuthenticationException(PASSWORD_CANT_BE_MORE_THAN_MAXIMUM);
			}
		}
		else{
			throw new AuthenticationException(PASSWORD_CANT_BE_NULL);
		}
	}
	
	/** Getters */
	public String getPassword(){
		return this.password;
	}
	
	
}
