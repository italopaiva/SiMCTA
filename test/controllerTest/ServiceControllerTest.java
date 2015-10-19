package controllerTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import model.Course;
import model.Package;
import model.Payment;
import model.Service;
import model.Student;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;
import controller.PaymentController;
import controller.ServiceController;
import controller.StudentController;
import dao.CourseDAO;
import dao.PackageDAO;
import dao.PaymentDAO;
import dao.ServiceDAO;
import exception.AddressException;
import exception.CPFException;
import exception.CourseException;
import exception.DateException;
import exception.PaymentException;
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.StudentException;

public class ServiceControllerTest {

	private ServiceController serviceController;
	private ServiceDAO serviceDAOMock;
	private PaymentDAO paymentDAOMock;
	private Date date;
	private Address address;
	private Phone phone1;
	private Phone phone2;
	private CPF cpf;
	private RG rg;
	private String email;
	private Student student;
	private Date contractsDate;
	private PaymentController paymentControllerMock;
	
	@Before
	public void setUp() throws DateException, AddressException, PhoneException, CPFException, RGException, StudentException, PaymentException{
		
		MockitoAnnotations.initMocks(this);
		serviceDAOMock = mock(ServiceDAO.class);
		paymentDAOMock = mock(PaymentDAO.class);
		paymentControllerMock = mock(PaymentController.class);

		serviceController = new ServiceController();
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("59418933535");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
		
		 student = new Student("Jacó Mário Souza", cpf, rg, date, email,
				address, phone1, phone2, "Milene Souza Medeiros",
				"Mário Souza Filho", 1);
		 
		contractsDate = new Date(17,10,2015);
		
	}

	@Test
	public void testIfSearchAService() throws PaymentException,
			StudentException, ServiceException, CourseException, DateException {

		ArrayList<String> courses = new ArrayList<String>();
		ArrayList<String> packages = new ArrayList<String>();

		courses.add("1");
		
		Payment payment1 = new Payment(1);
		Service service = new Service(student, courses, packages, contractsDate, payment1);

		ArrayList<Service> services = new ArrayList<Service>();
		services.add(service);

		when(serviceDAOMock.get(student)).thenReturn(services);
		serviceController.setServiceDAO(serviceDAOMock);
	
		int paymentId = 1;
		Payment paymentID = new Payment(paymentId);
		Payment payment = new Payment(paymentId,1,1,1);
		when(paymentControllerMock.searchPayment(paymentID)).thenReturn(payment);
		serviceController.setPaymentController(paymentControllerMock);

		ArrayList<Service> servicesWithPayment = new ArrayList<Service>();
		Service serviceWithPayment = new Service(service, payment);
		servicesWithPayment.add(serviceWithPayment);

		ArrayList<Service> receivedServices = new ArrayList<Service>();
		receivedServices = serviceController.searchService(student);

		serviceWithPayment = servicesWithPayment.get(0);
		Service receivedService = receivedServices.get(0);
		
		assertEquals(serviceWithPayment.getServiceId(), receivedService.getServiceId());
		assertEquals(serviceWithPayment.getStudent(), receivedService.getStudent());

	}

}
