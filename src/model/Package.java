package model;

import java.util.ArrayList;

import exception.CourseException;
import exception.PackageException;

public class Package {

	/**
	 * Error messages 
	 */
	private static final String PACKAGE_NAME_CANT_BE_NULL = "O nome do pacote deve ser preenchido";
	private static final String PACKAGE_VALUE_CANT_BE_ZERO = "O pacote deve ter um valor";
	private static final String PACKAGE_ID_MUST_BE_GREATER_THAN_ZERO = "O código do pacote deve ser maior que zero";
	private static final String PACKAGE_DURATION_CANT_GREATHER_THAN_MAX = "O pacote não pode ter mais que 99 semanas, remova algum curso";
	private static final String COURSES_OF_PACKAGE_CANT_BE_ZERO = "O pacote não pode ser criado sem cursos";
	private static final String PACKAGE_VALUE_GREATHER_THAN_MAX = "O pacote não pode custar mais R$ 9999,99";
	private static final String PACKAGE_DURATION_CANT_BE_ZERO = "O pacote deve durar pelo menos 1 semana";

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
	private Integer packageStatus;

	/**
	 * Courses contained in the package
	 */
	private ArrayList <String> courses = new ArrayList<String>();

	
	/**
	 * Given in reals (R$)
	 * Cannot have more than 6 digits (Ex.: R$ 1500,50 = 150050)
	 */
	private Integer packageValue;
		
		
	/** Constructors */
	public Package(){}
	
	public Package(Integer packageId, String packageName, Integer packageValue, 
			       Integer packageDuration, ArrayList <String> courses) throws PackageException{
		
		setPackageId(packageId);
		setPackageName(packageName);
		setPackageValue(packageValue);
		setCourses(courses);
		setPackageDuration(packageDuration);
	}
	
	public Package(Integer packageId, String packageName, Integer packageValue, 
			       Integer packageDuration, Integer status, ArrayList <String> courses) throws PackageException{
		
		setPackageId(packageId);
		setPackageName(packageName);
		setPackageValue(packageValue);
		setPackageDuration(packageDuration);
		setPackageStatus(status);
		setCourses(courses);
	}
	
	public Package(Integer packageId, String packageName, Integer packageValue, 
		       Integer packageDuration, Integer status) throws PackageException{
	
		setPackageId(packageId);
		setPackageName(packageName);
		setPackageValue(packageValue);
		setPackageDuration(packageDuration);
		setPackageStatus(status);
	}
	
	/** Setters 
	 * @throws PackageException */
	
	private void setPackageId(Integer packageId) throws PackageException {
		
		boolean packageIdIsValid = packageId != null && packageId > 0;
		
		if(packageIdIsValid){
			
			this.packageId = packageId;
		}else{
			
			throw new PackageException(PACKAGE_ID_MUST_BE_GREATER_THAN_ZERO);
		}
	}

	private void setPackageName(String packageName) throws PackageException {
		
		boolean packageNameIsValid = ((packageName != null) && (!packageName.isEmpty()));  
		
		if(packageNameIsValid){
			this.packageName = packageName;
		}
		else{
			throw new PackageException(PACKAGE_NAME_CANT_BE_NULL);
		}
	}
	private void setPackageValue(Integer packageValue) throws PackageException {
		
		if(packageValue != null){
			int value = packageValue.intValue();
			
			boolean caseMin = value >= MIN_VALUE;
			boolean caseMax = value <= MAX_VALUE;
			
			boolean packageValueIsValid = caseMin && caseMax;
			if(packageValueIsValid){
				
				this.packageValue = packageValue;
			}
			else{
				if(!caseMin){
					throw new PackageException(PACKAGE_VALUE_CANT_BE_ZERO);
				}
				else{
					throw new PackageException(PACKAGE_VALUE_GREATHER_THAN_MAX);
				}
			}
		}
		else{
			throw new PackageException(PACKAGE_VALUE_CANT_BE_ZERO);
		}
		
	}
	
	private void setPackageDuration(Integer packageDuration) throws PackageException{
		
		if(packageDuration != null){
			int duration = packageDuration.intValue();
			
			boolean caseMin = duration >= MIN_DURATION;
			boolean caseMax = duration <= MAX_DURATION;
			
			boolean packageDurationIsValid = caseMin && caseMax;
			
			if(packageDurationIsValid){
				
				this.packageDuration = packageDuration;
			}
			else{
				if(!caseMin){
					throw new PackageException(PACKAGE_DURATION_CANT_BE_ZERO);
				}
				else{
					throw new PackageException(PACKAGE_DURATION_CANT_GREATHER_THAN_MAX);
				}
			}
		}
		else{
			throw new PackageException(PACKAGE_DURATION_CANT_BE_ZERO);
		}
		
	}
	private void setCourses(ArrayList<String> courses) throws PackageException {
		
		boolean coursesAreValid = courses != null && !courses.isEmpty();
		
		if(coursesAreValid){		
			this.courses = courses;
		}else{
			throw new PackageException(COURSES_OF_PACKAGE_CANT_BE_ZERO);
		}
	}
	
	private void setPackageStatus(Integer packageStatus) {
		this.packageStatus = packageStatus;
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
	
	public ArrayList<String> getCourses() {
		return courses;
	}
	
	public Integer getPackageStatus() {
		return packageStatus;
	}

}
