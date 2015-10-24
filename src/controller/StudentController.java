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
import exception.PersonException;
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
	private static final String STUDENT_NULL = "Não foi possível encontrar o estudante para mudar o status";
	private static final int ACTIVE_STATUS	= 1;
	private StudentDAO studentDAO;
	private ServiceController serviceController;
	
	public StudentController(){
		studentDAO = new StudentDAO();
		serviceController = new ServiceController();
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	public void setServiceController(ServiceController serviceController) {
		this.serviceController = serviceController;
	}
	
	/**
	 * Creates a new student with the given information of the student and its service
	 * @param studentName - Name of the student
	 * @param studentCpf - CPF of the student
	 * @param studentRg - RG of the student
	 * @param birthdate - Birthdate of the student
	 * @param email - Email of the student (optional)
	 * @param address - Address of the student
	 * @param principalPhone - The principal phone number of the student
	 * @param secondaryPhone - The secondary phone number of the student (optional)
	 * @param motherName - The name of the student's mother
	 * @param fatherName - The name of the student's father
	 * @param courses - The courses ids contracted by the student 
	 * @param packages - The packages ids contracted by the student 
	 * @param paymentType - The payment type of the chosen payment by the student
	 * @param paymentForm- The payment form of the chosen payment by the student
	 * @param installments - Quantity of installments requested by the student
	 * @throws StudentException
	 * @throws ServiceException
	 * @throws PaymentException
	 * @throws PersonException 
	 */
	public void newStudent(String studentName, CPF studentCpf, RG studentRg, Date birthdate, String email, Address address,
			 			   Phone principalPhone, Phone secondaryPhone, String motherName, String fatherName,
			 			   ArrayList<String> courses, ArrayList<String> packages, int paymentType, int paymentForm, Integer installments) throws StudentException, ServiceException, PaymentException, PersonException{
		
		Student student = new Student(studentName, studentCpf, studentRg, birthdate, email, address, principalPhone, secondaryPhone, motherName, fatherName, ACTIVE_STATUS);
		studentDAO.save(student);
		
		serviceController.newService(student, courses, packages, paymentType, paymentForm, installments);
	}

	/**
	 * Search the student with the entered name
	 * @param studentName - the entered name by user
	 * @return an arraylist with the found students
	 * @throws StudentException
	 * @throws CPFException
	 */
	public ArrayList<Student> searchStudent(String studentName) throws PersonException, CPFException, StudentException {
			
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
	 * @throws PersonException 
	 */
	public ArrayList<Service> searchStudent(CPF studentCPF) throws SQLException, StudentException, PhoneException, CPFException, DateException, AddressException, RGException, CourseException, ServiceException, PaymentException, PersonException {
		
		Student basicDataOfStudent = studentDAO.get(studentCPF);
		ArrayList<Service> servicesOfStudent = new ArrayList<Service>();
		servicesOfStudent = null;
		if(basicDataOfStudent != null){
						
			servicesOfStudent = serviceController.searchService(basicDataOfStudent);		
			
		}
		else{	
			throw new StudentException(STUDENT_WITHOUT_SERVICE);
		}
				
		
		
		return servicesOfStudent;
	}

	public boolean alterStatusOfTheStudent(Student student) throws StudentException {

		boolean wasAltered = false;
		
		if(student != null){
			wasAltered = studentDAO.update(student);
		}
		else{
			throw new StudentException(STUDENT_NULL);
		}
		
		return wasAltered;
		
	}
}