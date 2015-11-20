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
	private static final String COULDNT_SAVE_THE_SITUATION = "Não foi possível salvar as notas e faltas dos alunos.";

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
	
	/**
	 * Get all students enrolled in the class
	 * @param enrolledClass - class to get students
	 * @return an array with all students enrolled in the class
	 * @throws StudentClassException
	 */
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

	/**
	 * Save the student situation
	 * @param studentCpf - the 'cpf' of the student to save the situation
	 * @param grade - the grade of the student
	 * @param absence - the absences of the student
	 * @param enrolledClass - the class of the student
	 * @throws CPFException
	 * @throws PersonException
	 * @throws StudentClassException
	 */
	public void setStudentSituation(String studentCpf, Integer grade, Integer absence, Class enrolledClass) throws StudentClassException, CPFException, PersonException{
	
		try {
			CPF cpf = new CPF(studentCpf);
			Student student = new Student(cpf);
			StudentClass studentClass = new StudentClass(student, enrolledClass, absence, grade);
			
			studentClassDAO.save(studentClass);
		} 
		catch (StudentClassException e) {
			throw new StudentClassException(COULDNT_SAVE_THE_SITUATION);
		}
		
	}
}
