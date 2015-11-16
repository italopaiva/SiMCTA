package controllerTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import model.Course;
import model.Teacher;
import model.Class;
import model.datatype.CPF;
import model.datatype.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controller.ClassController;
import dao.ClassDAO;
import dao.CourseDAO;
import exception.CPFException;
import exception.ClassException;
import exception.CourseException;
import exception.DateException;
import exception.PersonException;

public class ClassControllerTest {

	private ClassController classController;
	@Mock
	private ClassDAO classDAOMock;
	
	@Before
	public void setUp(){
		
		MockitoAnnotations.initMocks(this);
		classDAOMock = mock(ClassDAO.class);
		
		classController = new ClassController();

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
}
