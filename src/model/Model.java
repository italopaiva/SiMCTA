/**
 * Class: Model
 * 
 * This class provide methods used for validation on several other models.
 * All other models must extends this one to get access to these methods.
 */

package model;

public abstract class Model {
	
	/**
	 * Check if a given string is not null or empty
	 * @param string - The string to be checked
	 * @return TRUE if the string is NOT empty, or FALSE if it does
	 */
	protected boolean isNotEmpty(final String string){
		
		boolean isNotEmpty = false;
		
		if(string != null){
			
			isNotEmpty = !string.isEmpty();
		}else{
			isNotEmpty = false;
		}
		
		return isNotEmpty;
	}
	
	/**
	 * Check if a given string contains only numbers within
	 * @param string - The string to be checked
	 * @return TRUE if it contains only numbers, or FALSE if it does not
	 */
	protected boolean containsOnlyNumbers(String string){
		
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
	
	/**
	 * Check if a given string contains only alphabetical characters
	 * @param string - the string to be checked
	 * @return TRUE if it contains only letters, or FALSE if it does not
	 */
	protected boolean containsOnlyLetters(final String string){
		
		boolean containsOnlyLetters = false;
		
		if(this.isNotEmpty(string)){
			
			if(string.matches("[a-zA-Zà-úÀ-Ú]+")){
				
				containsOnlyLetters = true;
			}else{
				containsOnlyLetters = false;
			}
			
		}else{
			containsOnlyLetters = false;
		}
		
		return containsOnlyLetters;
	}
	
	/**
	 * Check if a given string contains only alphabetical characters or spaces within 
	 * @param string - The string to be checked
	 * @return TRUE if it contains only alphabetical characters or spaces, or FALSE if it does not
	 */
	protected boolean containsOnlyLettersAndSpaces(final String string){
		
		boolean containsOnlyLetters = false;
		
		if(this.isNotEmpty(string)){
			
			if(string.matches("[a-zA-Zà-úÀ-Ú\\s]+")){
				
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
