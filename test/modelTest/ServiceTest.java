package modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Service;
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
import exception.ServiceException;
import exception.StudentException;

public class ServiceTest {

	private Service service;
	private Student student;
	
	private Date date;
	private Address address;
	private Phone phone1;
	private Phone phone2;
	private CPF cpf;
	private RG rg;
	private String email;
	
	@Before
	public void setUp() throws DateException, AddressException, PhoneException,
						CPFException, RGException, StudentException, PersonException{
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
		
		student = new Student("Jacó Mário Souza", cpf, rg, date, email, address, phone1,
							  phone2, "Milene Souza Medeiros", "Mário Souza Filho",1);
	}
	
	/** Tests for student */
	@Test
	public void testValidStudentOfService(){
		
		try{
			service = new Service(student);
			assertEquals(service.getStudent().getCpf(), cpf);
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNullStudentOfService() throws ServiceException{
		
		service = new Service(null);
	}
	
	/** Tests for courses */
		
	/** Tests for packages */
}
