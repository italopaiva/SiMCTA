package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.PersonException;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;

public class Person extends Model{

	protected static final String NAMES_MUST_BE_ALPHA_CHARACTERS = "O nomes devem conter apenas caractares alfabéticos.";
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
	
	protected void setFatherName(String fatherName) throws PersonException {
		if(isNotEmpty(fatherName)){
			
			if(containsOnlyLettersAndSpaces(fatherName)){
				
				this.fatherName = fatherName;
			}
			else{
				throw new PersonException(NAMES_MUST_BE_ALPHA_CHARACTERS);
			}
		}
		else{
			this.fatherName = "";
		}
	}

	protected void setMotherName(String motherName) throws PersonException {
		if(containsOnlyLettersAndSpaces(motherName)){
			
			this.motherName = motherName;
		}
		else{
			throw new PersonException(NAMES_MUST_BE_ALPHA_CHARACTERS);
		}
	}
	
	protected void setName(String studentName) throws PersonException{
		
		if(containsOnlyLettersAndSpaces(studentName)){
			this.name = studentName;
		}
		else{
			throw new PersonException(NAMES_MUST_BE_ALPHA_CHARACTERS);
		}
	}

	protected void setSecondaryPhone(Phone secondaryPhone) throws PersonException{
		
		this.secondaryPhone = secondaryPhone;
	}

	protected void setPrincipalPhone(Phone principalPhone) throws PersonException{
		
		if(principalPhone != null){
			this.principalPhone = principalPhone;
		}
		else{
			throw new PersonException(PHONE_CANT_BE_NULL);
		}
	}

	protected void setAddress(Address address) throws PersonException{
		
		if(address != null){
			this.address = address;
		}
		else{
			throw new PersonException(ADDRESS_CANT_BE_NULL);
		}
	}

	protected void setRg(RG studentRg) throws PersonException{
		
		if(studentRg != null){
			this.rg = studentRg;
		}
		else{
			throw new PersonException(RG_CANT_BE_NULL);
		}
	}

	protected void setCpf(CPF studentCpf) throws PersonException{
		
		if(studentCpf != null){
			this.cpf = studentCpf;
		}
		else{
			throw new PersonException(CPF_CANT_BE_NULL);
		}
	}

	protected void setBirthdate(Date birthdate) throws PersonException{
		
		if(birthdate != null){
			this.birthdate = birthdate;
		}
		else{
			throw new PersonException(BIRTHDATE_CANT_BE_NULL);
		}
	}
	
	protected void setEmail(String studentEmail) throws PersonException{
		
		if(isNotEmpty(studentEmail)){
			
			String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
	        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(studentEmail);
	        boolean emailIsValid = matcher.matches();
	        
	        if(emailIsValid){
	        	this.email = studentEmail;
	        }
	        else{
	        	throw new PersonException(EMAIL_INVALID);
	        }
		}
		else{
			this.email = "";
		}
	}
	
	protected void setStatus(int status) throws PersonException {
		
		if(status == INACTIVE || status == ACTIVE){
			this.status = status;
		}
		else{
			throw new PersonException(STATUS_INVALID);
		}
	}
	
	public int getStatus() {
		return status;
	}

	public String getName(){
		return name;
	}

	public CPF getCpf(){
		return cpf;
	}

	public RG getRg(){
		return rg;
	}

	public Date getBirthdate(){
		return birthdate;
	}
	
	public String getEmail(){
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
