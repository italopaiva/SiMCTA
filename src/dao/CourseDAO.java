package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Course;

public class CourseDAO extends DAO {
	
	// Course table on database constants
	private static final String TABLE_NAME = "Course";
	private static final String ID_COLUMN = "id_course";
	private static final String NAME_COLUMN = "course_name";
	private static final String DESCRIPTION_COLUMN = "description";
	private static final String DURATION_COLUMN = "duration";
	private static final String VALUE_COLUMN = "value";
	
	/**
	 * Save the informed course into the database
	 * @param course - a Course object with the course information to be saved
	 * @param hasId - inform if the Course object has an specific ID 
	 * @return TRUE if the course was saved on the database, or FALSE if it does not
	 */
	public boolean save(Course course, boolean hasId){
		
		String courseName = course.getCourseName();
		String courseDescription = course.getCourseDescription();
		Integer courseDuration = course.getCourseDuration();  
		Integer courseValue = course.getCourseValue();
		
		String query = "";
		if(hasId){
			
			int courseId = course.getCourseId();
			query = "INSERT INTO "+ TABLE_NAME + "(" + ID_COLUMN + ", " + NAME_COLUMN + ", "
					+ DESCRIPTION_COLUMN + ", " + DURATION_COLUMN + ", "
					+ VALUE_COLUMN +")";
			query += "VALUES('" + courseId + "', '" + courseName + "','" + courseDescription + "', '"
				  + courseDuration + "', '" + courseValue + "')";
		}else{
			
			query = "INSERT INTO "+ TABLE_NAME + "(" + NAME_COLUMN + ", "
					+ DESCRIPTION_COLUMN + ", " + DURATION_COLUMN + ", "
					+ VALUE_COLUMN +")";
			query += "VALUES('" + courseName + "','" + courseDescription + "', '"
				  + courseDuration + "', '" + courseValue + "')";
		}
	
		boolean wasSaved = false;
		
		try{
			
			this.execute(query);
			wasSaved  = true;
		}catch(SQLException caughtException){
			
			wasSaved = false;
		}
		
		return wasSaved;
	}
	
	/**
	 * Update a given course on the database
	 * @param courseId - The course to be updated
	 * @param course - A Course object with the course new data
	 * @return TRUE if the course was updated on database or FALSE if it does not
	 */
	public boolean update(Integer courseId, Course course){
		
		String courseDescription = course.getCourseDescription();
		Integer courseDuration = course.getCourseDuration();  
		Integer courseValue = course.getCourseValue();
		
		String query = "UPDATE "+ TABLE_NAME + " SET "
					   + DESCRIPTION_COLUMN + "='" + courseDescription + "', "
					   + DURATION_COLUMN + "='" + courseDuration + "', "
					   + VALUE_COLUMN + "='" + courseValue + "' "
					   + "WHERE " + ID_COLUMN + "='" + courseId + "'";
	
		boolean wasUpdated = false;
		
		try{
			
			this.execute(query);
			wasUpdated  = true;
		}catch(SQLException caughtException){
			
			wasUpdated = false;
		}
		
		return wasUpdated;
	}
	
	// Method used to search the informations of a course 
	public ResultSet get(Course course){
		
		ResultSet result;

		String courseName = course.getCourseName();
		
		String query = ("SELECT * FROM "+ TABLE_NAME + " WHERE " + NAME_COLUMN + " LIKE '%"  + courseName + "%'");
		
		try{
			
			result = this.search(query);
		}catch(SQLException caughtException){
			
			result = null;
		}
		
		return result;
	}

	public ResultSet getAll(){
		
		ResultSet result;
		
		String query = ("SELECT * FROM "+ TABLE_NAME);
		
		try {
			
			result = this.search(query);
		} catch (SQLException caughtException){
			
			result = null;
		}
		
		return result;
	}
}
