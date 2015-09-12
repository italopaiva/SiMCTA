package controller;

import dao.CourseDAO;
import model.Course;

public class CourseController {
	
	public CourseController(){}
	
	/**
	 * Create a new course with the Course object specified
	 * @param course - the course to be created
	 * @return TRUE if the course was created or FALSE if it does not
	 */
	public boolean newCourse(Course course){
		
		CourseDAO courseDao = new CourseDAO();
		
		boolean wasSaved = courseDao.save(course);
		
		return wasSaved;
	}
}
