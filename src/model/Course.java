package model;

import exception.CourseException;

public class Course{
	
	// Error constants
	private static final String COURSE_VALUE_CANT_BE_ZERO = "O valor do curso não pode ser menor ou igual a zero.";
	private static final String COURSE_DURATION_CANT_BE_ZERO = "Um curso deve durar pelo menos uma semana.";
	private static final String COURSE_NAME_CANT_BE_NULL = "O nome do curso não pode ficar em branco.";
	private static final String COURSE_DESCRIPTION_CANT_BE_NULL = "A descrição do curso não pode ficar em branco.";
	
	private String courseName;
	private String courseDescription;

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
		
		boolean courseDurationIsValid = courseDuration.intValue() > 0;
		
		if(courseDurationIsValid){
			
			this.courseDuration = courseDuration;
		}else{
			
			throw new CourseException(COURSE_DURATION_CANT_BE_ZERO);
		}
	}
	
	private void setCourseValue(Integer courseValue) throws CourseException{
		
		boolean courseValueIsValid = courseValue.intValue() > 0;
		
		if(courseValueIsValid){
			
			this.courseValue = courseValue;
		}else{
			
			throw new CourseException(COURSE_VALUE_CANT_BE_ZERO);
		}
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

/** End of Getters */
}
