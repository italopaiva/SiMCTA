package modelTest;

import static org.junit.Assert.*;
import model.Course;

import org.junit.Before;
import org.junit.Test;

import exception.CourseException;

public class CourseTest {

	Course course;

	private void newCourse(String name, String description, int duration, int value) throws CourseException{
		
		Integer courseDuration = new Integer(duration);
		Integer courseValue = new Integer(value);
		
		course = new Course(name, description, courseDuration, courseValue);
	}
	
	@Test
	public void testAllAttributesValid(){

		try{
			
			newCourse("Instalação de som", "Curso para aprender instalar som", 3, 150000);
		}catch (CourseException e){
			
			fail("Should not throw exception.");
		}
	}
	
	

}
