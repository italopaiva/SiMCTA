package model;

import java.util.Date;

import model.datatype.CPF;
import model.datatype.RG;
import model.datatype.Address;
import model.datatype.Phone;

public class Student {
	
	private String studentName;
	private CPF studentCpf;
	private RG studentRg;
	private Date birthdate;
	private Address address;
	private Phone principalPhone;
	private Phone secondaryPhone;
	private String motherName;
	private String fatherName;
}
