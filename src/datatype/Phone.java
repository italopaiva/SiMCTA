package datatype;

import exception.PhoneException;
import model.Model;

public class Phone extends Model{
	
	private static final String PHONE_NUMBER_IS_INVALID = "O número do telefone informado é inválido.";
	private static final String DDD_NUMBER_IS_INVALID = "O número do DDD informado é inválido.";
	
	private static final int DDD_LENGTH = 2;
	private static final int NUMBER_LENGTH = 8;

	private String DDD;
	private String number;
	
	public Phone(String DDD, String number) throws PhoneException{
		setDDD(DDD);
		setNumber(number);
	}
	
	public Phone(String phone) throws PhoneException{
		String ddd = phone.substring(0,2);
		String number = phone.substring(2,10);
		
		setDDD(ddd);
		setNumber(number);
	}
	
	private void setDDD(String DDD) throws PhoneException{
		
		boolean DDDValidLength = DDD.length() == DDD_LENGTH;
		
		boolean validDDD = containsOnlyNumbers(DDD) && DDDValidLength;
		
		if(validDDD){
			this.DDD = DDD;
		}
		else{
			throw new PhoneException(DDD_NUMBER_IS_INVALID);
		}
		
	}
	
	private void setNumber(String number) throws PhoneException {
		
		boolean numberValidLength = number.length() == NUMBER_LENGTH;
		
		boolean validNumber = containsOnlyNumbers(number) && numberValidLength;
		
		if(validNumber){
			this.number = number;
		}
		else{
			throw new PhoneException(PHONE_NUMBER_IS_INVALID);
		}
	}

	public String getFormattedPhone(){
		
		String formattedPhone = "(";
		String ddd = getDDD();
		String phone = getNumber();
		
		formattedPhone += ddd + ") ";
		
		//Four first digits
		formattedPhone += phone.substring(0,4);
		formattedPhone += "-";
		
		//Last four digits
		formattedPhone += phone.substring(4,8);
				
		return formattedPhone;
		
	}
	
	public String getWholePhone(){
		
		String wholeNumber = getDDD() + getNumber();
		
		return wholeNumber;
	}
	
	public String getDDD(){
		return DDD;
	}

	public String getNumber(){
		return number;
	}	
}
