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
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.StudentException;

public class ServiceTest {

	private Service service;
	private Student student;
	private ArrayList<String> courses = new ArrayList<String>();
	private ArrayList<String> packages = new ArrayList<String>();
	
	private Date date;
	private Address address;
	private Phone phone1;
	private Phone phone2;
	private CPF cpf;
	private RG rg;
	private String email;
	
	@Before
	public void setUp() throws DateException, AddressException, PhoneException,
						CPFException, RGException, StudentException{
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
		
		student = new Student("Jacó Mário Souza", cpf, rg, date, email, address, phone1,
							  phone2, "Milene Souza Medeiros", "Mário Souza Filho");
		
		String invalidCourse = "46163";
		courses.add(0, "1");
		courses.add(1, "2");
		courses.add(2, invalidCourse);
		
		String invalidPackage = "12231";
		packages.add(0, "7");
		packages.add(1, invalidPackage);
	}
	
	/** Tests for student */
	@Test
	public void testValidStudentOfService(){
		
		try{
			service = new Service(student, courses, packages);
			assertEquals(service.getStudent().getStudentCpf(), cpf);
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNullStudentOfService() throws ServiceException{
		
		service = new Service(null, courses, packages);
	}
	
	/** Tests for courses */
	@Test
	public void testValidCoursesOfService(){
		
		try{
			service = new Service(student, courses, packages);
			assertEquals(1, service.getCourses().get(0).getCourseId());
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test
	public void testValidCourses2OfService(){
		
		try{
			service = new Service(student, courses, packages);
			assertEquals(2, service.getCourses().get(1).getCourseId());
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test
	public void testValidEmptyCoursesOfServiceWithNotNullPackages(){
		
		try{
			service = new Service(student, new ArrayList<String>(), packages);
			assertTrue(service.getCourses().isEmpty());
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test
	public void testValidNullCoursesOfServiceWithNotNullPackages(){
		
		try{
			service = new Service(student, null, packages);
			assertTrue(service.getCourses().isEmpty());
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	/** Tests for packages */
	@Test
	public void testValidPackagesOfService(){
		
		try{
			service = new Service(student, courses, packages);
			assertEquals(new Integer(7), service.getPackages().get(0).getPackageId());
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test
	public void testValidEmptyPackagesOfServiceWithNotNullCourses(){
		
		try{
			service = new Service(student, courses, new ArrayList<String>());
			assertTrue(service.getPackages().isEmpty());
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test
	public void testValidNullPackagesOfServiceWithNotNullCourses(){
		
		try{
			service = new Service(student, courses, null);
			assertTrue(service.getPackages().isEmpty());
		}
		catch(ServiceException e){
			fail("Should not throw this exception:" + e.getMessage());
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidEmptyPackagesAndCoursesOfService() throws ServiceException{
		
		service = new Service(student, new ArrayList<String>(), new ArrayList<String>());
	}
	
	@Test(expected = ServiceException.class)
	public void testInvalidNullPackagesAndCoursesOfService() throws ServiceException{
		
		service = new Service(student, null, null);
	}
}
