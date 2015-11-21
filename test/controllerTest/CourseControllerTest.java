package controllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Course;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controller.CourseController;
import dao.CourseDAO;
import exception.CourseException;

public class CourseControllerTest {
	
	private static final int ARBITRARY_ID = 41234;
		
	private CourseController courseController;
	
	@Mock
	private CourseDAO courseDAOMock;
	
	private ArrayList<Course> courses;
	private Course course;
	
	@Before
	public void setUp() throws CourseException{
		
		MockitoAnnotations.initMocks(this);
		courseDAOMock = mock(CourseDAO.class);
		
		courseController = new CourseController();
		courses = new ArrayList<Course>();
		course = new Course();
		
	}

	/*
	@Test
	public void testNewCourseMethodWithValidCourse() throws CourseException{
		
		Course course = new Course("Aplicação de película", "Curso bom", 3, 500000); 	
		courseController.setCourseDAO(courseDAOMock);		
		when(courseDAOMock.save(course, false)).thenReturn(true);
		
		boolean wasSaved = false;
		wasSaved = courseController.newCourse("Aplicação de película", "Curso bom", 3, 500000);
		
		assertTrue("Should create the given course", wasSaved);
	}
	
	@Test(expected = CourseException.class)
	public void testNewCourseMethodWithInvalidCourse() throws CourseException{
				
		Course course = new Course(null, null, -3, 1000000); 		
		when(courseDAOMock.save(course, false)).thenReturn(true);
		courseController.setCourseDAO(courseDAOMock);	
		
		boolean wasSaved = courseController.newCourse(null, null, -3, 1000000);
		
		assertFalse("Should not create the given course", wasSaved);
	}
	
	@Test
	public void testUpdateCourseMethodWithValidCourse() throws CourseException{
		
				
		Course course = new Course(ARBITRARY_ID, "Aplicação de película", "Curso complicado", 5, 25000); 		
		when(courseDAOMock.update(ARBITRARY_ID, course)).thenReturn(true);
		courseController.setCourseDAO(courseDAOMock);
		
		boolean wasUpdate = courseController.updateCourse(ARBITRARY_ID, "Aplicação de película", "Curso complicado", 5, 25000);
	
		assertTrue(wasUpdate);
	}
	*/
	@Test(expected = CourseException.class)
	public void testUpdateCourseMethodWithInvalidName() throws CourseException{
		
		courseController.updateCourse(ARBITRARY_ID, "", "Curso complicado", 5, 25000);
	}
	
	@Test(expected = CourseException.class)
	public void testUpdateCourseMethodWithInvalidDescription() throws CourseException{
		
		courseController.updateCourse(ARBITRARY_ID, "Aplicação de película", "", 5, 25000);
	}
	
	@Test(expected = CourseException.class)
	public void testUpdateCourseMethodWithInvalidDuration() throws CourseException{
		
		courseController.updateCourse(ARBITRARY_ID, "Aplicação de película", "Curso complicado", 0, 25000);
	}
	
	@Test(expected = CourseException.class)
	public void testUpdateCourseMethodWithInvalidValue() throws CourseException{
		
		courseController.updateCourse(ARBITRARY_ID, "Aplicação de película", "Curso complicado", 3, 0);
	}
	
	/*
	@Test
	public void testIfUpdateCourseDontUpdateTheCourseName() throws CourseException{
		
		Course searchedCourse = new Course(ARBITRARY_ID);
		course = new Course(ARBITRARY_ID, "Som", "Legal", 5, 600000);

		when(courseDAOMock.get(searchedCourse, true)).thenReturn(course);
		courseController.setCourseDAO(courseDAOMock);
		
		String absurdName = "Instalação aérea de rodas cromadas";
		
		when(courseDAOMock.update(ARBITRARY_ID, course)).thenReturn(true);
		boolean wasUpdate = courseController.updateCourse(ARBITRARY_ID, absurdName, "Curso complicado", 3, 25000);
		
		//Course updateCourse = courseController.showCourse(ARBITRARY_ID);
		
		assertTrue("Should update", wasUpdate);
	}
	*/
	@Test
	public void testSearchACourseWithAEnteredName() throws SQLException, CourseException{
		
		String enteredName = "Aplicação de película";
		Course course2 = new Course(enteredName);
	
		when(courseDAOMock.get(course2)).thenReturn(courses);
		courseController.setCourseDAO(courseDAOMock);
		
		ArrayList<Course> foundCourses = courseController.showCourse(enteredName);
		course = new Course(ARBITRARY_ID, enteredName, "Legal",5, 500000);
		courses.add(course);
		
		assertEquals(courses, foundCourses);
		
	}  
	
	@Test
	public void testSearchACourseWithTheId() throws SQLException, CourseException{
		
		Course course2 = new Course(ARBITRARY_ID);
	
		when(courseDAOMock.get(course2, true)).thenReturn(course);
		courseController.setCourseDAO(courseDAOMock);
		
		Course foundCourse = courseController.showCourse(ARBITRARY_ID);
		course = new Course(ARBITRARY_ID, "Som", "Legal",5, 500000);
		
		assertEquals(course, foundCourse);
		
	}  
	
	@Test(expected = Exception.class)
	public void testSearchACourseWithAEnteredNameNull() throws SQLException, CourseException{
		
		String enteredName = null;
		Course course2 = new Course(enteredName);
		
		when(courseDAOMock.get(course2)).thenReturn(courses);
		courseController.setCourseDAO(courseDAOMock);
		
		courses = courseController.showCourse(enteredName);
	
	}  

	@Test
	public void testShowAllCoursesRegistered() throws SQLException, CourseException{

		ArrayList<Course> foundCourses = new ArrayList<Course>();
		
		when(courseDAOMock.get()).thenReturn(courses);
		courseController.setCourseDAO(courseDAOMock);
		
		course = new Course(ARBITRARY_ID, "Som", "Legal", 5, 600000);
		courses.add(course);
		
		foundCourses = courseController.showCourse();

		assertEquals(courses, foundCourses);
		
	}
	

	@Test
	public void testAlterStatusCourseActiveToDeactived() throws CourseException{
		
		boolean wasAltered = false;
		
		when(courseDAOMock.returnStatusCourse(205)).thenReturn(1);
		when(courseDAOMock.alterCourseStatus(205, 0)).thenReturn(true);
		courseController.setCourseDAO(courseDAOMock);
		
		
		wasAltered = courseController.alterStatusCourse(205);
		
		assertTrue("Should alter status the given course", wasAltered);
	}
	
	@Test
	public void testAlterStatusCourseDeactivedToActive() throws CourseException{
		
		boolean wasAltered = false;
		
		when(courseDAOMock.returnStatusCourse(205)).thenReturn(0);
		when(courseDAOMock.alterCourseStatus(205, 1)).thenReturn(true);
		courseController.setCourseDAO(courseDAOMock);
		
		
		wasAltered = courseController.alterStatusCourse(205);
		
		assertTrue("Should alter status the given course", wasAltered);
	}
	
}
