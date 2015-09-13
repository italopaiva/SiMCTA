package view;

import java.awt.Component;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;

import controller.AuthenticationController;
import exception.AuthenticationException;

public class AuthenticationView extends JOptionPane{
	
	final Object [] inputPassword;
	final JPasswordField enteredPasswordField;
	final JLabel passwordLabel;
	
	public AuthenticationView(){
		
		passwordLabel = new JLabel("Digite a senha:");
		enteredPasswordField = new JPasswordField();
		inputPassword = new Object[]{passwordLabel, enteredPasswordField};
	}
		
	/** 
	 * Method used to get the entered password and check if exists
	 * @return - A boolean that represents if the entered password is correct
	 * @throws AuthenticationException
	 * @throws SQLException
	 */
	public boolean authenticateUser(String enteredPassword) throws AuthenticationException, SQLException{
		
		AuthenticationController authentication = new AuthenticationController();
		boolean correctPassword = true;

		correctPassword = authentication.checkDataFromUser(enteredPassword);
		
		return correctPassword;
	}
	

}
