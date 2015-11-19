package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.StudentClassException;
import exception.StudentException;
import model.Class;
import model.Student;
import model.StudentClass;
import model.datatype.CPF;

public class StudentClassDAO extends DAO {

	private static final String COULDNT_ENROLL_STUDENT_TO_CLASS = "Não foi possível associar este aluno para esta turma.";
	private static final String COULDNT_FIND_STUDENT_OF_CLASS = "Não foi possível encontrar os estudantes dessa turma.";
	
	private static final String STUDENT_CLASS_TABLE = "StudentClass";
	private static final String STUDENT_TABLE = "Student";
	private static final String ID_CLASS_COLUMN = "id_class";
	private static final String STUDENT_CPF_COLUMN = "cpf";
	private static final String ABSCENCE_COLUMN = "abscence";
	private static final String GRADE_COLUMN = "grade";
	private static final String SITUATION_COLUMN = "situation";
	
	public void enrollStudentInClass(StudentClass studentClass) throws StudentClassException{
		
		String classId = studentClass.getEnrolledClass().getClassId();
		String studentCpf = studentClass.getStudent().getCpf().getCpf();
		
		String query = "";
		query += "INSERT INTO " + STUDENT_CLASS_TABLE + " ("+ ID_CLASS_COLUMN +", "+ STUDENT_CPF_COLUMN +") ";
		query += "VALUES('"+ classId +"', '"+ studentCpf +"')";
				
		try{
			this.execute(query);
		}
		catch(SQLException e){
			throw new StudentClassException(COULDNT_ENROLL_STUDENT_TO_CLASS);
		}
	}

	public ArrayList<Student> get(Class enrolledClass) throws StudentClassException, CPFException {
		
		String enrolledClassId = enrolledClass.getClassId();
				
		ArrayList<Student> students = new ArrayList<Student>();
		
		String query = "";
		query += "SELECT " + STUDENT_CPF_COLUMN + " FROM " + STUDENT_CLASS_TABLE;
		query += " WHERE " + ID_CLASS_COLUMN + " = '" + enrolledClassId + "'";
		
		try{
			ResultSet resultSelectStudentClass = this.search(query);
			
			while(resultSelectStudentClass.next()){
				String cpf = resultSelectStudentClass.getString(STUDENT_CPF_COLUMN);
				CPF studentCpf = new CPF(cpf);
				
				StudentDAO studentDao = new StudentDAO();
				Student student = null;
				try {
					student = studentDao.get(studentCpf);
				} 
				catch (PhoneException | DateException | AddressException
						| RGException | StudentException | PersonException e) {
					throw new StudentClassException(COULDNT_FIND_STUDENT_OF_CLASS);
				}
				
				students.add(student);
			}			
		}
		catch(SQLException e){
			throw new StudentClassException(COULDNT_FIND_STUDENT_OF_CLASS);
		}

		return students;
	
	}
	
	
}
