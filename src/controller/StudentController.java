package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PackageDAO;
import dao.StudentDAO;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PhoneException;
import exception.RGException;
import exception.StudentException;
import model.Student;
import model.datatype.Address;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;
import model.datatype.CPF;

public class StudentController {
	
	private StudentDAO studentDAO;
	
	public StudentController(){
		studentDAO = new StudentDAO();
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	public void newStudent(String studentName, CPF studentCpf, RG studentRg, Date birthdate, String email, Address address,
			 			   Phone principalPhone, Phone secondaryPhone, String motherName, String fatherName,
			 			   ArrayList<String> courses, ArrayList<String> packages, int paymentType, int paymentForm) throws StudentException{
		
		Student student = new Student(studentName, studentCpf, studentRg, birthdate, email, address, principalPhone, secondaryPhone, motherName, fatherName);
		
		ServiceController serviceController = new ServiceController();
		serviceController.newService(student, courses, packages, paymentType, paymentForm);
	}

	public ArrayList<Student> searchStudent(String studentName) throws StudentException, CPFException {
			
		ArrayList <Student> foundStudents = studentDAO.get(studentName);
		
		return foundStudents;
	}

	public Student searchStudent(CPF studentCPF) throws SQLException, StudentException, PhoneException, CPFException, DateException, AddressException, RGException {
				
		Student student = studentDAO.get(studentCPF);
		
		return student;
	}
}