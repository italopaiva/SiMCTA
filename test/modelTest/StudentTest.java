package modelTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PhoneException;
import exception.RGException;
import exception.StudentException;
import model.Student;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Phone;
import model.datatype.RG;
import model.datatype.Date;

public class StudentTest {
	
	Student student;
	Date date;
	Address address;
	Phone phone1;
	Phone phone2;
	CPF cpf;
	RG rg;
	
	@Before
	public void setUp() throws PhoneException, CPFException, RGException, StudentException, DateException, AddressException{
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
	}
	
	/** Tests for student name */
	@Test
	public void testValidStudentName(){
		
		String validStudentName = "Jacó Mario Souza";
		
		try {
			student = new Student(validStudentName, cpf, rg, date, address, phone1, phone2, 
							      "Milene Souza Medeiros", "Mário Souza Filho");
			assertEquals(validStudentName, student.getStudentName());
		} catch (StudentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = StudentException.class)
	public void testEmptyStudentName() throws StudentException{
		
		String validStudentName = "";
		
		student = new Student(validStudentName, cpf, rg, date, address, phone1, phone2, 
							      "Milene Souza Medeiros", "Mário Souza Filho");
	}
	
	@Test(expected = StudentException.class)
	public void testNullStudentName() throws StudentException{
		
		String validStudentName = null;
		
		student = new Student(validStudentName, cpf, rg, date, address, phone1, phone2, 
							      "Milene Souza Medeiros", "Mário Souza Filho");
	}
	
	@Test(expected = StudentException.class)
	public void testNonLettersStudentName() throws StudentException{
		
		String validStudentName = "João 123";
		
		student = new Student(validStudentName, cpf, rg, date, address, phone1, phone2, 
							      "Milene Souza Medeiros", "Mário Souza Filho");
	}
	
	/** Tests for mother name*/
	@Test
	public void testValidMotherName(){
		
		String validMotherName = "Milene Souza Medeiros";
		
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
							      validMotherName, "Mário Souza Filho");
			assertEquals(validMotherName, student.getMotherName());
		} catch (StudentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = StudentException.class)
	public void testEmptyMotherName() throws StudentException{
		
		String invalidMotherName = "";
		
		student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
						      invalidMotherName, "Mário Souza Filho");
	}

	@Test(expected = StudentException.class)
	public void testNullMotherName() throws StudentException{
		
		String invalidMotherName = null;
		
		student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
						      invalidMotherName, "Mário Souza Filho");
	}
	
	@Test(expected = StudentException.class)
	public void testNonLettersMotherName() throws StudentException{
		
		String invalidMotherName = "Milene 124";
		
		student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
						      invalidMotherName, "Mário Souza Filho");
	}
	
	/** Tests for father name*/
	@Test
	public void testValidFatherName(){
		
		String validFatherName = "Mário Souza Filho";
		
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
								  "Milene Souza Medeiros", validFatherName);
			assertEquals(validFatherName, student.getFatherName());
		} catch (StudentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testValidEmptyFatherName(){
		
		String validFatherName = "";
		
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
								  "Milene Souza Medeiros", validFatherName);
			assertEquals("", student.getFatherName());
		} catch (StudentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testValidNullFatherName(){
		
		String validFatherName = null;
		
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
								  "Milene Souza Medeiros", validFatherName);
			assertEquals("", student.getFatherName());
		} catch (StudentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = StudentException.class)
	public void testNonLettersFatherName() throws StudentException{
		
		String invalidFatherName = "Mário 123";
		
		student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
							  "Milene Souza Medeiros", invalidFatherName);
	}
	
	/** Tests for CPF*/
	@Test
	public void testValidStudentCPF(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho");
			assertEquals(cpf, student.getStudentCpf());
		} catch (StudentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = StudentException.class)
	public void testInvalidStudentCPF() throws StudentException{
			
		student = new Student("Jacó Mario Souza", null, rg, date, address, phone1, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho");
	}
	
	/** Tests for RG*/
	@Test
	public void testValidStudentRG(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho");
			assertEquals(rg, student.getStudentRg());
		} catch (StudentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = StudentException.class)
	public void testInvalidStudentRG() throws StudentException{
			
		student = new Student("Jacó Mario Souza", cpf, null, date, address, phone1, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho");
	}
	
	/** Tests for Address*/
	@Test
	public void testValidStudentAddress(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho");
			assertEquals(address, student.getAddress());
		} catch (StudentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = StudentException.class)
	public void testInvalidStudentAddress() throws StudentException{
			
		student = new Student("Jacó Mario Souza", cpf, rg, date, null, phone1, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho");
	}
	
	/** Tests for Phone 1*/
	@Test
	public void testValidStudentPhone1(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho");
			assertEquals(phone1, student.getPrincipalPhone());
		} catch (StudentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = StudentException.class)
	public void testInvalidStudentPhone1() throws StudentException{
			
		student = new Student("Jacó Mario Souza", cpf, rg, date, address, null, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho");
	}
	
	/** Tests for Phone 2*/
	@Test
	public void testValidStudentPhone2(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho");
			assertEquals(phone2, student.getSecondaryPhone());
		} catch (StudentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = StudentException.class)
	public void testInvalidStudentPhone2() throws StudentException{
			
		student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, null, 
							  "Milene Souza Medeiros", "Mário Souza Filho");
	}
	
	/** Tests for Birthdate*/
	@Test
	public void testValidStudentBirthdate(){
				
		try {
			student = new Student("Jacó Mario Souza", cpf, rg, date, address, phone1, phone2, 
								  "Milene Souza Medeiros", "Mário Souza Filho");
			assertEquals(date, student.getBirthdate());
		} catch (StudentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = StudentException.class)
	public void testInvalidStudentBirthdate() throws StudentException{
			
		student = new Student("Jacó Mario Souza", cpf, rg, null, address, phone1, phone2, 
							  "Milene Souza Medeiros", "Mário Souza Filho");
	}
}
