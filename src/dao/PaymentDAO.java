package dao;

import java.sql.SQLException;

import model.Payment;

public class PaymentDAO extends DAO{
	
	private static final String PAYMENT_TABLE_NAME = "Payment";
	private static final String INSTALLMENT_COLUMN = "installments";
	private static final String VALUE_COLUMN = "value";
	private static final String PAYMENT_DESCRIPTION_COLUMN = "payment_description";
	
	public PaymentDAO(){}
	
	public boolean savePayment(Payment payment){
		
		boolean wasSaved = false;
		
		if(payment != null){
			
			String query = "INSERT INTO "+ PAYMENT_TABLE_NAME; 
				   query += "(" + INSTALLMENT_COLUMN + ", " + VALUE_COLUMN + ", " + PAYMENT_DESCRIPTION_COLUMN + ") ";
				   query += "VALUES ('" + payment.getInstallments() + "', '" + payment.getValue();
				   query += "', '" + payment.getDescription().getPaymentDescriptionId() + "')";
			
		    try{
				this.execute(query);
				wasSaved = true;
			}
		    catch(SQLException e){
		    	wasSaved = false;
			}
		}
		else{
			wasSaved = false;
		}
			   
		return wasSaved;
	}
}
