package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.CPFException;
import exception.StudentException;
import model.Student;
import model.datatype.CPF;

public class StudentDAO extends DAO {

	private static final String TABLE_NAME = "Student";
	private static final String NAME_COLUMN = "name_student";
	private static final String CPF_COLUMN = "cpf";

	public ArrayList<Student> get(String searchedStudentName) throws StudentException, CPFException {
		
		ResultSet resultOfTheSearch = null;
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_COLUMN + " LIKE \"%" + searchedStudentName + "%\""; 
		ArrayList<Student> foundStudents = new ArrayList<Student>();
		Student student = null;
		CPF studentCpf = null;
		
		try{
			resultOfTheSearch = this.search(query);
			
			while(resultOfTheSearch.next()){
				String studentName = (resultOfTheSearch.getString("name_student"));
				String cpf = (resultOfTheSearch.getString("cpf"));
				studentCpf = new CPF(cpf);
				student = new Student(studentName, studentCpf);
				foundStudents.add(student);
			}
			
		}
		catch(SQLException e){
			
		}

		return foundStudents;
	}
	
	public ResultSet get(CPF studentCpf) {
		
		ResultSet resultOfTheSearch = null;
		
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + CPF_COLUMN + "=" + studentCpf; 
		
		try{
			resultOfTheSearch = this.search(query);
		}
		catch(SQLException e){
			
		}
		return resultOfTheSearch;
	}

}
