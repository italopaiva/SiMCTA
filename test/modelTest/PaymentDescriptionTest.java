package modelTest;

import static org.junit.Assert.*;
import model.PaymentDescription;

import org.junit.Test;

import exception.PaymentException;

public class PaymentDescriptionTest {
	
	PaymentDescription paymentDescription;
	
	/** Tests for Cash type */
	@Test
	public void testPaymentDescriptionCashCash(){
		
		try{
			paymentDescription = new PaymentDescription(PaymentDescription.CASH_PAYMENT_TYPE, PaymentDescription.CASH_FORM);
			assertEquals(PaymentDescription.CASH_CASH_DESCRIPTION, paymentDescription.getDescription());
		}
		catch(PaymentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testPaymentDescriptionCashCard(){
		
		try{
			paymentDescription = new PaymentDescription(PaymentDescription.CASH_PAYMENT_TYPE, PaymentDescription.CARD_FORM);
			assertEquals(PaymentDescription.CASH_CARD_DESCRIPTION, paymentDescription.getDescription());
		}
		catch(PaymentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testPaymentDescriptionCashCheck(){
		
		try{
			paymentDescription = new PaymentDescription(PaymentDescription.CASH_PAYMENT_TYPE, PaymentDescription.CHECK_FORM);
			assertEquals(PaymentDescription.CASH_CHECK_DESCRIPTION, paymentDescription.getDescription());
		}
		catch(PaymentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	/** Tests for installment type */
	@Test
	public void testPaymentDescriptionInstallmentCash(){
		
		try{
			paymentDescription = new PaymentDescription(PaymentDescription.PAYMENT_IN_INSTALLMENTS_TYPE, PaymentDescription.CASH_FORM);
			assertEquals(PaymentDescription.INSTALLMENT_CASH_DESCRIPTION, paymentDescription.getDescription());
		}
		catch(PaymentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testPaymentDescriptionInstallmentCard(){
		
		try{
			paymentDescription = new PaymentDescription(PaymentDescription.PAYMENT_IN_INSTALLMENTS_TYPE, PaymentDescription.CARD_FORM);
			assertEquals(PaymentDescription.INSTALLMENT_CARD_DESCRIPTION, paymentDescription.getDescription());
		}
		catch(PaymentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	@Test
	public void testPaymentDescriptionInstallmentCheck(){
		
		try{
			paymentDescription = new PaymentDescription(PaymentDescription.PAYMENT_IN_INSTALLMENTS_TYPE, PaymentDescription.CHECK_FORM);
			assertEquals(PaymentDescription.INSTALLMENT_CHECK_DESCRIPTION, paymentDescription.getDescription());
		}
		catch(PaymentException e){
			fail("Should not throw this exception: "+e.getMessage());
		}
	}
	
	/** Tests for invalid entries of payment type */
	@Test(expected = PaymentException.class)
	public void testInvalidType0PaymentDescription() throws PaymentException{
		
		paymentDescription = new PaymentDescription(0, PaymentDescription.CHECK_FORM);
	}
	
	@Test(expected = PaymentException.class)
	public void testInvalidTypeNegativeNumberPaymentDescription() throws PaymentException{
		
		paymentDescription = new PaymentDescription(-1, PaymentDescription.CHECK_FORM);
	}
	
	@Test(expected = PaymentException.class)
	public void testInvalidType3PaymentDescription() throws PaymentException{
		
		paymentDescription = new PaymentDescription(3, PaymentDescription.CHECK_FORM);
	}
	
	@Test(expected = PaymentException.class)
	public void testInvalidType4PaymentDescription() throws PaymentException{
		
		paymentDescription = new PaymentDescription(4, PaymentDescription.CHECK_FORM);
	}
	
	@Test(expected = PaymentException.class)
	public void testInvalidTypePaymentDescription() throws PaymentException{
		
		paymentDescription = new PaymentDescription(30, PaymentDescription.CHECK_FORM);
	}
	
	/** Tests for invalid entries of payment form */
	@Test(expected = PaymentException.class)
	public void testInvalidForm0PaymentDescription() throws PaymentException{
		
		paymentDescription = new PaymentDescription(PaymentDescription.CASH_PAYMENT_TYPE, 0);
	}
	
	@Test(expected = PaymentException.class)
	public void testInvalidFormNegativeNumberPaymentDescription() throws PaymentException{
		
		paymentDescription = new PaymentDescription(PaymentDescription.CASH_PAYMENT_TYPE, -1);
	}
	
	@Test(expected = PaymentException.class)
	public void testInvalidForm4PaymentDescription() throws PaymentException{
		
		paymentDescription = new PaymentDescription(PaymentDescription.CASH_PAYMENT_TYPE, 4);
	}
	
	@Test(expected = PaymentException.class)
	public void testInvalidForm5PaymentDescription() throws PaymentException{
		
		paymentDescription = new PaymentDescription(PaymentDescription.CASH_PAYMENT_TYPE, 5);
	}
	
	@Test(expected = PaymentException.class)
	public void testInvalidFormPaymentDescription() throws PaymentException{
		
		paymentDescription = new PaymentDescription(PaymentDescription.CASH_PAYMENT_TYPE, 30);
	}
}
