package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public void newStudent(String studentName, CPF studentCpf, RG studentRg, Date birthdate, String email, Address address,
			 			   Phone principalPhone, Phone secondaryPhone, String motherName, String fatherName,
			 			   ArrayList<String> courses, ArrayList<String> packages) throws StudentException{
		
		Student student = new Student(studentName, studentCpf, studentRg, birthdate, email, address, principalPhone, secondaryPhone, motherName, fatherName);
		
		ServiceController serviceController = new ServiceController();
		serviceController.newService(student, courses, packages);
	}

	public ArrayList<Student> searchStudent(String studentName) throws StudentException, CPFException {
		
		StudentDAO studentDAO = new StudentDAO();
		
		ArrayList <Student> foundStudents = studentDAO.get(studentName);
		
		return foundStudents;
	}

	public Student searchStudent(CPF studentCPF) throws SQLException, StudentException, PhoneException, CPFException, DateException, AddressException, RGException {
		
		StudentDAO studentDAO = new StudentDAO();
		
		Student student = studentDAO.get(studentCPF);
		
		return student;
	}
}