package migration;

import java.sql.SQLException;

public class M004_CreatingPackageCourseTable extends Migration {
	private static final int MIGRATION_VERSION = 4;
	
	public M004_CreatingPackageCourseTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String query = "";
		
		query+= "CREATE TABLE IF NOT EXISTS PackageCourse (";
		query+= "id_course int NOT NULL,";
		query+= "id_package int NOT NULL,";
		query+= "FOREIGN KEY (id_course) REFERENCES Course(id_course),";
		query+= "FOREIGN KEY (id_package) REFERENCES Package(id_package)";
		query+= ")";
		
		setQuery(query);
		run();
	}
}
