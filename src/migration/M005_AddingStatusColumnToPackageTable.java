package migration;

import java.sql.SQLException;

public class M005_AddingStatusColumnToPackageTable extends Migration {
	private static final int MIGRATION_VERSION = 5;
	
	public M005_AddingStatusColumnToPackageTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String query = "";
		
		query+= "ALTER TABLE Package ADD COLUMN status tinyint(1) DEFAULT 1";
		
		setQuery(query);
		run();
	}
}
