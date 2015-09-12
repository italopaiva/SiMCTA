package model;

import exception.CourseException;

public class Course{
	
	private static final String COURSE_VALUE_CANT_BE_ZERO = "O valor do curso não pode ser menor ou igual a zero";
	private static final String NAME_CANT_BE_BLANK = "O nome do curso deve ser digitado";
	
	private String courseName;
	private String courseDescription;
	private Integer courseDuration; // In weeks
	private Integer courseValue;
	
	public Course(){}
	
	public Course(String courseName, String courseDescription,
				  Integer courseDuration, Integer courseValue)
				  throws CourseException{
		
		boolean courseValueIsValid = courseValue.intValue() > 0;
	
		if(courseValueIsValid){
			
			setCourseName(courseName);
			setCourseDescription(courseDescription);
			setCourseDuration(courseDuration);
			setCourseValue(courseValue);
		}else{
			
			throw new CourseException(COURSE_VALUE_CANT_BE_ZERO);
		}
	}
	
	public Course(String courseName) throws CourseException{
		if(!courseName.isEmpty()){
			setCourseName(courseName);
		}
		else{
			throw new CourseException(NAME_CANT_BE_BLANK);
		}
	}
	
	private void setCourseName(String courseName){
		this.courseName = courseName;
	}
	
	private void setCourseDescription(String courseDescription){
		this.courseDescription = courseDescription;
	}
	
	private void setCourseDuration(Integer courseDuration){
		this.courseDuration = courseDuration;
	}
	
	private void setCourseValue(Integer courseValue){
		this.courseValue = courseValue;
	}
	
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
}
