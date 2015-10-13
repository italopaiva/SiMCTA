package controller;

import dao.PaymentDAO;
import exception.PaymentException;
import model.Payment;
import model.Service;

public class PaymentController{
	
	private PaymentDAO paymentDAO = new PaymentDAO();
	
	public Payment newPayment(Service service, int paymentType, int paymentForm, Integer installments) throws PaymentException{
		
		Payment payment = new Payment(service, paymentType, paymentForm, installments);
		
		boolean wasSaved = paymentDAO.savePayment(payment);
		
		if(wasSaved){
			// Nothing to do
		}
		else{
			payment = null;
		}
		
		return payment;
	}
}
