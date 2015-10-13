package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import exception.PaymentException;
import model.Payment;

public class PaymentDAO extends DAO{
	
	private static final String PAYMENT_TABLE_NAME = "Payment";
	private static final String ID_COLUMN = "id_payment";
	private static final String INSTALLMENT_COLUMN = "installments";
	private static final String VALUE_COLUMN = "value";
	private static final String PAYMENT_DESCRIPTION_COLUMN = "payment_description";
	
	public PaymentDAO(){}
	
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
}
