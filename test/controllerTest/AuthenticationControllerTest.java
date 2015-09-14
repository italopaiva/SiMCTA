package controllerTest;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Authentication;
import model.Course;

import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;

import controller.AuthenticationController;
import controller.CourseController;
import exception.AuthenticationException;
import exception.CourseException;

public class AuthenticationControllerTest {

	private AuthenticationController authenticationController;
	private String password;
	
	@Before
	public void setUp() throws AuthenticationException{
		authenticationController = new AuthenticationController();
	}
	
	/** Test of valid entry */
	@Test
	public void testTheResultOfCheckDataFromUserWithAPasswordValid() throws AuthenticationException, SQLException {
		
		password = "admin";
		boolean correctPassword = false;
		
		correctPassword = authenticationController.checkDataFromUser(password);
		
		assertEquals(true, correctPassword);
		
	}
	
	/** Test of invalid entries */
	@Test(expected = AuthenticationException.class)
	public void testTheResultOfCheckDataFromUserWithAPasswordBlank() throws AuthenticationException, SQLException {
		
		password = " ";
		boolean correctPassword = false;
		
		correctPassword = authenticationController.checkDataFromUser(password);
				
	}

	@Test(expected = Exception.class)
	public void testTheResultOfCheckDataFromUserWithAPasswordNull() throws AuthenticationException, SQLException {
		
		password = null;
		boolean correctPassword = false;
		
		correctPassword = authenticationController.checkDataFromUser(password);
				
	}
	
	@Test(expected = Exception.class)
	public void testTheResultOfCheckDataFromUserWithAPasswordLessThanMinimum() throws AuthenticationException, SQLException {
		
		password = "12";
		boolean correctPassword = false;
		
		correctPassword = authenticationController.checkDataFromUser(password);
				
	}
	
	@Test(expected = Exception.class)
	public void testTheResultOfCheckDataFromUserWithAPasswordMoreTheMaximum() throws AuthenticationException, SQLException {
		
		password = "0123456789";
		boolean correctPassword = false;
		
		correctPassword = authenticationController.checkDataFromUser(password);
				
	}
}
