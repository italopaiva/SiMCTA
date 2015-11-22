package modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Course;
import model.Package;
import model.Payment;
import model.Service;
import model.ServiceItem;
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
import exception.CourseException;
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
	private Course course1;
	private Course course2;
	private Package package1;
	
	private Date contractDate;
	
	private Date date;
	private Address address;
	private Phone phone1;
	private Phone phone2;
	private CPF cpf;
	private RG rg;
	private String email;
	private Integer value;
	
	@Before
	public void setUp() throws Exception{
		
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
		
		course1 = new Course("Aplicação de portas", "Aplica portas.", 5, 150000);
		course2 = new Course("Aplicação de janelas", "Aplica janelas.", 3, 250000);
		package1 = new Package(1, "Aplicação top", 300000);
		package1.addServiceItem(course1);
		package1.addServiceItem(course2);
		
		value = new Integer(100000);
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
			service = new Service(1, student, contractDate, payment, value);
			assertEquals(service.getServiceId(), new Integer(1));
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNullServiceId() throws ServiceException{
		
		service = new Service(null, student, contractDate, payment, value);
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNegativeServiceId() throws ServiceException{
		
		service = new Service(-1, student, contractDate, payment, value);
	}
	
	/** Tests for contract date **/
	
	@Test
	public void testValidContractDate(){
		
		try{
			service = new Service(1, student, contractDate, payment, value);
			assertEquals(service.getContractsDate(), contractDate);
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNullDate() throws ServiceException{
		
		service = new Service(1, student, null, payment, value);
	}
	
	/** Tests for service payment **/
	
	@Test
	public void testValidPayment(){
		
		try{
			service = new Service(1, student, contractDate, payment, value);
			assertEquals(service.getPayment(), payment);
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNullPayment() throws ServiceException{
		
		service = new Service(1, student, contractDate, null, value);
	}
	
	/** Tests for service student **/
	
	@Test
	public void testValidStudent(){
		
		try{
			service = new Service(1, student, contractDate, payment, value);
			assertEquals(service.getStudent(), student);
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNullStudentOfService() throws ServiceException{
		
		service = new Service(1, null, contractDate, payment, value);
	}
	
	/** Test for addItem() */
	@Test
	public void testIfAddValidItem(){
		
		try{
						
			service = new Service(1, student, contractDate, payment, value);
			service.addItem(course1);
			
			assertEquals(service.getItens().get(0), course1);
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test
	public void testIfAddValidPackageItem(){
		
		try{
						
			service = new Service(1, student, contractDate, payment, value);
			service.addItem(package1);
			
			assertEquals(service.getItens().get(0), package1);
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testIfAddInvalidNullItem() throws ServiceException{
						
		service = new Service(1, student, contractDate, payment, value);
		service.addItem(null);
	}
	
	/** Tests for get methods*/
	@Test
	public void testIfGetFormattedServiceValue(){
		
		try{
						
			service = new Service(1, student, contractDate, payment, value);
			
			assertEquals("R$ 1000,00", service.getTotalValueFormatted());
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test
	public void testIfGetInstallmentsValue(){
		
		try{
						
			service = new Service(1, student, contractDate, payment, value);
			
			assertEquals("R$ 500,00", service.getInstallmentsValue());
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	/** Tests for courses */
	
	@Test
	public void testIfGetCoursesOfService(){
		
		try{
						
			service = new Service(1, student, contractDate, payment, value);
			service.addItem(course1);
			service.addItem(package1);
			
			assertEquals(course1, service.getCourses().get(0));
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	/** Tests for packages */
	
	@Test
	public void testIfGetPackagesOfService(){
		
		try{
						
			service = new Service(1, student, contractDate, payment, value);
			service.addItem(course1);
			service.addItem(package1);
			
			assertEquals(package1, service.getPackages().get(0));
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
}
