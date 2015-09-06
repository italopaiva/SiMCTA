package dao;

import java.sql.SQLException;

import model.Student;

public class StudentDAO extends DAO {
	
	public StudentDAO(){}
	
	public boolean saveStudent(Student student){
		
		String query = "INSERT INTO student (name) VALUES (\""+student.getName()+"\"); ";
		
		boolean wasSaved = false;
		
		try{
			
			this.execute(query);
			wasSaved = true;
		}catch(SQLException caughtException){
			
			wasSaved = false;
		}
		
		return wasSaved;
	}
}
