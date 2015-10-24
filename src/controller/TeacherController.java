package controller;

import java.util.ArrayList;

import dao.TeacherDAO;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.TeacherException;
import model.Teacher;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;

public class TeacherController {

	private static final String COULDNT_SAVE_TEACHER = "Não foi possível cadastrar o professor";
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
	public void newTeacher(String teacherName, CPF teacherCpf, RG teacherRg, Date birthdate, 
							String email, Address address, Phone principalPhone, Phone secondaryPhone, 
							String motherName, String fatherName, String qualification) throws PersonException, TeacherException{
		
		Teacher teacher;
		try {
			teacher = new Teacher(teacherName, teacherCpf, teacherRg, birthdate, email, address,
					 					  principalPhone, secondaryPhone, motherName, fatherName, qualification);
			teacherDAO.save(teacher);
		} 
		catch (PersonException e) {
			
			throw new PersonException(COULDNT_SAVE_TEACHER);
	
		}

	}
	
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

	public void setTeacherDAO(TeacherDAO teacherDAO){
		this.teacherDAO = teacherDAO;
	}
	
}
