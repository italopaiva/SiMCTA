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
		
		boolean hasId = false;
		boolean wasSaved = saveCourse(course, hasId);
		
		return wasSaved;
	}
	
	/**
	 * Create a new course with the given information plus an ID
	 * @param courseId - Id of the course to be created
	 * @param courseName - Name of the course
	 * @param courseDescription - Description of the course
	 * @param courseDuration - Duration of the course
	 * @param courseValue - Value of the course
	 * @return TRUE if the course was created or FALSE if it does not
	 * @throws CourseException
	 */
	public boolean newCourse(int courseId, String courseName, String courseDescription,
							 Integer courseDuration, Integer courseValue)
							 throws CourseException{
		
		Course course = new Course(courseId, courseName, courseDescription, courseDuration, courseValue);
		
		boolean hasId = true;
		boolean wasSaved = saveCourse(course, hasId);
		
		return wasSaved;
	}
	
	/**
	 * Communicate with the DAO layer to save the course
	 * @param course - Course object with the course to be saved
	 * @param hasId - Inform if the course object has an ID associated
	 * @return a boolean with the result from the DAO layer
	 */
	private boolean saveCourse(Course course, boolean hasId){
		
		boolean wasSaved =  false;
		
		CourseDAO courseDao = new CourseDAO();
		
		wasSaved = courseDao.save(course, hasId);
		
		return wasSaved;
	}
	
	/**
	 * Update a given course with the new information
	 * @param courseId - The course id to be updated
	 * @param courseName - The name of the course
	 * @param courseDescription - The new description of the course
	 * @param courseDuration - The new duration of the course
	 * @param courseValue - The new value of the course
	 * @return TRUE if the course was updated or FALSE if it does not
	 * @throws CourseException
	 */
	public boolean updateCourse(Integer courseId, String courseName, String courseDescription,
								 Integer courseDuration, Integer courseValue)
								 throws CourseException{
		
		Course course = new Course(courseName, courseDescription, courseDuration, courseValue);
		
		boolean wasSaved = false;
		
		CourseDAO courseDao = new CourseDAO();
		
		wasSaved = courseDao.update(courseId, course);
		
		return wasSaved;
	}
	
	/**
	 * Show the information of a course searched by user
	 * @param searchedCourse - The name of course to be searched
	 * @return the data produced by the given query
	 * @throws CourseException
	 */
	public ResultSet showCourse(String searchedCourse) throws CourseException{
		
		CourseDAO courseDao = new CourseDAO();
		Course course = new Course(searchedCourse);
		boolean hasId = false;
		
		ResultSet resultOfSearch = courseDao.get(course, hasId);
				
		return resultOfSearch;
	}
	
	/**
	 * Show the name and the status of all courses registered
	 * @return the data produced by the given query
	 */
	public ResultSet showCourse(){
		
		ResultSet resultOfTheSelect;
		CourseDAO courseDao = new CourseDAO();		
		resultOfTheSelect = courseDao.getAll();
		
		return resultOfTheSelect;
		
	}
	
	/**
	 * Show the information of a course searched by user
	 * @param idCourse - The id of course to be searched
	 * @return the data produced by the given query
	 * @throws CourseException
	 */
	public ResultSet showCourse(int idCourse) throws CourseException{
		
		CourseDAO courseDao = new CourseDAO();
		Course course = new Course(idCourse);
		boolean hasId = true;
		ResultSet resultOfSearch = courseDao.get(course, hasId);
				
		return resultOfSearch;
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
