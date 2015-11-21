package controller;

import java.sql.SQLException;
import java.util.ArrayList;

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
	private static final String STUDENT_NULL = "Não foi possível encontrar o estudante";
	private static final String CANT_SAVE_NULL_STUDENT = "Não é possível salvar um estudante nulo.";
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
	 * @param birthdate - Birth date of the student
	 * @param email - Email of the student (optional)
	 * @param address - Address of the student
	 * @param principalPhone - The principal phone number of the student
	 * @param secondaryPhone - The secondary phone number of the student (optional)
	 * @param motherName - The name of the student's mother
	 * @param fatherName - The name of the student's father
	 * @throws StudentException
	 */	
	public Student newStudent(String studentName, CPF studentCpf, RG studentRg, Date birthdate, String email, Address address,
			   Phone principalPhone, Phone secondaryPhone, String motherName, String fatherName) throws StudentException{

		Student student;
		try{
			student = new Student(studentName, studentCpf, studentRg, birthdate, email, address, principalPhone, secondaryPhone, motherName, fatherName, ACTIVE_STATUS);
		}
		catch(PersonException e){
			throw new StudentException(e.getMessage());
		}
		
		return student;
	}
	
	/**
	 * Try to save the given student
	 * @param student
	 * @throws StudentException
	 */
	public void saveStudent(Student student) throws StudentException{
		
		if(student != null){
			try{
				studentDAO.save(student);
			}
			catch(PersonException e){
				throw new StudentException(e.getMessage());
			}
		}
		else{
			throw new StudentException(CANT_SAVE_NULL_STUDENT);
		}
	}
	
	public Student getStudent(CPF cpf) throws StudentException{
		
		Student foundStudent;
		try {
			foundStudent = studentDAO.get(cpf);
		} 
		catch (PhoneException | CPFException | DateException
				| AddressException | RGException | StudentException | PersonException e) {
			throw new StudentException(STUDENT_NULL);
		}
		
		return foundStudent;
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
	/*public ArrayList<Service> searchStudent(CPF studentCPF) throws SQLException, StudentException, PhoneException, CPFException, DateException, AddressException, RGException, CourseException, ServiceException, PaymentException, PersonException {
		
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
	*/
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