package controller;

import java.util.ArrayList;

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
			 			   ArrayList<String> courses, ArrayList<String> packages, int paymentType, int paymentForm) throws StudentException{
		
		Student student = new Student(studentName, studentCpf, studentRg, birthdate, email, address, principalPhone, secondaryPhone, motherName, fatherName);
		
		ServiceController serviceController = new ServiceController();
		serviceController.newService(student, courses, packages, paymentType, paymentForm);
	}

}