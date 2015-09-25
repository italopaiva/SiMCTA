package migration;

import java.sql.SQLException;

public class M005_AddingDurationToPackageTable extends Migration {

	private static final int MIGRATION_VERSION = 5;
	
	public M005_AddingDurationToPackageTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String query = "";
		
		query+= "ALTER TABLE Package ";
		query+= "ADD COLUMN packageDuration int NOT NULL";
		
		setQuery(query);
		run();
	}
}
