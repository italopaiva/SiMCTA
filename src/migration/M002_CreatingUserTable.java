package migration;

import java.sql.SQLException;

public class M002_CreatingUserTable extends Migration {
	
	private static final int MIGRATION_VERSION = 2;
	
	public M002_CreatingUserTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String query = "";
		
		query+= "CREATE TABLE IF NOT EXISTS User (";
		query+= "password varchar(10) NOT NULL";
		query+= ")";
		
		setQuery(query);
		run();
	}
}
