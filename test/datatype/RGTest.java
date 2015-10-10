package datatype;

import static org.junit.Assert.*;
import model.datatype.RG;

import org.junit.Test;

import exception.RGException;

public class RGTest {
	
	private RG rg;
	
	@Test
	public void testValidRGNumber(){
		
		String validRGNumber = "18468465";
		
		try{
			rg = new RG(validRGNumber, "SSP", "DF");
			assertEquals(validRGNumber, rg.getRgNumber());
		}catch (RGException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	@Test(expected = RGException.class)
	public void testEmptyRGNumber() throws RGException{
		
		String invalidRGNumber = "";
		
		rg = new RG(invalidRGNumber, "SSP", "DF");
	}
	
	@Test(expected = RGException.class)
	public void testNullRGNumber() throws RGException{
		
		String invalidRGNumber = null;
		
		rg = new RG(invalidRGNumber, "SSP", "DF");
	}
}
