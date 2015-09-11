package dao;

import java.sql.SQLException;

import model.Course;

public class CourseDAO extends DAO {
	
	// Course table on database constants
	private static final String TABLE_NAME = "course";
	private static final String NAME_COLUMN = "name";
	private static final String DESCRIPTION_COLUMN = "description";
	private static final String DURATION_COLUMN = "duration";
	private static final String VALUE_COLUMN = "value";
	
	public boolean save(Course course){
		
		String courseName = course.getCourseName();
		String courseDescription = course.getCourseDescription();
		Integer courseDuration = course.getCourseDuration();
		Integer courseValue = course.getCourseValue();
		
		String query = "INSERT INTO "+ TABLE_NAME + "(" + NAME_COLUMN + ", "
						+ DESCRIPTION_COLUMN + ", " + DURATION_COLUMN + ", "
						+ VALUE_COLUMN +")";
		query += "VALUES('" + courseName + "','" + courseDescription + "', '"
				 + courseDuration + "', '" + courseValue + "')";
		
		boolean wasSaved = false;
		
		try{
			
			this.execute(query);
			wasSaved  = true;
		}catch(SQLException caughtException){
			
			wasSaved = false;
		}
		
		return wasSaved;
	}
}
