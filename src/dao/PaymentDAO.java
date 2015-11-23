package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datatype.CPF;
import exception.PaymentException;
import model.Payment;
import model.PaymentDescription;
import model.Service;

public class PaymentDAO extends DAO{
	
	private static final String PAYMENT_TABLE_NAME = "Payment";
	private static final String ID_COLUMN = "id_payment";
	private static final String INSTALLMENT_COLUMN = "installments";
	private static final String VALUE_COLUMN = "value";
	private static final String PAYMENT_DESCRIPTION_COLUMN = "payment_description";
	private static final String PAYMENT_DESCRIPTION_TABLE_NAME = "PaymentDescription";
	private static final String ID_DESCRIPTION_COLUMN = "id_description";
	private static final String PAYMENT_TYPE_COLUMN = "payment_type";
	private static final String PAYMENT_FORM_COLUMN = "payment_form";
	private static final String DESCRIPTION_COLUMN = "description";
	
	public PaymentDAO(){}
	
	/**
	 * Save a given payment on the database
	 * @param payment - The payment to be saved
	 * @return the saved payment id 
	 * @throws PaymentException
	 */
	public int save(Payment payment) throws PaymentException{
		
		int savedPaymentId = 0;
		
		if(payment != null){
			
			try{
				int paymentId = this.getNextId(PAYMENT_TABLE_NAME, ID_COLUMN);
								
				String query = "INSERT INTO "+ PAYMENT_TABLE_NAME; 
				   query += "(" + ID_COLUMN + "," + INSTALLMENT_COLUMN + ", " + VALUE_COLUMN + ", " + PAYMENT_DESCRIPTION_COLUMN + ") ";
				   query += "VALUES ('" + paymentId + "', '" + payment.getInstallments() + "', '" + payment.getValue();
				   query += "', '" + payment.getDescription().getPaymentDescriptionId() + "')";
				   
				this.execute(query);
				savedPaymentId = paymentId;
			    
			}
			catch(SQLException e){
				throw new PaymentException(Payment.PAYMENT_ID_CANT_BE_ZERO);
			}
		}
		else{
			throw new PaymentException(Payment.PAYMENT_ID_CANT_BE_ZERO);
		}
			   
		return savedPaymentId;
	}

	public Payment get(int paymentId) throws PaymentException {
		
		ResultSet payments = null;
	
		Payment payment = null;
		
		String query = "SELECT * FROM " + PAYMENT_TABLE_NAME + " WHERE " + ID_COLUMN + "=\"" + paymentId + " \"";
	
		try{
			payments = this.search(query);

			while(payments.next()){

				int paymentDescriptionId = payments.getInt(PAYMENT_DESCRIPTION_COLUMN);
				int installments = payments.getInt(INSTALLMENT_COLUMN);
				PaymentDescription paymentDescription = getPaymentDescription(paymentDescriptionId);

				if(paymentDescription != null){
						int paymentForm = paymentDescription.getPaymentForm();
						int paymentType = paymentDescription.getPaymentType();

						payment = new Payment(paymentId, paymentType, paymentForm, installments);
				}
				else{
					throw new PaymentException("Não foi possível encontrar a forma de pagamento");
				}
	
			}
			
			
		}
		catch(SQLException e){
			
		}
		
		return payment;
	}

	private PaymentDescription getPaymentDescription(int paymentDescriptionId) throws PaymentException {
		
		String query = "SELECT * FROM " + PAYMENT_DESCRIPTION_TABLE_NAME + " WHERE " + ID_DESCRIPTION_COLUMN + "=" + paymentDescriptionId;
		PaymentDescription paymentDescription = null;
		ResultSet descriptionResult = null;

		try{
			descriptionResult = this.search(query);

			while(descriptionResult.next()){
							
				int paymentType = descriptionResult.getInt(PAYMENT_TYPE_COLUMN);
				int paymentForm = descriptionResult.getInt(PAYMENT_FORM_COLUMN);
				paymentDescription = new PaymentDescription(paymentType, paymentForm);
			}
			
		}
		catch(SQLException e){
			
		}
		

		return paymentDescription;
	}
}
