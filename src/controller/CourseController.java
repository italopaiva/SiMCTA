package controller;

import java.sql.ResultSet;

import dao.CourseDAO;
import exception.CourseException;
import model.Course;

public class CourseController {
	
	public CourseController(){}
	
	/**
	 * Create a new course with the Course object specified
	 * @param course - the course to be created
	 * @return TRUE if the course was created or FALSE if it does not
	 */
	public boolean newCourse(Course course){
		
		boolean wasSaved;
		
		if(course != null){
			
			CourseDAO courseDao = new CourseDAO();
		
			wasSaved = courseDao.save(course);
		}else{
			wasSaved = false;
		}
		
		return wasSaved;
	}
	

	public ResultSet showCourse(String searchedCourse) throws CourseException{
		
		ResultSet resultOfSearch;
		CourseDAO courseDao = new CourseDAO();
		Course course = new Course(searchedCourse);
		
		resultOfSearch = courseDao.get(course);
				
		return resultOfSearch;
	}
	
	public ResultSet showCourse(){
		
		ResultSet resultOfTheSelect;
		CourseDAO courseDao = new CourseDAO();		
		resultOfTheSelect = courseDao.getAll();
		
		return resultOfTheSelect;
		
	}

}
