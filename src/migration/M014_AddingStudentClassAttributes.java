package migration;

import java.sql.SQLException;

public class M014_AddingStudentClassAttributes extends Migration {
	
	private static final int MIGRATION_VERSION = 14;
	
	public M014_AddingStudentClassAttributes(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String addColumns = "";
		
		addColumns+= "ALTER TABLE StudentClass ADD absences int";
		setQuery(addColumns);
		run();
		
		addColumns = "ALTER TABLE StudentClass ADD grade int";
		setQuery(addColumns);
		run();
		
		addColumns = "ALTER TABLE StudentClass ADD situation varchar(10)";
		setQuery(addColumns);
		run();
	}
}
