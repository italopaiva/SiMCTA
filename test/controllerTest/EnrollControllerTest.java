package controllerTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Payment;
import model.Service;
import model.Student;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import controller.EnrollController;
import controller.PaymentController;
import controller.ServiceController;
import controller.StudentController;
import datatype.Address;
import datatype.CPF;
import datatype.Date;
import datatype.Phone;
import datatype.RG;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PaymentException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.StudentException;

public class EnrollControllerTest {
	
	  @Mock (name = "studentController") //same name as private var.
	  StudentController studentController;
	  @Mock (name = "serviceController")
	  ServiceController serviceController;
	  @Mock (name = "paymentController")
	  PaymentController paymentController;
	  @Mock
	  Student studentMock; //the object we want returned
	  @Mock
	  Service serviceMock;
	  @Mock
	  Payment paymentMock;
	  @InjectMocks
	  EnrollController enrollControllerTester; //the class to test

	private EnrollController enrollController;
	
	private Date date;
	private Address address;
	private Phone phone1;
	private Phone phone2;
	private CPF cpf;
	private RG rg;
	private String email;
	private ArrayList<String> courses;
	private ArrayList<String> packages;
	private Integer value;
	
	@Before
	public void setUp() throws AddressException, DateException, PhoneException, CPFException, RGException, PersonException{
		
		MockitoAnnotations.initMocks(this);
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasilia");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
		courses = new ArrayList<String>();
		packages = new ArrayList<String>();
		
		value = new Integer(100000);
		
	}
	
	@Test
	public void enrollStudentTest() throws StudentException, ServiceException, PaymentException{
		
		Mockito.when(studentController.newStudent("Ana Julia Costa", cpf, rg, date, email, address, phone1, phone2, "Maria Julia", "Julio Costa")).thenReturn(studentMock);
		Mockito.when(serviceController.newService(studentMock, courses, packages, value)).thenReturn(serviceMock);
		Mockito.when(paymentController.newPayment(serviceMock, 1, 1, 1)).thenReturn(paymentMock);
		
		try{
			enrollController.enrollStudent("Ana Julia Costa", cpf, rg, date, email, address, phone1, phone2, "Maria Julia", "Julio Costa", courses, packages, 1, 1, 2, value);
		}
		catch (StudentException | ServiceException | PaymentException e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}
}
