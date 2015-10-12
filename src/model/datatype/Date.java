package model.datatype;

import exception.DateException;
import model.Model;

public class Date extends Model{

	
	private static final int MIN_YEAR = 1900;
	private static final int MIN_MONTH = 1;
	private static final int MAX_MONTH = 12;
	private static final int MIN_DAY = 1;
	private static final int MAX_DAY = 31;
	private static final int FEBRUARY_BISSEXTILE_MAX_DAYS = 29;

	private static final String YEAR_INVALID = "O ano de nascimento informado é inválido";
	private static final String DAY_INVALID = "O dia de nascimento informado é inválido";
	private static final String DATE_INVALID = "A data de nascimento informada é inválida";
	private static final String MONTH_INVALID = "O mês de nascimento informado é inválido";;
	
	private Integer day;
	private Integer month;
	private Integer year;
	
	public Date(Integer day, Integer month, Integer year) throws DateException {
		setYear(year);
		setMonth(month);
		setDay(day);
	}
	
	private void setYear(Integer year) throws DateException {
		
		boolean yearIsValid = year != null && (year >= MIN_YEAR);
		
		if(yearIsValid){
			this.year = year;
		}
		else{
			throw new DateException(YEAR_INVALID);
		}
		
	}

	private void setMonth(Integer month) throws DateException {
		
		boolean rangeValid = month >= MIN_MONTH && month <= MAX_MONTH;
		boolean monthIsValid = month != null && rangeValid;
		
		if(monthIsValid){
			this.month = month;
		}
		else{
			throw new DateException(MONTH_INVALID);
		}
	
	}


	private void setDay(Integer day) throws DateException {
		
		boolean maxValid = false;
		Integer month = getMonth();
		
		switch(month){
		
		// Month with 31 days
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			maxValid = (day < MAX_DAY);
			break;
			
		// Month with 30 days
		case 4:
		case 6:
		case 9:
		case 11:
			maxValid = day < (MAX_DAY - 1);
			break;
		
		// February
		case 2:
			
			boolean yearIsBissextile = checkIfYearIsBissextile();
			
			if(yearIsBissextile){
				maxValid = day <= FEBRUARY_BISSEXTILE_MAX_DAYS;
			}
			else{
				maxValid = day <= (FEBRUARY_BISSEXTILE_MAX_DAYS - 1);
			}
		}
		
		boolean rangeValid = day >= MIN_DAY && maxValid;
		boolean dayIsValid = day != null && rangeValid;

		if(dayIsValid){
			this.day = day;
		}
		else{
			throw new DateException(DAY_INVALID);
		}
	}
	

	private boolean checkIfYearIsBissextile() {
		
		Integer year = getYear();
		boolean isBissextile = false;
		
		if(year % 4 == 0){
			String yearToBeCheck = year.toString();
			yearToBeCheck = yearToBeCheck.substring(1,4);
			if(yearToBeCheck != "00" || year % 400 == 0){
				isBissextile = true;
			}
			else{
				isBissextile = false;
			}
		}
		
		return isBissextile;
	}
	
	public String getFormattedDate(){
		
		String birthdate = getDay() + "/" ;
		birthdate+= getMonth() + "/";
		birthdate+= getYear();

		return birthdate;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public Integer getMonth() {
		return month;
	}

	public Integer getDay() {
		return day;
	}
}
