package migration;

import java.sql.SQLException;

public class M015_UpdatingIdClassColumnToClass extends Migration {
	
	private static final int MIGRATION_VERSION = 15;
	
	public M015_UpdatingIdClassColumnToClass(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String changeColumn = "";
		
		changeColumn+= "ALTER TABLE Class CHANGE id_class id_class VARCHAR(100) NOT NULL ;";
		setQuery(changeColumn);
		run();
		
		changeColumn = "ALTER TABLE StudentClass CHANGE id_class id_class VARCHAR(100) NOT NULL ;";
		setQuery(changeColumn);
		run();
	}
}
