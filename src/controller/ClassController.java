package controller;

import dao.ClassDAO;
import dao.CourseDAO;
import exception.ClassException;
import exception.CourseException;
import exception.DateException;
import exception.PersonException;
import model.datatype.CPF;
import model.datatype.Date;
import model.Class;
import model.Course;
import model.Teacher;

public class ClassController {
	
	private static final String INVALID_TEACHER = "O professor informado não é válido.";
	private static final String INVALID_COURSE = "O curso informado não é válido.";

	private ClassDAO classDAO;

	
	public ClassController(){
		classDAO = new ClassDAO();
	}
	
	/**
	 * Create a new class with the given information
	 * @param teacherCpf - The teacher of the class
	 * @param shift - The shift of the class
	 * @param startDate - The startDate of the class
	 * @param courseId - The course of the class
	 * @throws ClassException 
	 * @throws DateException 
	 */
	public void newClass(CPF teacherCpf, String shift,
			Date startDate, Integer courseId) throws ClassException, DateException {
		
		Teacher teacher = null;
		Course course = null;
		try {
			
			teacher = new Teacher(teacherCpf);
			
			course = new Course(courseId);
			CourseDAO courseDAO = new CourseDAO();
			course = courseDAO.get(course, true);
			
			Class classToSave = new Class(startDate, shift, teacher, course);
			classDAO.save(classToSave);

		} 
		catch(PersonException e1){
			throw new ClassException(INVALID_TEACHER);
		}
		catch(CourseException e){
			throw new ClassException(INVALID_COURSE);
		}
		catch(ClassException e) {
	
		}
		
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
