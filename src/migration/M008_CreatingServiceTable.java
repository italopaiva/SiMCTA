package migration;

import java.sql.SQLException;

public class M008_CreatingServiceTable extends Migration {
	private static final int MIGRATION_VERSION = 8;
	
	public M008_CreatingServiceTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String query = "";
		query+= "CREATE TABLE IF NOT EXISTS Service (";
		query+= "id_service int NOT NULL PRIMARY KEY AUTO_INCREMENT,";
		query+= "contract_date Date NOT NULL,";
		query+= "id_payment int NOT NULL NOT NULL,";
		query+= "cpf varchar(11) NOT NULL,";
		query+= "value int(6) NOT NULL,";
		query+= "FOREIGN KEY(id_payment) REFERENCES Payment (id_payment),";
		query+= "FOREIGN KEY(cpf) REFERENCES Student (cpf)";
		query+= ")";

		setQuery(query);
		run();
	}
}
