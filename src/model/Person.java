package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.StudentException;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;

public class Person extends Model{

	private static final String NAMES_MUST_BE_ALPHA_CHARACTERS = "O nomes devem conter apenas caractares alfabéticos.";
	private static final String PHONE_CANT_BE_NULL = "Os telefones não podem ficar em branco.";
	private static final String ADDRESS_CANT_BE_NULL = "O endereço não pode estar em braco.";
	private static final String BIRTHDATE_CANT_BE_NULL = "A data de aniversário não pode estar em branco.";
	private static final String RG_CANT_BE_NULL = "O RG não pode estar em branco.";
	private static final String CPF_CANT_BE_NULL = "O CPF não pode estar em branco.";
	private static final String EMAIL_INVALID = "O e-mail informado é inválido.";
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	private static final String STATUS_INVALID = "O status deve ser 0 ou 1";
	
	private String name;
	private CPF cpf;
	private RG rg;
	private Date birthdate;
	private String email;
	private Address address;
	private Phone principalPhone;
	private Phone secondaryPhone;
	private String motherName;
	private String fatherName;
	private int status;
	
	protected void setFatherName(String fatherName) throws StudentException {
		
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

	protected void setMotherName(String motherName) throws StudentException {
		
		if(containsOnlyLettersAndSpaces(motherName)){
			
			this.motherName = motherName;
		}
		else{
			throw new StudentException(NAMES_MUST_BE_ALPHA_CHARACTERS);
		}
	}
	
	protected void setStudentName(String studentName) throws StudentException{
		
		if(containsOnlyLettersAndSpaces(studentName)){
			this.name = studentName;
		}
		else{
			throw new StudentException(NAMES_MUST_BE_ALPHA_CHARACTERS);
		}
	}

	protected void setSecondaryPhone(Phone secondaryPhone) throws StudentException{
		
		this.secondaryPhone = secondaryPhone;
	}

	protected void setPrincipalPhone(Phone principalPhone) throws StudentException{
		
		if(principalPhone != null){
			this.principalPhone = principalPhone;
		}
		else{
			throw new StudentException(PHONE_CANT_BE_NULL);
		}
	}

	protected void setAddress(Address address) throws StudentException{
		
		if(address != null){
			this.address = address;
		}
		else{
			throw new StudentException(ADDRESS_CANT_BE_NULL);
		}
	}

	protected void setStudentRg(RG studentRg) throws StudentException{
		
		if(studentRg != null){
			this.rg = studentRg;
		}
		else{
			throw new StudentException(RG_CANT_BE_NULL);
		}
	}

	protected void setStudentCpf(CPF studentCpf) throws StudentException{
		
		if(studentCpf != null){
			this.cpf = studentCpf;
		}
		else{
			throw new StudentException(CPF_CANT_BE_NULL);
		}
	}

	protected void setBirthdate(Date birthdate) throws StudentException{
		
		if(birthdate != null){
			this.birthdate = birthdate;
		}
		else{
			throw new StudentException(BIRTHDATE_CANT_BE_NULL);
		}
	}
	
	protected void setStudentEmail(String studentEmail) throws StudentException{
		
		if(isNotEmpty(studentEmail)){
			
			String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
	        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(studentEmail);
	        boolean emailIsValid = matcher.matches();
	        
	        if(emailIsValid){
	        	this.email = studentEmail;
	        }
	        else{
	        	throw new StudentException(EMAIL_INVALID);
	        }
		}
		else{
			this.email = "";
		}
	}
	
	protected void setStatus(int status) throws StudentException {
		
		if(status == INACTIVE || status == ACTIVE){
			this.status = status;
		}
		else{
			throw new StudentException(STATUS_INVALID);
		}
	}
	
	public int getStatus() {
		return status;
	}

	public String getStudentName(){
		return name;
	}

	public CPF getStudentCpf(){
		return cpf;
	}

	public RG getStudentRg(){
		return rg;
	}

	public Date getBirthdate(){
		return birthdate;
	}
	
	public String getStudentEmail(){
		return email;
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
