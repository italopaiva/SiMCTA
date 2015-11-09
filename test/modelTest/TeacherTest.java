package modelTest;

import static org.junit.Assert.*;
import model.Student;
import model.Teacher;
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
import exception.TeacherException;

public class TeacherTest {

	Teacher teacher;
	Date date;
	Address address;
	Phone phone1;
	Phone phone2;
	CPF cpf;
	RG rg;
	String name;
	String email;
	String qualification;
	
	@Before
	public void setUp() throws PhoneException, CPFException, RGException, PersonException, DateException, AddressException{
		
		name = "João da Silva";
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
		qualification = "Mecânica automotiva";
	}
	
	
	@Test
	public void testValidTeacherToRegister(){
		try {
			teacher = new Teacher(name, cpf, rg, date, email, address, phone1, phone2, 
							      "Milene Souza Medeiros", "Mário Souza Filho",qualification);
			assertEquals(name, teacher.getName());
			assertEquals(cpf, teacher.getCpf());
			assertEquals(rg, teacher.getRg());
		} 
		catch (PersonException|TeacherException e) {
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	
	@Test
	public void testValidTeacherQualification() throws TeacherException {
		
			try {
				teacher = new Teacher(name, cpf, rg, date, email, address, phone1, phone2, 
								      "Milene Souza Medeiros", "Mário Souza Filho",qualification);
				assertEquals(qualification, teacher.getQualification());
			} 
			catch (PersonException|TeacherException e) {
				fail("Should not throw this exception: "+e.getMessage());
			}

	}
	
	@Test(expected = TeacherException.class)
	public void testInvalidTeacherQualification() throws TeacherException {
			
			String invalidQualification = "Mecânica 123!@";
			try {
				teacher = new Teacher(name, cpf, rg, date, email, address, phone1, phone2, 
									  "Milene Souza Medeiros", "Mário Souza Filho",invalidQualification);
			} 
			catch (PersonException e) {
				fail("Should throw this exception: "+e.getMessage());
			}
	}
	
	@Test(expected = TeacherException.class)
	public void testNullTeacherQualification() throws TeacherException {
			
			String nullQualification = null;
			try {
				teacher = new Teacher(name, cpf, rg, date, email, address, phone1, phone2, 
									  "Milene Souza Medeiros", "Mário Souza Filho",nullQualification);
			} 
			catch (PersonException e) {
				fail("Should throw this exception: "+e.getMessage());
			}
	}
	

	@Test(expected = TeacherException.class)
	public void testEmptyTeacherQualification() throws TeacherException {
			
			String emptyQualification = "";
			try {
				teacher = new Teacher(name, cpf, rg, date, email, address, phone1, phone2, 
									  "Milene Souza Medeiros", "Mário Souza Filho",emptyQualification);
			} 
			catch (PersonException e) {
				fail("Should throw this exception: "+e.getMessage());
			}
	}

	@Test
	public void testValidTeacherToSearch(){
		try {
			teacher = new Teacher(name, cpf);
			assertEquals(name, teacher.getName());
		} 
		catch (PersonException e) {
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test(expected = PersonException.class)
	public void testInalidTeacherToSearch() throws PersonException{
		teacher = new Teacher("", cpf);
	}

}
