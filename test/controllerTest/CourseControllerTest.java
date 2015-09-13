package controllerTest;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Course;

import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.Connection;

import controller.CourseController;
import dao.CourseDAO;
import dao.FactoryConnection;
import exception.CourseException;

public class CourseControllerTest {

	private CourseController courseController;
	private Course course;
	private ResultSet resultOfTheMethod;
	private Connection connection;

	@Before
	public void setUp() throws CourseException{
		
		courseController = new CourseController();
		
		// Register to test the methods of show course
		courseController.newCourse("Instalação de Som", "Curso bom", 3, 500000);
		courseController.newCourse("Aplicação de película", "Curso bom", 3, 500000);

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
	private void testAlterStatusCourse() {
		Course courseExpected = new Course(1, "Instalação de som", "Curso que ensina a instalar som", 22, 120000,0);
		Course course = new Course(1, "Instalação de som", "Curso que ensina a instalar som", 22, 120000,1);
		
		courseController.alterStatusCourse(1);
		assertEquals(courseExpected, course);
	}
}
