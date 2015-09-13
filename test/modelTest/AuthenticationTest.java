package modelTest;

import static org.junit.Assert.*;
import model.Authentication;

import org.junit.Before;
import org.junit.Test;

import exception.AuthenticationException;

public class AuthenticationTest {
	
	String password;
	Authentication authentication;

	
	/** Tests of valid entries */
	@Test
	public void testPasswordValid() throws AuthenticationException {
		
		password = "12345";	
		authentication = new Authentication(password);
		
		assertEquals(password, authentication.getPassword());
	}

	@Test
	public void testPasswordValidWithMininumLength() throws AuthenticationException{
		
		password = "1234";
		authentication = new Authentication(password);
		
		assertEquals(password, authentication.getPassword());
		
	}
	
	@Test
	public void testPasswordValidWithMaxinumLength() throws AuthenticationException{
		
		password = "0123456789";
		authentication = new Authentication(password);
		
		assertEquals(password, authentication.getPassword());
		
	}
	
	@Test
	public void testIfCanAccessTheGetsWithAnotherConstructor(){
		
		authentication = new Authentication();
		assertEquals(null, authentication.getPassword());
		
	}
	
	/** End of tests of valid entries */
	
	/** Tests of invalid entries */	
	@Test(expected = AuthenticationException.class)
	public void testPasswordBlank() throws AuthenticationException{
		
		password = "";
		authentication = new Authentication(password);
				
	}
	
	@Test(expected = Exception.class)
	public void testPasswordNull() throws AuthenticationException{
		
		password = null;
		authentication = new Authentication(password);
				
	}
	
	@Test(expected = AuthenticationException.class)
	public void testPasswordWithLessMininumLength() throws AuthenticationException{
		
		password = "123";
		authentication = new Authentication(password);
				
	}
	
	@Test(expected = AuthenticationException.class)
	public void testPasswordWithMoreMaximumLength() throws AuthenticationException{
		
		password = "012345678910";
		authentication = new Authentication(password);
				
	}
	/** End of tests of invalid entries */
}
