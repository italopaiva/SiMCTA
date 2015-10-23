package controller;

import dao.TeacherDAO;
import model.Teacher;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;

public class TeacherController {

	TeacherDAO teacherDAO;
	
	public TeacherController(){
		teacherDAO = new TeacherDAO();
	}
	
	
	public void newTeacher(String teacherName, CPF teacherCpf, RG teacherRg, Date birthdate, String email, Address address, Phone principalPhone, Phone secondaryPhone, String motherName, String fatherName){
		
		Teacher teacher = new Teacher(teacherName, teacherCpf, teacherRg, birthdate, email, address,
				 					  principalPhone, secondaryPhone, motherName, fatherName);
		teacherDAO.save(teacher);

	}

	public void setTeacherDAO(TeacherDAO teacherDAO){
		this.teacherDAO = teacherDAO;
	}
	
}
