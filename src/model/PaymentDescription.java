package model;

import exception.PaymentException;

public class PaymentDescription extends Model{
	
	// Payment types
	public static final int CASH_PAYMENT_TYPE = 1;
	public static final int PAYMENT_IN_INSTALLMENTS_TYPE = 2;
	
	// Payment forms
	public static final int CASH_FORM = 1;
	public static final int CARD_FORM = 2;
	public static final int CHECK_FORM = 3;
	
	public static final String CASH_CASH_DESCRIPTION = "Pagamento a vista em dinheiro.";
	public static final String CASH_CARD_DESCRIPTION = "Pagamento a vista no cartão.";
	public static final String CASH_CHECK_DESCRIPTION = "Pagamento a vista no cheque.";
	
	public static final String INSTALLMENT_CASH_DESCRIPTION = "Pagamento parcelado em dinheiro.";
	public static final String INSTALLMENT_CARD_DESCRIPTION = "Pagamento parcelado no cartão.";
	public static final String INSTALLMENT_CHECK_DESCRIPTION = "Pagamento parcelado no cheque.";
	
	private static final String INVALID_PAYMENT_TYPE = "O tipo de pagamento informado é inválido. Escolha entre pagamento a vista ou parcelado.";
	private static final String INVALID_PAYMENT_FORM = "A forma de pagamento informada é inválida. Escolha entre pagamento com dinheiro, cartão ou cheque.";
	
	private int paymentType;
	private int paymentForm;
	private String description;
	
	public PaymentDescription(int paymentType, int paymentForm) throws PaymentException{
		
		setPaymentType(paymentType);
		setPaymentForm(paymentForm);
		setPaymentDescription();
	}

	private void setPaymentType(int paymentType) throws PaymentException{
		
		switch(paymentType){
			case CASH_PAYMENT_TYPE:
				this.paymentType = paymentType;
				break;
				
			case PAYMENT_IN_INSTALLMENTS_TYPE:
				this.paymentType = paymentType;
				break;
				
			default:
				throw new PaymentException(INVALID_PAYMENT_TYPE);
		}
	}
	
	private void setPaymentForm(final int paymentForm) throws PaymentException{
		
		switch(paymentForm){
			case CASH_FORM:
				this.paymentForm = paymentForm;
				break;
				
			case CARD_FORM:
				this.paymentForm = paymentForm;
				break;
			
			case CHECK_FORM:
				this.paymentForm = paymentForm;
				break;
				
			default:
				throw new PaymentException(INVALID_PAYMENT_FORM);
		}
	}
	
	private void setPaymentDescription() throws PaymentException{
		
		int paymentType = this.paymentType;
		int paymentForm = this.paymentForm;
		
		String description = "";
		switch(paymentType){
		
			case CASH_PAYMENT_TYPE:
				
				switch(paymentForm){
					case CASH_FORM:
						description = CASH_CASH_DESCRIPTION;
						break;
					case CARD_FORM:
						description = CASH_CARD_DESCRIPTION;
						break;
					case CHECK_FORM:
						description = CASH_CHECK_DESCRIPTION;
						break;
					default:
						throw new PaymentException(INVALID_PAYMENT_FORM);
				}
				
				break;
			
			case PAYMENT_IN_INSTALLMENTS_TYPE:
				
				switch(paymentForm){
					case CASH_FORM:
						description = INSTALLMENT_CASH_DESCRIPTION;
						break;
					case CARD_FORM:
						description = INSTALLMENT_CARD_DESCRIPTION;
						break;
					case CHECK_FORM:
						description = INSTALLMENT_CHECK_DESCRIPTION;
						break;
					default:
						throw new PaymentException(INVALID_PAYMENT_FORM);
				}
				break;
				
			default:
				throw new PaymentException(INVALID_PAYMENT_TYPE);
		}
		
		this.description = description;
	}

	public int getPaymentType(){
		return this.paymentType;
	}

	public int getPaymentForm(){
		return this.paymentForm;
	}

	public String getDescription(){
		return this.description;
	}
}
