package controllerTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import model.Package;
import model.Student;
import model.Teacher;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controller.ServiceController;
import controller.TeacherController;
import dao.TeacherDAO;
import datatype.Address;
import datatype.CPF;
import datatype.Date;
import datatype.Phone;
import datatype.RG;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.TeacherException;

public class TeacherControllerTest {

	private Package packageInstance;
	private ArrayList<String> coursesID;
	
	@Mock
	private TeacherDAO teacherDAOMock;
	private Date date;
	private Address address;
	private Phone phone1;
	private Phone phone2;
	private CPF cpf;
	private RG rg;
	private String email;
	String qualification;
	private Teacher teacher;
	private TeacherController teacherController;
	
	@Before
	public void setUp() throws DateException, AddressException, PhoneException, CPFException, RGException, PersonException, PersonException, TeacherException{
		
		MockitoAnnotations.initMocks(this);
		teacherDAOMock = mock(TeacherDAO.class);
		
		teacherController = new TeacherController();
		
		date = new Date(05, 06, 1996);
		address = new Address("Rua 3 ", "6B", "", "72323411", "Brasília");
		phone1 = new Phone("61","83265622");
		phone2 = new Phone("61","32551111");
		cpf = new CPF("51464638403");
		rg = new RG("8598298", "SSP", "DF");
		email = "jacoma@gmail.com";
		qualification = "Mecânica automotiva";

		teacher = new Teacher("Jacó Mário Souza", cpf, rg, date, email,
				address, phone1, phone2, "Milene Souza Medeiros",
				"Mário Souza Filho", qualification, Teacher.ACTIVE);
	}
	
	
	@Test
	public void testIfGetAllTeachersActive() throws PersonException, PhoneException, CPFException, DateException, AddressException, RGException, TeacherException {
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		Teacher teacher = new Teacher("Jacó Mário Souza", cpf);
		teachers.add(teacher);
		
		when(teacherDAOMock.get()).thenReturn(teachers);
		teacherController.setTeacherDAO(teacherDAOMock);
		
		ArrayList<Teacher> foundTeachers = new ArrayList<Teacher>();
		foundTeachers = teacherController.getTeachers();
				
		assertEquals(teachers, foundTeachers);	
	}
	
	@Test(expected = TeacherException.class)
	public void testIfThrowsTheExceptionInGetAllTeachersActive() throws PersonException, PhoneException, CPFException, DateException, AddressException, RGException, TeacherException {
	
		TeacherException teacherException = new TeacherException("Nome vazio");
		
		when(teacherDAOMock.get()).thenThrow(teacherException);
		teacherController.setTeacherDAO(teacherDAOMock);
		
		ArrayList<Teacher> foundTeachers = teacherController.getTeachers();
	}

	@Test
	public void testIfGetTeacherWithTheEnteredName() throws PersonException, PhoneException, CPFException, DateException, AddressException, RGException, TeacherException {
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		Teacher teacher = new Teacher("Jacó Mário Souza", cpf);
		teachers.add(teacher);
		
		when(teacherDAOMock.get("Jaco")).thenReturn(teachers);
		teacherController.setTeacherDAO(teacherDAOMock);
		
		ArrayList<Teacher> foundTeachers = new ArrayList<Teacher>();
		foundTeachers = teacherController.getTeachers("Jaco");
				
		assertEquals(teachers, foundTeachers);	
	}
	
	@Test (expected = TeacherException.class)
	public void testIfGetTeacherWithTheEnteredNameEmpty() throws PersonException, PhoneException, CPFException, DateException, AddressException, RGException, TeacherException {
		
		TeacherException teacherException = new TeacherException("Nome vazio");
		
		when(teacherDAOMock.get("")).thenThrow(teacherException);
		teacherController.setTeacherDAO(teacherDAOMock);
		
		ArrayList<Teacher> foundTeachers = teacherController.getTeachers("");
				
	}
	
	@Test
	public void testIfGetTeacherSelectedByUser() throws PersonException, PhoneException, CPFException, DateException, AddressException, RGException, TeacherException {
	
		Teacher receivedTeacher = new Teacher("Jacó Mário Souza", cpf);
		
		when(teacherDAOMock.get(cpf)).thenReturn(receivedTeacher);
		teacherController.setTeacherDAO(teacherDAOMock);
		
		receivedTeacher = teacherController.getTeacher(cpf);
				
		assertEquals(teacher.getName(), receivedTeacher.getName());	
	}
	
	@Test
	public void testIfDisableATeacher(){
		
		
		doNothing().when(teacherDAOMock);
		teacherController.setTeacherDAO(teacherDAOMock);
		
		try{
			teacherController.disableTeacher(teacher);
		}
		catch (TeacherException e){
			fail("Should not trow this exception: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfActivateATeacher(){
		
		
		doNothing().when(teacherDAOMock);
		teacherController.setTeacherDAO(teacherDAOMock);
		
		try{
			teacherController.activateTeacher(teacher);
		}
		catch (TeacherException e){
			fail("Should not trow this exception: " + e.getMessage());
		}
	}
}
