package datatype;

import exception.CPFException;

public class CPF {
	
	private static final String CPF_CANT_BE_EMPTY = "O CPF não pode estar em branco.";
	private static final String CPF_IS_INVALID = "O CPF informado não é válido";
	
	private static final int CPF_LENGTH = 11;
	
	private String cpf;
	
	public CPF(String cpf) throws CPFException{
		
		setCPF(cpf);
	}
	
	public String getCpf(){
		return this.cpf;
	}
	
	public String getFormattedCpf(){
		
		String cpf = getCpf();
		String formatted = "";
		
		// Three first numbers
		formatted+= cpf.substring(0, 2);
		formatted+=".";
		
		// Three second numbers
		formatted+=cpf.substring(3, 5);
		formatted+=".";
		
		// Three third numbers
		formatted+=cpf.substring(6, 8);
		formatted+="-";
		
		//Last two digits
		formatted+=cpf.substring(9, 10);
		
		return formatted;
	}
	
	private void setCPF(final String cpf) throws CPFException{
		
		boolean cpfIsNotEmpty = !cpf.isEmpty() && cpf != null;
		
		if(cpfIsNotEmpty){
			
			if(cpf.length() == CPF_LENGTH){
	
				boolean isValid = validateCpf(cpf);
				
				if(isValid){
					this.cpf = cpf;
				}else{
					throw new CPFException(CPF_IS_INVALID);
				}
			}else{
				throw new CPFException(CPF_IS_INVALID);
			}
		}else{
			throw new CPFException(CPF_CANT_BE_EMPTY);
		}
	}
	
	private boolean validateCpf (String cpf){
		
		// Auxiliary variables to check Cpf
		int auxiliary1 = 0;
		int auxiliary2 = 0;
		
		// Variables used to represent the last two digits of a Cpf
		int penultimateDigit = 0;
		int lastDigit = 0;
		
		// Auxiliary variable to check Cpf (cadastro de pessoa f�sica, in portuguese)
		int rest = 0;
		
		// Receives the current digit of the Cpf
		int cpfDigit;
		
		// Receives the last two digits of a Cpf
		String result;

		// (currentDigit) Variable used to walk through the digits of cpf 
		int i = 1;
		
		// auxiliary1 = auxiliary2 = penultimateDigit = lastDigit = rest = 0;
		
		
		for (i = 1; i < cpf.length() - 1; i++){
			// Receives the digit of the Cpf for pass for integer 
			String cpfSubstring;
			cpfSubstring = cpf.substring(i - 1, i);
			
			cpfDigit = Integer.valueOf(cpfSubstring).intValue();

			auxiliary1 = auxiliary1 + (11 - i) * cpfDigit;
			auxiliary2 = auxiliary2 + (12 - i) * cpfDigit;
		}

		rest = auxiliary1 % 11;

		if (rest < 2){
			penultimateDigit = 0;
		}
		else{
			penultimateDigit = 11 - rest;
		}

		auxiliary2 += 2*penultimateDigit;
		rest = (auxiliary2 % 11);

		if (rest < 2){
			lastDigit = 0;
		}
		else{
			lastDigit = 11 - rest;
		}
		
		// Receives the last two digits of a Cpf
		String verific = cpf.substring(cpf.length() - 2, cpf.length());
		result = String.valueOf(penultimateDigit) + String.valueOf(lastDigit);

		boolean isValid = verific.equals(result);
		
		return isValid;
	}
}
