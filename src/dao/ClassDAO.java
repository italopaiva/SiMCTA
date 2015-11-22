package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hamcrest.core.IsNull;

import controller.CourseController;
import controller.TeacherController;
import exception.CPFException;
import exception.ClassException;
import exception.DateException;
import exception.PersonException;
import exception.TeacherException;
import model.Class;
import model.Course;
import model.Teacher;
import model.datatype.CPF;
import model.datatype.Date;

public class ClassDAO extends DAO {
	
	private static final String COULDNT_UPDATE_CLASS = "Não foi possível atualizar os dados da turma informada.";
	private static final String COULDNT_SAVE_CLASS = "Não foi possível abrir a nova turma";
	private static final String CLASS_ALREADY_EXISTS = "Essa turma já existe.";
	private static final String COULDNT_FIND_CLASS = "Não foi possível encontrar a turma.";
	private static final String COULDNT_CLOSE_CLASS = "Não foi possível fechar a turma.";
	private static final String INVALID_STATUS = "Status inválido";

	private static final String CLASS_TABLE_NAME = "Class";
	private static final String ID_CLASS_COLUMN = "id_class";
	private static final String ID_COURSE_COLUMN = "id_course";
	private static final String TEACHER_CPF_COLUMN = "teacher_cpf";
	private static final String START_DATE_COLUMN = "startDate";
	private static final String END_DATE_COLUMN = "endDate";
	private static final String SHIFT_COLUMN = "shift";
	private static final String STATUS_COLUMN = "status";

	private static final int OPEN_CLASS = 1;
	private static final int CLOSED_CLASS = 0;
	
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
							 + SHIFT_COLUMN +","+ ID_COURSE_COLUMN + "," + ID_CLASS_COLUMN +","+ END_DATE_COLUMN +","+ STATUS_COLUMN +")";
				
				query += "VALUES ('"+ formattedStartDate +"','" + teacherCpf +"','" + shift +"','" + courseId +"','" + classId +"','" + endDate +"', '"+ OPEN_CLASS +"')"; 

