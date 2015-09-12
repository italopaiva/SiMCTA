package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Course;

public class CourseDAO extends DAO {
	
	// Course table on database constants
	private static final String TABLE_NAME = "Course";
	private static final String NAME_COLUMN = "course_name";
	private static final String DESCRIPTION_COLUMN = "description";
	private static final String DURATION_COLUMN = "duration";
	private static final String VALUE_COLUMN = "value";
	
	public boolean save(Course course){
		
		String courseName = course.getCourseName();
		String courseDescription = "lalal";
		Integer courseDuration =1;  
		Integer courseValue = 2;
		
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
	
	// Method used to search the informations of a course 
		public ResultSet get(Course course){
			
			ResultSet result;

			String courseName = course.getCourseName();
			
			String query = ("SELECT * FROM "+ TABLE_NAME + " WHERE " + NAME_COLUMN + " LIKE '%"  + courseName + "%'");
			try{
				Connection connection = this.connectToDB(); 
				PreparedStatement preparedStatement = connection.prepareStatement(query); 
				result = preparedStatement.executeQuery();
				
			}catch(SQLException caughtException){
				
				result = null;
			}
			
			return result;	
			
		}

		public ResultSet getAll() {
			ResultSet result;
			
			String query = ("SELECT * FROM "+ TABLE_NAME);
			try{
				Connection connection = this.connectToDB(); 
				PreparedStatement preparedStatement = connection.prepareStatement(query); 
				result = preparedStatement.executeQuery();
				
			}catch(SQLException caughtException){
				
				result = null;
			}
			
			return result;
		}

}
