package modelTest;

import static org.junit.Assert.*;

import java.util.Random;

import junit.framework.Assert;
import model.Course;

import org.junit.Before;
import org.junit.Test;

import exception.CourseException;

public class CourseTest {
	
	private static final int MAX_DURATION = 99;
	private static final int MIN_DURATION = 1;
	
	private static final int MAX_VALUE = 999999;
	private static final int MIN_VALUE = 1;
	
	Course course;
	
	private final Random random = new Random();

	private void newCourse(String name, String description, int duration, int value) throws CourseException{
		
		Integer courseDuration = new Integer(duration);
		Integer courseValue = new Integer(value);
		
		course = new Course(name, description, courseDuration, courseValue);
	}

/** Test of valid entries */
	@Test
	public void testAllAttributesValid(){

		try{
			
			newCourse("Instalação de som", "Curso para aprender instalar som",
						3, 150000);
			
			assertEquals("Instalação de som", course.getCourseName());
			assertEquals("Curso para aprender instalar som", course.getCourseDescription());
		}catch (CourseException e){
			
			fail("Should not throw exception.");
		}
	}
	
	/** Course duration tests */
	
	@Test
	public void testcourseDurationEqualsMinDuration(){
		
		try{
			
			newCourse("Aplicação de película", "Curso para aprender a aplicar película",
						MIN_DURATION, 150000);
			
			assertEquals(1, course.getCourseDuration().intValue());
		}catch (CourseException e){
			
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testcourseDurationEqualsMaxDuration(){
		
		try{
			
			newCourse("Aplicação de película", "Curso para aprender a aplicar película",
						MAX_DURATION, 150000);
			
			assertEquals(99, course.getCourseDuration().intValue());
		}catch (CourseException e){
			
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testcourseDurationInValidRange(){
		
		// This way the range will be (1, MAX_DURATION)
		int randomDuration = random.nextInt(MAX_DURATION - 1) + 1;
		
		try{
			
			newCourse("Aplicação de película", "Curso para aprender a aplicar película",
						randomDuration, 150000);
			
			assertEquals(randomDuration, course.getCourseDuration().intValue());
		}catch (CourseException e){
			
			fail("Should not throw exception.");
		}
	}
	/** End of course duration tests */
	
	/** Course value tests */
	
	@Test
	public void testcourseValueWithMinValue(){
				
		try{
			
			newCourse("Aplicação de película", "Curso para aprender a aplicar película",
						3, MIN_VALUE);
			
			assertEquals(MIN_VALUE, course.getCourseValue().intValue());
		}catch (CourseException e){
			
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testcourseValueWithMaxValue(){
				
		try{
			
			newCourse("Aplicação de película", "Curso para aprender a aplicar película",
						3, MAX_VALUE);
			
			assertEquals(MAX_VALUE, course.getCourseValue().intValue());
		}catch (CourseException e){
			
			fail("Should not throw exception.");
		}
	}
	
	@Test
	public void testcourseValueInValidRange(){
		
		// This way the range will be (1, MAX_VALUE)
		int randomValue = random.nextInt(MAX_VALUE - 1) + 1;
		
		try{
			
			newCourse("Aplicação de película", "Curso para aprender a aplicar película",
						3, randomValue);
			
			assertEquals(randomValue, course.getCourseValue().intValue());
		}catch (CourseException e){
			
			fail("Should not throw exception.");
		}
	}
	
	/** End of course duration tests */	
/** End of valid entries */
	
/** Tests of invalid entries */
	
	/** Tests for course name */
	
	@Test(expected = CourseException.class)
	public void testCourseNameBlank() throws CourseException{
		
		newCourse("", "Curso para aprender a aplicar película", 3, 150000);
	}
	
	@Test(expected = CourseException.class)
	public void testCourseNameNull() throws CourseException{
		
		newCourse(null, "Curso para aprender a aplicar película", 3, 150000);
	}
	
	/** End of tests for course name */
	
	/** Tests for course description */
	
	@Test(expected = CourseException.class)
	public void testCourseDescriptionBlank() throws CourseException{
		
		newCourse("Aplicação de película", "", 3, 150000);
	}
	
	@Test(expected = CourseException.class)
	public void testCourseDescriptionNull() throws CourseException{
		
		newCourse("Aplicação de película", null, 3, 150000);
	}
	
	/** End of tests for course description */
	
	/** Tests for course duration */
	
	@Test(expected = CourseException.class)
	public void testCourseDurationWith0() throws CourseException{
		
		newCourse("Aplicação de película", "Curso para aprender a aplicar película",
					0, 150000);
	}
	
	@Test(expected = CourseException.class)
	public void testCourseDurationWithNegativeNumber() throws CourseException{
		
		// This way won't be zero
		int randomInt = random.nextInt(Integer.MAX_VALUE - 1) + 1;
		
		newCourse("Aplicação de película", "Curso para aprender a aplicar película",
					MIN_DURATION - randomInt, 150000);
	}
	
	@Test(expected = CourseException.class)
	public void testCourseDurationOneGreaterThanMax() throws CourseException{
				
		newCourse("Aplicação de película", "Curso para aprender a aplicar película",
					MAX_DURATION + 1, 150000);
	}
	
	@Test(expected = CourseException.class)
	public void testCourseDurationGreaterThanMax() throws CourseException{
		
		// This way won't be zero
		int randomInt = random.nextInt(Integer.MAX_VALUE - MAX_DURATION) + 1;
		
		newCourse("Aplicação de película", "Curso para aprender a aplicar película",
					MAX_DURATION + randomInt, 150000);
	}
	
	/** End of tests for course duration */
	
/** End of tests of invalid entries */
}
