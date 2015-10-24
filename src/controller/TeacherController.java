package controller;

import dao.TeacherDAO;
import exception.StudentException;
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
	
	
	public void newTeacher(String teacherName, CPF teacherCpf, RG teacherRg, Date birthdate, 
							String email, Address address, Phone principalPhone, Phone secondaryPhone, 
							String motherName, String fatherName) throws StudentException{
		
		Teacher teacher;
		try {
			teacher = new Teacher(teacherName, teacherCpf, teacherRg, birthdate, email, address,
					 					  principalPhone, secondaryPhone, motherName, fatherName);
			teacherDAO.save(teacher);
		} 
		catch (StudentException e) {
			
			throw new StudentException(COULDNT_SAVE_TEACHER);
	
		}

	}

	public void setTeacherDAO(TeacherDAO teacherDAO){
		this.teacherDAO = teacherDAO;
	}
	
}
