package dao;

import java.sql.SQLException;

import exception.StudentClassException;
import model.StudentClass;

public class StudentClassDAO extends DAO {

	private static final String COULDNT_ENROLL_STUDENT_TO_CLASS = "Não foi possível associar este aluno para esta turma.";
	
	private static final String ID_CLASS_COLUMN = "id_class";
	private static final String STUDENT_CPF_COLUMN = "cpf";
	
	public void enrollStudentInClass(StudentClass studentClass) throws StudentClassException{
		
		String classId = studentClass.getEnrolledClass().getClassId();
		String studentCpf = studentClass.getStudent().getCpf().getCpf();
		
		String query = "";
		query += "INSERT INTO StudentClass("+ ID_CLASS_COLUMN +", "+ STUDENT_CPF_COLUMN +") ";
		query += "VALUES('"+ classId +"', '"+ studentCpf +"')";
				
		try{
			this.execute(query);
		}
		catch(SQLException e){
			throw new StudentClassException(COULDNT_ENROLL_STUDENT_TO_CLASS);
		}
	}
	
	
}
