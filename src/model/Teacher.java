package model;

import exception.StudentException;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;

public class Teacher extends Person{

	public Teacher(String teacherName, CPF teacherCpf, RG teacherRg,
				   Date birthdate, String email, Address address,
			       Phone principalPhone, Phone secondaryPhone, String motherName,
			       String fatherName) throws StudentException{
		setStudentName(teacherName);
		setStudentCpf(teacherCpf);
		setStudentRg(teacherRg);
		setBirthdate(birthdate);
		setStudentEmail(email);
		setAddress(address);
		setPrincipalPhone(principalPhone);
		setSecondaryPhone(secondaryPhone);
		setMotherName(motherName);
		setFatherName(fatherName);
	}
	
	
	
	

}
