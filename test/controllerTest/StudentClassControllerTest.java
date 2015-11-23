package controllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import model.Class;
import model.Course;
import model.Student;
import model.StudentClass;
import model.Teacher;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import controller.StudentClassController;
import dao.StudentClassDAO;
import datatype.CPF;
import datatype.Date;
import exception.CPFException;
import exception.PersonException;
import exception.StudentClassException;
import exception.StudentException;

public class StudentClassControllerTest {
	
	private StudentClassController studentClassController; 
	private String classId;
	private ArrayList<String> students = new ArrayList<String>();
	private StudentClassDAO studentClassDAOMock;
	private Integer absences;
	private Integer grade;
	private Class enrolledClass;

	
	@Before
	public void setUp() throws Exception {
		
		studentClassController = new StudentClassController();

		MockitoAnnotations.initMocks(this);
		studentClassDAOMock = mock(StudentClassDAO.class);

		classId = "APLICAÇÃO - MA 10/2";
		students.add("76658496285");
		students.add("82835356509");
		
		absences = new Integer("00");
		grade = new Integer("100");
		
		Course course = new Course("Instalação", "lala", 5, 10000);

		Teacher teacher = new Teacher(new CPF("03382132109"));
		enrolledClass = new Class(new Date(11,8,2015), "VE", teacher, course);
	}

	@Test
	public void testIfGetAllStudents() throws PersonException, CPFException{
		
		model.Class enrolledClass = new model.Class(classId);
		ArrayList<Student> students = new ArrayList<Student>();
		
		Student student = new Student(new CPF(this.students.get(0)));
		students.add(student);
		
		student = new Student(new CPF(this.students.get(1)));
		students.add(student);
		
		ArrayList<Student> receivedStudents = new ArrayList<Student>();

		try {
			when(studentClassDAOMock.get(enrolledClass)).thenReturn(students);
			studentClassController.setDAO(studentClassDAOMock);		

			receivedStudents = studentClassController.getStudents(enrolledClass);
			
			assertEquals(students, receivedStudents);
		} 
		catch (StudentClassException | CPFException e) {
			fail("Should not throw this exception: "+ e.getMessage());
		} 
	}
	

	@Test
	public void testIfGetStudentsSituation() throws PersonException, CPFException{
		
		ArrayList<StudentClass> studentsClass = new ArrayList<StudentClass>();
		
		Student student = new Student(new CPF(this.students.get(0)));
		StudentClass studentClass;

		ArrayList<StudentClass> receivedStudents = new ArrayList<StudentClass>();

		try {
			studentClass = new StudentClass(student, enrolledClass, absences, grade, "APROVADO");
			studentsClass.add(studentClass);

			when(studentClassDAOMock.get(enrolledClass, true)).thenReturn(studentsClass);
			studentClassController.setDAO(studentClassDAOMock);		

			receivedStudents = studentClassController.getStudentSituation(enrolledClass);
			
			assertEquals(studentsClass, receivedStudents);
		} 
		catch (StudentClassException | CPFException e) {
			fail("Should not throw this exception: "+ e.getMessage());
		} 
	}
	
	
	@Test (expected = StudentClassException.class)
	public void testIfGetAllStudentsWithClassNull() throws PersonException, StudentClassException, CPFException{
		
		model.Class enrolledClass = null;
		ArrayList<Student> students = new ArrayList<Student>();
		
		ArrayList<Student> receivedStudents = new ArrayList<Student>();
		
		when(studentClassDAOMock.get(enrolledClass)).thenReturn(students);
		studentClassController.setDAO(studentClassDAOMock);		

		receivedStudents = studentClassController.getStudents(enrolledClass);

	}
	
	@Test (expected = StudentClassException.class)
	public void testIfGetAllStudentsIfDoesntFindTheStudents() throws PersonException, CPFException, StudentClassException{
				
		ArrayList<Student> receivedStudents = new ArrayList<Student>();
		
		when(studentClassDAOMock.get(enrolledClass)).thenThrow(StudentClassException.class);
		studentClassController.setDAO(studentClassDAOMock);		

		receivedStudents = studentClassController.getStudents(enrolledClass);
		 
	} 
	
	@Test (expected = StudentClassException.class)
	public void testIfGetStudentsSituationIfDoesntFindTheStudents() throws PersonException, CPFException, StudentClassException{
	
		ArrayList<StudentClass> receivedStudents = new ArrayList<StudentClass>();

		when(studentClassDAOMock.get(enrolledClass, true)).thenThrow(StudentClassException.class);
		studentClassController.setDAO(studentClassDAOMock);		

		receivedStudents = studentClassController.getStudentSituation(enrolledClass);
		 
	}
	
	@Test (expected = StudentClassException.class)
	public void testIfGetStudentsSituationWithClassNull() throws StudentClassException{
		
		ArrayList<StudentClass> receivedStudents = new ArrayList<StudentClass>();

		enrolledClass = null;
		receivedStudents = studentClassController.getStudentSituation(enrolledClass);
		 
	}
	
	@Test
	public void testIfConvertsAbsenceStringToInteger(){
		
		String absence = "08";
		Integer absences = studentClassController.convertAbsenceStringToInteger(absence);
		
		assertEquals(new Integer(8), absences);
		
	}
	
	
	@Test
	public void testIfConvertsAbsenceWithOneToInteger(){
		
		String absence = "8 ";
		Integer absences = studentClassController.convertAbsenceStringToInteger(absence);
		
		assertEquals(new Integer(8), absences);
		
	}
	
	@Test
	public void testIfConvertsGradeWithOneDigitToInteger() throws StudentClassException{
		
		String grade = "01.0";
		Integer integerGrade = studentClassController.convertGradeStringToInteger(grade);
	
		assertEquals(new Integer(10), integerGrade);
	}

	@Test
	public void testIfConvertsGradeWithTwoDigitsToInteger() throws StudentClassException{
		
		String grade = "10.0";
		Integer integerGrade = studentClassController.convertGradeStringToInteger(grade);
		
		assertEquals(new Integer(100), integerGrade);
		
	}
	@Test (expected = StudentClassException.class)
	public void testIfConvertsInvalidGradeToInteger() throws StudentClassException{
		
		String grade = "1.";
		Integer integerGrade = studentClassController.convertGradeStringToInteger(grade);
	}

}
