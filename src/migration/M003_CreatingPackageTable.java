package migration;

import java.sql.SQLException;

public class M003_CreatingPackageTable extends Migration {
	
	private static final int MIGRATION_VERSION = 3;
	
	public M003_CreatingPackageTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String query = "";
		
		query+= "CREATE TABLE IF NOT EXISTS Package (";
		query+= "id_package int(11) NOT NULL AUTO_INCREMENT,";
		query+= "name varchar(100) NOT NULL,";
		query+= "value int(11) NOT NULL,";
		query+= "duration int(3) NOT NULL,";
		query+= "PRIMARY KEY (id_package)";
		query+= ")";
		
		setQuery(query);
		run();
	}
}
