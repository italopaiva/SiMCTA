package controller;

import java.util.ArrayList;

import dao.TeacherDAO;
import datatype.Address;
import datatype.CPF;
import datatype.Date;
import datatype.Phone;
import datatype.RG;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.TeacherException;
import model.Teacher;

public class TeacherController {

	private static final String COULDNT_SAVE_TEACHER = "Não foi possível cadastrar o professor.";
	private static final String CANT_UPDATE_NULL_TEACHER = "Não é possível atualizar um professor nulo.";
	
	TeacherDAO teacherDAO;
	
	public TeacherController(){
		teacherDAO = new TeacherDAO();
	}
	
	/**
	 * Receives the data of the teacher to be registered
	 * @param teacherName - name of the teacher to be registered
	 * @param teacherCpf - cpf of the teacher to be registered
	 * @param teacherRg - rg of the teacher to be registered
	 * @param birthdate - birth date of the teacher to be registered
	 * @param email - email of the teacher to be registered (optional)
	 * @param address - address of the teacher to be registered
	 * @param principalPhone - principal phone of the teacher to be registered
	 * @param secondaryPhone - secondary phone of the teacher to be registered (optional)
	 * @param motherName - mother of the teacher to be registered
	 * @param fatherName - father of the teacher to be registered
	 * @param qualification 
	 * @throws PersonException
	 * @throws TeacherException 
	 */
	public Teacher newTeacher(String teacherName, CPF teacherCpf, RG teacherRg, Date birthdate, 
							String email, Address address, Phone principalPhone, Phone secondaryPhone, 
							String motherName, String fatherName, String qualification) throws PersonException, TeacherException{
		
		Teacher teacher;
		try {
			teacher = new Teacher(teacherName, teacherCpf, teacherRg, birthdate, email, address,
					 					  principalPhone, secondaryPhone, motherName, fatherName, qualification);
			teacherDAO.save(teacher);
		} 
		catch (PersonException e) {
			teacher = null;
			throw new PersonException(COULDNT_SAVE_TEACHER);
		}
		
		return teacher;

	}
	
	/**
	 * Get all teachers registered that are active
	 * @return an array with the teachers
	 * @throws TeacherException
	 */
	public ArrayList<Teacher> getTeachers() throws TeacherException{
		
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		try {
			teachers = teacherDAO.get();
		} 
		catch(PhoneException | CPFException | DateException
				| AddressException | RGException | TeacherException e){
			
			throw new TeacherException(e.getMessage());
		}
		
		return teachers;
	}

	/**
	 * Get all teachers registered found with the searched name
	 * @param searchedTeacher - the name entered by the user
	 * @return an array with the found teachers
	 * @throws TeacherException
	 * @throws PersonException
	 */
	public ArrayList<Teacher> getTeachers(String searchedTeacher) throws TeacherException, PersonException{
		
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();

		try {
			teachers = teacherDAO.get(searchedTeacher);
		} 
		catch(TeacherException e){
			
			throw new TeacherException(e.getMessage());
		}
		
		return teachers;
	}
	
	/**
	 * Get the data of the teacher selected by the user
	 * @param selectedTeacher - the 'cpf' of the selected teacher
	 * @return the data of the teacher selected
	 * @throws TeacherException
	 * @throws PersonException
	 */
	public Teacher getTeacher(CPF selectedTeacher) throws TeacherException, PersonException{
			
		Teacher teacher = null;
		try {
			teacher = teacherDAO.get(selectedTeacher);
		} 
		catch(PhoneException | CPFException | DateException
				| AddressException | RGException | TeacherException e){
			
			throw new TeacherException(e.getMessage());
		}
		
		return teacher;
	}
	
	public void setTeacherDAO(TeacherDAO teacherDAO){
		this.teacherDAO = teacherDAO;
	}

	/**
	 * Update the data passed
	 * @param teacher - an object with the current data
	 * @return the teacher altered
	 * @throws TeacherException 
	 * @throws PersonException 
	 */
	public Teacher updateTeacher(String teacherName, CPF teacherCpf,
			RG teacherRg, Date birthdate, String email, Address address,
			Phone principalPhone, Phone secondaryPhone, String motherName,
			String fatherName, String qualification) throws TeacherException {
		
		Teacher teacher; 
		try{
			teacher = new Teacher(teacherName, teacherCpf, teacherRg, birthdate, email, address, principalPhone, secondaryPhone, motherName, fatherName, qualification);

			teacher = teacherDAO.update(teacher);
		}
		catch(TeacherException | PersonException e){
			teacher = null;
			throw new TeacherException(e.getMessage());
		}

		return teacher;
	}
	
	/**
	 * Deactivate a teacher
	 * @param teacher - Teacher to be deactivated
	 * @throws TeacherException
	 */
	public void disableTeacher(Teacher teacher) throws TeacherException{
		
		if(teacher != null){
			updateTeacherStatus(teacher, Teacher.INACTIVE);
		}
		else{
			throw new TeacherException(CANT_UPDATE_NULL_TEACHER);
		}
	}
	
	/**
	 * Activate a teacher
	 * @param teacher - Teacher to be activated
	 * @throws TeacherException
	 */
	public void activateTeacher(Teacher teacher) throws TeacherException{
		
		if(teacher != null){
			updateTeacherStatus(teacher, Teacher.ACTIVE);
		}
		else{
			throw new TeacherException(CANT_UPDATE_NULL_TEACHER);
		}
	}
	
	/**
	 * Change the status of a teacher
	 * @param teacher - Teacher to change the status
	 * @param newStatus - New status to be setted to the teacher
	 * @throws TeacherException
	 */
	private void updateTeacherStatus(Teacher teacher, int newStatus) throws TeacherException{
		
		teacherDAO.updateStatus(teacher, newStatus);
	}
}
