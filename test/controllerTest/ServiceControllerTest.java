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
import exception.PersonException;
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
	public void setUp() throws DateException, AddressException, PhoneException, CPFException, RGException, StudentException, PaymentException, PersonException{
		
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
		
		Payment payment1_ = new Payment(1);
		Payment payment2_ = new Payment(2);
		
		Integer value = new Integer(100000);
		
		Service service1 = new Service(5, student, new Date(17,10,2015), payment1_, value);
		Service service2 = new Service(3, student, new Date(20,10,2015), payment2_,value);

		ArrayList<Service> services = new ArrayList<Service>();
		services.add(service1);
		services.add(service2);
		
		when(serviceDAOMock.get(student)).thenReturn(services);
		serviceController.setServiceDAO(serviceDAOMock);
		
		Payment payment1 = new Payment(1, 1, 1, 1);
		Payment payment2 = new Payment(2, 1, 1, 2);
		
		when(paymentControllerMock.searchPayment(payment1_)).thenReturn(payment1);
		when(paymentControllerMock.searchPayment(payment2_)).thenReturn(payment2);
		serviceController.setPaymentController(paymentControllerMock);
		
		CPF studentCpf = student.getCpf();
		ArrayList<Service> arrayListOfService =  serviceController.searchService(studentCpf);
		
		ArrayList<Service> arrayListExpected = new ArrayList<Service>();

		Service service1_ = new Service(5, student, new Date(17,10,2015), payment1, value);
		Service service2_ = new Service(3, student, new Date(20,10,2015), payment2, value);
		arrayListExpected.add(service1_);
		arrayListExpected.add(service2_);
		
		assertEquals(arrayListExpected, arrayListOfService);

	}

}
