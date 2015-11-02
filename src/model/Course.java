package model;

import exception.CourseException;
import exception.ServiceItemException;

public class Course extends ServiceItem{ 

	private static final String COURSE_DESCRIPTION_CANT_BE_NULL = "A descrição do curso não pode ficar em branco.";
	
	private String courseDescription;
		
	public Course(){}
	
	public Course(String courseName, String courseDescription, Integer courseDuration, Integer courseValue)
				  throws CourseException{

		try{
		
			setName(courseName);
			setCourseDescription(courseDescription);
			setDuration(courseDuration);
			setValue(courseValue);
		}
		catch(ServiceItemException e){
			throw new CourseException(e.getMessage());
		}
	}
	
	public Course(int courseId) throws CourseException{
		
		try{
			setId(courseId);
		}
		catch(ServiceItemException e){
			throw new CourseException(e.getMessage());
		}
	}
	
	public Course(String searchedCourse) throws CourseException{
		
		try{
			setName(searchedCourse);
		}
		catch(ServiceItemException e){
			throw new CourseException(e.getMessage());
		}
	}
	
	public Course(int courseId, String courseName, String courseDescription,
			  Integer courseDuration, Integer courseValue)
			  throws CourseException{

		try{
			setId(courseId);
			setName(courseName);
			setCourseDescription(courseDescription);
			setDuration(courseDuration);
			setValue(courseValue);
		}
		catch(ServiceItemException e){
			throw new CourseException(e.getMessage());
		}
	}

	public Course(int courseId, String courseName, String courseDescription,
			  Integer courseDuration, Integer courseValue, int courseStatus)
			  throws CourseException{
		
		try{
			setId(courseId);
			setName(courseName);
			setCourseDescription(courseDescription);
			setDuration(courseDuration);
			setValue(courseValue);
			setStatus(courseStatus);
		}
		catch(ServiceItemException e){
			throw new CourseException(e.getMessage());
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
	
	public String getCourseDescription(){
		return this.courseDescription;
	}
	

	/**
	 * Method used to pass the course value to monetary form
	 * @param value - Receives the course value on entire form
	 * @return - Return the course value on monetary form (R$)
	 */
	public String getFormattedValue(Integer value) {
		
		String valueText= "";
		String entireValue = "";
		String decimalValue = "";
		int lengthOfValue = 0;
		
		valueText = value.toString();
		lengthOfValue = valueText.length();
		entireValue = valueText.substring(0, (lengthOfValue - 2));
		decimalValue = valueText.substring((lengthOfValue - 2), lengthOfValue);
		valueText = entireValue + "," + decimalValue;
		
		return valueText;
	}
}
