package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	private static final String INVALID_GRADE = "Digite todos os números da nota";
	private static final String CLASS_CANT_BE_NULL = "Não foi possível encontrar a turma";

	private StudentClassDAO studentClassDAO;
	
	public StudentClassController(){
		studentClassDAO = new StudentClassDAO();
	}
	
	public void enrollStudentToClass(String classId, ArrayList<String> students) throws PersonException, StudentClassException{
		
		Class classToEnroll = new Class(classId);
		
		for(String cpf : students){
			
			try{
				CPF studentCpf = new CPF(cpf);
				
				Student student = new Student(studentCpf);
				
				StudentClass studentClass = new StudentClass(student, classToEnroll);
				
				studentClassDAO.enrollStudentInClass(studentClass);
			}
			catch(CPFException e){
				JOptionPane.showMessageDialog(null, "Não foi possível salvar o aluno do CPF nº" + cpf + ". Clique em OK para continuar.");
			}
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
		
		if(enrolledClass != null){
			try {
				students = studentClassDAO.get(enrolledClass);
			} 
			catch (StudentClassException | CPFException e) {
				throw new StudentClassException(COULDNT_FIND_STUDENT_OF_CLASS);
			}
			
		}
		else{
			throw new StudentClassException(CLASS_CANT_BE_NULL);
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
	public StudentClass setStudentSituation(String studentCpf, String grade, String absence, Class enrolledClass) throws StudentClassException, CPFException, PersonException{
	
		StudentClass studentClass = null;
		try {
			CPF cpf = new CPF(studentCpf);
			Student student = new Student(cpf);
			
			Integer entireGrade = convertGradeStringToInteger(grade);
			Integer absences = convertAbsenceStringToInteger(absence); 
			studentClass = new StudentClass(student, enrolledClass, absences, entireGrade);
			
			studentClassDAO.save(studentClass);
		} 
		catch (StudentClassException e) {
			throw new StudentClassException(e.getMessage());
		}
		
		return studentClass;
	}
	
	
	/**
	 * Used to convert absense in number
	 * @param gradeField - get the grade with '.'
	 * @return
	 */
	public Integer convertAbsenceStringToInteger(String absence) {

		int lastDigit = absence.length();
		
		if(Character.isSpaceChar(absence.charAt(lastDigit - 1))){
			absence = "0" + absence.charAt(0);
		}
		else{
			// Nothing to do
		}
		Integer absences = new Integer(absence);

		return absences;
	}

	/**
	 * Used to get only number of the grade
	 * @param gradeField - get the grade with '.'
	 * @return
	 * @throws StudentClassException 
	 */
	public Integer convertGradeStringToInteger(String gradeField) throws StudentClassException {

		Integer grade = null;

		try{
			int lastDigit = gradeField.length();
			
			String entirePart = gradeField.substring(0, (lastDigit - 2));
			char decimalPart = gradeField.charAt(lastDigit - 1);
			grade = new Integer(entirePart + decimalPart);
		}
		catch (NumberFormatException e){
			throw new StudentClassException(INVALID_GRADE);
		}
		
		return grade;
	}

	public void setDAO(StudentClassDAO studentClassDAO) {
		this.studentClassDAO = studentClassDAO;
	}
}
