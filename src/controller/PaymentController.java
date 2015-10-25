package controller;

import java.util.ArrayList;

import dao.PaymentDAO;
import exception.PaymentException;
import model.Payment;
import model.Service;

public class PaymentController{
	
	private PaymentDAO paymentDAO;
	
	public PaymentController(){
		paymentDAO = new PaymentDAO();
	}
	
	
	/**
	 * Create a new payment for the given service
	 * @param service - Service that generated the payment
	 * @param paymentType - Type of payment
	 * @param paymentForm - Form of payment
	 * @param installments - Quantity of installments
	 * @return a Payment object with the payment created
	 * @throws PaymentException
	 */
	public Payment newPayment(Service service, int paymentType, int paymentForm, Integer installments) throws PaymentException{
		
		Payment payment = new Payment(service, paymentType, paymentForm, installments);
		
		int savedPaymentId = paymentDAO.save(payment);
		
		Payment savedPayment = new Payment(savedPaymentId, service, paymentType, paymentForm, installments);
		
		return savedPayment;
	}

	/**
	 * Gets the payment of the service 
	 * @param payment - an Payment object that contains the payment id
	 * @return the payment
	 * @throws PaymentException 
	 */
	public Payment searchPayment(Payment payment) throws PaymentException {
		
		if(payment != null){
			
			int paymentId = payment.getPaymentId();
			payment = paymentDAO.get(paymentId);

			if(payment == null){
				throw new PaymentException(Payment.PAYMENT_SERVICE_CANT_BE_NULL);
			}
		}
		else{
			throw new PaymentException(Payment.PAYMENT_SERVICE_CANT_BE_NULL);
		}
		return payment;
		
	}

	public void setPaymentDAO(PaymentDAO paymentDAO) {
		this.paymentDAO = paymentDAO;
		
	}
}