				this.execute(query);
			}
			catch(SQLException e){
				System.out.print(e.getMessage());
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

	/**
	 * Update a status class to CLOSED
	 * @param enrolledClass - the class to be update 
	 * @param newStatus - the status
	 * @throws ClassException 
	 */
	public void update(Class enrolledClass, int newStatus) throws ClassException {
		
		String classId = enrolledClass.getClassId();
		
		if(newStatus == CLOSED_CLASS){
			try {
				String query = "UPDATE "+ CLASS_TABLE_NAME 
							 + " SET "+ STATUS_COLUMN +" = " + newStatus
				 			 + " WHERE "+ ID_CLASS_COLUMN +" = '"+ classId +"'";
							
				this.execute(query);
			}
			catch(SQLException e){
				throw new ClassException(COULDNT_CLOSE_CLASS);
			}	
		}
		else{
			throw new ClassException(INVALID_STATUS);
		}
	}
	/**
	 * Get a class by the code(classId)
	 * @param classId
	 * @throws DateException 
	 * @throws PersonException 
	 * @throws TeacherException 
	 * @throws CPFException 
	 * @throws ClassException
	 * @return Class
	 */
	public Class getClassByCode(int classId) throws ClassException, CPFException, TeacherException, PersonException, DateException{
		
		ResultSet resultSet = null;
		
		String query = "SELECT * FROM " + CLASS_TABLE_NAME + " WHERE " + ID_CLASS_COLUMN + " = " + classId ;
		
		Class cls = null;
		
		try {
			resultSet = this.search(query);
			
			if (!resultSet.next()){
				throw new ClassException(COULDNT_FIND_CLASS);
			} 
			else {
				while(resultSet.next()){
					
					cls = getClassByResultSet(resultSet);
									
				}
			}
		}
		finally {
			return cls;
		}		
	}
	/**
	 * 
	 * @param courseId
	 * @param teacherCPF
	 * @param shift
	 * @return
	 */
	public ArrayList<Class> getClass(Integer courseId, String teacherCPF, String shift){
		
		String query = null;
		
		if (courseId != null && teacherCPF != null && shift != null){
			query = "SELECT * FROM " + CLASS_TABLE_NAME + " WHERE " + ID_COURSE_COLUMN + " = "+ courseId + " AND " + 
							TEACHER_CPF_COLUMN + " = " + teacherCPF + " AND " + SHIFT_COLUMN + " = " + shift;
		} else {
			if (courseId != null && teacherCPF != null){
				query = "SELECT * FROM " + CLASS_TABLE_NAME + " WHERE " + 
								ID_COURSE_COLUMN + " = "+ courseId + " AND " + 
								TEACHER_CPF_COLUMN + " = " + teacherCPF;
			} else if(courseId != null && shift != null) {
				query = "SELECT * FROM " + CLASS_TABLE_NAME + " WHERE " + 
								ID_COURSE_COLUMN + " = "+ courseId + " AND " + 
								SHIFT_COLUMN + " = " + shift;
			} else if(teacherCPF != null && shift != null) {
				query = "SELECT * FROM " + CLASS_TABLE_NAME + " WHERE " + 
								TEACHER_CPF_COLUMN + " = " + teacherCPF + " AND " + 
								SHIFT_COLUMN + " = " + shift;
			} else {
				if(courseId != null){
					query = "SELECT * FROM " + CLASS_TABLE_NAME + " WHERE " + 
									ID_COURSE_COLUMN + " = "+ courseId;
				} else if (teacherCPF != null){
					query = "SELECT * FROM " + CLASS_TABLE_NAME + " WHERE " + 
									TEACHER_CPF_COLUMN + " = " + teacherCPF;
				} else {
					query = "SELECT * FROM " + CLASS_TABLE_NAME + " WHERE " + 
									SHIFT_COLUMN + " = " + shift;
				}
			}
			
		}
		
		ResultSet resultSet = null;
		ArrayList<Class> classes = null;
		
		try {
			resultSet = this.search(query);
			
			if (!resultSet.next()){
				throw new ClassException(COULDNT_FIND_CLASS);
			} 
			else {
				while(resultSet.next()){
					classes.add(getClassByResultSet(resultSet));
				}
			}
		} finally {
			
			return classes;
		}
		
	}
	
	/**
	 * Get a Class Object from a valid resulSet
	 * @param resultSet
	 * @return
	 * @throws PersonException 
	 * @throws TeacherException 
	 * @throws CPFException 
	 * @throws ClassException 
	 * @throws SQLException 
	 * @throws DateException 
	 */
	public Class getClassByResultSet(ResultSet resultSet) throws ClassException, CPFException, TeacherException, PersonException, SQLException, DateException{
		
		String classId_ = resultSet.getString(ID_CLASS_COLUMN);
		int courseId = resultSet.getInt(ID_COURSE_COLUMN);
		String teacherCPF = resultSet.getString(TEACHER_CPF_COLUMN);
		Date classOpened = new Date(resultSet.getString(OPEN_CLASS));
		Date classClosed = new Date(resultSet.getString(CLOSED_CLASS));
		String shift = resultSet.getString(SHIFT_COLUMN);
		Integer classStatus = resultSet.getInt(STATUS_COLUMN);
		
		Class cls = new Class(classId_, classOpened, classClosed, shift, getTeacher(teacherCPF), getCourse(courseId), classStatus);
		
		return cls;
	}
	
	public Teacher getTeacher(String cpf) throws CPFException, TeacherException, PersonException{
		CPF teacherCpf = new CPF(cpf);
		TeacherController teacherController = new TeacherController();
		return teacherController.getTeacher(teacherCpf);
	}
	
	public Course getCourse(int courseId){
		CourseController courseController = new CourseController();
		return courseController.get(courseId);
	}
	

}
