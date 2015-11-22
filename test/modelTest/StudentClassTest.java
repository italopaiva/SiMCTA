package modelTest;

import static org.junit.Assert.*;
import model.Class;
import model.Course;
import model.Student;
import model.StudentClass;
import model.Teacher;

import org.junit.Before;
import org.junit.Test;

import datatype.CPF;
import datatype.Date;
import exception.StudentClassException;

public class StudentClassTest {
	
	private StudentClass studentClass;
	private Student student;
	private Class enrolledClass;
	private CPF studentCpf;
	private Integer absences;
	private Integer grade;

	@Before
	public void setUp() throws Exception {
		
		studentCpf = new CPF("51464638403");
		student = new Student(studentCpf);
		absences = new Integer("00");
		grade = new Integer("100");
		
		Course course = new Course("Instalação", "lala", 5, 10000);

		Teacher teacher = new Teacher(new CPF("03382132109"));
		enrolledClass = new Class(new Date(11,8,2015), "VE", teacher, course);
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
	
	@Test
	public void testValidAttributesOfSituation(){
		
		try{
			studentClass = new StudentClass(student, enrolledClass, absences, grade);
			assertEquals(absences, studentClass.getAbsences());
		}
		catch(StudentClassException e){
			fail("Should not throw this exception: " + e.getMessage());
		}
		
	}
	
	@Test
	public void testIfSetsApprovedSituation(){
		
		try{
			studentClass = new StudentClass(student, enrolledClass, absences, grade);
			assertEquals("APROVADO", studentClass.getStudentSituation());
		}
		catch(StudentClassException e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfSetsDisapprovedSituationWithLessThanMinimumGrade(){
		
		try{
			grade = new Integer("040");
			studentClass = new StudentClass(student, enrolledClass, absences, grade);
			assertEquals("REPROVADO", studentClass.getStudentSituation());
		}
		catch(StudentClassException e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}
	
	@Test
	public void testIfSetsDisapprovedSituationWithLessThanPermittedPercentAbsences(){
		
		try{
			// For the test the duration of the class is 25 days
			absences = new Integer("23");
			studentClass = new StudentClass(student, enrolledClass, absences, grade);
			assertEquals("REPROVADO", studentClass.getStudentSituation());
		}
		catch(StudentClassException e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}
	
	@Test (expected = StudentClassException.class)
	public void testNullAbsenceOnStudentSituation() throws StudentClassException{

			absences = null;
			studentClass = new StudentClass(student, enrolledClass, absences, grade);
		
	}
	
	@Test (expected = StudentClassException.class)
	public void testNullGradeOnStudentSituation() throws StudentClassException{

			grade = null;
			studentClass = new StudentClass(student, enrolledClass, absences, grade);
		
	}
	
	@Test (expected = StudentClassException.class)
	public void testInvalidAbsenceOnStudentSituation() throws StudentClassException{

			// Greater than course duration
			absences = new Integer("26"); 
			studentClass = new StudentClass(student, enrolledClass, absences, grade);
		
	}
	
	@Test (expected = StudentClassException.class)
	public void testInvalidGradeOnStudentSituation() throws StudentClassException{

			// Greater than maximum
			grade = new Integer("110"); 
			studentClass = new StudentClass(student, enrolledClass, absences, grade);
		
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
