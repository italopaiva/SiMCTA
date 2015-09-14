package model;

import exception.CourseException;

public class Course{
	
	// Error constants
	private static final String COURSE_VALUE_CANT_BE_ZERO = "O valor do curso não pode ser menor ou igual a zero.";
	private static final String COURSE_DURATION_CANT_BE_ZERO = "Um curso deve durar pelo menos uma semana.";
	private static final String COURSE_NAME_CANT_BE_NULL = "O nome do curso não pode ficar em branco.";
	private static final String COURSE_DESCRIPTION_CANT_BE_NULL = "A descrição do curso não pode ficar em branco.";
	private static final String COURSE_ID_MUST_BE_GREATER_THAN_ZERO = "O id do curso não pode ser menor que 0"; 

	/**
	 * The max and min duration are these because the duration must have at least 1 digit and
	 * no more than 2 digits
	 * Ex.: 10 weeks -> 10 has two digits
	 * So, the greater number with 2 digits is 99, and the minimun is 1 (because can't be zero)
	 */
	private static final int MAX_DURATION = 99;
	private static final int MIN_DURATION = 1;
	
	/**
	 * The max and min value are these because the value must have no more than 6 digits
	 * Ex.: R$ 2500,39 = 250039
	 * So, the greater acceptable value is 999999 (R$ 9999,99) 
	 */
	private static final int MAX_VALUE = 999999;
	private static final int MIN_VALUE = 1;
	
	private int courseId;
	private String courseName;
	private String courseDescription;
	private int courseStatus;

	/**
	 * Given in weeks
	 * Must only have at least one digit and no more than two digits (Ex.: 10 weeks)
	 */
	private Integer courseDuration;
	
	/**
	 * Given in reals (R$)
	 * Cannot have more than 6 digits (Ex.: R$ 1500,50 = 150050)
	 */
	private Integer courseValue;
	
	public Course(){}
	
	public Course(String courseName, String courseDescription,
				  Integer courseDuration, Integer courseValue)
				  throws CourseException{
		try{
			setCourseName(courseName);
			setCourseDescription(courseDescription);
			setCourseDuration(courseDuration);
			setCourseValue(courseValue);
		}catch(CourseException caughtException){
			
			throw caughtException;
		}
	}
	
	public Course(String courseName) throws CourseException{
		setCourseName(courseName);
	}
	
	public Course(int courseId, String courseName, String courseDescription,
			  Integer courseDuration, Integer courseValue)
			  throws CourseException{
		try{
			setCourseId(courseId);
			setCourseName(courseName);
			setCourseDescription(courseDescription);
			setCourseDuration(courseDuration);
			setCourseValue(courseValue);
		}catch(CourseException caughtException){
			
			throw caughtException;
		}
	}

	public Course(int courseId, String courseName, String courseDescription,
			  Integer courseDuration, Integer courseValue, int courseStatus)
			  throws CourseException{
		try{
			setCourseId(courseId);
			setCourseName(courseName);
			setCourseDescription(courseDescription);
			setCourseDuration(courseDuration);
			setCourseValue(courseValue);
			setCourseStatus(courseStatus);
		}catch(CourseException caughtException){
		
			throw caughtException;
		}
	}

/** Setters */
	
	private void setCourseName(String courseName) throws CourseException{
		
		boolean courseNameIsValid = courseName != null && !courseName.isEmpty();  
		
		if(courseNameIsValid){
			this.courseName = courseName;
		}else{
			throw new CourseException(COURSE_NAME_CANT_BE_NULL);
		}
	}
	
	private void setCourseDescription(String courseDescription) throws CourseException{
		
		boolean courseDescriptionIsValid = courseDescription != null 
										   && !courseDescription.isEmpty();  
		
		if(courseDescriptionIsValid){
			
			this.courseDescription = courseDescription;
		}else{
			
			throw new CourseException(COURSE_DESCRIPTION_CANT_BE_NULL);
		}
	}
	
	private void setCourseDuration(Integer courseDuration) throws CourseException{
		
		int duration = courseDuration.intValue();
		
		boolean courseDurationIsValid = duration >= MIN_DURATION 
										&& duration <= MAX_DURATION;
		
		if(courseDurationIsValid){
			
			this.courseDuration = courseDuration;
		}else{
			
			throw new CourseException(COURSE_DURATION_CANT_BE_ZERO);
		}
	}
	
	private void setCourseValue(Integer courseValue) throws CourseException{
		
		int value = courseValue.intValue();
		
		boolean courseValueIsValid = value >= MIN_VALUE 
										&& value <= MAX_VALUE;
		if(courseValueIsValid){
			
			this.courseValue = courseValue;
		}else{
			
			throw new CourseException(COURSE_VALUE_CANT_BE_ZERO);
		}
	}
	
	private void setCourseId(int courseId) throws CourseException{
		
		boolean courseIdIsValid = courseId > 0;
		
		if(courseIdIsValid){
			
			this.courseId = courseId;
		}else{
			
			throw new CourseException(COURSE_ID_MUST_BE_GREATER_THAN_ZERO);
		}
	}
	
	private void setCourseStatus(int status) {
		this.courseStatus = status;
	}
	
/** End of Setters */
	
/** Getters */
	
	public String getCourseName(){
		return this.courseName;
	}
	
	public String getCourseDescription(){
		return this.courseDescription;
	}
	
	public Integer getCourseDuration(){
		return this.courseDuration;
	}
	
	public Integer getCourseValue(){
		return this.courseValue;
	}
	
	public int getCourseId(){
		return this.courseId;
	}
	
	public int getCourseStatus(){
		return this.courseStatus;
	}

/** End of Getters */
}
