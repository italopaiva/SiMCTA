package controllerTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Course;
import model.Package;
import model.Student;
import model.Service;
import model.Payment;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import controller.PaymentController;
import controller.ServiceController;
import controller.StudentController;
import dao.PaymentDAO;
import dao.ServiceDAO;
import dao.StudentDAO;
import exception.AddressException;
import exception.CPFException;
import exception.CourseException;
import exception.DateException;
import exception.PaymentException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.PersonException;
import exception.StudentException;

public class StudentControllerTest {

	private StudentController studentController;
	private Package packageInstance;
	private ArrayList<String> coursesID;
	
	@Mock
	private StudentDAO studentDAOMock;
	private Date date;
	private Address address;
	private Phone phone1;
	private Phone phone2;
	private CPF cpf;
	private RG rg;
	private String email;
	private ServiceController serviceControllerMock;
	private Student student;
	
	@Before
	public void setUp() throws DateException, AddressException, PhoneException, CPFException, RGException, PersonException, PersonException{
		
		MockitoAnnotations.initMocks(this);
		studentDAOMock = mock(StudentDAO.class);
		serviceControllerMock = mock(ServiceController.class);
		
		studentController = new StudentController();
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
		
		student = new Student("Jacó Mário Souza", cpf, rg, date, email,
				address, phone1, phone2, "Milene Souza Medeiros",
				"Mário Souza Filho", 1);
	}
	
	@Test
	public void testIfFoundTheListOfStudents() throws PersonException, CPFException, PersonException, StudentException {
		
		ArrayList<Student> students = new ArrayList<Student>();
		
		Student student = new Student("Jacó Mário Souza", cpf);
		students.add(student);
		
		when(studentDAOMock.get("Jaco")).thenReturn(students);
		studentController.setStudentDAO(studentDAOMock);
		
		ArrayList<Student> studentName = new ArrayList<Student>();
		studentName = studentController.searchStudent("Jaco");
				
		assertEquals(students, studentName);
	}
	
	@Test
	public void testIfFoundTheDataOfAStudentWithACourse() throws PersonException, CPFException, PhoneException, 
													DateException, AddressException, RGException, SQLException, CourseException, ServiceException, PaymentException, StudentException {
		
		
		 
		ArrayList <String> courses = new ArrayList<String>();
		
		courses.add("1");
			
		when(studentDAOMock.get(cpf)).thenReturn(student);
		studentController.setStudentDAO(studentDAOMock);
		
		Date contractsDate = new Date(17,10,2015);
			
		int paymentId = 1;
		Payment payment = new Payment(paymentId);
		
		Service service = new Service(student, courses, null, contractsDate, payment);
		
		ArrayList<Service> services = new ArrayList<Service>();
		services.add(service);
		
		when(serviceControllerMock.searchService(student)).thenReturn(services);
		studentController.setServiceController(serviceControllerMock);
		
		ArrayList<Service> receivedServices = studentController.searchStudent(cpf);
		
		ArrayList<Service> servicesWithPayments = new ArrayList<Service>();
		Service serviceWithPayment = new Service(service, payment);
		servicesWithPayments.add(serviceWithPayment);
		
		serviceWithPayment = servicesWithPayments.get(0);
		Service receivedService = receivedServices.get(0);
		
		assertEquals(serviceWithPayment.getStudent(),receivedService.getStudent());
	}
	
	@Test
	public void testIfFoundTheDataOfAStudentWithAPackage() throws PersonException, CPFException, PhoneException, 
													DateException, AddressException, RGException, SQLException, CourseException, ServiceException, PaymentException, StudentException {
		
		Student student = new Student("Jacó Mário Souza", cpf, rg, date, email,
				address, phone1, phone2, "Milene Souza Medeiros",
				"Mário Souza Filho",1);
		 
		ArrayList <String> packages = new ArrayList<String>();
		
		packages.add("7");
			
		when(studentDAOMock.get(cpf)).thenReturn(student);
		studentController.setStudentDAO(studentDAOMock);
		
		Date contractsDate = new Date(17,10,2015);
			
		int paymentId = 1;
		Payment payment = new Payment(paymentId);
		
		Service service = new Service(student, null, packages, contractsDate, payment);
		
		ArrayList<Service> services = new ArrayList<Service>();
		services.add(service);
		
		when(serviceControllerMock.searchService(student)).thenReturn(services);
		studentController.setServiceController(serviceControllerMock);
		
		ArrayList<Service> receivedServices = studentController.searchStudent(cpf);
		
		ArrayList<Service> servicesWithPayments = new ArrayList<Service>();
		Service serviceWithPayment = new Service(service, payment);
		servicesWithPayments.add(serviceWithPayment);
		
		serviceWithPayment = servicesWithPayments.get(0);
		Service receivedService = receivedServices.get(0);
		
		assertEquals(serviceWithPayment.getStudent(),receivedService.getStudent());
	}

	
	@Test
	public void testIfFoundTheDataOfAStudentWithAPackageAndCourse() throws PersonException, CPFException, PhoneException, 
													DateException, AddressException, RGException, SQLException, CourseException, ServiceException, PaymentException, StudentException {
		
		Student student = new Student("Jacó Mário Souza", cpf, rg, date, email,
				address, phone1, phone2, "Milene Souza Medeiros",
				"Mário Souza Filho",1);
		 
		ArrayList <String> courses = new ArrayList<String>();
		ArrayList <String> packages = new ArrayList<String>();

		courses.add("1");
		packages.add("1");
			
		when(studentDAOMock.get(cpf)).thenReturn(student);
		studentController.setStudentDAO(studentDAOMock);
		
		Date contractsDate = new Date(17,10,2015);
			
		int paymentId = 1;
		Payment payment = new Payment(paymentId);
		
		Service service = new Service(student, courses, packages, contractsDate, payment);
		
		ArrayList<Service> services = new ArrayList<Service>();
		services.add(service);
		
		when(serviceControllerMock.searchService(student)).thenReturn(services);
		studentController.setServiceController(serviceControllerMock);
		
		ArrayList<Service> receivedServices = studentController.searchStudent(cpf);
		
		ArrayList<Service> servicesWithPayments = new ArrayList<Service>();
		Service serviceWithPayment = new Service(service, payment);
		servicesWithPayments.add(serviceWithPayment);
		
		serviceWithPayment = servicesWithPayments.get(0);
		Service receivedService = receivedServices.get(0);
		
		assertEquals(serviceWithPayment.getStudent(),receivedService.getStudent());
	}
	
	@Test
	public void testIfAlterStatusOfTheStudent() throws PersonException, StudentException{
		
		when(studentDAOMock.update(student)).thenReturn(true);
		
		boolean wasUpdate = studentController.alterStatusOfTheStudent(student);
		
		assertTrue(wasUpdate);
		
	}
	
	@Test(expected = PersonException.class)
	public void testIfNotAlterStatusWithANullStudent() throws PersonException, StudentException{
		
		student = null;
		when(studentDAOMock.update(student)).thenReturn(true);
		
		boolean wasUpdate = studentController.alterStatusOfTheStudent(student);
		
		assertTrue(wasUpdate);
		
	}
}
