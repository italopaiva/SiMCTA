package datatype;

import static org.junit.Assert.*;

import org.junit.Test;

import datatype.Address;
import exception.AddressException;

public class AddressTest {

	private Address address;  
	
	/** Tests for address info*/
	@Test
	public void testValidAddressInfo(){
		
		String validAddressInfo = "Quadra 5 Conjunto 7";
		
		try{
			address = new Address(validAddressInfo, "6", "Sobreloja 10", "72242111", "Brasília");
			assertEquals(validAddressInfo, address.getAddressInfo());
		}
		catch (AddressException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	@Test(expected=AddressException.class)
	public void testEmptyAddressInfo() throws AddressException{
		
		String invalidAddressInfo = "";
		
		address = new Address(invalidAddressInfo, "6", "Sobreloja 10", "72242111", "Brasília");
	}
	
	@Test(expected=AddressException.class)
	public void testNullAddressInfo() throws AddressException{
		
		String invalidAddressInfo = null;
		
		address = new Address(invalidAddressInfo, "6", "Sobreloja 10", "72242111", "Brasília");
	}
	
	@Test(expected=AddressException.class)
	public void testAddressInfo() throws AddressException{
		
		String invalidAddressInfo = "";
		
		address = new Address(invalidAddressInfo, "6", "Sobreloja 10", "72242111", "Brasília");
	}
	
	/** Tests for address number */
	
	@Test
	public void testValidAddressNumber(){
		
		String validAddressNumber = "6";
		
		try{
			address = new Address("Quadra 5 Conjunto 7", validAddressNumber, "Sobreloja 10", "72242111", "Brasília");
			assertEquals(validAddressNumber, address.getNumber());
		}
		catch(AddressException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testValidAddressNumberWithLetter(){
		
		String validAddressNumber = "6A";
		
		try{
			address = new Address("Quadra 5 Conjunto 7", validAddressNumber, "Sobreloja 10", "72242111", "Brasília");
			assertEquals(validAddressNumber, address.getNumber());
		}
		catch(AddressException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	@Test(expected=AddressException.class)
	public void testEmptyAddressNumber() throws AddressException{
		
		String invalidAddressNumber = "";
		
		address = new Address("Quadra 5 Conjunto 7", invalidAddressNumber, "Sobreloja 10", "72242111", "Brasília");
	}
	
	@Test(expected=AddressException.class)
	public void testNullAddressNumber() throws AddressException{
		
		String invalidAddressNumber = null;
		
		address = new Address("Quadra 5 Conjunto 7", invalidAddressNumber, "Sobreloja 10", "72242111", "Brasília");
	}
	
	/** Tests for address complement*/
	@Test
	public void testValidAddressComplement(){
		
		String validAddressComplement = "SObreloja 7";
		
		try{
			address = new Address("Quadra 5 Conjunto 7", "6A",validAddressComplement, "72242111", "Brasília");
			assertEquals(validAddressComplement, address.getComplement());
		}
		catch(AddressException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testValidBlankAddressComplement(){
		
		String validAddressComplement = "";
		
		try{
			address = new Address("Quadra 5 Conjunto 7", "6A",validAddressComplement, "72242111", "Brasília");
			assertEquals(validAddressComplement, address.getComplement());
		}
		catch(AddressException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testValidNullAddressComplement(){
		
		String validAddressComplement = null;
		
		try{
			address = new Address("Quadra 5 Conjunto 7", "6A",validAddressComplement, "72242111", "Brasília");
			assertEquals("", address.getComplement());
		}
		catch(AddressException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	/** Tests for CEP */
	
	@Test
	public void testValidAddressCep(){
		
		String validAddressCep = "72242111";
		
		try{
			address = new Address("Quadra 5 Conjunto 7", "6A", "Sobreloja 7", validAddressCep, "Brasília");
			assertEquals(validAddressCep, address.getCep());
		}
		catch(AddressException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	@Test(expected=AddressException.class)
	public void testEmptyAddressCep() throws AddressException{
		
		String invalidAddressCep = "";
		
		address = new Address("Quadra 5 Conjunto 7", "6A", "Sobreloja 7", invalidAddressCep, "Brasília");
	}
	
	@Test(expected=AddressException.class)
	public void testNullAddressCep() throws AddressException{
		
		String invalidAddressCep = null;
		
		address = new Address("Quadra 5 Conjunto 7", "6A", "Sobreloja 7", invalidAddressCep, "Brasília");
	}
	
	@Test(expected=AddressException.class)
	public void testNotNumberAddressCep() throws AddressException{
		
		String invalidAddressCep = "723fasd1";
		
		address = new Address("Quadra 5 Conjunto 7", "6A", "Sobreloja 7", invalidAddressCep, "Brasília");
	}
	
	@Test(expected=AddressException.class)
	public void testInvalidAddressCepWithBlankSpaces() throws AddressException{
		
		String invalidAddressCep = "723  178";
		
		address = new Address("Quadra 5 Conjunto 7", "6A", "Sobreloja 7", invalidAddressCep, "Brasília");
	}
	
	@Test(expected=AddressException.class)
	public void testLongAddressCep() throws AddressException{
		
		String invalidAddressCep = "723341788";
		
		address = new Address("Quadra 5 Conjunto 7", "6A", "Sobreloja 7", invalidAddressCep, "Brasília");
	}
	
	@Test(expected=AddressException.class)
	public void testShortAddressCep() throws AddressException{
		
		String invalidAddressCep = "7233417";
		
		address = new Address("Quadra 5 Conjunto 7", "6A", "Sobreloja 7", invalidAddressCep, "Brasília");
	}
	
	/** Tests for address city */
	
	@Test
	public void testValidAddressCity(){
		
		String validAddressCity = "Brasília";
		
		try{
			address = new Address("Quadra 5 Conjunto 7", "6A", "Sobreloja 7", "72242111", validAddressCity);
			assertEquals(validAddressCity, address.getCity());
		}
		catch(AddressException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testValidAddressCityWithSpaces(){
		
		String validAddressCity = "Campos do Jordão";
		
		try{
			address = new Address("Quadra 5 Conjunto 7", "6A", "Sobreloja 7", "72242111", validAddressCity);
			assertEquals(validAddressCity, address.getCity());
		}
		catch(AddressException e){
			fail("Should not throw exception: "+e.getMessage());
		}
	}
	
	@Test(expected=AddressException.class)
	public void testEmptyAddressCity() throws AddressException{
		
		String validAddressCity = "";
		
		address = new Address("Quadra 5 Conjunto 7", "6A", "Sobreloja 7", "72242111", validAddressCity);
	}
	
	@Test(expected=AddressException.class)
	public void testNullAddressCity() throws AddressException{
		
		String validAddressCity = null;
		
		address = new Address("Quadra 5 Conjunto 7", "6A", "Sobreloja 7", "72242111", validAddressCity);
	}
}
