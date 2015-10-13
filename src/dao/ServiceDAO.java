package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.CourseException;
import exception.DateException;
import exception.ServiceException;
import model.Course;
import model.Service;
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
	
	public boolean save(Service service){
		
		boolean wasSaved = false;
		
		try{
			
			Integer serviceId = this.getNextId(SERVICE_TABLE_NAME, ID_COLUMN);
			
			String query = "INSERT INTO " + SERVICE_TABLE_NAME;
			   	   query += "("+ ID_COLUMN +"," + CPF_COLUMN + ", " + PAYMENT_ID_COLUMN + ", "; 
			   	   query += VALUE_COLUMN +", "+ DATE_COLUMN +") ";
			   	   query += "VALUES('"+ serviceId +"', '"+ service.getStudent().getStudentCpf().getCpf() +"', '";
			   	   query += service.getPayment().getPaymentId() +"', '"+ service.getTotalValue() +"', CURRENT_DATE())";
			
	   	   this.execute(query);
	   	   	   	   
	   	   saveServiceCourses(serviceId, service);
	   	   saveServicePackages(serviceId, service);
	   	   wasSaved = true;
		}
		catch(SQLException e){
			wasSaved = false;
		}
		
		return wasSaved;
	}
	
	private void saveServiceCourses(Integer serviceId, Service service) throws SQLException{
		
		int i = 0;
		ArrayList<Course> courses = service.getCourses();
		int quantityOfCourses = courses.size();
		
		for(i = 0; i < quantityOfCourses; i++){
			
			int currentCourseId = courses.get(i).getCourseId();
			String query = "INSERT INTO "+ TABLE_SERVICE_COURSE_NAME +" ("+ ID_COLUMN +", "+ ID_COURSE_COLUMN +") ";
				   query += "VALUES ('"+ serviceId +"', '"+ currentCourseId +"')";
				   
			this.execute(query);
		}		
	}

	private void saveServicePackages(Integer serviceId, Service service) throws SQLException{
		
		int i = 0;
		ArrayList<Package> packages = service.getPackages();
		int quantityOfPackages = packages.size();
		
		for(i = 0; i < quantityOfPackages; i++){
			
			int currentPackageId = packages.get(i).getPackageId();
			String query = "INSERT INTO "+ TABLE_SERVICE_PACKAGE_NAME +" ("+ ID_COLUMN +", "+ ID_PACKAGE_COLUMN +") ";
				   query += "VALUES ('"+ serviceId +"', '"+ currentPackageId +"')";
				   
			this.execute(query);
		}	
	}

	public ArrayList<Service> get(Student student) throws CourseException, DateException, ServiceException {
		
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

	private Service getDataFromService(ResultSet services, Student student, ArrayList<String> courses, ArrayList<String> packages) throws SQLException, DateException, ServiceException {
		
		Service service = null;
	
		String date = services.getString(DATE_COLUMN);
		String year = date.substring(0,4);
		String month = date.substring(5,7);
		String day = date.substring(8,10);
		Date contractsDate = new Date(new Integer(day),new Integer(month),new Integer(year));

		service  = new Service(student, courses, packages, contractsDate);
		
		return service;
	}

}
