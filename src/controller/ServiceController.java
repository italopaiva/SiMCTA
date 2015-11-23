package controller;

import java.util.ArrayList;

import model.Course;
import model.Package;
import model.Payment;
import model.Service;
import model.Student;
import dao.ServiceDAO;
import exception.AddressException;
import exception.CPFException;
import datatype.CPF;
import exception.CourseException;
import exception.DateException;
import exception.PaymentException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.StudentException;

public class ServiceController {
	
	private static final String CANT_SAVE_NULL_SERVICE = "Não é possível salvar um serviço nulo";
	private ServiceDAO serviceDAO;
	private PaymentController paymentController;
	private CourseController courseController;
	private PackageController packageController;
	
	public ServiceController(){
		serviceDAO = new ServiceDAO();
		paymentController = new PaymentController();
		courseController = new CourseController();
		packageController = new PackageController();
	}
	
	/**
	 * Creates a new service with the requested courses and packages by a student
	 * @param student - The student that requested the services
	 * @param courses - The courses requested
	 * @param packages - The packages requested
	 * @throws ServiceException
	 */	
	public Service newService(Student student, ArrayList<String> courses, ArrayList<String> packages, Integer value) throws ServiceException{

		Service service = new Service(student, value);
		
		service = addCoursesToService(service, courses);
		service = addPackagesToService(service, packages);
		
		return service;
	}
	
	private Service addCoursesToService(Service service, ArrayList<String> coursesId){
		
		for(String courseId : coursesId){
			
			Course course = courseController.get(new Integer(courseId));
			
			if(course != null){
				try{
					service.addItem(course);
				}
				catch (ServiceException e){
					// Nothing to do
				}
			}
			else{
				// Nothing to do
			}
		}
		
		return service;
	}
	
	private Service addPackagesToService(Service service, ArrayList<String> packagesId){
		
		for(String packageId : packagesId){
			
			Package foundPackage = packageController.getPackage(new Integer(packageId));
			
			if(foundPackage != null){
				try{
					service.addItem(foundPackage);
				}
				catch (ServiceException e){
					// Nothing to do
				}
			}
			else{
				// Nothing to do
			}
		}
		
		return service;
	}
	
	/**
	 * Try to save the given service
	 * @param service
	 * @throws ServiceException
	 */
	public void saveService(Service service) throws ServiceException{
		
		if(service != null){
			serviceDAO.save(service);
		}
		else{
			throw new ServiceException(CANT_SAVE_NULL_SERVICE);
		}
	}
	
	/**
	 * Creates a new service with the requested courses and packages by a student
	 * @param studentCPF - The student's CPF that requested the services
	 * @param courses - The courses requested
	 * @param packages - The packages requested
	 * @param paymentType - The payment type of the chosen payment
	 * @param paymentForm - The payment form of the chosen payment
	 * @param installments - Quantity of installments of the payment
	 * @throws ServiceException
	 * @throws PaymentException
	 * @throws PersonException 
	 * @throws StudentException 
	 * @throws RGException 
	 * @throws AddressException 
	 * @throws DateException 
	 * @throws CPFException 
	 * @throws PhoneException 
	 */
	public void newService(CPF studentCpf, String studentName, ArrayList<String> courses, ArrayList<String> packages,
						   int paymentType, int paymentForm, Integer installments) throws ServiceException, PaymentException, PhoneException, CPFException, DateException, AddressException, RGException, StudentException, PersonException{
		
		Student student = new Student(studentName, studentCpf);
		
		Service service = new Service(student);
		
		service = addCoursesToService(service, courses);
		service = addPackagesToService(service, packages);
					
		PaymentController paymentController = new PaymentController();
		Payment payment = paymentController.newPayment(service, paymentType, paymentForm, installments);
		
		service.addPayment(payment);
		
		serviceDAO.save(service);
	}

	/**
	 * Search a service of a specific student
	 * @param studentCpf - contains the cpf of the student
	 * @return and arrayList of services
	 * @throws CourseException
	 * @throws DateException
	 * @throws ServiceException
	 * @throws PaymentException 
	 */
	public ArrayList<Service> searchService(CPF studentCpf) throws ServiceException {
		
		ArrayList<Service> services = new ArrayList<Service>();
		ArrayList<Service> servicesWithPayments = new ArrayList<Service>();

		Student student;
		try {
			student = new Student(studentCpf);
			services = serviceDAO.get(student);
			
			for(Service service : services){
							
				Payment payment = service.getPayment();
				payment = paymentController.searchPayment(payment);

				service.addPayment(payment);
				
				servicesWithPayments.add(service);
			}
		} 
		catch (PersonException | CourseException | DateException | PaymentException e1) {
			throw new ServiceException(e1.getMessage());
		}	
		
		
		return servicesWithPayments;
	}
	
	public void setServiceDAO(ServiceDAO serviceDao) {
		this.serviceDAO = serviceDao;
	}
	
	public void setPaymentController(PaymentController paymentController){
		this.paymentController = paymentController;
	}

	
}
