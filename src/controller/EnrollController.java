package controller;

import java.util.ArrayList;

import datatype.Address;
import datatype.CPF;
import datatype.Date;
import datatype.Phone;
import datatype.RG;
import exception.PaymentException;
import exception.ServiceException;
import exception.StudentException;
import model.Payment;
import model.Service;
import model.Student;

public class EnrollController extends Enroll {
	
	private StudentController studentController;
	private ServiceController serviceController;
	private PaymentController paymentController;
	private Student student;
	private Service service;
	
	public EnrollController(){
		studentController = new StudentController();
		serviceController = new ServiceController();
		paymentController = new PaymentController();
	}
	
	/**
	 * Enrolls a student in the given courses and packages with the given payment data
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
	 * @param courses - The courses requested
	 * @param packages - The packages requested
	 * @param paymentType - Type of payment
	 * @param paymentForm - Form of payment
	 * @param installments - Quantity of installments
	 * @throws StudentException
	 * @throws ServiceException
	 * @throws PaymentException
	 */
	public Student enrollStudent(String studentName, CPF studentCpf, RG studentRg, Date birthdate, String email, Address address,
			   Phone principalPhone, Phone secondaryPhone, String motherName, String fatherName,
			   ArrayList<String> courses, ArrayList<String> packages, int paymentType, int paymentForm, Integer installments, Integer value) throws StudentException, ServiceException, PaymentException{
		
		try{
		
			Student student = studentController.newStudent(studentName, studentCpf, studentRg, birthdate, email, address,
				 									   principalPhone, secondaryPhone, motherName, fatherName);
		
			this.student = student;
			
			Service service = serviceController.newService(student, courses, packages, value);
			System.out.println(service.getTotalValue());
			Payment payment = paymentController.newPayment(service, paymentType, paymentForm, installments);
			
			service.addPayment(payment);
			this.service = service;
			
			enroll();
		}
		catch(StudentException e){
			student = null;
			throw new StudentException(e.getMessage());
		}
	
		return student;
	}
	
	@Override
	protected void saveStudent() throws StudentException{
		studentController.saveStudent(this.student);
	}
	
	@Override
	protected void saveService() throws ServiceException{
		serviceController.saveService(this.service);
	}
}
