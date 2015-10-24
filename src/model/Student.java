package model;

import exception.StudentException;
import model.datatype.CPF;
import model.datatype.RG;
import model.datatype.Address;
import model.datatype.Phone;
import model.datatype.Date;

public class Student extends Person{
	
	public Student(String studentName, CPF studentCpf, RG studentRg,
			Date birthdate, String studentEmail, Address address, Phone principalPhone,
			Phone secondaryPhone, String motherName, String fatherName, int status) throws StudentException {
			
		setStudentName(studentName);
		setStudentCpf(studentCpf);
		setStudentRg(studentRg);
		setBirthdate(birthdate);
		setStudentEmail(studentEmail);
		setAddress(address);
		setPrincipalPhone(principalPhone);
		setSecondaryPhone(secondaryPhone);
		setMotherName(motherName);
		setFatherName(fatherName);
		setStatus(status);
	}
	
	public Student(String studentName, CPF studentCpf) throws StudentException {
		setStudentName(studentName);
		setStudentCpf(studentCpf);
	}



}

	