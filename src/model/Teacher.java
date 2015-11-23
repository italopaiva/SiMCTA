package model;

import datatype.Address;
import datatype.CPF;
import datatype.Date;
import datatype.Phone;
import datatype.RG;
import exception.PersonException;
import exception.TeacherException;

public class Teacher extends Person{

	private String qualification;
	
	public Teacher(String teacherName, CPF teacherCpf, RG teacherRg,
				   Date birthdate, String email, Address address,
			       Phone principalPhone, Phone secondaryPhone, String motherName,
			       String fatherName, String qualification) throws PersonException, TeacherException{
		setName(teacherName);
		setCpf(teacherCpf);
		setRg(teacherRg);
		setBirthdate(birthdate);
		setEmail(email);
		setAddress(address);
		setPrincipalPhone(principalPhone);
		setSecondaryPhone(secondaryPhone);
		setMotherName(motherName);
		setFatherName(fatherName);
		setQualification(qualification);
	}
	
	public Teacher(String teacherName, CPF teacherCpf, RG teacherRg,
			   Date birthdate, String email, Address address,
		       Phone principalPhone, Phone secondaryPhone, String motherName,
		       String fatherName, String qualification, int status) throws PersonException, TeacherException{
		setName(teacherName);
		setCpf(teacherCpf);
		setRg(teacherRg);
		setBirthdate(birthdate);
		setEmail(email);
		setAddress(address);
		setPrincipalPhone(principalPhone);
		setSecondaryPhone(secondaryPhone);
		setMotherName(motherName);
		setFatherName(fatherName);
		setQualification(qualification);
		setStatus(status);
	}

	public Teacher(String teacherName, CPF teacherCpf) throws PersonException {
		setName(teacherName);
		setCpf(teacherCpf);
	}
	
	public Teacher(CPF teacherCpf) throws PersonException {
		setCpf(teacherCpf);
	}

	private void setQualification(String qualification) throws TeacherException{
		
		if(containsOnlyLettersAndSpaces(qualification)){
			
			this.qualification = qualification;
		}
		else{
			throw new TeacherException(NAMES_MUST_BE_ALPHA_CHARACTERS);
		}
	}
	
	public String getQualification(){
		return qualification;
	}

}
