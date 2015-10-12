package controller;

import exception.PaymentException;
import model.Payment;
import model.Service;

public class PaymentController{
	
	public Payment newPayment(Service service, int paymentType, int paymentForm) throws PaymentException{
		
		Payment payment = new Payment(service, paymentType, paymentForm);
		
		return payment;
	}
}
