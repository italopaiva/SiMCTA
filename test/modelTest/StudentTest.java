package modelTest;

import static org.junit.Assert.*;

import org.junit.Test;

import exception.CPFException;
import exception.DateException;
import exception.PhoneException;
import exception.RGException;
import exception.StudentException;
import model.Student;
import model.datatype.CPF;
import model.datatype.Phone;
import model.datatype.RG;
import model.datatype.Date;

public class StudentTest {
	
	Student student;
	
	public void testIfCreatesAStudent() throws PhoneException, CPFException, RGException, StudentException, DateException{
		
		
		Date date = new Date(1986,05,11);
		//Address address = new Address();
		Phone phone1 = new Phone("61","83265622");
		Phone phone2 = new Phone("61","32551111");
		CPF cpf = new CPF("87654321124");
		RG rg = new RG("8598298", "SSP", "DF");
		
		student = new Student("Jacó Mario Souza",cpf, rg, date, null, phone1, phone2, 
						      "Milene Souza Medeiros", "Mário Souza Filho");
	}
	
	/**
	 * Birthdate tests
	 * @throws StudentException
	 */
	@Test
	public void testBirthDateValid() throws StudentException{
		
				
	}
	
	@Test(expected = StudentException.class)
	public void testBirthDateInvalid() throws StudentException{
		
		Date birthdate = null;
		student = new Student(birthdate);
	}
	
}
