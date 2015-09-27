package controllerTest;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.binding.When;
import model.Course;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.*;

import org.mockito.MockitoAnnotations;

import com.mysql.jdbc.Connection;

import controller.CourseController;
import dao.CourseDAO;
import dao.FactoryConnection;
import exception.CourseException;

public class CourseControllerTest {
	
	private static final int ARBITRARY_ID = 41234;
		
	private CourseController courseController;
	private CourseController courseController2;
	
	@Mock
	private CourseDAO courseDAOMock;
	
	private Course course;
	private ResultSet resultOfTheMethod;
	private Connection connection;
	
	@Before
	public void setUp() throws CourseException{
		
		MockitoAnnotations.initMocks(this);
		
		courseDAOMock = mock(CourseDAO.class);
		
		courseController2 = new CourseController();
		
		
		// Register courses on database to test the search of a course
		//courseController.newCourse("Instalação de Som", "Curso bom", 3, 500000);
		//courseController.newCourse(ARBITRARY_ID, "Aplicação de película", "Curso bom", 3, 500000);

	}

	/*@Test
	public void testNewCourseMethodWithValidCourse() throws CourseException{
		
		boolean wasSaved = courseController.newCourse("Aplicação de película", "Curso bom", 3, 500000);
		
		assertTrue("Should create the given course", wasSaved);
	}
	
	@Test(expected = CourseException.class)
	public void testNewCourseMethodWithInvalidCourse() throws CourseException{
				
		boolean wasSaved = courseController.newCourse(null, null, -3, 1000000);
		
		assertFalse("Should not create the given course", wasSaved);
	}
	
	@Test
	public void testUpdateCourseMethodWithValidCourse(){
		
		try{
			
			courseController.updateCourse(ARBITRARY_ID, "Aplicação de película", "Curso complicado", 5, 25000);
		}catch(CourseException caughtException){
			
			fail("Should not throw exception");
		}
	}
	
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
	
	@Test
	public void testIfUpdateCourseDontUpdateTheCourseName() throws CourseException{
		
		String absurdName = "Instalação aérea de rodas cromadas";
		
		courseController.updateCourse(ARBITRARY_ID, absurdName, "Curso complicado", 3, 25000);
		
		resultOfTheMethod = courseController.showCourse(absurdName);
		
		boolean hasValues;
		try{
			
			hasValues = resultOfTheMethod.next();
			assertFalse(hasValues);
		}catch(SQLException e){
			
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchACourseWithAEnteredName() throws SQLException{
		
		String enteredName = "Aplicação de película";
		resultOfTheMethod = null;
		
		try{
			resultOfTheMethod = courseController.showCourse(enteredName);
			String courseFound = "";
			
			resultOfTheMethod.next();
			courseFound = resultOfTheMethod.getString("course_name");
			assertEquals(enteredName, courseFound);

		}catch (CourseException e){
			e.printStackTrace();
		}
		
	}  
	
	@Test(expected = Exception.class)
	public void testSearchACourseWithAEnteredNameNull() throws SQLException, CourseException{
		
		String enteredName = null;
		
		resultOfTheMethod = courseController.showCourse(enteredName);
		String courseFound = "";
		
		resultOfTheMethod.next();
		courseFound = resultOfTheMethod.getString("course_name");
	}  
	
	@Test
	public void testShowAllCoursesRegistered() throws SQLException, CourseException{
		
		String courseFound;
		String coursesExpected;
		ResultSet resultOfTheDatabase;
		
		connection = this.connectToDBForTest(); 
		PreparedStatement preparedStatement = connection.prepareStatement
				("SELECT * FROM Course"); 
		resultOfTheDatabase = preparedStatement.executeQuery();

		resultOfTheMethod = courseController.showCourse();
				
		while(resultOfTheMethod.next() && resultOfTheDatabase.next()){
			courseFound = resultOfTheMethod.getString("course_name");
			coursesExpected = resultOfTheDatabase.getString("course_name");
			assertEquals(coursesExpected, courseFound);
		}		
		preparedStatement.close();
		connection.close();
	}
	
	
	private Connection connectToDBForTest() throws SQLException{
		FactoryConnection factoryConnection = new FactoryConnection();
		Connection connection = (Connection) factoryConnection.establishConnection();
		
		return connection;
	}
	
	@Test
	public void testAlterStatusCourseActiveToDeactived() throws CourseException{
		
		boolean wasAltered = false;
		
		when(courseDAOMock.returnStatusCourse(205)).thenReturn(1);
		when(courseDAOMock.alterCourseStatus(205, 0)).thenReturn(true);
		courseController2.setCourseDAO(courseDAOMock);
		
		
		wasAltered = courseController2.alterStatusCourse(205);
		
		assertTrue(wasAltered);
	}*/
	
	@Test
	public void testAlterStatusCourseDeactivedToActive() throws CourseException{
		
		boolean wasAltered = false;
		
		when(courseDAOMock.returnStatusCourse(205)).thenReturn(0);
		when(courseDAOMock.alterCourseStatus(205, 1)).thenReturn(true);
		courseController2.setCourseDAO(courseDAOMock);
		
		
		wasAltered = courseController2.alterStatusCourse(205);
		
		assertTrue(wasAltered);
	}
}
