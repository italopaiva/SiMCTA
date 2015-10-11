package model;

import exception.StudentException;
import model.datatype.CPF;
import model.datatype.RG;
import model.datatype.Address;
import model.datatype.Phone;
import model.datatype.Date;

public class Student extends Model{
	
	private static final String NAMES_MUST_BE_ALPHA_CHARACTERS = "O nomes devem conter apenas caractares alfabéticos.";
	private static final String PHONE_CANT_BE_NULL = "Os telefones não podem ficar em branco.";
	private static final String ADDRESS_CANT_BE_NULL = "O endereço não pode estar em braco.";
	private static final String BIRTHDATE_CANT_BE_NULL = "A data de aniversário não pode estar em branco.";
	private static final String RG_CANT_BE_NULL = "O RG não pode estar em branco.";
	private static final String CPF_CANT_BE_NULL = "O CPF não pode estar em branco.";
	
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

	private void setFatherName(String fatherName) throws StudentException {
		
		if(fatherName != null && fatherName != ""){
			
			if(containsOnlyLettersAndSpaces(fatherName)){
				
				this.fatherName = fatherName;
			}
			else{
				throw new StudentException(NAMES_MUST_BE_ALPHA_CHARACTERS);
			}
		}
		else{
			this.fatherName = "";
		}
	}

	private void setMotherName(String motherName) throws StudentException {
		
		if(containsOnlyLettersAndSpaces(motherName)){
			
			this.motherName = motherName;
		}
		else{
			throw new StudentException(NAMES_MUST_BE_ALPHA_CHARACTERS);
		}
	}
	
	private void setStudentName(String studentName) throws StudentException{
		
		if(containsOnlyLettersAndSpaces(studentName)){
			this.studentName = studentName;
		}
		else{
			throw new StudentException(NAMES_MUST_BE_ALPHA_CHARACTERS);
		}
	}

	private void setSecondaryPhone(Phone secondaryPhone) throws StudentException{
		
		if(secondaryPhone != null){
			this.secondaryPhone = secondaryPhone;
		}
		else{
			throw new StudentException(PHONE_CANT_BE_NULL);
		}
	}

	private void setPrincipalPhone(Phone principalPhone) throws StudentException{
		
		if(principalPhone != null){
			this.principalPhone = principalPhone;
		}
		else{
			throw new StudentException(PHONE_CANT_BE_NULL);
		}
	}

	private void setAddress(Address address) throws StudentException{
		
		if(address != null){
			this.address = address;
		}
		else{
			throw new StudentException(ADDRESS_CANT_BE_NULL);
		}
	}

	private void setStudentRg(RG studentRg) throws StudentException{
		
		if(studentRg != null){
			this.studentRg = studentRg;
		}
		else{
			throw new StudentException(RG_CANT_BE_NULL);
		}
	}

	private void setStudentCpf(CPF studentCpf) throws StudentException{
		
		if(studentCpf != null){
			this.studentCpf = studentCpf;
		}
		else{
			throw new StudentException(CPF_CANT_BE_NULL);
		}
	}

	private void setBirthdate(Date birthdate) throws StudentException{
		
		if(birthdate != null){
			this.birthdate = birthdate;
		}
		else{
			throw new StudentException(BIRTHDATE_CANT_BE_NULL);
		}
	}

	public String getStudentName(){
		return studentName;
	}

	public CPF getStudentCpf(){
		return studentCpf;
	}

	public RG getStudentRg(){
		return studentRg;
	}

	public Date getBirthdate(){
		return birthdate;
	}

	public Address getAddress(){
		return address;
	}

	public Phone getPrincipalPhone(){
		return principalPhone;
	}

	public Phone getSecondaryPhone(){
		return secondaryPhone;
	}

	public String getMotherName(){
		return motherName;
	}

	public String getFatherName(){
		return fatherName;
	}
}

	