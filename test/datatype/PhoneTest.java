package datatype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import model.datatype.Phone;

import org.junit.Test;

import exception.PhoneException;
import exception.RGException;

public class PhoneTest {
	
	private Phone phone;
	
	/** Valid Phone number tests 
	 * @throws PhoneException */
	@Test
	public void testValidDDDNumber() throws PhoneException{
		
		String validDDDNumber = "61";
		
		try{
			phone = new Phone(validDDDNumber, "81558781");
			assertEquals(validDDDNumber, phone.getDDD());
		}
		catch (PhoneException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}

	@Test
	public void testValidPhoneNumber() throws PhoneException{
		
		String validPhoneNumber = "81558781";
		
		try{
			phone = new Phone("61", validPhoneNumber);
			assertEquals(validPhoneNumber, phone.getNumber());
		}
		catch (PhoneException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	
	@Test
	public void testPhoneFormatted() throws PhoneException{
		
		String validDDD = "61";
		String validPhoneNumber = "81558781";
		
		phone = new Phone(validDDD, validPhoneNumber);
		String formattedPhone = phone.getFormattedPhone();
		assertEquals("(61) 8155-8781", formattedPhone);
	}
	
	/**
	 * Invalid Phone number tests 
	 * @throws PhoneException
	 */
	@Test(expected = PhoneException.class)
	public void testDDDEmpty() throws PhoneException{
		
		phone = new Phone("", "81558781");
	}
	
	@Test(expected = PhoneException.class)
	public void testDDDWithLengthLessThanTwo() throws PhoneException{
		
		phone = new Phone("1", "81558781");
	}
	
	@Test(expected = PhoneException.class)
	public void testDDDWithLengthGreaterThanTwo() throws PhoneException{
		
		phone = new Phone("061", "81558781");
	}

	@Test(expected = PhoneException.class)
	public void testPhoneWithLengthLessThanEight() throws PhoneException{
		
		phone = new Phone("61", "8155878");
	}
	
	@Test(expected = PhoneException.class)
	public void testNumberEmpty() throws PhoneException{
		
		phone = new Phone("61", "");
	}
	
	@Test(expected = PhoneException.class)
	public void testPhoneWithLengthGreaterThanEight() throws PhoneException{
		
		phone = new Phone("61", "815587811");
	}
}
	
