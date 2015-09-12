package controllerTest;

import static org.junit.Assert.*;
import model.Course;

import org.junit.Before;
import org.junit.Test;

import controller.CourseController;
import exception.CourseException;

public class CourseControllerTest {

	private CourseController courseController;
	private Course course;
	
	@Before
	public void setUp(){
		
		courseController = new CourseController();
	}

	@Test
	public void testNewCourseMethodWithValidCourse() throws CourseException{
		
		course = new Course("Aplicação de película", "Curso bom", 3, 500000);
		
		boolean wasSaved = courseController.newCourse(course);
		
		assertTrue("Should create the given course", wasSaved);
	}
	
	@Test
	public void testNewCourseMethodWithInvalidCourse() throws CourseException{
				
		boolean wasSaved = courseController.newCourse(null);
		
		assertFalse("Should not create the given course", wasSaved);
	}
}
