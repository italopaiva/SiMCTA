package migration;

import java.sql.SQLException;

public class M009_CreatingServiceAssociationsTable extends Migration {
	private static final int MIGRATION_VERSION = 9;
	
	public M009_CreatingServiceAssociationsTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String queryForCourse = "";
		queryForCourse+= "CREATE TABLE IF NOT EXISTS ServiceCourse (";
		queryForCourse+= "id_service int NOT NULL,";
		queryForCourse+= "id_course int NOT NULL,";
		queryForCourse+= "FOREIGN KEY(id_service) REFERENCES Service (id_service),";
		queryForCourse+= "FOREIGN KEY(id_course) REFERENCES Course (id_course)";
		queryForCourse+= ")";
		
		String queryForPackage = "";
		queryForPackage+= "CREATE TABLE IF NOT EXISTS ServicePackage (";
		queryForPackage+= "id_service int NOT NULL,";
		queryForPackage+= "id_package int NOT NULL,";
		queryForPackage+= "FOREIGN KEY(id_service) REFERENCES Service (id_service),";
		queryForPackage+= "FOREIGN KEY(id_package) REFERENCES Package (id_package)";
		queryForPackage+= ")";

		setQuery(queryForCourse);
		run();
		
		setQuery(queryForPackage);
		run();
	}

}
