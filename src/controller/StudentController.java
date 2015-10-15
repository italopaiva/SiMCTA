package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PackageDAO;
import dao.StudentDAO;
import exception.AddressException;
import exception.CPFException;
import exception.CourseException;
import exception.DateException;
import exception.PaymentException;
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.StudentException;
import model.Student;
import model.Service;
import model.datatype.Address;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;
import model.datatype.CPF;

public class StudentController {
	
	private static final String STUDENT_WITHOUT_SERVICE = "Um aluno deve possuir um serviço associado";
	private static final int ACTIVE_STATUS	= 1;
	private StudentDAO studentDAO;
	
	public StudentController(){
		studentDAO = new StudentDAO();
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	public boolean newStudent(String studentName, CPF studentCpf, RG studentRg, Date birthdate, String email, Address address,
			 			   Phone principalPhone, Phone secondaryPhone, String motherName, String fatherName,
			 			   ArrayList<String> courses, ArrayList<String> packages, int paymentType, int paymentForm, Integer installments) throws StudentException{
		
		Student student = new Student(studentName, studentCpf, studentRg, birthdate, email, address, principalPhone, secondaryPhone, motherName, fatherName, ACTIVE_STATUS);
		boolean studentWasSaved = studentDAO.save(student);
		
		boolean allSaved = false;
		if(studentWasSaved){
			ServiceController serviceController = new ServiceController();
			allSaved = serviceController.newService(student, courses, packages, paymentType, paymentForm, installments);
		}
		else{
			allSaved = false;
		}
		
		return allSaved;
	}

	/**
	 * Search the student with the entered name
	 * @param studentName - the entered name by user
	 * @return an arraylist with the found students
	 * @throws StudentException
	 * @throws CPFException
	 */
	public ArrayList<Student> searchStudent(String studentName) throws StudentException, CPFException {
			
		ArrayList <Student> foundStudents = studentDAO.get(studentName);
		
		return foundStudents;
	}

	/**
	 * Search the student selected by the user
	 * @param studentCPF - the 'cpf' of the selected student 
	 * @return an object with the data of the selected student
	 * @throws SQLException
	 * @throws StudentException
	 * @throws PhoneException
	 * @throws CPFException
	 * @throws DateException
	 * @throws AddressException
	 * @throws RGException
	 * @throws CourseException
	 * @throws ServiceException
	 * @throws PaymentException 
	 */
	public ArrayList<Service> searchStudent(CPF studentCPF) throws SQLException, StudentException, PhoneException, CPFException, DateException, AddressException, RGException, CourseException, ServiceException, PaymentException {
		
		Student basicDataOfStudent = studentDAO.get(studentCPF);
		ArrayList<Service> servicesOfStudent = new ArrayList<Service>();
		servicesOfStudent = null;
		if(basicDataOfStudent != null){
			
			ServiceController serviceController = new ServiceController(); 
			
			servicesOfStudent = serviceController.searchService(basicDataOfStudent);		
			
		}
		else{	
			throw new StudentException(STUDENT_WITHOUT_SERVICE);
		}
				
		
		
		return servicesOfStudent;
	}

	public boolean alterStatusOfTheStudent(Student student) {
		return false;
		// TODO Auto-generated method stub
		
	}
}