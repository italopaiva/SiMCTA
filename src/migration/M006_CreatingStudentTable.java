package migration;

import java.sql.SQLException;

public class M006_CreatingStudentTable extends Migration {
	private static final int MIGRATION_VERSION = 6;
	
	public M006_CreatingStudentTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String query = "";
		
		query+= "CREATE TABLE IF NOT EXISTS Student (";
		query+= "cpf varchar(11) NOT NULL PRIMARY KEY,";
		query+= "name_student varchar(200) NOT NULL,";
		query+= "birthdate Date NOT NULL,";
		query+= "email varchar(100),";
		query+= "mother varchar(100) NOT NULL,";
		query+= "father varchar(100),";
		query+= "uf varchar(20) NOT NULL,";
		query+= "issuing_institution varchar(100) NOT NULL,";
		query+= "number int NOT NULL,";
		query+= "residence_phone varchar(10) NOT NULL,";
		query+= "cell_phone varchar(10) NOT NULL,";
		query+= "complement text,";
		query+= "city varchar(100) NOT NULL,";
		query+= "cep varchar(8) NOT NULL,";
		query+= "address_info varchar(200) NOT NULL";
		query+= ")";

		setQuery(query);
		run();
	}
}
