package controllerTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Package;
import model.Student;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controller.StudentController;
import dao.StudentDAO;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PhoneException;
import exception.RGException;
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

	@Before
	public void setUp() throws DateException, AddressException, PhoneException, CPFException, RGException{
		
		MockitoAnnotations.initMocks(this);
		studentDAOMock = mock(StudentDAO.class);
		
		studentController = new StudentController();
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
	}
	
	@Test
	public void testIfFoundTheListOfStudents() throws StudentException, CPFException {
		
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
	public void testIfFoundTheBasicDataOfAStudent() throws StudentException, CPFException, PhoneException, 
													DateException, AddressException, RGException, SQLException {
		
		Student student = new Student("Jacó Mário Souza", cpf);
		
		when(studentDAOMock.get(cpf)).thenReturn(student);
		studentController.setStudentDAO(studentDAOMock);
		
		Student receivedStudent = new Student("Jacó Mário Souza", cpf, rg, date, email, address, phone1, phone2, 
				  "Milene Souza Medeiros", "Mário Souza Filho");
		receivedStudent = studentController.searchStudent(cpf);
				
		assertEquals(receivedStudent, student);
	}

}
