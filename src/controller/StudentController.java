package controller;

import dao.StudentDAO;
import model.Student;

public class StudentController {
	
	public StudentController(){}
	
	public String newStudent(String studentName){
		
		Student student = new Student(studentName);
		
		StudentDAO studentDao = new StudentDAO();
		
		boolean wasSaved = studentDao.saveStudent(student);
		
		String status = "";
		if(wasSaved){
			status = "Aluno cadastrado com sucesso.";
		}else{
			status = "Não foi possível cadastrar o aluno informado.";
		}
		
		return status;
	}
}
