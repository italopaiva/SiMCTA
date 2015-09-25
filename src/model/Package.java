package model;

import java.util.ArrayList;

import exception.CourseException;
import exception.PackageException;

public class Package {

	/**
	 * Error messages 
	 */
	private static final String PACKAGE_NAME_CANT_BE_NULL = "O nome do pacote não pode ser nulo.";
	private static final String PACKAGE_VALUE_CANT_BE_ZERO = "O valor do pacote não pode ser zero";
	private static final String PACKAGE_ID_MUST_BE_GREATER_THAN_ZERO = "O código do pacote deve ser maior que zero";
	private static final String PACKAGE_DURATION_CANT_BE_ZERO = "O pacote deve durar pelo menos 1 semana";
	private static final String COURSES_OF_PACKAGE_CANT_BE_ZERO = "O pacote não pode ser criado sem cursos";

	/**
	 * The max and min duration are these because the duration must have at least 1 digit and
	 * no more than 2 digits
	 * Ex.: 10 weeks -> 10 has two digits
	 * So, the greater number with 2 digits is 99, and the minimun is 1 (because can't be zero)
	 */
	private static final int MIN_DURATION = 1;
	private static final int MAX_DURATION = 99;
	/**
	 * The max and min value are these because the value must have no more than 6 digits
	 * Ex.: R$ 2500,39 = 250039
	 * So, the greater acceptable value is 999999 (R$ 9999,99) 
	 */
	private static final int MAX_VALUE = 999999;
	private static final int MIN_VALUE = 1;
	
	
	private Integer packageId;
	private String packageName;
	private Integer packageDuration;
	
	/**
	 * Courses contained in the package
	 */
	private ArrayList <Course> courses = new ArrayList<Course>();

	
	/**
	 * Given in reals (R$)
	 * Cannot have more than 6 digits (Ex.: R$ 1500,50 = 150050)
	 */
	private Integer packageValue;
		
		
	/** Constructors */
	public Package(){}
	
	public Package(String packageName, Integer packageValue) throws PackageException{
		setPackageName(packageName);
		setPackageValue(packageValue);
	}
	
	public Package(Integer packageId, String packageName, Integer packageValue) throws PackageException{
		setPackageId(packageId);
		setPackageName(packageName);
		setPackageValue(packageValue);
	}
	
	/** Setters 
	 * @throws PackageException */
	
	private void setPackageId(Integer packageId) throws PackageException {
		boolean packageIdIsValid = packageId > 0;
		
		if(packageIdIsValid){
			
			this.packageId = packageId;
		}else{
			
			throw new PackageException(PACKAGE_ID_MUST_BE_GREATER_THAN_ZERO);
		}
	}

	private void setPackageName(String packageName) throws PackageException {
		
		boolean packageNameIsValid = packageName != null && !packageName.isEmpty();  
		
		if(packageNameIsValid){
			this.packageName = packageName;
		}
		else{
			throw new PackageException(PACKAGE_NAME_CANT_BE_NULL);
		}
	}
	private void setPackageValue(Integer packageValue) throws PackageException {
		
		int value = packageValue.intValue();
		
		boolean packageValueIsValid = value >= MIN_VALUE 
									 && value <= MAX_VALUE;
		if(packageValueIsValid){
			
			this.packageValue = packageValue;
		}
		else{
			
			throw new PackageException(PACKAGE_VALUE_CANT_BE_ZERO);
		}
	}
	
	private void setPackageDuration(Integer packageDuration) throws PackageException{
		
		int duration = packageDuration.intValue();
		
		boolean packageDurationIsValid = duration >= MIN_DURATION 
										&& duration <= MAX_DURATION;
		
		if(packageDurationIsValid){
			
			this.packageDuration = packageDuration;
		}else{
			
			throw new PackageException(PACKAGE_DURATION_CANT_BE_ZERO);
		}
	}
	private void setCourses(ArrayList<Course> courses) throws PackageException {
		
		boolean coursesAreEmpty = courses.isEmpty();
		
		if(!coursesAreEmpty){		
			this.courses = courses;
		}else{
			
			throw new PackageException(COURSES_OF_PACKAGE_CANT_BE_ZERO);
		}
	}

	/** Getters */ 
	public Integer getPackageId() {
		return packageId;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public Integer getPackageValue() {
		return packageValue;
	}
	
	public Integer getPackageDuration() {
		return packageDuration;
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}

	
	
}
