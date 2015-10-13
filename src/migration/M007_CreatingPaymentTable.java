package migration;

import java.sql.SQLException;

public class M007_CreatingPaymentTable extends Migration {
	private static final int MIGRATION_VERSION = 7;
	
	public M007_CreatingPaymentTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String query = "";
		query+= "CREATE TABLE IF NOT EXISTS Payment (";
		query+= "id_payment int NOT NULL PRIMARY KEY AUTO_INCREMENT,";
		query+= "installments int NOT NULL,";
		query+= "payment_type varchar(10) NOT NULL,";
		query+= "payment_form varchar(10) NOT NULL,";
		query+= "value int(6) NOT NULL";
		query+= ")";

		setQuery(query);
		run();
	}
}
