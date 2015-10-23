package controller;

import java.util.ArrayList;

import dao.PackageDAO;
import dao.ServiceDAO;
import exception.CourseException;
import exception.DateException;
import exception.PaymentException;
import exception.ServiceException;
import model.Payment;
import model.Service;
import model.Student;
import model.datatype.CPF;

public class ServiceController {
	
	private ServiceDAO serviceDAO;
	private PaymentController paymentController;
	
	public ServiceController(){
		serviceDAO = new ServiceDAO();
		paymentController = new PaymentController();
	}
	
	/**
	 * Creates a new service with the requested courses and packages by a student
	 * @param student - The student that requested the services
	 * @param courses - The courses requested
	 * @param packages - The packages requested
	 * @param paymentType - The payment type of the chosen payment
	 * @param paymentForm - The payment form of the chosen payment
	 * @param installments - Quantity of installments of the payment
	 * @throws ServiceException
	 * @throws PaymentException
	 */
	public void newService(Student student, ArrayList<String> courses, ArrayList<String> packages,
						   int paymentType, int paymentForm, Integer installments) throws ServiceException, PaymentException{
		
		Service service = new Service(student, courses, packages);
					
		PaymentController paymentController = new PaymentController();
		Payment payment = paymentController.newPayment(service, paymentType, paymentForm, installments);
		
		service.addPayment(payment);
		
		serviceDAO.save(service);
	}

	/**
	 * Search a service of a specific student
	 * @param basicDataOfStudent - contains the data of the student
	 * @return and arrayList of services
	 * @throws CourseException
	 * @throws DateException
	 * @throws ServiceException
	 * @throws PaymentException 
	 */
	public ArrayList<Service> searchService(Student basicDataOfStudent) throws CourseException, DateException, ServiceException, PaymentException{
		
		ArrayList<Service> services = new ArrayList<Service>();
		ArrayList<Service> servicesWithPayments = new ArrayList<Service>();

		services = serviceDAO.get(basicDataOfStudent);
		
		int i = 0;
		while(i < services.size()){
			
			Service service = services.get(i);
			
			Payment payment = service.getPayment();
			
			payment = paymentController.searchPayment(payment);

			service = new Service(services.get(i), payment);
			servicesWithPayments.add(service);
			i++;
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
