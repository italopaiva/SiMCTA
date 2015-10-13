package controller;

import dao.PaymentDAO;
import exception.PaymentException;
import model.Payment;
import model.Service;

public class PaymentController{
	
	private PaymentDAO paymentDAO = new PaymentDAO();
	
	/**
	 * Create a new payment for the given service
	 * @param service - Service tha generated the payment
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
}
