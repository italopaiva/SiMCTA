package modelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import model.Student;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;

import org.junit.Before;
import org.junit.Test;

import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;

public class PersonTest {
	
	Student student;
	Date date;
	Address address;
	Phone phone1;
	Phone phone2;
	CPF cpf;
	RG rg;
	String email;
	
	@Before
	public void setUp() throws PhoneException, CPFException, RGException, PersonException, DateException, AddressException{
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
	}
	
	/** Tests for student name 
	 * @throws PersonException */
	@Test
	public void testValidStudentName() throws PersonException{
		
		String validStudentName = "Jacó Mario Souza";
		
		try {
			student = new Student(validStudentName, cpf, rg, date, email, address, phone1, phone2, 
							      "Milene Souza Medeiros", "Mário Souza Filho",1);
			assertEquals(validStudentName, student.getName());
		} 
		catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	

	@Test(expected = PersonException.class)
	public void testEmptyStudentName() throws PersonException, PersonException{
		
		String validStudentName = "";
		
		student = new Student(validStudentName, cpf, rg, date, email, address, phone1, phone2, 
							      "Milene Souza Medeiros", "Mário Souza Filho",1);
	}
	
	@Test(expected = PersonException.class)
	public void testNullStudentName() throws PersonException{
		
		String validStudentName = null;
		
		student = new Student(validStudentName, cpf, rg, date, email, address, phone1, phone2, 
							      "Milene Souza Medeiros", "Mário Souza Filho",1);
	}
	
	@Test(expected = PersonException.class)
	public void testNonLettersStudentName() throws PersonException{
		
		String validStudentName = "João 123";
		
		student = new Student(validStudentName, cpf, rg, date, email, address, phone1, phone2, 
							      "Milene Souza Medeiros", "Mário Souza Filho",1);
	}
	
	/** Tests for mother name*/
	@Test
	public void testValidMotherName(){
		
		String validMotherName = "Milene Souza Medeiros";
		
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
							      validMotherName, "Mário Souza Filho",1);
			assertEquals(validMotherName, student.getMotherName());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = PersonException.class)
	public void testEmptyMotherName() throws PersonException{
		
		String invalidMotherName = "";
		
		student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
						      invalidMotherName, "Mário Souza Filho",1);
	}

	@Test(expected = PersonException.class)
	public void testNullMotherName() throws PersonException{
		
		String invalidMotherName = null;
		
		student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
						      invalidMotherName, "Mário Souza Filho",1);
	}
	
	@Test(expected = PersonException.class)
	public void testNonLettersMotherName() throws PersonException{
		
		String invalidMotherName = "Milene 124";
		
		student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
						      invalidMotherName, "Mário Souza Filho",1);
	}
	
	/** Tests for father name*/
	@Test
	public void testValidFatherName(){
		
		String validFatherName = "Mário Souza Filho";
		
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
								  "Milene Souza Medeiros", validFatherName,1);
			assertEquals(validFatherName, student.getFatherName());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testValidEmptyFatherName(){
		
		String validFatherName = "";
		
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
								  "Milene Souza Medeiros", validFatherName,1);
			assertEquals("", student.getFatherName());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testValidNullFatherName(){
		
		String validFatherName = null;
		
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
								  "Milene Souza Medeiros", validFatherName,1);
			assertEquals("", student.getFatherName());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = PersonException.class)
	public void testNonLettersFatherName() throws PersonException{
		
		String invalidFatherName = "Mário 123";
		
		student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
							  "Milene Souza Medeiros", invalidFatherName,1);
	}
	
	/** Tests for CPF*/
	@Test
	public void testValidStudentCPF(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho",1);
			assertEquals(cpf, student.getCpf());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = PersonException.class)
	public void testInvalidStudentCPF() throws PersonException{
			
		student = new Student("Jacó Mario Souza", null, rg, date, email, address, phone1, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho",1);
	}
	
	/** Tests for RG*/
	@Test
	public void testValidStudentRG(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho",1);
			assertEquals(rg, student.getRg());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = PersonException.class)
	public void testInvalidStudentRG() throws PersonException{
			
		student = new Student("Jacó Mario Souza", cpf, null, date, email, address, phone1, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho",1);
	}
	
	/** Tests for Address*/
	@Test
	public void testValidStudentAddress(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho",1);
			assertEquals(address, student.getAddress());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = PersonException.class)
	public void testInvalidStudentAddress() throws PersonException{
			
		student = new Student("Jacó Mario Souza", cpf, rg, date, email, null, phone1, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho",1);
	}
	
	/** Tests for Phone 1*/
	@Test
	public void testValidStudentPhone1(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho",1);
			assertEquals(phone1, student.getPrincipalPhone());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = PersonException.class)
	public void testInvalidStudentPhone1() throws PersonException{
			
		student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, null, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho",1);
	}
	
	/** Tests for Phone 2*/
	@Test
	public void testValidStudentPhone2(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho",1);
			assertEquals(phone2, student.getSecondaryPhone());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testValidNullStudentPhone2(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, null, 
								  "Milene Souza Medeiros", "Mário Souza Filho",1);
			assertEquals(null, student.getSecondaryPhone());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	/** Tests for Birthdate*/
	@Test
	public void testValidStudentBirthdate(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho",1);
			assertEquals(date, student.getBirthdate());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = PersonException.class)
	public void testInvalidStudentBirthdate() throws PersonException{
			
		student = new Student("Jacó Mario Souza", cpf, rg, null, email, address, phone1, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho",1);
	}
	
	/** Tests for Email*/
	@Test
	public void testValidStudentEmail(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, email, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho",1);
			assertEquals(email, student.getEmail());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testValidEmptyStudentEmail(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, "", address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho",1);
			assertEquals("", student.getEmail());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testValidNullStudentEmail(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, null, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho",1);
			assertEquals("", student.getEmail());
		} catch (PersonException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = PersonException.class)
	public void testInvalidSingleNameStudentEmail() throws PersonException{
				
		String invalidEmail = "jacoma";
		
		student = new Student("Jacó Mario Souza", cpf, rg, date, invalidEmail, address, phone1, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho",1);
	}
	
	@Test(expected = PersonException.class)
	public void testInvalidWithBlankSpaceslStudentEmail() throws PersonException{
				
		String invalidEmail = "jac  oma @ gmail.com";
		
		student = new Student("Jacó Mario Souza", cpf, rg, date, invalidEmail, address, phone1, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho",1);
	}
	
	@Test(expected = PersonException.class)
	public void testInvalidONStudentEmail() throws PersonException{
				
		String invalidEmail = "21342345";
		
		student = new Student("Jacó Mario Souza", cpf, rg, date, invalidEmail, address, phone1, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho",1);
	}
		
}
