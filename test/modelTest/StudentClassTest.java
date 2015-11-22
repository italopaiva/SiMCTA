package modelTest;

import static org.junit.Assert.*;
import model.Class;
import model.Student;
import model.StudentClass;
import model.datatype.CPF;

import org.junit.Before;
import org.junit.Test;

import exception.StudentClassException;

public class StudentClassTest {
	
	private StudentClass studentClass;
	private Student student;
	private Class enrolledClass;
	private CPF studentCpf;
	
	@Before
	public void setUp() throws Exception {
		
		studentCpf = new CPF("51464638403");
		student = new Student(studentCpf);
		
		enrolledClass = new Class("INSTALAÇÃO-MA 11/08/15");
	}

	@Test
	public void testValidStudent(){
		
		try{
			studentClass = new StudentClass(student, enrolledClass);
			assertEquals(studentCpf, studentClass.getStudent().getCpf());
		}
		catch(StudentClassException e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}
	
	@Test
	public void testValidClass(){
		
		try{
			studentClass = new StudentClass(student, enrolledClass);
			assertEquals(enrolledClass, studentClass.getEnrolledClass());
		}
		catch(StudentClassException e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}
	
	@Test(expected = StudentClassException.class)
	public void testInvalidNullStudent() throws StudentClassException{
		
		studentClass = new StudentClass(null, enrolledClass);
	}
	
	@Test(expected = StudentClassException.class)
	public void testInvalidNullClass() throws StudentClassException{
		
		studentClass = new StudentClass(student, null);
	}
	
	@Test(expected = StudentClassException.class)
	public void testInvalidNullStudentAndClass() throws StudentClassException{
		
		studentClass = new StudentClass(null, null);
	}
}
