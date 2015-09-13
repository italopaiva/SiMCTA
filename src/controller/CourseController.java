package controller;

import java.sql.ResultSet;

import dao.CourseDAO;
import exception.CourseException;
import model.Course;

public class CourseController {
	
	public CourseController(){}
		
	/**
	 * Create a new course with the given information
	 * @param courseName - Name of the course
	 * @param courseDescription - Description of the course
	 * @param courseDuration - Duration of the course
	 * @param courseValue - Value of the course
	 * @return TRUE if the course was created or FALSE if it does not
	 * @throws CourseException
	 */
	public boolean newCourse(String courseName, String courseDescription,
							 Integer courseDuration, Integer courseValue)
							 throws CourseException{
		
		Course course = new Course(courseName, courseDescription, courseDuration, courseValue);
		
		boolean wasSaved;
		
		CourseDAO courseDao = new CourseDAO();
		
		wasSaved = courseDao.save(course);
		
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
	
	public boolean alterStatusCourse(int idCourse) throws CourseException{
		
		boolean statusWasAltered;
		
		CourseDAO courseDao = new CourseDAO();
		int teste = courseDao.returnStatusCourse(idCourse);
	
		
		if (teste == 1){
			statusWasAltered = courseDao.alterCourseStatus(idCourse, 0);
		} else {
			statusWasAltered = courseDao.alterCourseStatus(idCourse, 1);
		}
		
		return statusWasAltered;
		
	}

}
