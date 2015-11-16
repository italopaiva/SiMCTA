package modelTest;

import static org.junit.Assert.*;
import model.Class;
import model.Course;
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
import exception.ClassException;
import exception.CourseException;
import exception.DateException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.TeacherException;

public class ClassTest {
	
	private Class classInstance;
	private Course course;
	private Teacher teacher;
	private Date date;
	private Address address;
	private Phone phone1;
	private Phone phone2;
	private CPF cpf;
	private RG rg;
	private String name;
	private String email;
	private String qualification;
	
	@Before
	public void setUp() throws PersonException, TeacherException, RGException, CPFException, PhoneException, DateException, AddressException, CourseException{
		name = "João da Silva";
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
		qualification = "Mecânica automotiva";
		
		teacher = new Teacher(name, cpf, rg, date, email, address, phone1, phone2, 
			      			"Milene Souza Medeiros", "Mário Souza Filho",qualification);
		
		course = new Course(1, "Aplicação de película", "Top", 3, 150000);
	}
	
	@Test
	public void testIfGeneratesId(){
		
		try{
			classInstance = new Class(new Date(10, 02, 2015), "MA", teacher, course);
			
			assertEquals("APLICAÇÃO - MA 10/2/15", classInstance.getClassId());
		}
		catch(ClassException | DateException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test
	public void testIfGeneratesEndDate(){
		
		try{
			classInstance = new Class(new Date(10, 02, 2015), "MA", teacher, course);
			
			assertEquals("2015-2-25", classInstance.getEndDate().getHyphenFormattedDate());
		}
		catch(ClassException | DateException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}

	@Test (expected = DateException.class)	
	public void testIfGeneratesEndDateWithInvalidStartDate() throws ClassException, DateException{

		classInstance = new Class(new Date(10, 14, 2015), "MA", teacher, course);
		classInstance.getEndDate();
	
	}
}
