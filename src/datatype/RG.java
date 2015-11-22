package datatype;

import model.Model;
import exception.RGException;

public class RG extends Model{
	
	private static final String RG_NUMBER_IS_INVALID = "O número do RG informado é inválido.";
	private static final String ISSUING_INSTITUTION_IS_INVALID = "O órgão expedidor do RG deve conter apenas letras.";
	private static final String UF_MUST_BE_ONLY_2_LETTERS = "A UF do RG deve conter apenas 2 letras. Ex.: DF.";
	
	private static final int UF_LENGTH = 2;
	
	private String rgNumber;
	private String issuingInstitution;
	private String uf;
	
	public RG(String rgNumber, String issuingInstitution, String uf) throws RGException{
		
		setRgNumber(rgNumber);
		setIssuingInstitution(issuingInstitution);
		setUf(uf);
	}
	
	private void setRgNumber(String rgNumber) throws RGException{
		
		if(containsOnlyNumbers(rgNumber)){
			this.rgNumber = rgNumber;
		}else{
			throw new RGException(RG_NUMBER_IS_INVALID);
		}
	}

	private void setIssuingInstitution(String issuingInstitution) throws RGException{
		
		if(containsOnlyLetters(issuingInstitution)){
			this.issuingInstitution = issuingInstitution;
		}else{
			throw new RGException(ISSUING_INSTITUTION_IS_INVALID);
		}
	}

	private void setUf(String uf) throws RGException{
		
		boolean ufIsValid = containsOnlyLetters(uf) && uf.length() == UF_LENGTH;
		
		if(ufIsValid){
			this.uf = uf;
		}else{
			throw new RGException(UF_MUST_BE_ONLY_2_LETTERS);
		}
	}

	public String getFormattedRg(){
			
		String formattedRg;
		
		formattedRg = getRgNumber();
		formattedRg+= " " + getIssuingInstitution();
		formattedRg+= " " + getUf();
		
		return formattedRg;
		
	}
	
	public String getRgNumber() {
		return this.rgNumber;
	}

	public String getIssuingInstitution() {
		return this.issuingInstitution;
	}

	public String getUf() {
		return this.uf;
	}
}
