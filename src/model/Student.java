package model;

import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;
import exception.PersonException;

public class Student extends Person{
	
	public Student(String studentName, CPF studentCpf, RG studentRg,
			Date birthdate, String studentEmail, Address address, Phone principalPhone,
			Phone secondaryPhone, String motherName, String fatherName, int status) throws PersonException {
			
		setName(studentName);
		setCpf(studentCpf);
		setRg(studentRg);
		setBirthdate(birthdate);
		setEmail(studentEmail);
		setAddress(address);
		setPrincipalPhone(principalPhone);
		setSecondaryPhone(secondaryPhone);
		setMotherName(motherName);
		setFatherName(fatherName);
		setStatus(status);
	}
	
	public Student(String studentName, Date birthdate, String studentEmail, Address address, Phone principalPhone,
			Phone secondaryPhone, String motherName, String fatherName, int status) throws PersonException {
			
		setName(studentName);
		setBirthdate(birthdate);
		setEmail(studentEmail);
		setAddress(address);
		setPrincipalPhone(principalPhone);
		setSecondaryPhone(secondaryPhone);
		setMotherName(motherName);
		setFatherName(fatherName);
		setStatus(status);
	}
	
	public Student(String studentName, CPF studentCpf) throws PersonException {
		setName(studentName);
		setCpf(studentCpf);
	}

	public Student(CPF studentCpf) throws PersonException{
		setCpf(studentCpf);
	}
}

	