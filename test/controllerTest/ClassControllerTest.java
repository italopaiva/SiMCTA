package controllerTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import model.Course;
import model.Teacher;
import model.Class;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controller.ClassController;
import dao.ClassDAO;
import dao.CourseDAO;
import datatype.CPF;
import datatype.Date;
import exception.CPFException;
import exception.ClassException;
import exception.CourseException;
import exception.DateException;
import exception.PersonException;

public class ClassControllerTest {

	private ClassController classController;
	@Mock
	private ClassDAO classDAOMock;
	private Class enrolledClass;
	
	@Before
	public void setUp() throws CourseException, PersonException, CPFException, ClassException, DateException{
		
		MockitoAnnotations.initMocks(this);
		classDAOMock = mock(ClassDAO.class);
		
		classController = new ClassController();
		
		Course course = new Course("Instalação", "lala", 5, 10000);

		Teacher teacher = new Teacher(new CPF("03382132109"));
		enrolledClass = new Class(new Date(11,8,2015), "VE", teacher, course);

	}
	
	// Test assuming the class "APLICAÇÃO - MA 10/2" and the teacher "85988316972" exists 
	@Test
	public void testIfUpdatesAClass() throws DateException, CPFException{
		
		CPF teacherCpf = new CPF("85988316972");
		Date startDate = new Date(7, 8, 2016);
		
		try {
			classController.updateClass("APLICAÇÃO - MA 10/2", teacherCpf , "VE", startDate);
		}
		catch(ClassException e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}
	
	/*
	@Test
	public void testIfSaveAClass() throws CPFException, DateException, PersonException, CourseException, ClassException{
		
		CPF teacherCpf = new CPF("85988316972");
		Date startDate = new Date(7, 8, 2016);
		Integer courseId = 41455;
		
		Teacher teacher = new Teacher(teacherCpf);
		Course course = new Course(courseId);
		
		Class classToSave = new Class(startDate, "VE", teacher, course);
		
		try {
			Class receivedClass = classController.newClass(teacherCpf, "VE", startDate, courseId);
		}
		catch(ClassException e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}*/

	@Test (expected = ClassException.class)
	public void testIfCloseANullClass() throws ClassException{

		enrolledClass = null;
		classController.closeClass(enrolledClass);

	}
}
