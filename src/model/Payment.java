package model;

import exception.PaymentException;

public class Payment extends Model{
	
	private static final String PAYMENT_SERVICE_CANT_BE_NULL = "O serviço que gerou o pagamento não pode ser nulo.";
	private static final String INVALID_INSTALLMENT = "A quantidade de parcelas devem ser maiores ou iguais a zero.";
	
	private static final int MIN_INSTALLMENT = 0;
	
	private Integer paymentId;
	private PaymentDescription description;
	private Integer installments;
	private Service service;
	private Integer value;
	
	public Payment(Service service, int paymentType, int paymentForm, Integer installments) throws PaymentException{
		
		setService(service);
		setDescription(paymentType, paymentForm);
		setInstallments(installments);
	}
	
	private void setInstallments(Integer installments) throws PaymentException{
		
		if(installments != null){
			if(installments >= MIN_INSTALLMENT){
				this.installments = installments;
			}
			else{
				throw new PaymentException(INVALID_INSTALLMENT);
			}
		}
		else{
			this.installments = MIN_INSTALLMENT;
		}
	}
	
	private void setService(Service service) throws PaymentException{
		
		if(service != null){
			this.service = service;
			setValue(service.getTotalValue());
		}else{
			throw new PaymentException(PAYMENT_SERVICE_CANT_BE_NULL);
		}
	}
	
	private void setValue(Integer value){
		
		this.value = value;
	}
	
	private void setDescription(int paymentType, int paymentForm) throws PaymentException{
		
		PaymentDescription paymentDescription = new PaymentDescription(paymentType, paymentForm);
		
		this.description = paymentDescription;
	}
	
	private void setPaymentId(Integer paymentId){
		this.paymentId = paymentId;
	}

	public Integer getPaymentId(){
		return this.paymentId;
	}

	public PaymentDescription getDescription(){
		return this.description;
	}

	public Service getService(){
		return this.service;
	}

	public Integer getValue(){
		return this.value;
	}
	
	public Integer getInstallments(){
		return this.installments;
	}
}
