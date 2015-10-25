package model;

import exception.ServiceItemException;

public abstract class ServiceItem extends Model{
	
	// Error constants
		protected static final String VALUE_CANT_BE_ZERO = "O valor do curso não pode ser menor ou igual a zero.";
		protected static final String DURATION_CANT_BE_ZERO = "Um curso deve durar pelo menos uma semana.";
		protected static final String NAME_CANT_BE_NULL = "O nome do curso não pode ficar em branco.";
		protected static final String ID_MUST_BE_GREATER_THAN_ZERO = "O id do curso não pode ser menor que 0";
	
	/**
	 * The max and min duration are these because the duration must have at least 1 digit and
	 * no more than 2 digits
	 * Ex.: 10 weeks -> 10 has two digits
	 * So, the greater number with 2 digits is 99, and the minimun is 1 (because can't be zero)
	 */
	protected static final int MAX_DURATION = 99;
	protected static final int MIN_DURATION = 1;
	
	/**
	 * The max and min value are these because the value must have no more than 6 digits
	 * Ex.: R$ 2500,39 = 250039
	 * So, the greater acceptable value is 999999 (R$ 9999,99) 
	 */
	protected static final int MAX_VALUE = 999999;
	protected static final int MIN_VALUE = 1;
	
	protected Integer id;
	protected String name;
	protected int status;

	/**
	 * Given in weeks
	 * Must only have at least one digit and no more than two digits (Ex.: 10 weeks)
	 */
	protected Integer duration;
	
	/**
	 * Given in reals (R$)
	 * Cannot have more than 6 digits (Ex.: R$ 1500,50 = 150050)
	 */
	protected Integer value;
	
/** Setters */
	
	protected void setName(String name) throws ServiceItemException{
		
		boolean nameIsValid = name != null && !name.isEmpty();  
		
		if(nameIsValid){
			this.name = name;
		}else{
			throw new ServiceItemException(NAME_CANT_BE_NULL);
		}
	}
	
	protected void setDuration(Integer duration) throws ServiceItemException{
		
		boolean durationIsValid = duration >= MIN_DURATION 
										&& duration <= MAX_DURATION;
		
		if(durationIsValid){
			
			this.duration = duration;
		}else{
			
			throw new ServiceItemException(DURATION_CANT_BE_ZERO);
		}
	}
	
	protected void setValue(Integer value) throws ServiceItemException{
				
		boolean courseValueIsValid = value >= MIN_VALUE 
										&& value <= MAX_VALUE;
		if(courseValueIsValid){
			
			this.value = value;
		}else{
			
			throw new ServiceItemException(VALUE_CANT_BE_ZERO);
		}
	}
	
	protected void setId(Integer id) throws ServiceItemException{
		
		boolean courseIdIsValid = id != null && id > 0;
		
		if(courseIdIsValid){
			
			this.id = id;
		}else{
			
			throw new ServiceItemException(ID_MUST_BE_GREATER_THAN_ZERO);
		}
	}
	
	protected void setStatus(int status){
		this.status = status;
	}
	
/** End of Setters */
	
/** Getters */
	
	public String getName(){
		return this.name;
	}
	
	public Integer getDuration(){
		return this.duration;
	}
	
	public Integer getValue(){
		return this.value;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public int getStatus(){
		return this.status;
	}

/** End of Getters */
}
