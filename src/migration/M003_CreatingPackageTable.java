package migration;

import java.sql.SQLException;

public class M003_CreatingPackageTable extends Migration {
	
	private static final int MIGRATION_VERSION = 2;
	
	public M003_CreatingPackageTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String query = "";
		
		query+= "CREATE TABLE IF NOT EXISTS Package (";
		query+= "packageID int(11) NOT NULL AUTO_INCREMENT,";
		query+= "packageName varchar(100) NOT NULL,";
		query+= "packageValue int(11) NOT NULL,";
		query+= "PRIMARY KEY (packageID)";
		query+= ")";
		
		setQuery(query);
		run();
	}
}
