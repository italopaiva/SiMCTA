package model;

import exception.PaymentException;

public class Payment extends Model{
	
	private static final String PAYMENT_SERVICE_CANT_BE_NULL = "O serviço que gerou o pagamento não pode ser nulo.";
	
	private Integer paymentId;
	private PaymentDescription description;
	private Service service;
	private Integer value;
	
	public Payment(Service service, int paymentType, int paymentForm) throws PaymentException{
		
		setService(service);
		setDescription(paymentType, paymentForm);
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
	
	private void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
}
