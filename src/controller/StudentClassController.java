package controller;

import java.util.ArrayList;

import dao.StudentClassDAO;
import exception.PersonException;
import exception.StudentClassException;
import model.Class;
import model.Student;
import model.StudentClass;
import model.datatype.CPF;

public class StudentClassController{

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
}
