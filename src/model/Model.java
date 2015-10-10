package model;

public abstract class Model {
	
	protected boolean isNotEmpty(final String string){
		
		boolean isNotEmpty = false;
		
		if(string != null){
			
			isNotEmpty = !string.isEmpty();
		}else{
			isNotEmpty = false;
		}
		
		return isNotEmpty;
	}
	
	protected boolean containsOnlyNumbers(final String string){
		
		boolean containsOnlyNumbers = false;
		
		if(this.isNotEmpty(string)){
			
			if(string.matches("[0-9]+")){
				
				containsOnlyNumbers = true;
			}else{
				containsOnlyNumbers = false;
			}
		}else{
			containsOnlyNumbers = false;
		}
		
		return containsOnlyNumbers;
	}
	
	protected boolean containsOnlyLetters(final String string){
		
		boolean containsOnlyLetters = false;
		
		if(this.isNotEmpty(string)){
			
			if(string.matches("[a-zA-Z\\s]+")){
				
				containsOnlyLetters = true;
			}else{
				containsOnlyLetters = false;
			}
			
		}else{
			containsOnlyLetters = false;
		}
		
		return containsOnlyLetters;
	}
}
