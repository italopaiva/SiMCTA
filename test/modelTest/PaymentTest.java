package modelTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Payment;
import model.PaymentDescription;
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
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.StudentException;

public class PaymentTest{
	
	private Payment payment;
	
	private Service service;	
	private Student student;
	private Date date;
	private Address address;
	private Phone phone1;
	private Phone phone2;
	private CPF cpf;
	private RG rg;
	private String email;
	private ArrayList<String> courses = new ArrayList<String>();
	private ArrayList<String> packages = new ArrayList<String>();
	
	// May change because the database. It is the sum of the courses and packages values.
	private static final Integer SERVICE_VALUE = new Integer(455005);
	
	@Before
	public void setUp() throws DateException, AddressException, PhoneException,
						CPFException, RGException, StudentException, ServiceException{
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
		
		student = new Student("Jacó Mário Souza", cpf, rg, date, email, address, phone1,
							  phone2, "Milene Souza Medeiros", "Mário Souza Filho", 1);
		
		courses.add(0, "1");
		courses.add(1, "2");
		packages.add(0, "7");
		
		service = new Service(student, courses, packages);
	}
	
	/** Tests for payment service */
	@Test
	public void testValidServicePayment(){
		
		try{
			payment = new Payment(service, 1, 1, 0);
			assertEquals(service, payment.getService());
		}
		catch (PaymentException e){
			fail("Should not throw this exception: "+ e.getMessage());
		}
	}
	
	@Test(expected = PaymentException.class)
	public void testNullServicePayment() throws PaymentException{
		
		payment = new Payment(null, 1, 1, 0);
	}
	
	@Test
	public void testValueOfServicePayment(){
		
		try{
			payment = new Payment(service, 1, 1, 0);
			assertEquals(SERVICE_VALUE, payment.getValue());
		}
		catch (PaymentException e){
			fail("Should not throw this exception: "+ e.getMessage());
		}
	}
	
	@Test
	public void testDescriptionOfServicePayment(){
		
		try{
			PaymentDescription description = new PaymentDescription(1, 1);
			payment = new Payment(service, 1, 1, 0);
			assertEquals(description.getPaymentType(), payment.getDescription().getPaymentType());
			assertEquals(description.getPaymentForm(), payment.getDescription().getPaymentForm());
		}
		catch (PaymentException e){
			fail("Should not throw this exception: "+ e.getMessage());
		}
	}
	
	/** Tests for payment installments*/
	@Test
	public void testInstallmentsOfPayment(){
		
		try{
			payment = new Payment(service, 1, 1, 0);
			assertEquals(new Integer(0), payment.getInstallments());
		}
		catch (PaymentException e){
			fail("Should not throw this exception: "+ e.getMessage());
		}
	}
	
	@Test
	public void testInstallments1OfPayment(){
		
		try{
			payment = new Payment(service, 1, 1, 1);
			assertEquals(new Integer(1), payment.getInstallments());
		}
		catch (PaymentException e){
			fail("Should not throw this exception: "+ e.getMessage());
		}
	}
	
	@Test
	public void testInstallments10OfPayment(){
		
		try{
			payment = new Payment(service, 1, 1, 10);
			assertEquals(new Integer(10), payment.getInstallments());
		}
		catch (PaymentException e){
			fail("Should not throw this exception: "+ e.getMessage());
		}
	}
	
	@Test
	public void testValidNullInstallmentsOfPayment(){
		
		try{
			payment = new Payment(service, 1, 1, null);
			assertEquals(new Integer(0), payment.getInstallments());
		}
		catch (PaymentException e){
			fail("Should not throw this exception: "+ e.getMessage());
		}
	}
	
	@Test(expected = PaymentException.class)
	public void testNegativeInstallmentsOfPayment() throws PaymentException{
		
		payment = new Payment(service, 1, 1, -1);
	}
	
	@Test(expected = PaymentException.class)
	public void testNegative10InstallmentsOfPayment() throws PaymentException{
		
		payment = new Payment(service, 1, 1, -10);
	}
}
