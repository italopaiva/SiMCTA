package dao;

import java.sql.SQLException;

import exception.ClassException;
import model.Class;
import model.Teacher;
import model.datatype.Date;

public class ClassDAO extends DAO {
	
	private static final String COULDNT_UPDATE_CLASS = "Não foi possível atualizar os dados da turma informada.";
	
	private static final String CLASS_TABLE_NAME = "Class";
	private static final String ID_CLASS_COLUMN = "id_class";
	private static final String ID_COURSE_COLUMN = "id_course";
	private static final String TEACHER_CPF_COLUMN = "teacher_cpf";
	private static final String START_DATE_COLUMN = "startDate";
	private static final String END_DATE_COLUMN = "endDate";
	private static final String SHIFT_COLUMN = "shift";
	
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
			
			System.out.println(query);
			
			this.execute(query);
		}
		catch(SQLException e){
			throw new ClassException(COULDNT_UPDATE_CLASS);
		}
	}
}
