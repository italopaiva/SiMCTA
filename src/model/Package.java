package model;

import java.util.ArrayList;

import exception.PackageException;
import exception.ServiceItemException;

public class Package extends ServiceItem{

	/**
	 * Error messages 
	 */
	private static final String PACKAGE_DURATION_CANT_BE_ZERO = "O pacote deve durar pelo menos 1 semana";
	private static final String PACKAGE_MUST_CONTAIN_COURSES = "Um pacote deve conter pelo menos um curso associado a ele.";
	private static final String GIVEN_INVALID_COURSE_TO_PACKAGE = "O curso informado para adicionar ao pacote não é válido.";
	private static final String CANT_ADD_NULL_ITEM = "Não é possível adicionar um curso vazio ao pacote.";
	private static final String ERROR_CALCULATING_PACKAGE_DURATION = "A duração do pacote não está dentro dos limites inferior e superior.";
	
	private static final int PACKAGE_ACTIVE = 1;
	
	private ArrayList<ServiceItem> serviceItens = new ArrayList<ServiceItem>();
	
	/** Constructors */
	public Package(){}
	
	public Package(Integer packageId, String packageName, Integer packageValue) throws PackageException{
		
		try{
			setId(packageId);
			setName(packageName);
			setValue(packageValue);
			setStatus(PACKAGE_ACTIVE);
		}
		catch (ServiceItemException e){
			throw new PackageException(e.getMessage());
		}
	}
	
	public Package(Integer packageId, String packageName, Integer packageValue, 
			       Integer status) throws PackageException{
		
		try{
			setId(packageId);
			setName(packageName);
			setValue(packageValue);
			setStatus(status);
		}
		catch (ServiceItemException e){
			throw new PackageException(e.getMessage());
		}
	}
	
	public Package(Integer packageId, String packageName, Integer packageValue, 
		       Integer packageDuration, Integer status) throws PackageException{
		
		try{
			setId(packageId);
			setName(packageName);
			setValue(packageValue);
			setDuration(packageDuration);
			setStatus(status);
		}
		catch (ServiceItemException e){
			throw new PackageException(e.getMessage());
		}
	}
	
	public Package(Integer packageId, String packageName, Integer packageValue, 
		       Integer packageDuration, Integer status, ArrayList<ServiceItem> packageItens) throws PackageException{
		
		try{
			setId(packageId);
			setName(packageName);
			setValue(packageValue);
			setDuration(packageDuration);
			setServiceItens(packageItens);
		}
		catch (ServiceItemException e){
			throw new PackageException(e.getMessage());
		}
	}
	
	public void addServiceItem(ServiceItem item) throws PackageException{
		
		if(item != null){
	
			serviceItens.add(item);
			
			try {
				this.setDuration();
			}
			catch(ServiceItemException e){
				throw new PackageException(ERROR_CALCULATING_PACKAGE_DURATION);
			}
		}
		else{
			throw new PackageException(CANT_ADD_NULL_ITEM);
		}
	}
	
	private void setDuration() throws ServiceItemException{
		Integer duration = calculateDuration(); 
		setDuration(duration);
	}
	
	private Integer calculateDuration(){
		
		Integer totalDuration = 0;
		
		for(ServiceItem item : this.serviceItens){
			totalDuration += item.getDuration();
		}
		
		return totalDuration;
	}
	
	public ArrayList<String> getCourses(){
		
		ArrayList<ServiceItem> itens = getServiceItens();
		
		ArrayList<String> courses = new ArrayList<String>();
		
		for(ServiceItem item : itens){
			courses.add(item.getId().toString());
		}
		
		return courses;
	}
	
	private void setServiceItens(ArrayList<ServiceItem> itens){
		this.serviceItens = itens;
	}
	
	public ArrayList<ServiceItem> getServiceItens(){
		return this.serviceItens;
	}

}
