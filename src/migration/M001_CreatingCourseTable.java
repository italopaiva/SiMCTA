package migration;

import java.sql.SQLException;

public class M001_CreatingCourseTable extends Migration {
	
	private static final int MIGRATION_VERSION = 1;
	
	public M001_CreatingCourseTable(){}
	
	public void up() throws SQLException{
			
		setVersion(MIGRATION_VERSION);
		
		String query = "";
		
		query+= "CREATE TABLE IF NOT EXISTS Course (";
		query+= "id_course int(11) NOT NULL AUTO_INCREMENT,";
		query+= "course_name varchar(50) NOT NULL,";
		query+= "description text NOT NULL,";
		query+= "duration int(2) NOT NULL,";
		query+= "value int(6) NOT NULL,";
		query+= "status int(1) NOT NULL DEFAULT '1',";
		query+= "PRIMARY KEY (id_course)";
		query+= ")";
		
		setQuery(query);
		run();
	}
}
