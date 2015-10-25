package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.CourseException;
import exception.DateException;
import exception.PaymentException;
import exception.ServiceException;
import model.Course;
import model.Payment;
import model.Service;
import model.ServiceItem;
import model.Student;
import model.Package;
import model.datatype.CPF;
import model.datatype.Date;

public class ServiceDAO extends DAO {

	private static final String SERVICE_TABLE_NAME = "Service";
	private static final String ID_COLUMN = "id_service";
	private static final String CPF_COLUMN = "cpf";
	private static final String DATE_COLUMN	= "contract_date";
	private static final String VALUE_COLUMN	= "value";
	private static final String TABLE_SERVICE_COURSE_NAME = "ServiceCourse";
	private static final String TABLE_SERVICE_PACKAGE_NAME = "ServicePackage";
	private static final String ID_COURSE_COLUMN = "id_course";
	private static final String ID_PACKAGE_COLUMN = "id_package";
	private static final String PAYMENT_ID_COLUMN = "id_payment";
	private static final String PAYMENT_TABLE = "Payment";
	private static final String COULDNT_SAVE_SERVICE = "Não foi possível salvar os dados do serviço informado.";
	
	/**
	 * Save a given service on the database
	 * @param service - The service to be saved
	 * @throws ServiceException
	 */
	public void save(Service service) throws ServiceException{
		
		try{
			
			Integer serviceId = this.getNextId(SERVICE_TABLE_NAME, ID_COLUMN);
			
			String query = "INSERT INTO " + SERVICE_TABLE_NAME;
			   	   query += "("+ ID_COLUMN +"," + CPF_COLUMN + ", " + PAYMENT_ID_COLUMN + ", "; 
			   	   query += VALUE_COLUMN +", "+ DATE_COLUMN +") ";
			   	   query += "VALUES('"+ serviceId +"', '"+ service.getStudent().getStudentCpf().getCpf() +"', '";
			   	   query += service.getPayment().getPaymentId() +"', '"+ service.getTotalValue() +"', CURRENT_DATE())";
			
	   	   this.execute(query);
	   	   	   	   
	   	   saveServiceItens(serviceId, service);
		}
		catch(SQLException e){
			throw new ServiceException(COULDNT_SAVE_SERVICE); 
		}
	}
	
	/**
	 * Save the courses and packages associated with the service
	 * @param serviceId - The service to associate the courses with
	 * @param service - The service with the courses and packagese to be associated
	 * @throws SQLException
	 */
	private void saveServiceItens(Integer serviceId, Service service) throws SQLException{
		
		ArrayList<ServiceItem> itens = service.getItens();
		
		for(ServiceItem item : itens){	
			
			String query = "";
			
			boolean isCourse = item.getClass().equals(Course.class);
			if(isCourse){

				Integer currentCourseId = item.getId();
				
				query = "INSERT INTO "+ TABLE_SERVICE_COURSE_NAME +" ("+ ID_COLUMN +", "+ ID_COURSE_COLUMN +") ";
				query += "VALUES ('"+ serviceId +"', '"+ currentCourseId +"')";
				
			}
			// If it is not a course, is a package
			else{
				
				Integer currentPackageId = item.getId();
				
				query = "INSERT INTO "+ TABLE_SERVICE_PACKAGE_NAME +" ("+ ID_COLUMN +", "+ ID_PACKAGE_COLUMN +") ";
			    query += "VALUES ('"+ serviceId +"', '"+ currentPackageId +"')";
			}
							   
			this.execute(query);
		}		
	}
	
	/**
	 * Gets the services of a selected student
	 * @param student - an object with the data of the selected student
	 * @return an arraylist with the found services
	 * @throws CourseException
	 * @throws DateException
	 * @throws ServiceException
	 * @throws PaymentException 
	 */
	public ArrayList<Service> get(Student student) throws CourseException, DateException, ServiceException, PaymentException {
		
		ResultSet services = null;
		ResultSet coursesOfService = null;
		ResultSet packagesOfService = null;
		CPF cpf = student.getStudentCpf();
		String studentCPF = cpf.getCpf();
		ArrayList<Service> foundServices = new ArrayList<Service>();
		Service service = null;
		ArrayList<String> courses = new ArrayList<String>();
		ArrayList<String> packages = new ArrayList<String>();
		
		String queryForService = "SELECT * FROM " + SERVICE_TABLE_NAME + " WHERE " + CPF_COLUMN + "=\"" + studentCPF + " \""; 
		
		try {
			services = this.search(queryForService);
			
			while(services.next()){			
				int serviceId = services.getInt(ID_COLUMN);				
				try{
					String queryForCourses =  "SELECT * FROM " + TABLE_SERVICE_COURSE_NAME + " WHERE " + ID_COLUMN + "=" + serviceId;
					coursesOfService = this.search(queryForCourses);
					
					while(coursesOfService.next()){			
						String courseId = coursesOfService.getString(ID_COURSE_COLUMN);
						courses.add(courseId);
					}
					String queryForPackages = "SELECT * FROM " + TABLE_SERVICE_PACKAGE_NAME + " WHERE " + ID_COLUMN + "=" + serviceId;
					packagesOfService = this.search(queryForPackages);

					while(packagesOfService.next()){		
						String packageId = packagesOfService.getString(ID_PACKAGE_COLUMN);
						packages.add(packageId);
					}
					service = getDataFromService(services,student, courses, packages);
					foundServices.add(service);
				}
				catch(SQLException e){
					
				}

			}
			
		} 
		catch(SQLException e){
			
		}
		return foundServices;
	}

	/**
	 * Gets the data of the found service
	 * @param services - the row that contains the service
	 * @param student - the selected student 
	 * @param courses - the courses id of the service
	 * @param packages - the packages id of the service
	 * @return an Service object that contains the data of the found service
	 * @throws SQLException
	 * @throws DateException
	 * @throws ServiceException
	 * @throws PaymentException 
	 */
	private Service getDataFromService(ResultSet services, Student student, ArrayList<String> courses, ArrayList<String> packages) throws SQLException, DateException, ServiceException, PaymentException {
		
		Service service = null;
	
		String date = services.getString(DATE_COLUMN);
		String year = date.substring(0,4);
		String month = date.substring(5,7);
		String day = date.substring(8,10);
		Date contractsDate = new Date(new Integer(day),new Integer(month),new Integer(year));

		if(courses.isEmpty()){
			courses = null;
		}
		else{
			// Nothing to do
		}
		if(packages.isEmpty()){
			packages = null;
		}
		else{
			// Nothing to do
		}

		int paymentId = services.getInt(PAYMENT_ID_COLUMN);
		Payment payment = new Payment(paymentId);
				
		service  = new Service(student, courses, packages, contractsDate,payment);
		
		return service;
	}

}
