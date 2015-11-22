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
}
