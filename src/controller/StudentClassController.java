package controller;

import java.util.ArrayList;

import dao.StudentClassDAO;
import exception.CPFException;
import exception.PersonException;
import exception.StudentClassException;
import model.Class;
import model.Student;
import model.StudentClass;
import model.datatype.CPF;

public class StudentClassController{

	private static final String COULDNT_FIND_STUDENT_OF_CLASS = "Não foi possível encontrar os estudantes dessa turma.";

	private StudentClassDAO studentClassDAO;
	
	public StudentClassController(){
		studentClassDAO = new StudentClassDAO();
	}
	
	public void enrollStudentToClass(String classId, ArrayList<CPF> students) throws PersonException, StudentClassException{
		
		Class classToEnroll = new Class(classId);
		
		for(CPF studentCpf : students){
			
			Student student = new Student(studentCpf);
			
			StudentClass studentClass = new StudentClass(student, classToEnroll);
			
			studentClassDAO.enrollStudentInClass(studentClass);
		}
	}
	
	public ArrayList<Student> getStudents(Class enrolledClass) throws StudentClassException{
		
		ArrayList<Student> students = new ArrayList<Student>();
		
		try {
			students = studentClassDAO.get(enrolledClass);
		} 
		catch (StudentClassException | CPFException e) {
			throw new StudentClassException(COULDNT_FIND_STUDENT_OF_CLASS);
		}
		
		return students;
	}
}
