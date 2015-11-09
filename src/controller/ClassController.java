package controller;

import dao.ClassDAO;
import exception.ClassException;
import exception.PersonException;
import model.datatype.CPF;
import model.datatype.Date;
import model.Class;
import model.Teacher;

public class ClassController {
	
	private static final String INVALID_TEACHER = "O professor informado não é válido.";
	
	private ClassDAO classDAO;
	
	public ClassController(){
		classDAO = new ClassDAO();
	}
	
	/**
	 * Update a given class with the given information
	 * @param classId - The class to be updated
	 * @param teacherCpf - New teacher of the class
	 * @param shift - New shift of the class
	 * @param startDate - New startDate of the class
	 * @throws ClassException
	 */
	public void updateClass(String classId, CPF teacherCpf, String shift, Date startDate) throws ClassException{
		
		try{
			
			Teacher teacher = new Teacher(teacherCpf);
			
			Class newClass = new Class(classId, startDate, shift, teacher);
			
			classDAO.update(newClass);
		}
		catch (PersonException caughtException){			
			throw new ClassException(INVALID_TEACHER);
		}
		catch (ClassException caughtException){	
			throw caughtException;
		}
	}
}
