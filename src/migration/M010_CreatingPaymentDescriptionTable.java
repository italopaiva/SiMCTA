package migration;

import java.sql.SQLException;

import model.PaymentDescription;
import exception.PaymentException;

public class M010_CreatingPaymentDescriptionTable extends Migration {
	
	private static final int MIGRATION_VERSION = 10;
	
	public M010_CreatingPaymentDescriptionTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String createPaymentDescriptionTable = "";
		createPaymentDescriptionTable+= "CREATE TABLE IF NOT EXISTS PaymentDescription (";
		createPaymentDescriptionTable+= "id_description int NOT NULL AUTO_INCREMENT,";
		createPaymentDescriptionTable+= "payment_type int NOT NULL,";
		createPaymentDescriptionTable+= "payment_form int NOT NULL,";
		createPaymentDescriptionTable+= "description varchar(50) NOT NULL,";
		createPaymentDescriptionTable+= "PRIMARY KEY (id_description)";
		createPaymentDescriptionTable+= ")";
				
		setQuery(createPaymentDescriptionTable);
		run();
		
		String dropPaymentTypeColumn = "ALTER TABLE Payment DROP COLUMN payment_type";
		setQuery(dropPaymentTypeColumn);
		run();
		
		String dropPaymentFormColumn = "ALTER TABLE Payment DROP COLUMN payment_form";
		setQuery(dropPaymentFormColumn);
		run();
		
		String addDescriptionColumn = "ALTER TABLE Payment ADD COLUMN payment_description int NOT NULL";
		setQuery(addDescriptionColumn);
		run();
		
		String setDescriptionColumnFk = "ALTER TABLE Payment ADD FOREIGN KEY (payment_description) ";
		setDescriptionColumnFk += "REFERENCES PaymentDescription(id_description)";
		setQuery(setDescriptionColumnFk);
		run();
		
		for(int type = 1; type <= 2; type++){
			for(int form = 1; form <= 3; form++){
				
				String description = "";
				switch(type){
				
					case 1:
						switch(form){
							case 1:
								description = PaymentDescription.CASH_CASH_DESCRIPTION;
								break;
							case 2:
								description = PaymentDescription.CASH_CARD_DESCRIPTION;
								break;
							case 3:
								description = PaymentDescription.CASH_CHECK_DESCRIPTION;
								break;
						}
						
						break;
					
					case 2:
						
						switch(form){
							case 1:
								description = PaymentDescription.INSTALLMENT_CASH_DESCRIPTION;
								break;
							case 2:
								description = PaymentDescription.INSTALLMENT_CARD_DESCRIPTION;
								break;
							case 3:
								description = PaymentDescription.INSTALLMENT_CHECK_DESCRIPTION;
								break;
						}
						break;
				}
				
				String insertDescription = "INSERT INTO PaymentDescription(payment_type, payment_form, description)"
						+ " VALUES ('"+ type +"', '"+ form +"', '" + description + "')";
				setQuery(insertDescription);
				run();
			}
		}
	}

}
