package controller;

import java.sql.ResultSet;

import dao.CourseDAO;
import exception.CourseException;
import model.Course;

public class CourseController {
	
	public CourseController(){}
	
	public boolean newCourse(Course course){
		
		CourseDAO courseDao = new CourseDAO();
		
		boolean wasSaved = courseDao.save(course);
		
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
