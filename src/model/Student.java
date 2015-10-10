package model;

import exception.StudentException;
import model.datatype.CPF;
import model.datatype.RG;
import model.datatype.Address;
import model.datatype.Phone;
import model.datatype.Date;

public class Student extends Model{
	
	private String studentName;
	private CPF studentCpf;
	private RG studentRg;
	private Date birthdate;
	private Address address;
	private Phone principalPhone;
	private Phone secondaryPhone;
	private String motherName;
	private String fatherName;
	
	
	public Student(String studentName, CPF studentCpf, RG studentRg,
			Date birthdate, Address address, Phone principalPhone,
			Phone secondaryPhone, String motherName, String fatherName) throws StudentException {
			setStudentName(studentName);
			setStudentCpf(studentCpf);
			setStudentRg(studentRg);
			setBirthdate(birthdate);
			setAddress(address);
			setPrincipalPhone(principalPhone);
			setSecondaryPhone(secondaryPhone);
			setMotherName(motherName);
			setFatherName(fatherName);
	}
	
	public Student(Date birth) throws StudentException{
		setBirthdate(birth);
	}

	private void setFatherName(String fatherName) {
		
	}

	private void setMotherName(String motherName) {
		
	}

	private void setSecondaryPhone(Phone secondaryPhone) {
		
	}

	private void setPrincipalPhone(Phone principalPhone) {
	
		
	}

	private void setAddress(Address address) {
		
		
	}

	private void setStudentRg(RG studentRg) {
		
	}

	private void setStudentCpf(CPF studentCpf2) {
		
	}

	private void setStudentName(String studentName2) {
		
	}

	private void setBirthdate(Date birthdate) throws StudentException{

		
	}
	
}
