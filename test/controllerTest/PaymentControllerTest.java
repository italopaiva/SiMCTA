package controllerTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import org.mockito.MockitoAnnotations;

import controller.PaymentController;
import controller.StudentController;
import dao.PaymentDAO;
import dao.StudentDAO;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PaymentException;
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.StudentException;

public class PaymentControllerTest {

	private PaymentController paymentController;
	private PaymentDAO paymentDAOMock;
	private Date date;
	private Address address;
	private Phone phone1;
	private Phone phone2;
	private CPF cpf;
	private RG rg;
	private String email;
	
	@Before
	public void setUp() throws DateException, AddressException, PhoneException, CPFException, RGException{
		
		MockitoAnnotations.initMocks(this);
		paymentDAOMock = mock(PaymentDAO.class);
		
		paymentController = new PaymentController();
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
	}
	
	@Test
	public void testIfGetsThePaymentForTheId() throws PaymentException, StudentException, ServiceException {
		
		int paymentId = 3;
		Student student = new Student("Jacó Mário Souza", cpf, rg, date, email, address, phone1, phone2, 
				  "Milene Souza Medeiros", "Mário Souza Filho");		
		ArrayList <String> courses = new ArrayList<String>();
		ArrayList <String> packages = new ArrayList<String>();
		
		courses.add("1");
		packages = null;
		Service service = new Service(student, courses, packages);
		int paymentType = 1;
		int paymentForm = 1;
		int installments = 0;
		
		Payment payment = new Payment(paymentId);
		
		when(paymentDAOMock.get(paymentId)).thenReturn(payment);
		paymentController.setPaymentDAO(paymentDAOMock);
		
		Payment receivedPayment = paymentController.searchPayment(payment);

		assertEquals(payment.getPaymentId(),receivedPayment.getPaymentId());

	}
	
	@Test(expected = PaymentException.class)
	public void testIfGetsThePaymentForTheIdWithNoPayment() throws PaymentException, StudentException, ServiceException {
		
		int paymentId = 3;
		
		Payment payment = null;
		
		when(paymentDAOMock.get(paymentId)).thenReturn(payment);
		paymentController.setPaymentDAO(paymentDAOMock);
		
		Payment receivedPayment = paymentController.searchPayment(payment);
	
	}
	
	@Test(expected = PaymentException.class)
	public void testIfGetsThePaymentForTheIdInexistentPaymentId() throws PaymentException, StudentException, ServiceException {
		
		int paymentId = 1;
		Student student = new Student("Jacó Mário Souza", cpf, rg, date, email, address, phone1, phone2, 
				  "Milene Souza Medeiros", "Mário Souza Filho");		
		ArrayList <String> courses = new ArrayList<String>();
		ArrayList <String> packages = new ArrayList<String>();
		
		courses.add("1");
		packages = null;
		Service service = new Service(student, courses, packages);
		int paymentType = 1;
		int paymentForm = 1;
		int installments = 0;
		
		Payment payment = new Payment(paymentId, service, paymentType, paymentForm, installments);
		
		when(paymentDAOMock.get(paymentId)).thenReturn(payment);
		paymentController.setPaymentDAO(paymentDAOMock);
		
		Payment receivedPayment = paymentController.searchPayment(payment);
	
	}

}
