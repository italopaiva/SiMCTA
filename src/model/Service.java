package model;

import java.util.ArrayList;

import model.datatype.Date;
import model.Package;
import exception.PaymentException;
import exception.ServiceException;

public class Service extends Model{
	
	private static final String STUDENT_OF_SERVICE_CANT_BE_NULL = "O serviço deve ser vinculado a um estudante. Informe um estudante válido.";
	private static final String SERVICE_MUST_CONTAIN_AT_LEAST_A_COURSE_OR_PACKAGE = "O estudante deve estar vinculado a um curso ou pacote, pelo menos.";
	private static final String PAYMENT_CANT_BE_NULL = "O pagamento do serviço não pode estar em branco.";
	private static final String DATE_CANT_BE_NULL = "A data da matrícula não pode estar em branco.";
	private static final String SERVICE_ID_CANT_BE_ZERO = "O ID do serviço deve ser maior que zero.";
	private static final String CANT_ADD_NULL_ITEM_TO_SERVICE = "Não é possível adicionar um item nulo ao serviço.";
	
	private Integer serviceId; 
	private Student student;	
	private Payment payment;
	private Date contractsDate;
	private ArrayList<ServiceItem> itens = new ArrayList<ServiceItem>();
	
	public Service(Student student) throws ServiceException{

		setStudent(student);
	}
	
	public Service(Integer serviceId, Student student) throws ServiceException{
		
		setServiceId(serviceId);
		setStudent(student);
	}
	
	public Service(Integer serviceId, Student student, Date contractsDate, Payment payment) throws ServiceException, PaymentException{
		
		setServiceId(serviceId);
		setContractsDate(contractsDate);
		setStudent(student);
		setPayment(payment);
	}
	
	public void addItem(ServiceItem item) throws ServiceException{
		
		if(item != null){
			this.itens.add(item);
		}
		else{
			throw new ServiceException(CANT_ADD_NULL_ITEM_TO_SERVICE);
		}
	}
	
	/**
	 * Adds a payment to the service
	 * @param payment - the payment to be added
	 * @throws PaymentException
	 */
	public void addPayment(Payment payment) throws PaymentException{
		
		if(payment != null){
			setPayment(payment);
		}
		else{
			throw new PaymentException(PAYMENT_CANT_BE_NULL);
		}
	}
	
	private void setPayment(Payment payment){
		this.payment = payment;
	}
	
	private void setServiceId(Integer serviceId) throws ServiceException{
		
		if(serviceId != null && serviceId > 0){
			this.serviceId = serviceId;
		}
		else{
			throw new ServiceException(SERVICE_ID_CANT_BE_ZERO);
		}
	}
	
	private void setStudent(Student student) throws ServiceException{
		
		if(student != null){
			this.student = student;
		}
		else{
			throw new ServiceException(STUDENT_OF_SERVICE_CANT_BE_NULL);
		}
	}
	
	private void setContractsDate(Date contractsDate) throws ServiceException{
		
		if(contractsDate != null){
			this.contractsDate = contractsDate;
		}
		else{
			throw new ServiceException(DATE_CANT_BE_NULL);
		}
	}
	
	public Integer getTotalValue(){
		
		Integer serviceTotal = 0;
		for(ServiceItem item : getItens()){
			serviceTotal += item.getValue();
		}
		
		return serviceTotal;
	}
	
	public Integer getServiceId(){
		return this.serviceId;
	}

	public Student getStudent(){
		return this.student;
	}
	
	public Payment getPayment(){
		return this.payment;
	}
	
	public Date getContractsDate(){
		return this.contractsDate;
	}
	
	public ArrayList<ServiceItem> getItens(){
		return this.itens;
	}

	public String getTotalValueFormatted() {
				
		String formattedValue = null;
		Integer value = getTotalValue();
		
		if(value != 0){
			
			formattedValue = value.toString();
			
			int lastIndex = formattedValue.length();
			
			String entireValue = formattedValue.substring(0,(lastIndex - 2));
			String decimalValue = formattedValue.substring((lastIndex - 2),lastIndex);
			
			formattedValue  = "R$ " + entireValue + "," + decimalValue;
		}
		else{
			formattedValue = "R$ 0,00";
		}
		
		return formattedValue;
	}
	
	public String getInstallmentsValue() {
		
		String formattedInstallmentsValue = null;
		Integer value = getTotalValue();

		int installments = getPayment().getInstallments();
		
		Integer installmentsValue = value/installments;
		
		if(installmentsValue != 0){
		
			formattedInstallmentsValue = installmentsValue.toString();
			int lastIndex = formattedInstallmentsValue.length();
			
			String entireValue = formattedInstallmentsValue.substring(0,(lastIndex - 2));
			String decimalValue = formattedInstallmentsValue.substring((lastIndex - 2),lastIndex);
			
			formattedInstallmentsValue  = "R$ " + entireValue + "," + decimalValue;
		}
		else{
			formattedInstallmentsValue = "R$ 0,00";
		}
		
		return formattedInstallmentsValue;
	}
	
	/**
	 * Get the service itens which is courses
	 * @return An Array of ServiceItem with the courses only
	 */
	public ArrayList<ServiceItem> getCourses(){
		
		ArrayList<ServiceItem> itens = this.getItens();
		
		ArrayList<ServiceItem> courses = new ArrayList<ServiceItem>();
		
		for(ServiceItem item : itens){
			
			boolean isCourse = item.getClass().equals(Course.class);
			
			if(isCourse){
				courses.add(item);
			}
		}
		
		return courses;
	}
	
	/**
	 * Get the service itens which is packages
	 * @return An Array of ServiceItem with the packages only
	 */
	public ArrayList<ServiceItem> getPackages(){
		
		ArrayList<ServiceItem> itens = this.getItens();
		
		ArrayList<ServiceItem> packages = new ArrayList<ServiceItem>();
		
		for(ServiceItem item : itens){
			
			boolean isPackage = item.getClass().equals(Package.class);
			
			if(isPackage){
				packages.add(item);
			}
		}
		
		return packages;
	}
}
