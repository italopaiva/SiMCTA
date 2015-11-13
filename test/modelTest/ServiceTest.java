package modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Payment;
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
import exception.PaymentException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.StudentException;

public class ServiceTest {

	private Service service;
	private Student student;
	private Payment payment;
	
	private Date contractDate;
	
	private Date date;
	private Address address;
	private Phone phone1;
	private Phone phone2;
	private CPF cpf;
	private RG rg;
	private String email;
	
	@Before
	public void setUp() throws DateException, AddressException, PhoneException,
						CPFException, RGException, StudentException, PersonException, PaymentException{
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
		
		student = new Student("Jacó Mário Souza", cpf, rg, date, email, address, phone1,
							  phone2, "Milene Souza Medeiros", "Mário Souza Filho",1);
		
		contractDate = new Date(10, 10, 2015);
		
		payment = new Payment(2, 1, 1, 2);
	}
	
	/** Tests for constructors */
	@Test
	public void testConstructorWithStudent(){
		
		try{
			service = new Service(student);
			assertEquals(service.getStudent().getCpf(), cpf);
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test
	public void testConstructorWithIdAndStudent(){
		
		try{
			service = new Service(1, student);
			assertEquals(service.getServiceId(), new Integer(1));
			assertEquals(service.getStudent().getCpf(), cpf);
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	/** Tests for serviceID **/
	
	@Test
	public void testValidServiceId(){
		
		try{
			service = new Service(1, student, contractDate, payment);
			assertEquals(service.getServiceId(), new Integer(1));
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNullServiceId() throws ServiceException{
		
		service = new Service(null, student, contractDate, payment);
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNegativeServiceId() throws ServiceException{
		
		service = new Service(-1, student, contractDate, payment);
	}
	
	/** Tests for contract date **/
	
	@Test
	public void testValidContractDate(){
		
		try{
			service = new Service(1, student, contractDate, payment);
			assertEquals(service.getContractsDate(), contractDate);
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNullDate() throws ServiceException{
		
		service = new Service(1, student, null, payment);
	}
	
	/** Tests for service payment **/
	
	@Test
	public void testValidPayment(){
		
		try{
			service = new Service(1, student, contractDate, payment);
			assertEquals(service.getPayment(), payment);
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNullPayment() throws ServiceException{
		
		service = new Service(1, student, contractDate, null);
	}
	
	/** Tests for service student **/
	
	@Test
	public void testValidStudent(){
		
		try{
			service = new Service(1, student, contractDate, payment);
			assertEquals(service.getStudent(), student);
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNullStudentOfService() throws ServiceException{
		
		service = new Service(1, null, contractDate, payment);
	}
	
	/** Tests for courses */
		
	/** Tests for packages */
}
