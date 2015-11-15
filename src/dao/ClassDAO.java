package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import exception.ClassException;
import exception.DateException;
import model.Class;
import model.Course;
import model.Teacher;
import model.datatype.Date;

public class ClassDAO extends DAO {
	
	private static final String COULDNT_UPDATE_CLASS = "Não foi possível atualizar os dados da turma informada.";
	private static final String COULDNT_SAVE_CLASS = "Não foi possível abrir a nova turma";
	private static final String CLASS_ALREADY_EXISTS = "Essa turma já existe.";
	private static final String COULDNT_FIND_CLASS = "Não foi possível encontrar a turma.";

	private static final String CLASS_TABLE_NAME = "Class";
	private static final String ID_CLASS_COLUMN = "id_class";
	private static final String ID_COURSE_COLUMN = "id_course";
	private static final String TEACHER_CPF_COLUMN = "teacher_cpf";
	private static final String START_DATE_COLUMN = "startDate";
	private static final String END_DATE_COLUMN = "endDate";
	private static final String SHIFT_COLUMN = "shift";

	
	/**
	 * Save a class on the database
	 * @param classToSave - The class with the informations
	 * @throws ClassException
	 * @throws DateException
	 */
	public void save(Class classToSave) throws ClassException, DateException {
		
		String classId = classToSave.getClassId();
		Date startDate = classToSave.getStartDate();
		
		boolean existentClass = checkIfClassAlreadyExists(classId, startDate);

		if(!existentClass){
			String formattedStartDate = classToSave.getStartDate().getHyphenFormattedDate();
			Teacher teacher = classToSave.getTeacher();
			String teacherCpf = teacher.getCpf().getCpf();
			String shift = classToSave.getShift(); 
			Course course = classToSave.getCourse();
			Integer courseId = course.getId();
			String endDate = classToSave.getEndDate().getHyphenFormattedDate();
		
			try {
				String query = "INSERT INTO "+ CLASS_TABLE_NAME 
							 + "( " + START_DATE_COLUMN + "," + TEACHER_CPF_COLUMN + ", "
							 + SHIFT_COLUMN +","+ ID_COURSE_COLUMN + "," + ID_CLASS_COLUMN +","+ END_DATE_COLUMN +")";
				
				query += "VALUES ('"+ formattedStartDate +"','" + teacherCpf +"','" + shift +"','" + courseId +"','" + classId +"','" + endDate +"')"; 

				this.execute(query);
			}
			catch(SQLException e){
				throw new ClassException(COULDNT_SAVE_CLASS);
			}
		}
		else{
			throw new ClassException(CLASS_ALREADY_EXISTS);
		}
		
	}
	
	/**
	 * Check if the class to be save already exists on database
	 * @param classId - The id of the class to be checked
	 * @param startDate - The startDate of the class to be checked
	 * @return a boolean - TRUE if the class already exists, FALSE if the class doesn't exist
	 * @throws ClassException 
	 */
	private boolean checkIfClassAlreadyExists(String classId, Date startDate) throws ClassException {
		
		boolean existentClass = false;
		
		try {
			String query = "SELECT " + ID_CLASS_COLUMN + "," + START_DATE_COLUMN + " FROM " + CLASS_TABLE_NAME + " WHERE "
					   + ID_CLASS_COLUMN + " = '" + classId + "'"; 
			
			ResultSet result = this.search(query);

			if(result.next()){
				existentClass = true;
			}
			else{
				existentClass = false;
			}
			
		}
		catch(SQLException e){
			throw new ClassException(COULDNT_FIND_CLASS);
		}
		
		
		return existentClass;
	}

	/**
	 * Update a class on the database
	 * @param classToUpdate - The class with the new information
	 * @throws ClassException
	 */
	public void update(Class classToUpdate) throws ClassException{
		
		String classId = classToUpdate.getClassId();
		String startDate = classToUpdate.getStartDate().getHyphenFormattedDate();
		Teacher teacher = classToUpdate.getTeacher();
		String teacherCpf = teacher.getCpf().getCpf();
		String shift = classToUpdate.getShift(); 
		
		/* Check how to set the end date
			//Date endDate = classToUpdate.getEndDate(); 
		 */
		
		try {
			String query = "UPDATE "+ CLASS_TABLE_NAME 
						 + " SET "+ START_DATE_COLUMN +" = '"+ startDate +"', "
						 + TEACHER_CPF_COLUMN +" = '"+ teacherCpf +"', "
						 + SHIFT_COLUMN +" = '"+ shift +"' "
			 			 + "WHERE "+ ID_CLASS_COLUMN +" = '"+ classId +"'";
						
			this.execute(query);
		}
		catch(SQLException e){
			throw new ClassException(COULDNT_UPDATE_CLASS);
		}
	}

}
