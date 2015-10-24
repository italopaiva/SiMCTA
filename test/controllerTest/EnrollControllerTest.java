package controllerTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;

import org.junit.Before;
import org.junit.Test;

import controller.EnrollController;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PaymentException;
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.StudentException;

public class EnrollControllerTest {

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
	
	@Before
	public void setUp() throws AddressException, DateException, PhoneException, CPFException, RGException{
		enrollController = new EnrollController();
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasilia");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
		courses = new ArrayList<String>();
		packages = new ArrayList<String>();
	}
	
	@Test
	public void testValidEnrollData(){
		
		courses.add("1");
		packages.add("7");
		
		try{
			enrollController.enrollStudent("Ana Julia Costa", cpf, rg, date, email, address, phone1, phone2, "Maria Julia", "Julio Costa", courses, packages, 1, 1, 2);
		}
		catch (StudentException | ServiceException | PaymentException e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}
}
