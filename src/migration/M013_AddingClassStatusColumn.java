package migration;

import java.sql.SQLException;

public class M013_AddingClassStatusColumn extends Migration {
	
private static final int MIGRATION_VERSION = 13;
	
	public M013_AddingClassStatusColumn(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String addStatusColumn = "";
		
		addStatusColumn+= "ALTER TABLE Class ADD status tinyint(1) NOT NULL DEFAULT '1'";
				
		setQuery(addStatusColumn);
		run();
	}
}
