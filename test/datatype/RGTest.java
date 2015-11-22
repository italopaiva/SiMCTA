package datatype;

import static org.junit.Assert.*;

import org.junit.Test;

import datatype.RG;
import exception.RGException;

public class RGTest {
	
	private RG rg;
	
	/** RG number tests */
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
	
	@Test(expected = RGException.class)
	public void testNotNumberRGNumber() throws RGException{
		
		String invalidRGNumber = "asdf13e134";
		
		rg = new RG(invalidRGNumber, "SSP", "DF");
	}
	
	/** RG issuing institution tests*/
	
	@Test
	public void testValidRGIssuingInstitution(){
		
		String validIssuingInstitution = "SSP";
		
		try{
			rg = new RG("19380354", validIssuingInstitution, "DF");
			assertEquals(validIssuingInstitution, rg.getIssuingInstitution());
		}catch (RGException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	@Test(expected = RGException.class)
	public void testEmptyRGIssuingInstitution() throws RGException{
		
		String invalidIssuingInstitution= "";
		
		rg = new RG("19380354", invalidIssuingInstitution, "DF");
	}
	
	@Test(expected = RGException.class)
	public void testNullRGIssuingInstitution() throws RGException{
		
		String invalidIssuingInstitution= null;
		
		rg = new RG("19380354", invalidIssuingInstitution, "DF");
	}
	
	@Test(expected = RGException.class)
	public void testNotLettersRGIssuingInstitution() throws RGException{
		
		String invalidIssuingInstitution= "12434534";
		
		rg = new RG("19380354", invalidIssuingInstitution, "DF");
	}
	
	@Test(expected = RGException.class)
	public void testNotLettersPartialRGIssuingInstitution() throws RGException{
		
		String invalidIssuingInstitution= "SSP1432DF";
		
		rg = new RG("19380354", invalidIssuingInstitution, "DF");
	}
	
	/** RG UF tests*/
	
	public void testValidRGUf(){
		
		String validUf = "DF";
		
		try{
			rg = new RG("19380354", "SSP", validUf);
			assertEquals(validUf, rg.getUf());
		}catch (RGException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	@Test(expected = RGException.class)
	public void testEmptyRGUf() throws RGException{
		
		String invalidUf= "";
		
		rg = new RG("19380354", "SSP", invalidUf);
	}
	
	@Test(expected = RGException.class)
	public void testNullRGUf() throws RGException{
		
		String invalidUf= null;
		
		rg = new RG("19380354", "SSP", invalidUf);
	}
	
	@Test(expected = RGException.class)
	public void testNotLetterRGUf() throws RGException{
		
		String invalidUf = "12";
		
		rg = new RG("19380354", "SSP", invalidUf);
	}
	
	@Test(expected = RGException.class)
	public void testLongRGUf() throws RGException{
		
		String invalidUf = "DFF";
		
		rg = new RG("19380354", "SSP", invalidUf);
	}
	
	@Test(expected = RGException.class)
	public void testRGUfWithBlankSpace() throws RGException{
		
		String invalidUf = "D ";
		
		rg = new RG("19380354", "SSP", invalidUf);
	}
}
