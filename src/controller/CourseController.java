package controller;

import dao.CourseDAO;
import model.Course;

public class CourseController {
	
	public CourseController(){}
	
	public boolean newCourse(Course course){
		
		CourseDAO courseDao = new CourseDAO();
		
		boolean wasSaved = courseDao.save(course);
		
		return wasSaved;
	}
}
