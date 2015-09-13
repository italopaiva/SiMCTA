package controllerTest;

import static org.junit.Assert.*;
import model.Course;

import org.junit.Before;
import org.junit.Test;

import controller.CourseController;
import exception.CourseException;

public class CourseControllerTest {

	private CourseController courseController;
	
	@Before
	public void setUp(){
		
		courseController = new CourseController();
	}

	@Test
	public void testNewCourseMethodWithValidCourse() throws CourseException{
		
		boolean wasSaved = courseController.newCourse("Aplicação de película", "Curso bom", 3, 500000);
		
		assertTrue("Should create the given course", wasSaved);
	}
	
	@Test(expected = CourseException.class)
	public void testNewCourseMethodWithInvalidCourse() throws CourseException{
				
		boolean wasSaved = courseController.newCourse(null, null, -3, 1000000);
		
		assertFalse("Should not create the given course", wasSaved);
	}
}
