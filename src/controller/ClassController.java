package controller;

import java.util.ArrayList;

import dao.ClassDAO;
import dao.CourseDAO;
import datatype.CPF;
import datatype.Date;
import exception.CPFException;
import exception.ClassException;
import exception.CourseException;
import exception.DateException;
import exception.PersonException;

import exception.TeacherException;
import model.Class;
import model.Course;
import model.Teacher;

public class ClassController {
	
	private static final String INVALID_TEACHER = "O professor informado não é válido.";
	private static final String INVALID_COURSE = "O curso informado não é válido.";
	private static final String COULDNT_SAVE_CLASS = "Não foi possível cadastrar a turma.";
	private static final String INVALID_CLASS = "Turma inválida";
	private static final String COULDNT_FIND_CLASS = "Não foi possível encontrar a turma.";

	private static final int CLOSED_CLASS = 0;

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
	public Class newClass(CPF teacherCpf, String shift,
			Date startDate, Integer courseId) throws ClassException, DateException {
		
		Teacher teacher = null;
		Course course = null;
		Class classToReturn = null;
		try {
			
			teacher = new Teacher(teacherCpf);
			
			course = new Course(courseId);
			CourseDAO courseDAO = new CourseDAO();
			course = courseDAO.get(course, true);
			
			Class classToSave = new Class(startDate, shift, teacher, course);
			Date endDate = classToSave.getEndDate();
			String classId = classToSave.getClassId();
			classToReturn = new Class(classId, startDate, endDate, shift, teacher, course);
			classDAO.save(classToSave);
		} 
		catch(PersonException e1){
			throw new ClassException(INVALID_TEACHER);
		}
		catch(CourseException e){
			throw new ClassException(INVALID_COURSE);
		}
		catch(ClassException e) {
			throw new ClassException(e.getMessage());
		}
		
		return classToReturn;
		
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

	public void setClassDAO(ClassDAO classDao){
		this.classDAO = classDao;
	}

	/**
	 * Used to close the class
	 * @param enrolledClass - class to close
	 * @throws ClassException 
	 */
	public void closeClass(Class enrolledClass) throws ClassException {
		
		int newStatus = CLOSED_CLASS;
		
		if(enrolledClass != null){
			classDAO.update(enrolledClass, newStatus);
		}
		else{
			throw new ClassException(INVALID_CLASS);
		}
		
	}
	/**
	 * Used to search classes by the code
	 * @throws ClassException 
	 */
	public ArrayList<Class> searchClass(String code) throws ClassException{
		
		try {
			return classDAO.searchClassByCode(code);
		} catch (ClassException | CPFException | TeacherException
				| PersonException | DateException e) {
			throw new ClassException(COULDNT_FIND_CLASS + " (" + e.getMessage() + ")");
		}
		
	}
	
	public Class getClass(String classId){
		return classDAO.getClass(classId);
	}
	
	/**
	 * Used to search classes by the courseId, teacherCPF and  shift
	 * @throws ClassException 
	 */
	public ArrayList<Class> getClasses(Integer courseId, String teacherCPF, String shift) throws ClassException{
		try{
			return classDAO.getClasses(courseId, teacherCPF, shift);
		} catch (Exception e){
			throw new ClassException(COULDNT_FIND_CLASS);
		}
		
	}
	
}
